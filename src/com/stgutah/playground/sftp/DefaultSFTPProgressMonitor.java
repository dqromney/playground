package com.stgutah.playground.sftp;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpProgressMonitor;

import java.util.logging.Logger;

/**
 * Secure FTP Progress Monitor class.
 * <p/>
 * User: dqromney
 * Date: Oct 19, 2010
 * Time: 5:52:17 PM
 */
public class DefaultSFTPProgressMonitor implements SftpProgressMonitor {

    private static final java.util.logging.Logger LOGGER = Logger.getLogger(DefaultSFTPProgressMonitor.class.toString());

    // ProgressMonitor monitor;
    long count = 0;
    long max = 0;

    public void init(int op, String src, String dest, long max) {
        System.out.println("init() :: Enter");
        this.max = max;
        String operation = ((op == SftpProgressMonitor.PUT) ? "put" : "get") + ": " + src;
        // monitor = new ProgressMonitor(null, operation, "", 0, (int) max);
        LOGGER.fine(operation);
        count = 0;
        percent = -1;
        //monitor.setProgress((int) this.count);
        //monitor.setMillisToDecideToPopup(1000);
        System.out.println("init() :: Exit");
    }

    private long percent = -1;

    public boolean count(long count) {
        System.out.println("count() :: Enter");
        this.count += count;

        if (percent >= this.count * 100 / max) {
            return true;
        }
        percent = this.count * 100 / max;

        //monitor.setNote("Completed " + this.count + "(" + percent + "%) out of " + max + ".");
        //monitor.setProgress((int) this.count);
        LOGGER.fine(String.format("Completed %1$d (%2$d%) out of %2$d max.",this.count, percent, max));
        //System.out.println(String.format("-- %1$s", monitor.getNote()));

        System.out.println("count() :: Exit");
        return this.count == max; //!(this.monitor.isCanceled());
    }

    public void end() {
        System.out.println("end() :: Enter");
        LOGGER.fine(String.format(String.format("Complete %1$d (%2$d%) out of %2$d max.",this.count, percent, max)));
//        monitor.close();
        System.out.println("end() :: Exit");
    }
}
