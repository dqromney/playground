package com.stgutah.playground.fileGeneration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * CSV or Comma Separated File class.
 * <p/>
 * User: dqromney
 * Date: Sep 21, 2010
 * Time: 11:03:20 AM
 */
public class CSVFile implements DataFile
{
    private static final java.util.logging.Logger LOGGER = Logger.getLogger(CSVFile.class.toString());

    private File dataFile;
    private List<String> headers;
    private String fileName;
    private String fileDirectory;
    private FileWriter writer;

    // Default values
    private String fileExtension = Constants.CSV_FILE_EXT;
    private String fieldSeparator = Constants.CSV_FIELD_SEPARATOR;
    private String textEncapsulationCharacter = Constants.CSV_TEXT_ENCAPSULATION_CHAR;
    private Boolean encapsulatedText = Boolean.TRUE;

    /**
     * TextFile empty constructor.
     */
    public CSVFile()
    {
        // Chained constructor with defaults call
        this(new ArrayList<String>(0), "CSVFile_UNDEFINED_", (System.getProperty("user.dir")));
    }

    /**
     * Constructor for CSVFile.
     *
     * @param pHeaders       the list of Headers for file
     * @param pFileName      the filename of file
     * @param pFileDirectory the file directory
     */
    public CSVFile(List<String> pHeaders, String pFileName, String pFileDirectory)
    {
        this.headers = pHeaders;
        this.fileName = pFileName;
        this.fileDirectory = pFileDirectory;
    }

    /**
     * Open CSV file for writing.
     *
     * @return the data File object to write to
     * @exception Exception if unable to open file
     */
    public File open() throws Exception
    {
        LOGGER.info("open() :: Enter");

        dataFile = null;
        try
        {
            dataFile = DataFileUtil.createTempFile(getFileDirectory(), getFileName(), getFileExtension());
            writer = new FileWriter(dataFile);

            String value;
            // Header for file if provided; could be enclosed in the FileRowVO already
            int i = 0;
            for(String header : headers)
            {
                value = prepareString((header != null) ? header : "UNDEFINED", Constants.DATA_TYPE_TEXT);
                writer.append(value);
                if (i++ < (headers.size() - 1))
                {
                    writer.append(getFieldSeparator());
                }
            }
            writer.append(System.getProperty("line.separator"));
        }
        catch (Exception e)
        {
            LOGGER.log(Level.SEVERE, "Error in open():" + e.toString());
            throw e;
        }
        LOGGER.info("open() :: Exit");

        return dataFile;
    }

    /**
     * Append CSV file with row of data.
     *
     * @param pDataFile the data File object to append
     * @param pData     the FileRowVO object to write
     * @exception Exception if unable to write data
     */
    public void append(File pDataFile, FileRowVO pData) throws Exception
    {
        String value;

        try
        {
            // Check if writer
            writer = verifyWriter(pDataFile);

            List<FileColumnVO> rowDataList = pData.getList();
            LOGGER.info("-- AFTER data.getList() size()=[" + pData.getList().size() + "]");

            int i = 0;
            for(FileColumnVO rowData : rowDataList)
            {
                value = prepareString((rowData.getValue() != null) ? rowData.getValue().toString() : "", rowData.getDataType());
                writer.append(value);
                LOGGER.info("-- AFTER writer.append(rowData.getValue().toString())=[" + value + "]");
                if (i++ < (rowDataList.size() - 1))
                {
                    writer.append(getFieldSeparator());
                }
            }
            writer.append(System.getProperty("line.separator"));

        } catch (IOException e)
        {
            LOGGER.log(Level.SEVERE, "Error in append():" + e.toString());
            throw e;
        }
    }

    /**
     * Close CSV File and FileWriter object.
     *
     * @param pDataFile the data File object to close
     * @exception IOException if unable to close writer and/or file
     */
    public void close(File pDataFile) throws Exception
    {
        try
        {
            writer = verifyWriter(pDataFile);
            writer.flush();
            writer.close();

            // reset all object variables
            writer = null;
            pDataFile = null;
            dataFile = null;
            headers = null;
            fileName = null;
            fileDirectory = null;

        } catch (IOException e)
        {
            LOGGER.log(Level.SEVERE, "Unable to close pDataFile");
            throw new IOException("Unable to close pDataFile");
        }
    }

