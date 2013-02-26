package com.stgutah.playground.fileUtils;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * Append File tests.
 *
 * User: dqromney
 * Date: Apr 1, 2011
 * Time: 3:17:33 PM
 */
public class DataAndTrailer {

    private static Logger log = Logger.getLogger(DataAndTrailer.class.getName());
    private static final String DEFAULT_FILE_SEPARATOR = System.getProperty("file.separator");
    static final String DEFAULT_LINE_SEPARATOR = System.getProperty("line.separator");
    private static String RESULTS_EVENT_COLLECTION_TRAILER_FILE_NAME = "ResultUpdateCollectionTrailer.FLF";
    private static String RESULTS_FILE_NAME = "ResultData.FLF";
    private static String RESULTS_TRAILER_FILE_NAME = "Trailer.FLF";

    private String userDir;
    private String baseDir;
    private String srcDir;
    private String destDir;
    private String dataOrigPathFile;
    private String dataPathFile;
    private String trailerPathFile;
    private String resultFilePath;

    private String dataPathFile2;
    private String trailerPathFile2;

    private String collectionDirectory = null;
    private String trailerRecordPathFile = null;
    private ResultRecordTrailer01 trl01 = new ResultRecordTrailer01("Trailer01 data", 0);
    private ResultRecordTrailer02 trl02 = new ResultRecordTrailer02("Trailer02 data", 0);
    private Integer dataSetsCreated = 0;

    public void execute() throws IOException {
        File moveDir = null;
        List<String> serviceList = null;

        System.out.println(String.format("srcDir=[%1$s]", srcDir));
        System.out.println(String.format("destDir=[%1$s]", destDir));

        System.out.println("*********** A P P E N D   F I L E   T E S T  ************************************");
        // Append trailer.FLF to data.FLF
        FileUtils.deleteQuietly(new File(dataPathFile));
        FileUtils.copyFile(new File(dataOrigPathFile), new File(dataPathFile));
        String[] fileArray = {dataPathFile, trailerPathFile};
        appendFiles(resultFilePath, fileArray);

        System.out.println("*********** D A T A   A N D   T R A I L E R   U P D A T E   T E S T  ************");

        // Delete both files
        FileUtils.deleteQuietly(new File(dataPathFile2));
        FileUtils.deleteQuietly(new File(trailerPathFile2));
        // Case - Initial Creation of Data and Trailer file
        int accountId = 1;
        serviceList = new ArrayList<String>(0);
        serviceList.add(String.format("REC%1$02d Data line", accountId ++));
        serviceList.add(String.format("REC%1$02d Data line", accountId ++));
        serviceList.add(String.format("REC%1$02d Data line", accountId ++));
        serviceList.add(String.format("REC%1$02d Data line", accountId ++));
        // Test creation and updating of data and trailer files
        updateResults(dataPathFile2, serviceList);

        // Case - Subsequent call to update Data and Trailer file
        serviceList = new ArrayList<String>(0);
        serviceList.add(String.format("REC%1$02d Data line", accountId ++));
        serviceList.add(String.format("REC%1$02d Data line", accountId ++));
        serviceList.add(String.format("REC%1$02d Data line", accountId ++));
        serviceList.add(String.format("REC%1$02d Data line", accountId ++));
        // Test creation and updating of data and trailer files
        updateResults(dataPathFile2, serviceList);

        // Case - Remove trailer file, simulate an exception during data update
        FileUtils.deleteQuietly(new File(trailerPathFile2));
        serviceList = new ArrayList<String>(0);
        serviceList.add(String.format("REC%1$02d Data line", accountId ++));
        serviceList.add(String.format("REC%1$02d Data line", accountId ++));
        serviceList.add(String.format("REC%1$02d Data line", accountId ++));
        serviceList.add(String.format("REC%1$02d Data line", accountId ++));
        // Test creation and updating of data and trailer files
        updateResults(dataPathFile2, serviceList);


    }

