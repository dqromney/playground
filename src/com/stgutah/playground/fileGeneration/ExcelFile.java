package com.stgutah.playground.fileGeneration;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Excel File class.
 * <p/>
 * User: dqromney
 * Date: Sep 21, 2010
 * Time: 11:02:18 AM
 */
public class ExcelFile implements DataFile
{
    private static final java.util.logging.Logger LOGGER = Logger.getLogger(ExcelFile.class.toString());

    private File dataFile;
    private List<String> headers;
    private String fileName;
    private String fileDirectory;
    private FileOutputStream fileOut;

    // Excel Worksheet variables
    private HSSFWorkbook workbook;
    private HSSFSheet sheet;
    private HSSFRow headerRow;
    private HSSFCellStyle style;
    private HSSFFont font;
    private int rowIndex = 0;
    private int cellIndex = 0;

    /**
     * ExcelFile empty constructor.
     */
    public ExcelFile()
    {
        // Chained constructor with defaults call
        this(new ArrayList<String>(0), "ExcelFile_UNDEFINED_", (System.getProperty("user.dir")));
    }

    /**
     * Constructor for ExcelFile.
     *
     * @param pHeaders       the list of Headers for file
     * @param pFileName      the filename of file
     * @param pFileDirectory the file directory
     */
    public ExcelFile(List<String> pHeaders, String pFileName, String pFileDirectory)
    {
        this.headers = pHeaders;
        this.fileName = pFileName;
        this.fileDirectory = pFileDirectory;

        // Create default workbook configuration
        this.workbook = new HSSFWorkbook();
        this.sheet = workbook.createSheet();
        this.headerRow = sheet.createRow((short) rowIndex);
        this.style = workbook.createCellStyle();
        this.font = workbook.createFont();

        // Default font and style settings; access through getters
        font.setFontName(HSSFFont.FONT_ARIAL);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.BLUE.index);
        style.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
        style.setFont(font);
    }

    /**
     * Open Excel file for writing.
     *
     * @return the data File object to write to
     * @exception Exception if unable to open file
     */
    public File open() throws Exception
    {
        LOGGER.info("open() :: Enter");

        try
        {
            // Create a temporary file
            dataFile = DataFileUtil.createTempFile(getFileDirectory(), getFileName(), Constants.XLS_FILE_EXT);

            // Add headers to sheet
            for(String header : getHeaders())
            {
                HSSFCell cell = headerRow.createCell((short) cellIndex);
                cell.setCellValue(header);
                cell.setCellStyle(style);
                cellIndex++;
            }
            // Write out header data
            fileOut = new FileOutputStream(dataFile);
            workbook.write(fileOut);
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
     * Append Excel file with row of data.
     *
     * @param pDataFile the data File object to append
     * @param pData     the FileRowVO object to write
     * @exception IOException if unable to write data
     */
    public void append(File pDataFile, FileRowVO pData) throws Exception
    {
        fileOut = new FileOutputStream(dataFile);

        cellIndex = 0;
        rowIndex = sheet.getLastRowNum() + 1;
        HSSFRow row = sheet.createRow(rowIndex);
        for(FileColumnVO rowData : pData.getList())
        {
            if(rowData.getValue() == null)
            {
                LOGGER.info("rowData.getValue() is null");
                row.createCell((short) cellIndex++).setCellValue(new String());
            }
            else
            {
                LOGGER.info("rowData.getValue() is [" + rowData.getValue().toString() + "]");
                row.createCell((short) cellIndex++).setCellValue(rowData.getValue().toString());
            }
        }
        workbook.write(fileOut);
    }

    /**
     * Close Excel File and FileWriter object.
     *
     * @param pDataFile the data File object to close
     * @exception IOException if unable to close writer and/or file
     */
    public void close(File pDataFile) throws Exception
    {
        fileOut.flush();
        fileOut.close();

        // reset all object variables
        fileOut = null;
        pDataFile = null;
        dataFile = null;
        headers = null;
        fileName = null;
        fileDirectory = null;
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

    public HSSFFont getFont()
    {
        return font;
    }

    public HSSFRow getHeaderRow()
    {
        return headerRow;
    }

    public HSSFCellStyle getStyle()
    {
        return style;
    }

    public int getRowIndex()
    {
        return rowIndex;
    }
}
