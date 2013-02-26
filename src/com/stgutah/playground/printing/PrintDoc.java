package com.stgutah.playground.printing;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.attribute.DocAttributeSet;
import java.io.IOException;
import java.io.Reader;
import java.io.InputStream;

/**
 * Created by IntelliJ IDEA.
 * User: dqromney
 * Date: Oct 1, 2010
 * Time: 4:50:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class PrintDoc implements Doc
{
    String fileName;
    DocAttributeSet attributes;
    DocFlavor docFlavor;

    public PrintDoc(String pFileName)
    {
        fileName = pFileName;    
    }

    public DocAttributeSet getAttributes()
    {
        return attributes;
    }

    public DocFlavor getDocFlavor()
    {
        return docFlavor;
    }

    public Object getPrintData() throws IOException
    {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Reader getReaderForText() throws IOException
    {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public InputStream getStreamForBytes() throws IOException
    {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
