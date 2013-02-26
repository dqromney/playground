package com.stgutah.playground.fileGeneration;

import java.util.List;
import java.util.logging.Logger;
import java.text.MessageFormat;

/**
 * DataFile Factory class.
 * <p/>
 * User: dqromney
 * Date: Sep 21, 2010
 * Time: 11:05:51 AM
 */
public class DataFileFactory
{
    private static final java.util.logging.Logger LOGGER = Logger.getLogger(DataFileFactory.class.toString());

    public static DataFile getDataFile(Character pDataFileType, List<String> pHeaders, String pFileName, String pFileDirectory)
    {
        if (pDataFileType.equals(Constants.FILE_TYPE_CSV))
            return new CSVFile(pHeaders, pFileName, pFileDirectory);
        else if (pDataFileType.equals(Constants.FILE_TYPE_TXT))
            return new TextFile(pHeaders, pFileName, pFileDirectory);
        else if (pDataFileType.equals(Constants.FILE_TYPE_XML))
            return new XMLFile(pHeaders, pFileName, pFileDirectory);
        else if (pDataFileType.equals(Constants.FILE_TYPE_XLS))
            return new ExcelFile(pHeaders, pFileName, pFileDirectory);
        else
            LOGGER.warning(MessageFormat.format("UNDEFINED DataFile type [{0}]", pDataFileType));  
        return null;
    }
}
