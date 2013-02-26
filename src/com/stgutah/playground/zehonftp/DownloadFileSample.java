package com.stgutah.playground.zehonftp;

/**
 * This <code>DownloadFileSample</code> class
 * This sample demonstrates how to download a file via SFTP
 * This sample uses a convenient method, for a Spring-able version of this refer to FTPsClient
 * @see com.zehon.ftp.FTPsClient
 * Please refer to http://www.zehon.com/features.htm for more information about our SFTP.
 * @author Zehon Team (we're happy to serve you!)  <a href="http://www.zehon.com/">http://www.zehon.com/</a>
 *
 */

import com.zehon.FileTransferStatus;
import com.zehon.exception.FileTransferException;
import com.zehon.sftp.SFTP;
import com.zehon.sftp.SFTPClient;

import java.io.IOException;

/**
 * Download File Sample (Zehon).
 * <p/>
 * User: dqromney
 * Date: Oct 18, 2010
 * Time: 2:17:31 PM
 */
public class DownloadFileSample {

    private String host;
    private int port;
    private String userName;
    private String sftpFolder;
    private String password;
    private String privateKeyPath;

    private void init() {
        host = "demo.commercialpreservation.com";
        port = 22;
        userName = "lps";
        password = "password";
        sftpFolder = "/opt/FAS/LPS";
        privateKeyPath = "/Users/dqromney/.ssh/id_dsa";

    }

    /**
     * Please refer to http://www.zehon.com/SFTP_samples.htm for its full source code
     *
     * @param args
     */
    private void execute(String[] args) {

        String writeToLocalFolder = System.getProperty("user.dir");
        String nameOfFile = "LPSIC_Sample_Load.csv";
        SFTPClient sftpClient = new SFTPClient(host, port, userName, privateKeyPath, true);

        try {
            int status = sftpClient.getFile(nameOfFile, sftpFolder, writeToLocalFolder);
            if (FileTransferStatus.SUCCESS == status) {
                System.out.println(nameOfFile + " got downloaded successfully to  folder " + writeToLocalFolder);
            } else if (FileTransferStatus.FAILURE == status) {
                System.out.println("Fail to download  to  folder " + writeToLocalFolder);
            }
        } catch (FileTransferException e) {
            e.printStackTrace();
        }
    }

    // ----------------------------------------------------------------
    // Driver
    // ----------------------------------------------------------------
    public static void main(String[] args) throws IOException {
        DownloadFileSample main = new DownloadFileSample();
        main.init();
        main.execute(args);
    }

}
