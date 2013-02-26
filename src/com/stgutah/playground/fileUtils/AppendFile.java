package com.stgutah.playground.fileUtils;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.logging.Logger;

/**
 * Append File tests.
 *
 * User: dqromney
 * Date: Apr 1, 2011
 * Time: 3:17:33 PM
 */
public class AppendFile {

    Logger log = Logger.getLogger(AppendFile.class.getName());
    private static final String DEFAULT_FILE_SEPARATOR = System.getProperty("file.separator");
    private String userDir;
    private String baseDir;
    private String srcDir;
    private String destDir;
    private String dataOrigPathFile;
    private String dataPathFile;
    private String trailerPathFile;
    private String resultFilePath;

    public void execute() throws IOException {
        File moveDir = null;

        System.out.println(String.format("srcDir=[%1$s]", srcDir));
        System.out.println(String.format("destDir=[%1$s]", destDir));

        // Append trailer.FLF to data.FLF
        FileUtils.deleteQuietly(new File(dataPathFile));
        FileUtils.copyFile(new File(dataOrigPathFile), new File(dataPathFile));
        String[] fileArray = {dataPathFile, trailerPathFile};
        appendFiles(resultFilePath, fileArray);
    }

    /**
     * Append to file(s) to a target file.
     *
     * @param pTargetPathFile the target path and file name
     * @param pFileList String array of path and file names to append/concatenate
     * @throws IOException thrown if unable to read/write file
     */
    public static void appendFiles(String pTargetPathFile, String[] pFileList) throws IOException {
        SequenceInputStream sis;
        // File resultFile = new File(pPathFile);
        FileWriter targetFileWriter = new FileWriter(new File(pTargetPathFile));
        ListOfFiles listOfFiles = new ListOfFiles(pFileList);

        sis = new SequenceInputStream(listOfFiles);
        if(sis != null) {
            int c;
            while ((c = sis.read()) != -1) {
               targetFileWriter.write(c);
            }
        }
        if(sis != null) {
            sis.close();
            sis = null;
        }
        if(targetFileWriter != null) {
            targetFileWriter.close();
        }
    }

    public void init() {
        userDir = System.getProperty("user.dir");
        baseDir = String.format("%1$s%2$s%3$s", userDir, DEFAULT_FILE_SEPARATOR, "src/com/stgutah/playground/fileUtils");
        // Assure file separators are being repalced for system specific character
        baseDir = baseDir.replace('/', DEFAULT_FILE_SEPARATOR.charAt(0));

        srcDir = String.format("%1$s%2$s%3$s", baseDir, DEFAULT_FILE_SEPARATOR, "srcDir");
        destDir = String.format("%1$s%2$s%3$s", baseDir, DEFAULT_FILE_SEPARATOR, "destDir");

        dataOrigPathFile = baseDir + DEFAULT_FILE_SEPARATOR + "ResultUpdateCollection.orig.FLF";
        dataPathFile = baseDir + DEFAULT_FILE_SEPARATOR + "ResultUpdateCollection.FLF";
        trailerPathFile = baseDir + DEFAULT_FILE_SEPARATOR + "ResultUpdateCollectionTrailer.FLF";
        resultFilePath = baseDir + DEFAULT_FILE_SEPARATOR + "Result.FLF"; 
    }

    /**
     * Main driver.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        AppendFile main = new AppendFile();
        main.init();
        main.execute();
    }
}
