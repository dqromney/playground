package com.stgutah.playground.sftp;

import com.jcraft.jsch.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Secure File Transport Protocol.
 * <p/>
 * User: dqromney
 * Date: Oct 18, 2010
 * Time: 4:55:32 PM
 */
public class SecureFTPImpl implements SecureFTP {

    public static final String DEFAULT_FILE_SEPARATOR = System.getProperty("file.separator");
    private static final java.util.logging.Logger log = java.util.logging.Logger.getLogger(SecureFTPImpl.class.toString());
    private final static String KNOWN_HOSTS = "/Users/dqromney/.ssh/known_hosts";

    private JSch jsch;
    private Session session;
    private Channel channel;
    private ChannelSftp channelSftp;
    private UserInfo userInfo;
    private SftpProgressMonitor progressMonitor;

    private Logger logger;
    private String host;
    private int port;
    private String privateKeyPath;
    private Boolean usingPrivateKey;

    // Could get this from UserInfo
    private String user;
    private String password;

    /**
     * Secure FTP constructor; empty
     */
    public SecureFTPImpl() {
        log.info(String.format("-- BEFORE JSch.getLogger()=[%1$s]", getLogger().getClass().toString()));
        JSch.setLogger(new MyLogger());
        log.info(String.format("-- AFTER getLogger()=[%1$s]", getLogger().getClass().toString()));
        jsch = new JSch();
    }

    /**
     * Secure FTP constructor; with Logger
     */
    public SecureFTPImpl(Logger pLogger) {
        setLogger(pLogger);
        log.info(String.format("-- BEFORE JSch.getLogger()=[%1$s]", getLogger().getClass()));
        JSch.setLogger(getLogger());
        log.info(String.format("-- AFTER getLogger()=[%1$s]", getLogger().getClass()));
        jsch = new JSch();
    }

    // ----------------------------------------------------------------
    // Implemented methods
    // ----------------------------------------------------------------

    /**
     * Open Session and SFTP Channel with default connection timeout or zero or 0 ms.
     *
     * @throws FileTransferException is thrown for connectivity, permissions, etc.
     */
    public void openSession() throws FileTransferException {
        try {
            if (session == null) {
                jsch.setKnownHosts(KNOWN_HOSTS);
                session = jsch.getSession(getUser(), getHost(), getPort());
                session.setConfig("StrictHostKeyChecking", "no");
                if(isUsingPrivateKey()) {
                    jsch.addIdentity(getPrivateKeyPath(), getPassword());
                }
                else {
                    session.setPassword(getPassword());
                }
                session.connect();
                // Set up a SFTP channel
                channel = session.openChannel("sftp");
                channel.connect();
                channelSftp = (ChannelSftp) channel;
            }
        } catch (JSchException e) {
            if(channelSftp != null) {
                channelSftp.disconnect();
            }
            throw new FileTransferException(ChannelSftp.SSH_FX_NO_CONNECTION, e.getMessage());
        }
    }

    /**
     * Open Session and SFTP Channel with connection timeout.
     *
     * @param pConnectTimeout the connection timeout in milliseconds?.
     * @throws FileTransferException is thrown for connectivity, permissions, etc.
     */
    public void openSession(int pConnectTimeout) throws FileTransferException {
        try {
            if (session == null) {
                session = jsch.getSession(getUser(), getHost(), getPort());
                session.setConfig("StrictHostKeyChecking", "no");
                if(isUsingPrivateKey()) {
                    jsch.addIdentity(getPrivateKeyPath(), getPassword());
                }
                else {
                    session.setPassword(getPassword());
                }
                session.connect(pConnectTimeout);
                // Set up a SFTP channel
                channel = session.openChannel("sftp");
                channel.connect();
                channelSftp = (ChannelSftp) channel;
            }
        } catch (JSchException e) {
            channelSftp.disconnect();
            throw new FileTransferException(ChannelSftp.SSH_FX_NO_CONNECTION, e.getMessage());
        }
    }

