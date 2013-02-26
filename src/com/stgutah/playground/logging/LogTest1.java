package com.stgutah.playground.logging;

import java.util.logging.*;

/**
 * Log Test 1 - using java.util.logging.Logger
 * <p/>
 * User: dqromney
 * Date: Sep 20, 2010
 * Time: 3:14:35 PM
 */
public class LogTest1
{
    private static Logger logger = Logger.getLogger("LogTest1.logging");


    public static void main(String argv[])
    {
        Handler fh1 = null;
        Handler fh2 = null;
        Handler fh3 = null;
        try
        {
            fh1 = new FileHandler("./LogTest1.html", 50000, 1);
            fh2 = new FileHandler("./LogTest1.log", 50000, 1);
            fh3 = new FileHandler("./LogTest1.xml", 50000, 1);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        // Don't really need this, since XML
        // formatting is the default for FileHandlers >
        fh1.setFormatter(new MyHtmlFormatter());
        fh2.setFormatter(new MyFormatter());
        fh3.setFormatter(new java.util.logging.XMLFormatter());

        Handler ch = new ConsoleHandler();

        // Don't really need this, since simple
        // formatting is the default for
        // ConsoleHandlers
         ch.setFormatter(new java.util.logging.SimpleFormatter());

        // Get an instance of the root logger,
        // which is the parent of all loggers,
        // and set the global handlers on it
        Logger rootLogger = Logger.getLogger("");
        rootLogger.addHandler(ch);
        rootLogger.addHandler(fh1);
        rootLogger.addHandler(fh2);
        rootLogger.addHandler(fh3);

        // Now use the named logger and...

        // log a FINE message
        logger.fine("done");


        // log a INFO message.
        logger.info("doing stuff");


        // log a WARNING
        logger.log(Level.WARNING, "something may be wrong");

        // we can log a WARNING as follows
        // logger.warning ( "something may be wrong" ) ;
    }

}