    /**
     * Append to file(s) to a target file.
     *
     * @param pTargetPathFile the target path and file name
     * @param pFileList String array of path and file names to append/concatenate
     * @throws java.io.IOException thrown if unable to read/write file
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


    public void updateResults(String pPathFile, List<String> serviceList) {

        File collectionFile = new File(pPathFile);
        boolean collectionFileExists = collectionFile.exists();
        collectionDirectory = DataAndTrailer.getPathFromPathFile(pPathFile);
        trailerRecordPathFile = collectionDirectory + DEFAULT_FILE_SEPARATOR + RESULTS_TRAILER_FILE_NAME;

        BufferedWriter bw = null;

        try {
            // TODO Check to see if both collection and collection trailer exists,
            // TODO if they don't, then there was a problem to report to via email,
            // TODO rename file, and recreate a new collection file.
            // Create collection file if it doesn't exist already
            if(!collectionFileExists) {
                collectionFile.createNewFile();
                bw = openBufferedWriter(collectionFile, false);
                log.info(String.format("-- Initially create and open new collection Result file [%1$s].", pPathFile));
                createResultHeaderRecord(bw, "* - H E A D E R - *");
                log.info(String.format("-- Initially create Header Record for collection Result file [%1$s].", pPathFile));
                // Note: Trailer Record file does not exist; but should be created as a part of the process
            }
            else {
                // Open collection file writer
                bw = openBufferedWriter(collectionFile, true);
                // Determine if trailer file exists; if not, there was most likely an error creating the last record.
                if(new File(trailerRecordPathFile).exists()) {
                    // Read in Trailer Records from existing file; updates TRL01 and TRL02 objects with latest data.
                    readTrailerRecordFile(trl01, trl02, trailerRecordPathFile);
                } else {
                    // TODO Rename collectionFile to <pathFile>.ERROR_<DATETIME_STAMP>
                    String renamedCollectionPathFile = String.format("%1$s.ERROR_%2$tY%2$tm%2$td_%2$tH%2$tM%2$tS_%2$tL",
                            collectionFile.getPath(), new Date());
                    collectionFile.renameTo(new File(renamedCollectionPathFile));
                    log.info(String.format("-- Error, no trailer collection file found, renaming old collection file with timestamp to [%1$s]",
                            renamedCollectionPathFile));
                    // TODO Create a new collection file
                    collectionFile.createNewFile();
                    bw = openBufferedWriter(collectionFile, false);
                    log.info(String.format("-- Initially creating and open new collection Result file [%1$s].", pPathFile));
                    createResultHeaderRecord(bw, "* - H E A D E R - *");
                    log.info(String.format("-- Initially creating Header Record for new collection Result file [%1$s].", pPathFile));
                    // Note: Trailer Record file does not exist; but should be created as a part of the process
                    trl01.setCount(0);  // Not needed in production code
                    trl02.setCount(0);  // Not needed in production code
                    // Replace collection Trailer Record file with new Trailer Record update
                    //replaceResultTrailerRecord(trl01, trl02, trailerRecordPathFile);
                }
            }

            // Create/Append collection Results Record file
            dataSetsCreated = createResultDetailRecords(trl01, trl02, bw, serviceList);
            // Replace collection Trailer Record file with new Trailer Record update
            replaceResultTrailerRecord(trl01, trl02, trailerRecordPathFile);

            // Close writer
            // bw.close();

        } catch (IOException e) {
            log.severe(e.getMessage());
            // throw new ServiceOrderException(String.format("Service write failed: [%1$s]", pPathFile), e);
        } finally {
            try {
                if(bw != null) {
                    bw.flush();
                    bw.close();
                }
            } catch (IOException e) {
                log.info("processOutgoingServiceResults() (new) :: Exit with Exceptions...");
                log.fine(String.format("Unable to close Result Collection file: [%1$s]", pPathFile));
                // throw new ServiceOrderException(String.format("Unable to close Result Collection file: [%1$s]", pPathFile), e);
            }
        }
    }

    private Integer createResultDetailRecords(ResultRecordTrailer01 trl01, ResultRecordTrailer02 trl02, BufferedWriter bw, List<String> serviceList) {
        int count = 0;
        for(String line : serviceList) {
            try {
                bw.write(line + DEFAULT_LINE_SEPARATOR);
                count ++;
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        trl01.addCount(count);
        trl02.addCount(count);
        return count;  //To change body of created methods use File | Settings | File Templates.
    }

    /**
     * Replace Results Record Trailer records from list of Service objects; used also for collection Trailer Record file.
     *
     * @param pTrl01 the Result Trailer 01 object
     * @param pTrl02 the Result Trailer 02 object
     * @param pTrailerRecordCollectionPathFile the path and file for Trailer Record collection file
     */
    private void replaceResultTrailerRecord(ResultRecordTrailer01 pTrl01, ResultRecordTrailer02 pTrl02, String pTrailerRecordCollectionPathFile) throws IOException {
        File collectionTrailerFile = new File(pTrailerRecordCollectionPathFile);
        File backupCollectionTrailerFile = new File(pTrailerRecordCollectionPathFile + ".BAK");

        // Delete old back up file if it exists
        if(backupCollectionTrailerFile.exists()) {
            if(!backupCollectionTrailerFile.delete()) {
                log.severe(String.format("-- Error, unable to delete existing collection Trail Record back up file [%1$s]",
                        backupCollectionTrailerFile.getPath()));
            }
        }
        // Rename existing Trailer Record collection file by appending a .BAK extension
        if(collectionTrailerFile.exists()) {
            collectionTrailerFile.renameTo(backupCollectionTrailerFile);
            log.info(String.format("-- Renamed existing collection Trail Record to [%1$s] backup file",
                    backupCollectionTrailerFile.getPath()));
        }

        // Open new Trailer Record collection file
        collectionTrailerFile.createNewFile();
        BufferedWriter bw = openBufferedWriter(collectionTrailerFile, false);

        // Set TaxID and Office Id
        pTrl01 = new ResultRecordTrailer01("Trailer01 data", pTrl01.getCount());
        pTrl02 = new ResultRecordTrailer02("Trailer02 data", pTrl02.getCount());

        try {
            bw.write(pTrl01.getFixedLengthFieldString());
            bw.write(pTrl02.getFixedLengthFieldString());
            log.info(String.format("-- Created and wrote new collection Trailer Record file to [%1$s]",
                    collectionTrailerFile.getPath()));

        } catch (IOException e) {
            log.severe(String.format("-- Error, unable to write collection Trailer Record [%1$s] file.",
                    collectionTrailerFile.getPath()));
        }
        finally {
            // Close the file
            if(bw != null) {
                bw.close();
            }
            // Determine if collection Trailer Record was created, if so delete the backup, i.e. *.BAK, if it exists
            if (collectionTrailerFile.exists()) {
                // Determine if backup collection Trailer Record exists
                if (backupCollectionTrailerFile.exists()) {
                    // Delete it!
                    backupCollectionTrailerFile.delete();
                    log.fine(String.format("-- Deleted backup collection Trailer Record [%1$s] file.",
                            backupCollectionTrailerFile.getPath()));
                }
            }
            // The collection Trailer Record didn't get created!
            else {
                // Determine if backup collection Trailer Record exist, then rename without .BAK extension
                if(backupCollectionTrailerFile.exists()) {
                    backupCollectionTrailerFile.renameTo(collectionTrailerFile);
                    log.severe(String.format("-- Renaming backup collection Trailer Record [%1$s] to [%2$s].",
                            backupCollectionTrailerFile.getPath(), collectionTrailerFile.getPath()));
                }
            }
        }
    }

