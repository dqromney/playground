package com.stgutah.playground.fileGeneration;

/**
 * Constants for file generation.
 *
 * User: dqromney
 * Date: Aug 13, 2010
 * Time: 3:45:37 PM
 */
public class Constants
{
    // File format constants
    public static final Character FILE_TYPE_UNDEFINED = 'X';
    public static final Character FILE_TYPE_XLS = '0';
    public static final Character FILE_TYPE_TXT = '1';
    public static final Character FILE_TYPE_XML = '2';
    public static final Character FILE_TYPE_CSV = '3';

    // Some data type constants
    public static final String DATA_TYPE_UNDEFINED = "UNDEFINED";
    public static final String DATA_TYPE_TEXT = "TEXT";
    public static final String DATA_TYPE_NUMERIC = "NUMERIC";
    public static final String DATA_TYPE_BOOLEAN = "BOOLEAN";
    public static final String CSV_FIELD_SEPARATOR = ",";
    public static final String CSV_TEXT_ENCAPSULATION_CHAR = "\"";
    public static final Boolean CSV_ENCAPSULATE_TEXT = Boolean.TRUE;
    
    // File extension constants
    public static final String XLS_FILE_EXT = "xls";
    public static final String XML_FILE_EXT = "xml";
    public static final String CSV_FILE_EXT = "csv";
    public static final String TXT_FILE_EXT = "txt";

    // XML default Element group and Element name
    public static final String XML_DEFAULT_ELEMENTS = "DATASET";
    public static final String XML_DEFAULT_ELEMENT = "ROW";
}