    /**
     * Close SFTP Channel and Session
     */
    public void closeSession() {
        if (channel != null) {
            channel.disconnect();
        }
        if (session != null) {
            session.disconnect();
        }
    }

    /**
     * Determines if remote file exists by checking with stat command.
     *
     * @param pRemoteFolder   the Remote SFTP folder
     * @param pRemoteFileName the Remote SFTP file name
     * @return a true if exists, otherwise false
     * @throws FileTransferException due to connectivity, permissions, etc.
     */
    public boolean fileExists(String pRemoteFolder, String pRemoteFileName) throws FileTransferException {
        boolean exists = false;
        String pathAndFileName = pRemoteFolder != null ?
                pRemoteFolder + DEFAULT_FILE_SEPARATOR + pRemoteFileName : pRemoteFileName;
        try {
            channelSftp.stat(pathAndFileName);
            exists = true;
        } catch (SftpException e) {
            // Throw anything except not such file
            if (ChannelSftp.SSH_FX_NO_SUCH_FILE != e.id) {
                throw new FileTransferException(e.id, e.getMessage());
            }
        }

        return exists;
    }

    /**
     * Determines if remote folder exists by checking with stat command.
     *
     * @param pRemoteFolder the Remote SFTP folder
     * @return a true if exists, otherwise false
     * @throws FileTransferException due to connectivity, permissions, etc.
     */
    public boolean folderExists(String pRemoteFolder) throws FileTransferException {
        boolean exists = false;
        try {
            channelSftp.stat(pRemoteFolder);
            exists = true;
        } catch (SftpException e) {
            // Throw anything except not such file
            if (ChannelSftp.SSH_FX_NO_SUCH_FILE != e.id) {
                throw new FileTransferException(e.id, e.getMessage());
            }
        }
        return exists;
    }

    /**
     * Get remote SFTP file and store it to local folder.
     *
     * @param pRemoteFolder        the remote SFTP folder
     * @param pRemoteFileName      the remote file name
     * @param pToLocalFolder       the local folder
     * @param pLocalFileName       the local file name
     * @param pSftpProgressMonitor optional SftpProgressMonitor object
     * @param pMode                the ChannelSftp mode, i.e. ChannelSftp.OVERWRITE, etc.
     * @param pTimeoutInSeconds    the session timeout in milliseconds
     * @throws FileTransferException
     */
    public void getFile(String pRemoteFolder, String pRemoteFileName, String pToLocalFolder, String pLocalFileName, SftpProgressMonitor pSftpProgressMonitor, int pMode, int pTimeoutInSeconds) throws FileTransferException {
        try {
            session.setTimeout(pTimeoutInSeconds * 1000);
            channelSftp.lcd(pToLocalFolder);
            channelSftp.cd(pRemoteFolder);
            channelSftp.get(pRemoteFileName, pLocalFileName, null, pMode);
        } catch (SftpException e) {
            throw new FileTransferException(e.id, "getFile()", e.getCause());
        } catch (JSchException e) {
            throw new FileTransferException(e.getCause());
        }
    }

    /**
     * Get remote SFTP file and store it to local folder.
     *
     * @param pRemoteFolder     the remote SFTP folder
     * @param pRemoteFileName   the remote file name, local file name defaults to pRemoteFileName
     * @param pToLocalFolder    the local folder
     * @param pMode             the ChannelSftp mode, i.e. ChannelSftp.OVERWRITE, etc.
     * @param pTimeoutInSeconds the session timeout in milliseconds
     * @throws FileTransferException
     */
    public void getFile(String pRemoteFolder, String pRemoteFileName, String pToLocalFolder, int pMode, int pTimeoutInSeconds) throws FileTransferException {
        getFile(pRemoteFolder, pRemoteFileName, pToLocalFolder, pRemoteFileName, getProgressMonitor(), pMode, pTimeoutInSeconds);
    }