    /**
     * Read existing TrailerRecord file and populate TRL01 and TRL02 record layouts.
     *
     * @param pTrl01 The TRL01 TrailerRecord layout 01
     * @param pTrl02 The TRL02 TrailerRecord layout 02
     * @param pTrailerRecordPathFile the path and file name of the Trailer Record file
     */
    private void readTrailerRecordFile(ResultRecordTrailer01 pTrl01, ResultRecordTrailer02 pTrl02, String pTrailerRecordPathFile) {
        log.info(String.format("readTrailerRecordFile() :: Enter"));
        BufferedReader br = openBufferedReader(new File(pTrailerRecordPathFile));
        // log.debug(String.format(""));
        try {
            String line = br.readLine();
            pTrl01.setTrailer(String.format("%1$15s", line.substring(0,15)));
            pTrl01.setCount(new Integer(line.substring(15)));
            line = br.readLine();
            pTrl02.setTrailer(String.format("%1$15s", line.substring(0,15)));
            pTrl02.setCount(new Integer(line.substring(15)));
        } catch (IOException e) {
            log.fine(String.format("-- Error reading TRL01 and TRL02 from collection TrailerRecord [%1$s].", pTrailerRecordPathFile));
        }
        log.info(String.format("readTrailerRecordFile() :: Exit"));
    }

