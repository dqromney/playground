package com.stgutah.playground.logging;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.Handler;
import java.util.Date;
import java.text.MessageFormat;
import java.io.StringWriter;
import java.io.PrintWriter;

/**
 * My own formatter.
 * <p/>
 * User: dqromney
 * Date: Sep 20, 2010
 * Time: 3:30:26 PM
 */
public class MyFormatter extends Formatter
{
    Date dat = new Date();
    private final static String format = "{0,date} {0,time}";
    private MessageFormat formatter;

    private Object args[] = new Object[1];

    // Line separator string.  This is the value of the line.separator
    // property at the moment that the SimpleFormatter was created.
    private String lineSeparator = (String) java.security.AccessController.doPrivileged(
            new sun.security.action.GetPropertyAction("line.separator"));

    /**
     * Format the given LogRecord.
     *
     * @param record the log record to be formatted.
     * @return a formatted log record
     */
    public synchronized String format(LogRecord record)
    {
        StringBuffer sb = getMessagePrefix(record);

        // sb.append(lineSeparator);
        sb.append(" ");
        String message = formatMessage(record);
        sb.append(record.getLevel().getLocalizedName());
        sb.append(": ");
        sb.append(message);
        sb.append(lineSeparator);
        if (record.getThrown() != null)
        {
            try
            {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                record.getThrown().printStackTrace(pw);
                pw.close();
                sb.append(sw.toString());
            } catch (Exception ex)
            {
            }
        }
        return sb.toString();
    }

    private StringBuffer getMessagePrefix(LogRecord record)
    {
        StringBuffer sb = new StringBuffer();
        // Minimize memory allocations here.
        dat.setTime(record.getMillis());
        args[0] = dat;
        StringBuffer text = new StringBuffer();

        // Creates a message format with args for Date and Time, i.e. {0,date} {0,time}
        if (formatter == null)
        {
            formatter = new MessageFormat(format);
        }
        // Fills in Date and Time stamp args and adds to string buffer
        formatter.format(args, text, null);
        sb.append(text);
        sb.append(" ");

        // Add logger name, i.e. com.stgutah.playground.logging.LogTest1
        if (record.getSourceClassName() != null)
        {
            sb.append(record.getSourceClassName());
        }
        else
        {
            sb.append(record.getLoggerName());
        }

        // Add class name if it exists, i.e. ' main'
        if (record.getSourceMethodName() != null)
        {
            sb.append(" ");
            sb.append(record.getSourceMethodName());
        }
        return sb;
    }

    @Override
    public String getHead(Handler h)
    {
        return "BEGINNING OF LOG...\n";
    }

    @Override
    public String getTail(Handler h)
    {
        return "END OF LOG...\n";
    }
}