    /**
     * Verify FileWriter object.
     *
     * @param pDataFile the File reference to the data
     * @return a FileWriter object
     * @exception IOException if unable to write
     */
    private FileWriter verifyWriter(File pDataFile)
            throws IOException
    {
        if (pDataFile != null)
        {
            if (writer == null)
            {
                writer = new FileWriter(pDataFile);
            }
        }
        else
        {
            LOGGER.log(Level.SEVERE, "pDataFile argument is null, unable to open file");
            throw new IOException("pDataFile argument is null, unable to open file");
        }
        return writer;
    }

    /**
     * Prepare field String for CSV value output.
     *
     * @param pValue    the value to prepare
     * @param pDataType the data type listed in Contants, i.e. Constants.DATA_TYPE_TEXT - optional
     * @return a prepared value, i.e. adding encapsulating double-quotes.
     */
    private String prepareString(String pValue, String pDataType)
    {
        final char textEncapsulationChar = getTextEncapsulationCharacter().charAt(0);
        int pValueLength = (pValue.length() - 1 < 0) ? 0 : pValue.length() - 1;
        // Is value already encapsulated in double-quotes?
        if ("".equals(pValue))
        {
            pValue = getEmptyValue(pValue, pDataType);
        }
        else if ((pValue.charAt(0) != textEncapsulationChar) && (pValue.charAt(pValueLength) != textEncapsulationChar))
        {
            pValue = getPopulatedValue(pValue, pDataType);
        }
        return pValue;
    }

    /**
     * Get populated value  based upon configuration.
     *
     * @param pValue    the empty value
     * @param pDataType the data type listed in Contants, i.e. Constants.DATA_TYPE_TEXT - optional
     * @return a configured value
     */
    private String getPopulatedValue(String pValue, String pDataType)
    {
        if (pDataType != null)
        {
            // Encapsulate text fields with encapsulation character
            if ((pDataType.compareTo(Constants.DATA_TYPE_TEXT) == 0) || (pDataType.compareTo(Constants.DATA_TYPE_UNDEFINED) == 0))
            {
                if (isEncapsulatedText())
                {
                    // Encapsulate text
                    pValue = new StringBuffer().append(getTextEncapsulationCharacter()).append(pValue).append(
                            getTextEncapsulationCharacter()).toString();
                }
            }
            // else not encapsulated, i.e. numbers, boolean, etc.
        }
        else
        {
            if (isEncapsulatedText())
            {
                // Encapsulate text
                pValue = new StringBuffer().append(getTextEncapsulationCharacter()).append(pValue).append(
                        getTextEncapsulationCharacter()).toString();
            }
        }
        return pValue;
    }

    /**
     * Get Empty value based upon configuration.
     *
     * @param pValue    the empty value
     * @param pDataType the data type listed in Contants, i.e. Constants.DATA_TYPE_TEXT - optional
     * @return a empty value
     */
    private String getEmptyValue(String pValue, String pDataType)
    {
        final String EMPTY_STRING = "";
        final String EMPTY_ENCAPSULATED_STRING = getTextEncapsulationCharacter() + getTextEncapsulationCharacter();

        if (pDataType != null)
        {
            if ((pDataType.compareTo(Constants.DATA_TYPE_TEXT) == 0) || (pDataType.compareTo(Constants.DATA_TYPE_UNDEFINED) == 0))
            {
                if (isEncapsulatedText())
                {
                    pValue = EMPTY_ENCAPSULATED_STRING;
                }
                else
                {
                    pValue = EMPTY_STRING;
                }
            }
            // else not encapsulated, i.e. numbers, boolean, etc.
        }
        else
        {
            if (isEncapsulatedText())
            {
                pValue = EMPTY_ENCAPSULATED_STRING;
            }
            else
            {
                pValue = EMPTY_STRING;
            }
        }
        return pValue;
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
        if (!(fieldSeparator == null || fieldSeparator.isEmpty()))
        {
            this.fieldSeparator = fieldSeparator;
        }
        else
        {
            LOGGER.warning("Invalid fieldSeparator being set");
        }
    }

    public String getTextEncapsulationCharacter()
    {
        return textEncapsulationCharacter;
    }

    public void setTextEncapsulationCharacter(String textEncapsulationCharacter)
    {
        if (!(textEncapsulationCharacter == null || textEncapsulationCharacter.isEmpty()))
        {
            this.textEncapsulationCharacter = textEncapsulationCharacter;
        }
        else
        {
            LOGGER.warning("Invalid textEncapsulationCharacter being set");
        }
    }

    public Boolean isEncapsulatedText()
    {
        return encapsulatedText;
    }

    public void setEncapsulatedText(Boolean encapsulatedText)
    {
        this.encapsulatedText = encapsulatedText;
    }
}
