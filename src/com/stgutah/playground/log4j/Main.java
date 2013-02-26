package com.stgutah.playground.log4j;

import org.apache.log4j.*;

import java.io.IOException;

/**
 * Log4j example.
 *
 * User: dqromney
 * Date: Dec 2, 2010
 * Time: 11:52:45 AM
 */
public class Main {
    private static Logger log = Logger.getLogger(Main.class);

    private void execute() {
        log.info("execute() :: Enter");
        log.info("execute() :: Exit");
    }

    private void init() {
        // Setup layout, see http://logging.apache.org/log4j/1.2/apidocs/org/apache/log4j/PatternLayout.html
        Layout layout = new PatternLayout("%d [%t] %-5p %c - %m%n"); // ("%-4r [%t] %-5p %c %x - %m%n");
        Appender dailyRollingFileAppender = null;

        try {
            // No need for explicit definition in log4j.properties file
            dailyRollingFileAppender = new DailyRollingFileAppender(layout, "log4jTest.log", "'.'yyyy-MM-dd");
//            BasicConfigurator.configure();
        } catch (IOException e) {
            log.error(String.format("Error creating test.log"), e);
        }
        // Set up a simple configuration that logs on the console.
        log.getLoggerRepository().getRootLogger().addAppender(dailyRollingFileAppender);
//        log.getLoggerRepository().getRootLogger().addAppender(new ConsoleAppender(layout));

        log.info("init() :: Enter");

        log.info("init() :: Exit");
    }

    // Driver
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.init();
        main.execute();
    }
}