    /**
     * Send local file to remote SFTP destination folder.
     *
     * @param pLocalFilePath       the local path and file to send
     * @param pRemoteDestFolder    the remote SFTP destination folder
     * @param pMode                the file creation mode, i.e. ChannelSftp.OVERWITE, ChannelSftp.APPEND, etc.
     * @param pSftpProgressMonitor optional SftpProgressMonitor object
     * @param pMode                the ChannelSftp mode, i.e. ChannelSftp.OVERWRITE, etc.
     * @param pTimeoutInSeconds    the session timeout in milliseconds
     * @throws FileTransferException
     */
    public void sendFile(String pLocalFilePath, String pRemoteDestFolder, SftpProgressMonitor pSftpProgressMonitor, int pMode, int pTimeoutInSeconds) throws FileTransferException {
        try {
            session.setTimeout(pTimeoutInSeconds * 1000);
            channelSftp.put(pLocalFilePath, pRemoteDestFolder, pSftpProgressMonitor, pMode);
        } catch (SftpException e) {
            throw new FileTransferException(e.id, "sendFile()", e.getCause());
        } catch (JSchException e) {
            throw new FileTransferException(e.getCause());
        }
    }

    /**
     * Send local file to OVERWRITE remote SFTP destination folder.
     *
     * @param pLocalFilePath    the local path and file to send
     * @param pRemoteDestFolder the remote SFTP destination folder
     * @param pTimeoutInSeconds the session timeout in milliseconds
     * @throws FileTransferException
     */
    public void sendFile(String pLocalFilePath, String pRemoteDestFolder, int pTimeoutInSeconds) throws FileTransferException {
        sendFile(pLocalFilePath, pRemoteDestFolder, null, ChannelSftp.OVERWRITE, pTimeoutInSeconds);
    }

    /**
     * Delete remote SFTP file.
     *
     * @param pRemoteFolder   the remote SFTP folder
     * @param pRemoteFileName the remote SFTP file name
     * @throws FileTransferException
     */
    public void deleteFile(String pRemoteFolder, String pRemoteFileName) throws FileTransferException {
        try {
            channelSftp.cd(pRemoteFolder);
            channelSftp.rm(pRemoteFileName);
        } catch (SftpException e) {
            if (ChannelSftp.SSH_FX_NO_SUCH_FILE != e.id) {
                throw new FileTransferException(e.id, e.getMessage());
            }
        }
    }

    /**
     * Get file names in remote SFTP folder.
     *
     * @param pRemoteFolder  the remote SFTP folder
     * @param pShowFilesOnly true to return only files, otherwise all entries will be returned
     * @return a String array of file names
     * @throws FileTransferException
     */
    public List<String> getFileNamesInFolder(String pRemoteFolder, Boolean pShowFilesOnly) throws FileTransferException {
        Vector list = null;
        try {
            list = channelSftp.ls(pRemoteFolder);
        } catch (SftpException e) {
            if (ChannelSftp.SSH_FX_NO_SUCH_FILE != e.id) {
                throw new FileTransferException(e.id, e.getMessage());
            }
        }
        assert list != null;
        List<String> fileNameList = new ArrayList<String>(0);
        ChannelSftp.LsEntry lsEntry;
        SftpATTRS sftpAttrs;
        for (Object fileName : list) {
            lsEntry = (ChannelSftp.LsEntry) fileName;
            if (pShowFilesOnly) {
                sftpAttrs = lsEntry.getAttrs();
                if (!(lsEntry.getFilename().equals(".")) && !(lsEntry.getFilename().equals("..")) &&
                        !(sftpAttrs.isDir()) && !(sftpAttrs.isLink()) &&
                        !(lsEntry.getFilename().startsWith("."))) {

                    fileNameList.add(lsEntry.getFilename());
                }
            } else {
                fileNameList.add(lsEntry.getFilename());
            }
        }
        return fileNameList;
    }


