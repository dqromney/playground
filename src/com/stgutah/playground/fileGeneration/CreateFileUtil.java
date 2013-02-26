package com.stgutah.playground.fileGeneration;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.xml.sax.helpers.AttributesImpl;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utility class to create file in different formats.
 * <p/>
 * User: dqromney
 * Date: Aug 13, 2010
 * Time: 3:43:54 PM
 */
public class CreateFileUtil
{
    private static final java.util.logging.Logger LOGGER = Logger.getLogger(CreateFileUtil.class.toString());

    /**
     * This method create a file.
     *
     * @param pFileFormat    file format
     * @param pHeaders       list of headers
     * @param pData          data to write
     * @param pFileName      name of the file
     * @param pFileDirectory directory of the file
     * @return the file
     * @exception Exception if fails
     */
    public File createFile(Character pFileFormat, List<String> pHeaders, List<FileRowVO> pData, String pFileName, String pFileDirectory)
            throws Exception
    {
        LOGGER.info("Entering createFile()");
        File file = null;
        try
        {
            if (pFileFormat.equals(Constants.FILE_TYPE_XLS))
            {
                file = createXLS(pHeaders, pData, pFileDirectory, pFileName);
            }
            else if (pFileFormat.equals(Constants.FILE_TYPE_XML))
            {
                file = createXML(pData, pFileDirectory, pFileName);
            }
            else
            {
                file = createCSV(pHeaders, pData, pFileDirectory, pFileName);
            }
        }
        catch (Exception e)
        {
            LOGGER.log(Level.SEVERE, "Error in createFile()");
            throw e;
        }
        LOGGER.info("Exiting createFile()");
        return file;
    }

    /**
     * This method create a file.
     *
     * @param pFileFormat file format
     * @param pHeaders    list of headers
     * @param pData       data to write
     * @param pFileName   name of the file
     * @return the file
     * @exception Exception if fails
     */
    public File createFile(Character pFileFormat, List<String> pHeaders, List<FileRowVO> pData, String pFileName)
            throws Exception
    {
        return createFile(pFileFormat, pHeaders, pData, pFileName, null);
    }

    /**
     * This method creates XML file.
     *
     * @param pFileName file name
     * @param pData     data
     * @return the created file
     * @exception Exception
     */
    private File createXML(List<FileRowVO> pData, String pFileDirectory, String pFileName) throws Exception
    {
        LOGGER.info("Entering createXML()");
        File file = null;
        try
        {
            if (pFileDirectory == null)
            {
                file = File.createTempFile(pFileName, ".xml");
            }
            else
            {
                file = File.createTempFile(pFileName, ".xml", new File(pFileDirectory));
            }
            // file = new File(pFileName);
            StreamResult streamResult = new StreamResult(file.toURI().getPath());
            SAXTransformerFactory tf = (SAXTransformerFactory) SAXTransformerFactory.newInstance();

            TransformerHandler hd = tf.newTransformerHandler();
            Transformer serializer = hd.getTransformer();
            serializer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
            serializer.setOutputProperty(OutputKeys.INDENT, "yes");
            hd.setResult(streamResult);
            hd.startDocument();
            AttributesImpl atts = new AttributesImpl();

            hd.startElement("", "", "USERS", atts);
            for(FileRowVO data : pData)
            {
                hd.startElement("", "", "USER", atts);

                List<FileColumnVO> rowDataList = data.getList();

                for(FileColumnVO rowData : rowDataList)
                {
                    hd.startElement("", "", rowData.getName(), atts);
                    hd.characters(rowData.getValue().toString().toCharArray(), 0, rowData.getValue().toString()
                            .length());
                    hd.endElement("", "", rowData.getName());
                }
                hd.endElement("", "", "USER");
            }
            hd.endElement("", "", "USERS");
            hd.endDocument();

        }
        catch (Exception e)
        {
            LOGGER.log(Level.SEVERE, "Error in createXML():" + e.toString());
            throw e;
        }

        LOGGER.info("Exiting createXML()");
        return file;

    }

