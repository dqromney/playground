package com.stgutah.playground.sftp;

/**
 * My Logger.
 *
 * User: dqromney
 * Date: Oct 18, 2010
 * Time: 5:09:07 PM
 */
public class MyLogger implements com.jcraft.jsch.Logger {
    private static final java.util.logging.Logger log = java.util.logging.Logger.getLogger(SecureFTPTest.class.toString());
    static java.util.Hashtable name = new java.util.Hashtable();

    static {
        name.put(new Integer(DEBUG), "DEBUG: ");
        name.put(new Integer(INFO), "INFO: ");
        name.put(new Integer(WARN), "WARN: ");
        name.put(new Integer(ERROR), "ERROR: ");
        name.put(new Integer(FATAL), "FATAL: ");
    }

    public boolean isEnabled(int level) {
        return true;
    }

    public void log(int level, String message) {
        log.info(String.format("%1$s %2$s", name.get(new Integer(level)), message));
    }
}
