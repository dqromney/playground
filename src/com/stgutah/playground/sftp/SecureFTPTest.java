package com.stgutah.playground.sftp;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Logger;
//import com.stgutah.playground.logging.MyFormatter;
import com.stgutah.playground.logging.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.regex.Pattern;

/**
 * Download File from secure FTP site.
 * <p/>
 * User: dqromney
 * Date: Oct 18, 2010
 * Time: 4:58:24 PM
 */
public class SecureFTPTest {

    private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(SecureFTPTest.class.toString());

    private final static String IDENTITY = "/Users/dqromney/.ssh/id_dsa";

    private SecureFTPImpl secureFTP;
    private String remoteBaseDirectory;
    private String remoteDownloadDirectory;
    private String remoteUploadDirectory;
    private String remoteFileName;
    private String localFolder;
    private String localJPGFileName;
    private String localJPGPathFileName;
    private int putSendTimeoutInSeconds;

    private static final Logger LOG = new Logger() {
        java.util.Hashtable name = new java.util.Hashtable();

        {
            name.put(new Integer(DEBUG), "DEBUG: ");
            name.put(new Integer(INFO), "INFO: ");
            name.put(new Integer(WARN), "WARN: ");
            name.put(new Integer(ERROR), "ERROR: ");
            name.put(new Integer(FATAL), "FATAL: ");
        }

        public boolean isEnabled(int level) {
            return true;
        }

        public void log(int level, String message) {
            System.err.print(name.get(new Integer(level)));
            System.err.println(message);
        }
    };


    /**
     * SecureFTPTest constructor.
     */
    public SecureFTPTest() {
        LOG.log(Logger.DEBUG, "SecureFTPTest() :: Enter");
        // ...
        LOG.log(Logger.DEBUG, "SecureFTPTest() :: Exit");
    }

    /**
     * Initialization
     */
    private void init() {
        boolean isLocalTest = false;
        LOGGER.info("init() :: Enter");
        secureFTP = new SecureFTPImpl(new MyLogger());
        if (isLocalTest) {
            secureFTP.setHost("demo.commercialpreservation.com");
            secureFTP.setPort(22);
            secureFTP.setUser("lps");
            secureFTP.setUsingPrivateKey(true);
            secureFTP.setPrivateKeyPath(IDENTITY);
            secureFTP.setPassword("password");
            secureFTP.setProgressMonitor(new DefaultSFTPProgressMonitor());
            
            // Set variables
            remoteBaseDirectory = "/opt/FAS/LPS/";
            remoteDownloadDirectory = remoteBaseDirectory + "Inspections/FromFAS";
            remoteUploadDirectory = remoteBaseDirectory + "Inspections/ToFAS";
            remoteFileName = "INTEGRATION_TEST.csv";
            localFolder = System.getProperty("user.dir");
            localJPGFileName = "INTEGRATION_TEST.jpg";
            localJPGPathFileName = localFolder + "/" + localJPGFileName;
            putSendTimeoutInSeconds = 30;
            
        } else {
            secureFTP.setHost("sftp.fieldassets.com");
            secureFTP.setPort(22);
            secureFTP.setUser("SpectrumUser");
            secureFTP.setUsingPrivateKey(false);
            // TODO secureFTP.setKnownHosts();
            secureFTP.setPassword("FASSp3ctrumFTP");
            secureFTP.setProgressMonitor(new DefaultSFTPProgressMonitor());
            secureFTP.setLogger(new MyLogger());
            
            // Set variables
            remoteBaseDirectory = "/";
            remoteDownloadDirectory = remoteBaseDirectory + "Inspections/FromFAS";
            remoteUploadDirectory = remoteBaseDirectory + "Inspections/ToFAS";
            remoteFileName = "INTEGRATION_TEST.csv";
            localFolder = System.getProperty("user.dir");
            localJPGFileName = "INTEGRATION_TEST.jpg";
            localJPGPathFileName = localFolder + "/" + localJPGFileName;
            putSendTimeoutInSeconds = 30;
            
        }
        LOGGER.info("init() :: Exit");
    }

