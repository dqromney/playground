package com.stgutah.playground.opencsv.examples;

import au.com.bytecode.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

/**
 * CSV (Comma Separated Value) Utility class
 * User: dqromney
 * Date: Aug 2, 2010
 * Time: 2:40:16 PM
 */
public class CsvUtil
{
    private static final java.util.logging.Logger LOGGER = Logger.getLogger(CsvUtil.class.toString());

    public static final String DEFAULT_LINE_SEPARATOR = System.getProperty("line.separator");
    public static final String DEFAULT_FILE_SEPARATOR = System.getProperty("file.separator");
    /**
     * The escape constant to use when you wish to suppress all escaping.
     */
    public static final char NO_ESCAPE_CHARACTER = '\u0000';
    public static final char ESCAPE_CHARACTER = '\\';

    File theFile;
    FileWriter writer;
    CSVWriter csvWriter;
    Boolean suppressEscape = true;

    private String defaultDirectory;
    private String defaultFileName;
    private String defaultFileExtension;

    public CsvUtil()
    {
        // Setup default values
        defaultDirectory = System.getProperty("user.dir");
        defaultFileExtension = "csv";
        defaultFileName = String.format("exportFileRecords-%1$tY%1$tm%1$te-%1$tH%1$tM%1$tS.%1$tL", Calendar.getInstance());
        LOGGER.info(
                "Using input file: " + this.getDefaultDirectory() + DEFAULT_FILE_SEPARATOR + this.getDefaultFileName() + '.' + this.getDefaultFileExtension());
    }

    public CsvUtil(String directory, String pFileName, String pFileExtension)
    {
        this.defaultDirectory = directory;
        this.defaultFileName = pFileName;
        this.defaultFileExtension = pFileExtension;
        LOGGER.info(
                "Using input file: " + this.getDefaultDirectory() + DEFAULT_FILE_SEPARATOR + this.getDefaultFileName() + '.' + this.getDefaultFileExtension());
    }

    /**
     * Parses a string array representing a path/file string.
     * Sets the directory if any, and fileName properties.
     *
     * @param pPathFile the file argument defined on the command-line
     */
    public CsvUtil(String pPathFile)
    {
        if (pPathFile != null)
        {
            int lastIndex = pPathFile.lastIndexOf(DEFAULT_FILE_SEPARATOR);
            if (lastIndex > 0)
            {
                String name = pPathFile.substring(lastIndex + 1);
                String directory = pPathFile.substring(0, lastIndex);
                this.setDefaultFileName(name);
                this.setDefaultDirectory(directory);
                // Handle file name and extension
                lastIndex = name.lastIndexOf('.');
                if (lastIndex > 0)
                {
                    this.setDefaultFileExtension(name.substring(lastIndex + 1));
                    this.setDefaultFileName(name.substring(0, lastIndex));
                }
                else
                {
                    this.setDefaultFileExtension("csv");
                }
            }
            LOGGER.info(
                    "Using input file: " + this.getDefaultDirectory() + DEFAULT_FILE_SEPARATOR + this.getDefaultFileName() + '.' + this.getDefaultFileExtension());
        }
        else
        {
            LOGGER.info(
                    "Using default input file: " + this.getDefaultDirectory() + DEFAULT_FILE_SEPARATOR + this.getDefaultFileName() + '.' + this.getDefaultFileExtension());
        }
    }

    public void openCsvFile() throws IOException
    {
        char escapeCharacter = (suppressEscape == Boolean.TRUE) ? ESCAPE_CHARACTER : NO_ESCAPE_CHARACTER;
        theFile = new File(this.getDefaultDirectory(), this.getDefaultFileName() + '.' + this.getDefaultFileExtension());
        writer = new FileWriter(theFile);
        csvWriter = new CSVWriter(writer, ',', '\"', escapeCharacter, DEFAULT_LINE_SEPARATOR);
    }

    public void closeCsvFile() throws IOException
    {
        csvWriter.close();
    }

    public void writeHeaderToFile(List<String> pHeaderList)
    {
        // Create the standard header
        csvWriter.writeNext(pHeaderList.toArray(new String[pHeaderList.size()]));
    }

    public void writeRowToFile(List<ColumnItem> pColumnItemList)
    {
        List<String> row = new ArrayList<String>(0);

        // Added converted fields
        for(ColumnItem item : pColumnItemList)
        {
            row.add(item.getFieldValue());
        }
        csvWriter.writeNext(row.toArray(new String[row.size()]));
    }

    /**
     * Writes list of ColumnItem list objects.
     *
     * @exception IOException when file or directory is not found, or unable to read.
     */
    public File writeCsvFile(List<String> pHeaderList, List<List<ColumnItem>> pListOfLists) throws IOException
    {
        // Check parameters
        if ((pHeaderList == null || pHeaderList.size() == 0) ||
                (pListOfLists == null || pListOfLists.size() == 0))
        {
            LOGGER.info("No Data to write");
        }
        else
        {
            openCsvFile();
            writeHeaderToFile(pHeaderList);
            for(List<ColumnItem> columnItemList : pListOfLists)
            {
                writeRowToFile(columnItemList);
            }
            closeCsvFile();
        }
        return theFile;
    }

    public File appendCsvFile(List<List<ColumnItem>> pListOfLists) throws IOException
    {
        if ((pListOfLists == null || pListOfLists.size() == 0))
        {
            LOGGER.info("No Data to write");
        }
        else
        {
            openCsvFile();
            for(List<ColumnItem> columnItemList : pListOfLists)
            {
                writeRowToFile(columnItemList);
            }
            closeCsvFile();
        }
        return theFile;
    }

    public String getDefaultDirectory()
    {
        return defaultDirectory;
    }

    public void setDefaultDirectory(String defaultDirectory)
    {
        this.defaultDirectory = defaultDirectory;
    }

    public String getDefaultFileExtension()
    {
        return defaultFileExtension;
    }

    public void setDefaultFileExtension(String defaultFileExtension)
    {
        this.defaultFileExtension = defaultFileExtension;
    }

    public String getDefaultFileName()
    {
        return defaultFileName;
    }

    public void setDefaultFileName(String defaultFileName)
    {
        this.defaultFileName = defaultFileName;
    }

    public Boolean getSuppressEscape()
    {
        return suppressEscape;
    }

    public void setSuppressEscape(Boolean suppressEscape)
    {
        this.suppressEscape = suppressEscape;
    }
}