    /**
     * Get pattern matched file names in remote SFTP folder.
     *
     * @param pRemoteFolder the remote SFTP folder
     * @param pPattern      the defined Pattern object for a file name
     * @return a String array of file names
     * @throws FileTransferException
     */
    public List<String> getFileNamesInFolder(String pRemoteFolder, Pattern pPattern) throws FileTransferException {
        Vector list = null;
        try {
            list = channelSftp.ls(pRemoteFolder);
        } catch (SftpException e) {
            if (ChannelSftp.SSH_FX_NO_SUCH_FILE != e.id) {
                throw new FileTransferException(e.id, e.getMessage());
            }
        }
        assert list != null;
        List<String> fileNameList = new ArrayList<String>(0);
        ChannelSftp.LsEntry lsEntry;
        Matcher matcher;
        for (Object fileName : list) {
            lsEntry = (ChannelSftp.LsEntry) fileName;
            matcher = pPattern.matcher(lsEntry.getFilename());
            if (matcher.matches()) {
                fileNameList.add(lsEntry.getFilename());
            }
        }
        return fileNameList;
    }

    /**
     * Move File, i.e. rename to another and/or to another directory.
     *
     * @param pRemoteFileNameToMove the remote SFTP file name to move
     * @param pRemoteFromFolder     the remote 'from' folder
     * @param pRemoteNewNameOfFile  the new remote SFTP file name
     * @param pRemoteToFolder       the remote SFTP 'to' folder
     * @throws FileTransferException
     */
    public void moveFile(String pRemoteFileNameToMove, String pRemoteFromFolder, String pRemoteNewNameOfFile, String pRemoteToFolder) throws FileTransferException {
        try {
            channelSftp.cd(pRemoteFromFolder);
            channelSftp.rename(pRemoteFileNameToMove, pRemoteToFolder + DEFAULT_FILE_SEPARATOR + pRemoteNewNameOfFile);
        } catch (SftpException e) {
            if (ChannelSftp.SSH_FX_NO_SUCH_FILE != e.id) {
                throw new FileTransferException(e.id, e.getMessage());
            }
        }
    }

    // ----------------------------------------------------------------
    // Accessors
    // ----------------------------------------------------------------

    public Logger getLogger() {
        if (this.logger == null) {
            this.logger = new MyLogger();
        }
        else {
            log.info(String.format("getLogger()=[%1$s]", logger.getClass()));        
        }
        return this.logger;
    }

    public void setLogger(Logger logger) {
        log.info(String.format("setLogger([%1$s])", logger.getClass().toString()));
        this.logger = logger;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getHostPort() {
        return port;
    }

    public void setHostPort(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPrivateKeyPath() {
        return privateKeyPath;
    }

    public void setPrivateKeyPath(String privateKeyPath) {
        this.privateKeyPath = privateKeyPath;
    }

    public SftpProgressMonitor getDefaultProgressMonitor() {
        return progressMonitor;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setDefaultProgressMonitor(SftpProgressMonitor defaultProgressMonitor) {
        this.progressMonitor = defaultProgressMonitor;
    }

    public SftpProgressMonitor getProgressMonitor() {
        if (progressMonitor == null) {
            setProgressMonitor(new DefaultSFTPProgressMonitor());
        }
        return progressMonitor;
    }

    public void setProgressMonitor(SftpProgressMonitor pProgressMonitor) {
        progressMonitor = pProgressMonitor;
    }

    public Boolean isUsingPrivateKey() {
        return usingPrivateKey;
    }

    public void setUsingPrivateKey(Boolean usingPrivateKey) {
        this.usingPrivateKey = usingPrivateKey;
    }

    /**
     * Default Logger for no logging; set to isEnabled to true for logging.
     */
    private static final Logger DEVNULL = new Logger() {
        java.util.Hashtable name = new java.util.Hashtable();

        {
            name.put(new Integer(DEBUG), "DEBUG: ");
            name.put(new Integer(INFO), "INFO: ");
            name.put(new Integer(WARN), "WARN: ");
            name.put(new Integer(ERROR), "ERROR: ");
            name.put(new Integer(FATAL), "FATAL: ");
        }

        public boolean isEnabled(int level) {
            return  false;
        }

        public void log(int level, String message) {
            log.info(String.format("%1$s %2$s", name.get(new Integer(level)), message));
        }
    };

}
