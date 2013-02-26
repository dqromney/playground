package com.stgutah.playground.log4j;

import org.apache.log4j.*;

import java.io.IOException;

/**
 * Tset Logging (Log4j).
 * <p/>
 * User: dqromney
 * Date: Dec 3, 2010
 * Time: 2:29:40 PM
 */
public class TestLogging {
    // Initialize a logging category.  Here, we get THE ROOT CATEGORY
    //static Category cat = Category.getRoot();
    // Or, get a custom category
    static Category cat = Category.getInstance(TestLogging.class.getName());

    private Appender appender;

    // From here on, log away!  Methods are: cat.debug(your_message_string),
    // cat.info(...), cat.warn(...), cat.error(...), cat.fatal(...)

    public static void main(String args[]) throws IOException {

        Layout layout = new PatternLayout("%d [%t] %-5p %c - %m%n"); // ("%-4r [%t] %-5p %c %x - %m%n");
        cat.addAppender(new DailyRollingFileAppender(layout, "TestLogging.log", "'.'yyyy-MM-dd"));

        // Try a few logging methods
        cat.debug("Start of main()");
        cat.info("Just testing a log message with priority set to INFO");
        cat.warn("Just testing a log message with priority set to WARN");
        cat.error("Just testing a log message with priority set to ERROR");
        cat.fatal("Just testing a log message with priority set to FATAL");

        // Alternate but INCONVENIENT form
        cat.log(Priority.DEBUG, "Calling init()");

        new TestLogging().init();
    }

    public void init() {
        java.util.Properties prop = System.getProperties();
        java.util.Enumeration propNames = prop.propertyNames();

        cat.info("***System Environment As Seen By Java***");
        cat.debug("***Format: PROPERTY = VALUE***");

        while (propNames.hasMoreElements()){
            String key = (String)
            propNames.nextElement();
            cat.info(key + " = " + System.getProperty(key));
        }
    }


}