    /**
     * Execution body
     */
    private void execute() {
        try {
            secureFTP.openSession();

            fileExistTest();
            folderExistTest();
            getFileTest();
            sendFileTest();
            deleteFileTest();
            getFileNamesInFolderTest();
            moveFileTest();

        } catch (FileTransferException e) {
            String message;
            switch (e.id) {
                case ChannelSftp.SSH_FX_BAD_MESSAGE:
                    message = String.format("[%1$d] returned if a badly formatted packet or protocol incompatibility is detected.", e.id);
                    break;
                case ChannelSftp.SSH_FX_CONNECTION_LOST:
                    message = String.format("[%1$d] indicates that the connection to the server has been lost (it can only be generated locally by the client, and MUST NOT be returned by servers).", e.id);
                    break;
                case ChannelSftp.SSH_FX_EOF:
                    message = String.format("[%1$d] indicates end-of-file condition; for SSH_FX_READ it means that no more data is available in the file, and for SSH_FX_READDIR it indicates that no more files are contained in the directory.", e.id);
                    break;
                case ChannelSftp.SSH_FX_FAILURE:
                    message = String.format("[%1$d] is a generic catch-all error message; it should be returned if an error occurs for which there is no more specific error code defined. The cause: %2$s", e.id, e.getCause());
                    break;
                case ChannelSftp.SSH_FX_NO_CONNECTION:
                    message = String.format("[%1$d] is a pseudo-error which indicates that the client has no connection to the server (it can only be generated locally by the client, and MUST NOT be returned by servers).", e.id);
                    break;
                case ChannelSftp.SSH_FX_NO_SUCH_FILE:
                    message = String.format("[%1$d] is returned when a reference is made to a file which should exist but doesn't.", e.id);
                    break;
                case ChannelSftp.SSH_FX_OK:
                    message = String.format("[%1$d] Indicates successful completion of the operation.", e.id);
                    break;
                case ChannelSftp.SSH_FX_OP_UNSUPPORTED:
                    message = String.format("[%1$d] indicates that an attempt was made to perform an operation which is not supported for the server (it may be generated locally by the client if e.g.  the version number exchange indicates that a required feature is not supported by the server, or it may be returned by the server if the server does not implement an operation).", e.id);
                    break;
                case ChannelSftp.SSH_FX_PERMISSION_DENIED:
                    message = String.format("[%1$d] is returned when the authenticated user does not have sufficient permissions to perform the operation.", e.id);
                    break;
                default:
                    message = String.format("[%1$d] Undefined error. The cause: %2$s", e.id, e.getCause());
            }
            LOGGER.severe(message);
        } finally {
            secureFTP.closeSession();
        }
    }

    private void moveFileTest() throws FileTransferException {
        LOGGER.info("moveFileTest() :: Enter");
        List<String> fileNameList;

        // Move file to results folder
        LOGGER.info(String.format("-- Moving [%1$s] file to [%2$s] as [%3$s]...", remoteFileName, remoteUploadDirectory, remoteFileName + ".moved"));
        secureFTP.moveFile(remoteFileName, remoteDownloadDirectory, remoteFileName + ".moved", remoteUploadDirectory);
        fileNameList = secureFTP.getFileNamesInFolder(remoteUploadDirectory, true);
        LOGGER.info(String.format("-- List of %1$d files in [%2$s]...", fileNameList.size(), remoteUploadDirectory));
        for (String fileName : fileNameList) {
            LOGGER.info(String.format("-- -- %s", fileName));
        }
        // Move it back to main folder
        LOGGER.info(String.format("-- Moving [%1$s] file to [%2$s] as [%3$s]...", remoteFileName + ".moved", remoteDownloadDirectory, remoteFileName));
        secureFTP.moveFile(remoteFileName + ".moved", remoteUploadDirectory, remoteFileName, remoteDownloadDirectory);
        fileNameList = secureFTP.getFileNamesInFolder(remoteDownloadDirectory, true);
        LOGGER.info(String.format("-- List of %1$d files in [%2$s]...", fileNameList.size(), remoteDownloadDirectory));
        for (String fileName : fileNameList) {
            LOGGER.info(String.format("-- -- %s", fileName));
        }

        LOGGER.info("moveFileTest() :: Exit");
    }

    private void getFileNamesInFolderTest() throws FileTransferException {
        LOGGER.info("getFileNamesInFolderTest() :: Enter");
        List<String> fileNameList;

        fileNameList = secureFTP.getFileNamesInFolder(remoteDownloadDirectory, false);
        LOGGER.info(String.format("-- List of %1$d ALL files, folders, and links in [%2$s]...", fileNameList.size(), remoteDownloadDirectory));
        for (String fileName : fileNameList) {
            LOGGER.info(String.format("-- -- %s", fileName));
        }

        fileNameList = secureFTP.getFileNamesInFolder(remoteDownloadDirectory, true);
        LOGGER.info(String.format("-- List of %1$d files in [%2$s]...", fileNameList.size(), remoteDownloadDirectory));
        for (String fileName : fileNameList) {
            LOGGER.info(String.format("-- -- %s", fileName));
        }

        Pattern pattern = Pattern.compile(".*.csv"); //INTEGRATION_TEST.csv
        fileNameList = secureFTP.getFileNamesInFolder(remoteDownloadDirectory, pattern);
        LOGGER.info(String.format("-- List of %1$d files matching [%3$s] in [%2$s]...", fileNameList.size(), remoteDownloadDirectory, pattern.pattern()));
        for (String fileName : fileNameList) {
            LOGGER.info(String.format("-- -- %s", fileName));
        }

        LOGGER.info("getFileNamesInFolderTest() :: Exit");
    }

    private void deleteFileTest() throws FileTransferException {
        LOGGER.info("deleteFileTest() :: Enter");
        secureFTP.deleteFile(remoteUploadDirectory, remoteFileName);
        LOGGER.info("deleteFileTest() :: Exit");
    }