    /**
     * This method creates XLS file.
     *
     * @param pFileName the file name
     * @param pHeaders  the list of headers
     * @param pData     the data to write
     * @return the file
     * @exception Exception if fails
     */
    private File createXLS(List<String> pHeaders, List<FileRowVO> pData, String pFileDirectory, String pFileName) throws Exception
    {
        LOGGER.info("Entering createXLS()");
        File file = null;
        try
        {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet();
            int rowIndex = 0;
            HSSFRow headerRow = sheet.createRow((short) rowIndex);
            HSSFCellStyle style = workbook.createCellStyle();
            HSSFFont font = workbook.createFont();
            font.setFontName(HSSFFont.FONT_ARIAL);
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            font.setColor(HSSFColor.BLUE.index);
            style.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
            style.setFont(font);

            int cellIndex = 0;
            for(String header : pHeaders)
            {
                HSSFCell cell = headerRow.createCell((short) cellIndex);
                cell.setCellValue(header);
                cell.setCellStyle(style);
                cellIndex++;
            }

            for(FileRowVO data : pData)
            {
                cellIndex = 0;
                HSSFRow row = sheet.createRow((short) ++rowIndex);
                List<FileColumnVO> rowDataList = data.getList();

                for(FileColumnVO rowData : rowDataList)
                {
                    row.createCell((short) cellIndex++).setCellValue(rowData.getValue().toString());
                }
            }
            if (pFileDirectory == null)
            {
                file = File.createTempFile(pFileName, ".xls");
            }
            else
            {
                file = File.createTempFile(pFileName, ".xls", new File(pFileDirectory));
            }
            FileOutputStream fileOut = new FileOutputStream(file);
            workbook.write(fileOut);
            fileOut.close();
        }
        catch (Exception e)
        {
            LOGGER.log(Level.SEVERE, "Error in createXLS():" + e.toString());
            throw e;
        }
        LOGGER.info("Exiting createXLS()");
        return file;
    }

    /**
     * This method creates CSV file.
     *
     * @param pFileName the file name
     * @param pData     the data to write
     * @return the file
     * @exception Exception if fails
     */
    private File createCSV(List<String> pHeaders, List<FileRowVO> pData, String pFileDirectory, String pFileName) throws Exception
    {
        LOGGER.info("Entering createCSV()");
        File file = null;
        try
        {
            if (pFileDirectory == null)
            {
                file = File.createTempFile(pFileName, ".csv");
            }
            else
            {
                file = File.createTempFile(pFileName, ".csv", new File(pFileDirectory));
            }
            // log.info("-- AFTER createTempFile([#0])", pFileName+".csv");
            FileWriter writer = new FileWriter(file);
            // log.info("-- AFTER FileWriter(file) file.getCanonicalPath()=[#0] file.getCanonicalFile()=[#1]",
            //        file.getCanonicalPath(), file.getCanonicalFile());
            String value;
            // Header for file if provided; could be enclosed in the FileRowVO already
            int i = 0;
            for(String header : pHeaders)
            {
                value = prepareString((header != null) ? header : "UNDEFINED", Constants.DATA_TYPE_TEXT);
                writer.append(value);
                if (i++ < (pHeaders.size() - 1))
                {
                    writer.append(',');
                }
            }
            writer.append(System.getProperty("line.separator"));
            // Data for file
            for(FileRowVO data : pData)
            {
                List<FileColumnVO> rowDataList = data.getList();
                // log.info("-- AFTER data.getList() size()=[#0]", data.getList().size());
                i = 0;
                for(FileColumnVO rowData : rowDataList)
                {
                    value = prepareString((rowData.getValue() != null) ? rowData.getValue().toString() : "", rowData.getDataType());
                    writer.append(value);
                    // log.info("-- AFTER writer.append(rowData.getValue().toString())=[#0]", value);
                    if (i++ < (rowDataList.size() - 1))
                    {
                        writer.append(',');
                    }
                }
                writer.append(System.getProperty("line.separator"));
            }
            writer.close();
        }
        catch (Exception e)
        {
            LOGGER.log(Level.SEVERE, "Error in createCSV():" + e.toString());
            throw e;
        }
        LOGGER.info("Exiting createCSV()");
        return file;
    }

