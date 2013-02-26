package com.stgutah.playground.fileGeneration;

import org.xml.sax.helpers.AttributesImpl;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * XML or Extensible Markup Language class.
 * <p/>
 * User: dqromney
 * Date: Sep 21, 2010
 * Time: 11:04:09 AM
 */
public class XMLFile implements DataFile
{
    private static final java.util.logging.Logger LOGGER = Logger.getLogger(XMLFile.class.toString());

    private final String EMPTY_STRING = "";
    
    private File dataFile;
    private List<String> headers;
    private String fileName;
    private String fileDirectory;
    private String elementGroupName;
    private String elementName;

    // XML Specific default setup variables
    private StreamResult streamResult;
    private SAXTransformerFactory tf;
    private TransformerHandler hd;
    private Transformer serializer;

    // Default configuation values
    private String uri = EMPTY_STRING;
    private String localName = EMPTY_STRING;

    /**
     * XMLFile empty constructor.
     */
    public XMLFile()
    {
        // Chained constructor with defaults call
        this(new ArrayList<String>(0), "XMLFile_UNDEFINED_", (System.getProperty("user.dir")));
    }

    /**
     * Constructor for XMLFile.
     *
     * @param pHeaders       the list of Headers for file
     * @param pFileName      the filename of file
     * @param pFileDirectory the file directory
     */
    public XMLFile(List<String> pHeaders, String pFileName, String pFileDirectory)
    {
        this.headers = pHeaders;
        this.fileName = pFileName;
        this.fileDirectory = pFileDirectory;
    }

    /**
     * Open XML file for writing.
     *
     * @return the data File object to write to
     * @exception Exception if unable to open file; TransformerConfigurationException also.
     */
    public File open() throws Exception
    {
        LOGGER.info("open() :: Enter");

        try
        {
            // Create default XML configuration
            tf = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
            hd = tf.newTransformerHandler();
            serializer = hd.getTransformer();
            serializer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
            serializer.setOutputProperty(OutputKeys.INDENT, "yes");
            serializer.setOutputProperty(OutputKeys.METHOD, "xml");

            dataFile = DataFileUtil.createTempFile(getFileDirectory(), getFileName(), Constants.XML_FILE_EXT);
            streamResult = new StreamResult(dataFile.toURI().getPath());
            hd.setResult(streamResult);

            hd.startElement(EMPTY_STRING, EMPTY_STRING, getElementGroupName(), new AttributesImpl());
        }
        catch (Exception e)
        {
            LOGGER.log(Level.SEVERE, "Error in createXML():" + e.toString());
            throw e;
        }

        LOGGER.info("open() :: Exit");

        return dataFile;
    }

    /**
     * Append XML file with row of data.
     *
     * @param pDataFile the data File object to append
     * @param pData     the FileRowVO object to write
     * @exception IOException if unable to write data
     */
    public void append(File pDataFile, FileRowVO pData) throws Exception
    {
        AttributesImpl atts = new AttributesImpl();

        try
        {
            hd.startElement(EMPTY_STRING, EMPTY_STRING, getElementName(), atts);

            List<FileColumnVO> rowDataList = pData.getList();

            for(FileColumnVO rowData : rowDataList)
            {
                hd.startElement(EMPTY_STRING, EMPTY_STRING, rowData.getName(), atts);
                hd.characters(rowData.getValue().toString().toCharArray(), 0, rowData.getValue().toString()
                        .length());
                hd.endElement(EMPTY_STRING, EMPTY_STRING, rowData.getName());
            }
            hd.endElement(EMPTY_STRING, EMPTY_STRING, getElementName());
        }
        catch (Exception e)
        {
            LOGGER.log(Level.SEVERE, "Unable to append file:" + e.toString());
            throw e;
        }
    }

    /**
     * Close XML File and FileWriter object.
     *
     * @param pDataFile the data File object to close
     * @exception IOException if unable to close writer and/or file
     */
    public void close(File pDataFile) throws Exception
    {
        hd.endElement(EMPTY_STRING, EMPTY_STRING, getElementGroupName());
        hd.endDocument();

        // reset all object variables
        streamResult = null;
        tf = null;
        hd = null;
        serializer = null;
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

    public TransformerHandler getHd()
    {
        return hd;
    }

    public Transformer getSerializer()
    {
        return serializer;
    }

    public SAXTransformerFactory getTf()
    {
        return tf;
    }

    public StreamResult getStreamResult()
    {
        return streamResult;
    }

    // ----------------------------------------------------------------
    // Read/Write Acessors
    // ----------------------------------------------------------------

    public String getElementName()
    {
        return elementName == null ? Constants.XML_DEFAULT_ELEMENT : elementName;
    }

    public void setElementName(String elementName)
    {
        this.elementName = elementName;
    }

    public String getElementGroupName()
    {
        return elementGroupName == null ? Constants.XML_DEFAULT_ELEMENTS : elementGroupName;
    }

    public void setElementGroupName(String elementGroupName)
    {
        this.elementGroupName = elementGroupName;
    }
    public String getLocalName()
    {
        return localName;
    }

    public void setLocalName(String localName)
    {
        if( !(localName == null || localName.isEmpty()) )
        {
            this.localName = localName;
        }
    }

    public String getUri()
    {
        return uri;
    }

    public void setUri(String uri)
    {
        if( !(uri == null || uri.isEmpty()) )
        {
            this.uri = uri;
        }
    }
}