    /**
     * Create Result Record Header record from list of Service objects.
     *
     * @param bw           a opened BufferedWriter object
     * @param pData     the Service object list
     */
    private void createResultHeaderRecord(BufferedWriter bw, String pData) {
        try {
            bw.write(pData + DEFAULT_LINE_SEPARATOR);
        } catch (IOException e) {
            log.severe(String.format("-- Error, unable to write 'HDR' Header record to Results file."));
        }
    }

    /**
     * Open file for buffered reading.
     *
     * @param pFile the File object to open
     * @return null if not able to open, otherwise the BufferedReader object
     */
    private static BufferedReader openBufferedReader(File pFile) {
        BufferedReader br = null;
        if (pFile.exists()) {
            try {
                br = new BufferedReader(new FileReader(pFile));
            } catch (FileNotFoundException e) {
                log.severe(String.format("Error, file [%1$s] is not found", pFile.getPath()));
            }
        } else {
            log.severe(String.format("Error, file [%1$s] does not exist", pFile.getPath()));
        }
        return br;
    }

    /**
     * Open file for buffered writing/appending.
     *
     * @param pFile the File object to open
     * @param pAppendFile the append file flag; true to append, otherwise create.
     * @return null if not able to open, otherwise the BufferedReader object
     */
    private static BufferedWriter openBufferedWriter(File pFile, Boolean pAppendFile) {
        BufferedWriter bw = null;
        if (pFile.exists()) {
            try {
                bw = new BufferedWriter(new FileWriter(pFile, pAppendFile), 1);
            } catch (IOException e) {
                log.severe(String.format("Error, unable to open file [%1$s] for writing", pFile.getPath()));
            }
        } else {
            log.severe(String.format("Error, file [%1$s] does not exist", pFile.getPath()));
        }
        return bw;
    }


    /**
     * Gets the path from a Path and File string.
     *
     * @param pPathFile the path and file, i.e. /home/foo/bar.txt
     * @return the path without the file name, i.e. /home/foo, if no path a './' is returned.
     */
    public static String getPathFromPathFile(String pPathFile) {
        return pPathFile == null ? "." + DEFAULT_FILE_SEPARATOR :
                pPathFile.lastIndexOf(DEFAULT_FILE_SEPARATOR) == -1 ? "." + DEFAULT_FILE_SEPARATOR :
                        pPathFile.substring(0, pPathFile.lastIndexOf(DEFAULT_FILE_SEPARATOR));
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

        dataPathFile2 = baseDir + DEFAULT_FILE_SEPARATOR + RESULTS_FILE_NAME;
        trailerPathFile2 = baseDir + DEFAULT_FILE_SEPARATOR + RESULTS_TRAILER_FILE_NAME;


    }

    /**
     * Main driver.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        DataAndTrailer main = new DataAndTrailer();
        main.init();
        main.execute();
    }
}

class ResultRecordTrailer01 {
    String trailer;
    Integer count;

    ResultRecordTrailer01(String trailer, Integer count) {
        this.trailer = trailer;
        this.count = count;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void addCount(Integer count) {
        this.count += count;
    }

    public String getFixedLengthFieldString() {
        return String.format("%1$s %2$04d%3$s", trailer, getCount(), DataAndTrailer.DEFAULT_LINE_SEPARATOR);
    }
}

class ResultRecordTrailer02 {
    String trailer;
    Integer count;

    ResultRecordTrailer02(String trailer, Integer count) {
        this.trailer = trailer;
        this.count = count;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void addCount(Integer count) {
        this.count += count;
    }

    public String getFixedLengthFieldString() {
        return String.format("%1$s %2$04d%3$s", trailer, getCount(), DataAndTrailer.DEFAULT_LINE_SEPARATOR);
    }
}

