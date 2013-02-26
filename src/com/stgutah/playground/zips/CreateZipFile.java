package com.stgutah.playground.zips;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.Enumeration;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * Create a ZIP File.
 * <p/>
 * User: dqromney
 * Date: Nov 1, 2010
 * Time: 3:00:12 PM
 */
public class CreateZipFile {

    Logger log = Logger.getLogger(CreateZipFile.class.getName());
    private String DEFAULT_FILE_SEPARATOR = System.getProperty("file.separator"); // i.e. '/'
    private String userDir = System.getProperty("user.dir"); // /Users/dqromney/stgdev/playground

    String testFileName;

    private void init() throws IOException {
        testFileName = "test.zip";
    }

    private void execute() throws IOException {
        makeZipFile(getFileList(userDir), userDir + DEFAULT_FILE_SEPARATOR + testFileName);
        showZipContents(userDir, testFileName);
        FileUtils.deleteQuietly(new File(userDir + DEFAULT_FILE_SEPARATOR + "writeData.csv"));
        FileUtils.deleteQuietly(new File(userDir + DEFAULT_FILE_SEPARATOR + "export.csv"));
        unZipFiles(userDir + DEFAULT_FILE_SEPARATOR + testFileName, "writeData.csv", "export.csv");
    }

    /**
     * UnZip (extract) files from zip file.
     *
     * @param pZipPathFile the zip path and file name
     * @param pFileNameList the list of file names to extract
     * @return true if success, false otherwise (see error logs)
     */
    public Boolean unZipFiles(String pZipPathFile, String... pFileNameList) {
        log.info("unZipFiles() :: Enter");
        Boolean success = true;
        try {
            ZipFile zipFile = new ZipFile(pZipPathFile);
            Enumeration entries = zipFile.entries();

            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();

                // Do not create directory structure; only files
                if (entry.isDirectory()) {
                    continue;
                }

                // Look for filename to extract
                for (String fileName : pFileNameList) {
                    if (entry.getName().contains(fileName)) {
                        System.err.println("Extracting file: " + entry.getName());
                        copyInputStream(zipFile.getInputStream(entry),
                                new BufferedOutputStream(new FileOutputStream(entry.getName())));
                    }
                }
            }
            zipFile.close();
        } catch (ZipException ze) {
            // if a ZIP format error has occurred
            log.severe(String.format("-- Zip format error has occurred [%1$s]", pZipPathFile));
            success = false;
        } catch (IOException ze) {
            // if an I/O error has occurred
            log.severe(String.format("-- I/O error has occurred with [%1$s]", pZipPathFile));
            success = false;
        } catch (SecurityException ze) {
            // if a security manager exists and its
            // <code>checkRead</code> method doesn't allow read access to the file.
            log.severe(String.format("-- Security Exception occurred [%1$s]", pZipPathFile));
            success = false;
        }

        log.info("unZipFiles() :: Exit");
        return success;
    }

    public static final void copyInputStream(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int len;

        while ((len = in.read(buffer)) >= 0)
            out.write(buffer, 0, len);

        in.close();
        out.close();
    }

    public static void main(String[] args) throws Exception {
        CreateZipFile main = new CreateZipFile();
        main.init();
        main.execute();
    }

    private void makeZipFile(File[] pFileList, String pDestinationPathFile) throws IOException {
        log.info("makeZipFile() :: Enter");
        byte[] buf = new byte[1024];
        int i = 0;
        FileInputStream in = null;
        ZipOutputStream out = null;

        try {
            out = new ZipOutputStream(new FileOutputStream(pDestinationPathFile));
            // Compress all the files
            for(File file : pFileList) {

                in = new FileInputStream(file.getCanonicalPath());
                // Add ZIP entry to output stream.
                out.putNextEntry(new ZipEntry(file.getName()));
                // Transfer bytes from the file to the ZIP file
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                // Complete the entry
                out.closeEntry();
                in.close();
            }
        } catch (IOException ioe) {
            log.severe(String.format("IOException while processing [%1$s] file", pFileList[i]));
        }
        finally {
            if (in != null) {
                out.close();
            }
        }
        log.info("makeZipFile() :: Exit");
    }

    private void showZipContents(String pPath, String pFile) throws IOException {
        // Open the ZIP file
        ZipFile zf = new ZipFile(pPath + DEFAULT_FILE_SEPARATOR + pFile);
        // Enumerate each entry
        for(Enumeration entries = zf.entries(); entries.hasMoreElements();) {
            // Get the entry name
            String zipEntryName = ((ZipEntry)entries.nextElement()).getName();
            log.info(String.format("zipEntryName=[%1$s]", zipEntryName));
        }
    }

    private File[] getFileList(String pDirectory) {
        File dir = new File(pDirectory);
        FilenameFilter filter = new FilenameFilter() {

            public boolean accept(File dir, String name) {
                String upperCaseName = name.toUpperCase();
                return upperCaseName.endsWith("JPG") || upperCaseName.endsWith("CSV");
            }
        };

        return dir.listFiles(filter);
    }

    // ----------------------------------------------------------------
    // ACCESSORS
    // ----------------------------------------------------------------

}
