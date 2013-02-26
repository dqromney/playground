package com.stgutah.playground.fileGeneration;

import java.io.File;
import java.io.IOException;

/**
 * DataFile utility.
 *
 * User: dqromney
 * Date: Sep 21, 2010
 * Time: 3:44:33 PM
 */
public class DataFileUtil
{
    /**
     * Create a temporary file based upon optional directory.
     *
     * @param pFileDirectory an optional target file diretory
     * @param pFileName the target filename
     * @param pFileExtension the target filename extension, without the "."
     * @throws java.io.IOException if unable to create temporary file
     */
    public static File createTempFile(String pFileDirectory, String pFileName, String pFileExtension)
            throws IOException
    {
        File dataFile;

        if (pFileDirectory == null)
        {
            dataFile = File.createTempFile(pFileName, "." + pFileExtension);
        }
        else
        {
            dataFile = File.createTempFile(pFileName, "." + pFileExtension, new File(pFileDirectory));
        }
        return dataFile;
    }

}
