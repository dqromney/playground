package com.stgutah.playground.systemProperties;

import java.util.logging.Logger;
import java.util.Properties;
import java.util.Map;

/**
 * Code to show System Properties.
 * <p/>
 * User: dqromney
 * Date: Aug 30, 2010
 * Time: 3:13:06 PM
 */
public class SystemProperties
{
    Logger log = Logger.getLogger(SystemProperties.class.getName());
    String newLine = System.getProperty("line.separator");

    // ----------------------------------------------------------------
    // Driver code
    // ----------------------------------------------------------------
    public static void main(String[] args)
    {
        SystemProperties systemProperties = new SystemProperties();
        systemProperties.init();
        systemProperties.execute();
    }

    private void init()
    {

    }

    private void execute()
    {
        Properties properties = System.getProperties();

        for(Map.Entry entry : properties.entrySet())
        {
            System.out.printf("%2$s = [%3$s]%1$s", newLine, entry.getKey(), entry.getValue());
        }
    }
}