    private void sendFileTest() throws FileTransferException {
        LOGGER.info("sendFileTest() :: Enter");
        boolean fileExists;
        // Delete the file; if it exists
        secureFTP.deleteFile(remoteUploadDirectory, remoteFileName);
        // Send the file
        secureFTP.sendFile(localFolder + "/" + remoteFileName, remoteUploadDirectory, putSendTimeoutInSeconds);
        // Verify that it exists
        fileExists = secureFTP.fileExists(remoteUploadDirectory, remoteFileName);
        if (fileExists) {
            LOGGER.info(String.format("-- %s sent", remoteFileName));
        } else {
            LOGGER.info(String.format("-- %s NOT sent", remoteFileName));
        }

        // Delete the JPG file; if it exists
        secureFTP.deleteFile(remoteDownloadDirectory, localJPGFileName);
        // Send the file
        secureFTP.sendFile(localJPGPathFileName, remoteDownloadDirectory, putSendTimeoutInSeconds);
        // Verify that it exists
        fileExists = secureFTP.fileExists(remoteDownloadDirectory, localJPGFileName);
        if (fileExists) {
            LOGGER.info(String.format("-- %s sent", localJPGFileName));
        } else {
            LOGGER.info(String.format("-- %s NOT sent", localJPGFileName));
        }

        LOGGER.info("sendFileTest() :: Exit");
    }

    private void getFileTest() throws FileTransferException {
        LOGGER.info("getFileTest() :: Enter");
        // Remove file if exists
        File file = new File(localFolder + "/" + remoteFileName);
        if (file.exists()) {
            file.delete();
            LOGGER.info(String.format("-- %s deleted", remoteFileName));
        }
        // Download file
        secureFTP.getFile(remoteDownloadDirectory, remoteFileName, localFolder, ChannelSftp.OVERWRITE, putSendTimeoutInSeconds);

        // Verify file was downloaded
        if (file.exists()) {
            LOGGER.info(String.format("%s exists", remoteFileName));
        }

        // Remove file if exists
        file = new File(localJPGPathFileName);
        if (file.exists()) {
            file.delete();
            LOGGER.info(String.format("-- %s deleted", remoteFileName));
        }
        // Download file
        secureFTP.getFile(remoteDownloadDirectory, localJPGFileName, localFolder, ChannelSftp.OVERWRITE, putSendTimeoutInSeconds);

        // Verify file was downloaded
        if (file.exists()) {
            LOGGER.info(String.format("%s exists", localJPGFileName));
        }

        LOGGER.info("getFileTest() :: Exit");
    }

    private void fileExistTest() throws FileTransferException {
        LOGGER.info("fileExistTest() :: Enter");
        boolean fileExists;
        String badRemoteFileName = remoteFileName + ".dat";
        fileExists = secureFTP.fileExists(remoteDownloadDirectory, badRemoteFileName);
        if (!fileExists) {
            LOGGER.info(String.format("File [%s] does not exist", badRemoteFileName));
        }
        fileExists = secureFTP.fileExists(null, badRemoteFileName);
        if (!fileExists) {
            LOGGER.info(String.format("File [%s] does not exist", badRemoteFileName));
        }

        fileExists = secureFTP.fileExists(remoteDownloadDirectory, remoteFileName);
        if (fileExists) {
            LOGGER.info(String.format("File [%s] does exist", remoteFileName));
        }
        fileExists = secureFTP.fileExists(null, remoteFileName);
        if (fileExists) {
            LOGGER.info(String.format("File [%s] does exist", remoteFileName));
        }
        LOGGER.info("fileExistTest() :: Exit");
    }

    private void folderExistTest() throws FileTransferException {
        LOGGER.info("folderExistTest() :: Enter");
        boolean folderExists;
        String badRemoteFolder = remoteDownloadDirectory + "/NotHere";
        // Test folderExist()
        folderExists = secureFTP.folderExists(badRemoteFolder);
        if (!folderExists) {
            LOGGER.info(String.format("Folder [%s] does not exist", badRemoteFolder));
        }
        folderExists = secureFTP.folderExists(remoteDownloadDirectory);
        if (folderExists) {
            LOGGER.info(String.format("Folder [%s] does exist", remoteDownloadDirectory));
        }
        LOGGER.info("folderExistTest() :: Exit");
    }

    // ----------------------------------------------------------------
    // Driver
    // ----------------------------------------------------------------

    public static void main(String[] args) throws IOException {
        // Initialize logger
        Handler ch = new ConsoleHandler();
        ch.setFormatter(new MyFormatter());
        LOGGER.setUseParentHandlers(true);
        java.util.logging.Logger rootLogger = java.util.logging.Logger.getLogger("");
        rootLogger.addHandler(ch);
        // Main driver
        SecureFTPTest main = new SecureFTPTest();
        main.init();
        main.execute();
    }
}

