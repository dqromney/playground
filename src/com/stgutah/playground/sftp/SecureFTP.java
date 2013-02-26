package com.stgutah.playground.sftp;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpProgressMonitor;
import com.jcraft.jsch.UserInfo;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Secure FTP Interface.
 *
 * User: dqromney
 * Date: Oct 18, 2010
 * Time: 5:27:49 PM
 */
public interface SecureFTP {

    public void openSession(int pConnectionTimeout) throws FileTransferException;
    public void openSession() throws FileTransferException;
    public void closeSession();
    
    public boolean fileExists(String sftpFolder, String nameOfFile) throws FileTransferException;
    public boolean folderExists(String sftpFolder) throws FileTransferException;

    public void getFile(String pRemoteFolder, String pRemoteFileName, String pToLocalFolder, String pLocalFileName, SftpProgressMonitor pSftpProgressMonitor, int pMode, int pTimeout) throws FileTransferException;
    public void getFile(String pRemoteFolder, String pRemoteFileName, String pToLocalFolder, int pMode, int pTimeout) throws FileTransferException;

    public void sendFile(String pLocalFilePath, String pRemoteDestFolder, SftpProgressMonitor pSftpProgressMonitor, int pMode, int pTimeout) throws FileTransferException;
    public void sendFile(String pLocalFilePath, String pRemoteDestFolder, int pTimeout) throws FileTransferException;
    
    public void deleteFile(String pRemoteFolder, String pRemoteFileName) throws FileTransferException;

    public List<String> getFileNamesInFolder(String pRemoteFolder, Boolean pShowFilesOnly) throws FileTransferException;
    public List<String> getFileNamesInFolder(String pRemoteFolder, Pattern pPattern) throws FileTransferException;
    
    public void moveFile(String pRemoteFileNameToMove, String pRemoteFromFolder, String pRemoteNewNameOfFile, String pRemoteToFolder) throws FileTransferException;

    // ----------------------------------------------------------------
    // Accessors
    // ----------------------------------------------------------------

    public void setLogger(com.jcraft.jsch.Logger logger);

    public UserInfo getUserInfo();

    public void setUserInfo(UserInfo userInfo);

    public String getHost();

    public void setHost(String host);

    public int getHostPort();

    public void setHostPort(int port);

    public String getPassword();

    public void setPassword(String password);

    public String getUser();

    public void setUser(String user);

    public String getPrivateKeyPath() throws IOException;

    public void setPrivateKeyPath(String privateKeyPath);

    public SftpProgressMonitor getDefaultProgressMonitor();

    public void setDefaultProgressMonitor(SftpProgressMonitor defaultProgressMonitor);
}