    /**
     * Prepare field String for CSV value output.
     *
     * @param pValue the value to prepare
     * @return a prepared value, i.e. adding encapsulating double-quotes.
     */
    private String prepareString(String pValue, String pDataType)
    {
        final char doubleQuote = '"';
        int pValueLength = (pValue.length() - 1 < 0) ? 0 : pValue.length() - 1;
        // Is value already encapsulated in double-quotes?
        if ("".equals(pValue))
        {
            if ((pDataType.compareTo(Constants.DATA_TYPE_TEXT) == 0) || (pDataType.compareTo(Constants.DATA_TYPE_UNDEFINED) == 0))
            {
                pValue = "\"\"";
            }
        }
        else if ((pValue.charAt(0) != doubleQuote) && (pValue.charAt(pValueLength) != doubleQuote))
        {
            if ((pDataType.compareTo(Constants.DATA_TYPE_TEXT) == 0) || (pDataType.compareTo(Constants.DATA_TYPE_UNDEFINED) == 0))
            {
                pValue = new StringBuffer().append('"').append(pValue).append('"').toString();
            }
        }
        return pValue;
    }


    // TEST driver
    File wholefile;
    File appendFile;
    DataFile dataFile;
    String exportDir = System.getProperty("user.dir");
    List<String> headerList = new ArrayList<String>(0);
    List<FileRowVO> dataList = new ArrayList<FileRowVO>(0);
    FileRowVO row;

    public static void main(String[] args) throws Exception
    {
        CreateFileUtil main = new CreateFileUtil();
        main.init();
        main.execute();
    }


    private void init()
    {
        headerList.add(0, "One");
        headerList.add(1, "Two");
        headerList.add(2, "Three");

        // With ordering
        row = new FileRowVO();
        row.addData(0, new FileColumnVO(Constants.DATA_TYPE_TEXT, "One", "Row1Test1"));
        row.addData(2, new FileColumnVO(Constants.DATA_TYPE_NUMERIC, "Three", 1.25));
        row.addData(1, new FileColumnVO(Constants.DATA_TYPE_BOOLEAN, "Two", Boolean.TRUE));
        dataList.add(row);

        // With ordering
        row = new FileRowVO();
        row.addData(2, new FileColumnVO(Constants.DATA_TYPE_NUMERIC, "Three", 1232.23));
        row.addData(0, new FileColumnVO(Constants.DATA_TYPE_TEXT, "One", "Row2Test1"));
        row.addData(1, new FileColumnVO(Constants.DATA_TYPE_BOOLEAN, "Two", Boolean.FALSE));
        dataList.add(row);

        // Without order; order as I go.
        row = new FileRowVO();
        row.addData(new FileColumnVO(Constants.DATA_TYPE_TEXT, "One", "Row3Test1"));
        row.addData(new FileColumnVO(Constants.DATA_TYPE_BOOLEAN, "Two", Boolean.TRUE));
        row.addData(new FileColumnVO(Constants.DATA_TYPE_NUMERIC, "Three", 1234.24));
        dataList.add(row);

    }

    private void execute() throws Exception
    {
        // ------------------------------------------------------------
        // Saving all data in dataList at once; could be memory
        //  expensive
        // ------------------------------------------------------------
        wholefile = createFile(Constants.FILE_TYPE_CSV, headerList, dataList, "createFileUtil_", exportDir);
        showFileContents(wholefile);
        wholefile = createFile(Constants.FILE_TYPE_XML, headerList, dataList, "createFileUtil_", exportDir);
        showFileContents(wholefile);
        wholefile = createFile(Constants.FILE_TYPE_TXT, headerList, dataList, "createFileUtil_", exportDir);
        showFileContents(wholefile);
        wholefile = createFile(Constants.FILE_TYPE_XLS, headerList, dataList, "createFileUtil_", exportDir);
        // no showFileContents here

        // ------------------------------------------------------------
        // A better way of doing the same thing above without using a
        // lot of memory is implemented below.
        // ------------------------------------------------------------

        // Create CSV or Comma Separated Value file
        doCreateCSV();

        // Create Excel file
        doCreateExcel();

        // Create XML file
        doCreateXML();

        // Create Text file
        doCreateText();

        // Create UNDEFINED file; should show warning, and not create a file
        doCreateUndefined();

    }

