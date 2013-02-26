package com.stgutah.playground.fileGeneration;

import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * TXT or Text File class.
 * <p/>
 * User: dqromney
 * Date: Sep 21, 2010
 * Time: 11:05:04 AM
 */
public class TextFile implements DataFile
{
    private static final java.util.logging.Logger LOGGER = Logger.getLogger(TextFile.class.toString());

    private File dataFile;
    private List<String> headers;
    private String fileName;
    private String fileDirectory;

    // Default configuration
    private String fieldSeparator = Constants.CSV_FIELD_SEPARATOR;
    private String fileExtension = Constants.TXT_FILE_EXT;
    private String textEncapsulationCharacter = Constants.CSV_TEXT_ENCAPSULATION_CHAR;
    private Boolean encapsulatedText = Constants.CSV_ENCAPSULATE_TEXT;

    // Implement as CSV with a TXT extension
    private DataFile textFile;

    /**
     * TextFile empty constructor.
     */
    public TextFile()
    {
        // Chained constructor with defaults call
        this(new ArrayList<String>(0), "TextFile_UNDEFINED_", (System.getProperty("user.dir")));
    }

    public TextFile(List<String> pHeaders, String pFileName, String pFileDirectory)
    {
        this.headers = pHeaders;
        this.fileName = pFileName;
        this.fileDirectory = pFileDirectory;
    }

    public File open() throws Exception
    {
        // Text file masquerading as a CSV file, but it is really a CSV with different configuration
        textFile = DataFileFactory.getDataFile(Constants.FILE_TYPE_CSV, headers, fileName, fileDirectory);
        dataFile = ((CSVFile) textFile).getDataFile();
        ((CSVFile) textFile).setFileExtension(getFileExtension());
        ((CSVFile) textFile).setFieldSeparator(getFieldSeparator());
        ((CSVFile) textFile).setTextEncapsulationCharacter(getTextEncapsulationCharacter());
        ((CSVFile) textFile).setEncapsulatedText(isEncapsulatedText());
        return textFile.open();
    }

    public void append(File pDataFile, FileRowVO pData) throws Exception
    {
        textFile.append(pDataFile, pData);
    }

    public void close(File pDataFile) throws Exception
    {
        textFile.close(pDataFile);
    }

    // ----------------------------------------------------------------
    // READ ONLY Acessors
    // ----------------------------------------------------------------

    public File getDataFile()
    {
        return dataFile;
    }

    public String getFileDirectory()
    {
        return fileDirectory;
    }

    public String getFileName()
    {
        return fileName;
    }

    public List<String> getHeaders()
    {
        return headers;
    }

    // ----------------------------------------------------------------
    // READ/WROTE Acessors
    // ----------------------------------------------------------------

    public String getFileExtension()
    {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension)
    {
        if (!(fileExtension == null || fileExtension.isEmpty()))
        {
            this.fileExtension = fileExtension;
        }
        else
        {
            LOGGER.warning("Invalid fileExtension being set");
        }
    }

    public String getFieldSeparator()
    {
        return fieldSeparator;
    }

    public void setFieldSeparator(String fieldSeparator)
    {
        this.fieldSeparator = fieldSeparator;
    }

    public Boolean isEncapsulatedText()
    {
        return encapsulatedText;
    }

    public void setEncapsulatedText(Boolean encapsulatedText)
    {
        this.encapsulatedText = encapsulatedText;
    }

    public String getTextEncapsulationCharacter()
    {
        return textEncapsulationCharacter;
    }

    public void setTextEncapsulationCharacter(String textEncapsulationCharacter)
    {
        this.textEncapsulationCharacter = textEncapsulationCharacter;
    }

}
