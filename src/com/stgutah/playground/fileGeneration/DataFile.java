package com.stgutah.playground.fileGeneration;

import java.io.File;
import java.util.List;

/**
 * DataFile Factory interface.
 *
 * User: dqromney
 * Date: Sep 21, 2010
 * Time: 10:59:22 AM
 */
public interface DataFile
{
    public File open() throws Exception;
    public void append(File pFile, FileRowVO pData) throws Exception;
    public void close(File pFile) throws Exception;

    //-----------------------------------------------------------------
    // General configuration methods
    //-----------------------------------------------------------------
    public File getDataFile();
    public String getFileDirectory();
    public String getFileName();
    public List<String> getHeaders();
}