    /**
     * Create UNDEFINED file; should show warning, and not create a file.
     *
     * @exception Exception
     */
    private void doCreateUndefined()
            throws Exception
    {
        dataFile = DataFileFactory.getDataFile(Constants.FILE_TYPE_UNDEFINED, headerList, "newCreateFileUtil_", exportDir);
        if(dataFile != null)
        {
            appendFile = dataFile.open();
            for(FileRowVO fileRow : dataList)
            {
                // Get row data from DB here, then..
                // Append row to the file
                dataFile.append(appendFile, fileRow);
            }
            // Close the appended file
            dataFile.close(appendFile);
            showFileContents(appendFile);
        }
    }

    /**
     * Create Text file.
     *
     * @exception Exception
     */
    private void doCreateText()
            throws Exception
    {
        dataFile = DataFileFactory.getDataFile(Constants.FILE_TYPE_TXT, headerList, "newCreateFileUtil_", exportDir);
        ((TextFile) dataFile).setFieldSeparator("\t");
        ((TextFile) dataFile).setEncapsulatedText(true);
        ((TextFile) dataFile).setTextEncapsulationCharacter("'");
        appendFile = dataFile.open();
        for(FileRowVO fileRow : dataList)
        {
            // Get row data from DB here, then..
            // Append row to the file
            dataFile.append(appendFile, fileRow);
        }
        // Close the appended file
        dataFile.close(appendFile);
        showFileContents(appendFile);
    }

    /**
     * Create XML file.
     *
     * @exception Exception
     */
    private void doCreateXML()
            throws Exception
    {
        dataFile = DataFileFactory.getDataFile(Constants.FILE_TYPE_XML, headerList, "newCreateFileUtil_", exportDir);
        // Set XML group element and element names
        ((XMLFile) dataFile).setElementGroupName("DATASET");
        ((XMLFile) dataFile).setElementName("ROW");
        ((XMLFile) dataFile).setLocalName("ROW"); // optional
        appendFile = dataFile.open();
        ((XMLFile) dataFile).setUri(appendFile.toURI().getPath()); // optional
        for(FileRowVO fileRow : dataList)
        {
            // Get row data from DB here, then..
            // Append row to the file
            dataFile.append(appendFile, fileRow);
        }
        // Close the appended file
        dataFile.close(appendFile);
        showFileContents(appendFile);
    }

    /**
     * Create Excel file.
     *
     * @exception Exception
     */
    private void doCreateExcel()
            throws Exception
    {
        dataFile = DataFileFactory.getDataFile(Constants.FILE_TYPE_XLS, headerList, "newCreateFileUtil_", exportDir);
        appendFile = dataFile.open();
        // Set some style and fonts by casting to ExcelFile here
        ((ExcelFile) dataFile).getFont().setItalic(true);
        ((ExcelFile) dataFile).getFont().setColor(HSSFColor.DARK_GREEN.index);
        ((ExcelFile) dataFile).getStyle().setFillBackgroundColor(HSSFColor.CORAL.index);
        ((ExcelFile) dataFile).getHeaderRow().setHeightInPoints(20);
        for(FileRowVO fileRow : dataList)
        {
            // Get row data from DB here, then..
            // Append row to the file
            dataFile.append(appendFile, fileRow);
        }
        // Close the appended file
        dataFile.close(appendFile);
        System.out.println("Wrote [" + ((ExcelFile) dataFile).getHeaderRow().getLastCellNum() + "] column(s) and [" +
                ((ExcelFile) dataFile).getRowIndex() + "] row(s) of data to sheet.");
    }

    /**
     * Create CSV or Comma Separated Value file.
     *
     * @exception Exception
     */
    private void doCreateCSV()
            throws Exception
    {
        dataFile = DataFileFactory.getDataFile(Constants.FILE_TYPE_CSV, headerList, "newCreateFileUtil_", exportDir);
        appendFile = dataFile.open();
        for(FileRowVO fileRow : dataList)
        {
            // Get row data from DB here, then..
            // Append row to the file
            dataFile.append(appendFile, fileRow);
        }
        // Close the appended file
        dataFile.close(appendFile);
        showFileContents(appendFile);
    }

    /**
     * Show file contents; convenience method.
     *
     * @param file the File object
     * @exception Exception if unable to read file object
     */
    private void showFileContents(File file) throws Exception
    {
        if (file != null)
        {
            LOGGER.info(String.format("-- file path=[%1$s]", file.getCanonicalPath()));
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s;
            while ((s = br.readLine()) != null)
            {
                System.out.println(s);
            }
            br.close();
        }
    }
}
