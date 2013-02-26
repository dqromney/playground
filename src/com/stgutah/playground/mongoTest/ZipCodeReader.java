package com.stgutah.playground.mongoTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

/**
 * ZipCode file reader
 * User: dqromney
 * Date: Jun 22, 2010
 * Time: 5:13:55 PM
 */
public class ZipCodeReader
{
    private BufferedReader in;

    public ZipCodeReader(Reader reader)
    {
        in = new BufferedReader(reader);
    }

    public ZipCode read() throws IOException
    {
        String line;
        do
        {
            line = in.readLine();
            //System.out.println("[" + line == null ? "EMPTY" : line + "]");
            if (line == null)
            {
                return null;
            }
        }
        // Ignore comments
        while (line.length() < 1 || line.charAt(0) == '#');

        String[] items = line.split(",");
        if (items == null || items.length < 3)
        {
            throw new IOException("Could not interpret line: " + line);
        }
        ZipCode zipCode = new ZipCode();
        zipCode.setZipCode(items[0].replaceAll("\"", ""));
        zipCode.setCounty(items[1].replaceAll("\"", ""));
        zipCode.setAreaCode(items[2].replaceAll("\"", ""));
        return zipCode;
    }
}
