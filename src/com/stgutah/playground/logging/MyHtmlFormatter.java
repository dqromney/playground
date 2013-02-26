package com.stgutah.playground.logging;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.Level;
import java.util.logging.Handler;
import java.util.Date;
import java.text.MessageFormat;


/**
 * This custom formatter formats parts of a log record to a single line.
 * <p/>
 * User: dqromney
 * Date: Sep 20, 2010
 * Time: 4:38:38 PM
 */
public class MyHtmlFormatter extends Formatter
{
    Date dat = new Date();
    private final static String format = "{0,date} {0,time}";
    private MessageFormat formatter;

    private Object args[] = new Object[1];

    // This method is called for every log records
    public String format(LogRecord rec)
    {
        StringBuffer buf = getMessagePrefix(rec);
        //buf.append(' ');
        buf.append("<TD>");
        buf.append(formatMessage(rec));
        buf.append("</TD>");

        buf.append("</TR>\n");

//        buf.append('\n');
        return buf.toString();
    }

    private StringBuffer getMessagePrefix(LogRecord record)
    {
        StringBuffer sb = new StringBuffer();
        // Minimize memory allocations here.
        dat.setTime(record.getMillis());
        args[0] = dat;
        StringBuffer text = new StringBuffer();

        sb.append("<TR>");

        // Creates a message format with args for Date and Time, i.e. {0,date} {0,time}
        if (formatter == null)
        {
            formatter = new MessageFormat(format);
        }
        // Fills in Date and Time stamp args and adds to string buffer
        formatter.format(args, text, null);
        sb.append("<TD>");
        sb.append(text);
        sb.append("</TD>");
//        sb.append(" ");

        // Add logger name, i.e. com.stgutah.playground.logging.LogTest1
        sb.append("<TD>");
        if (record.getSourceClassName() != null)
        {
            sb.append(record.getSourceClassName());
        }
        else
        {
            sb.append(record.getLoggerName());
        }
        sb.append("</TD>");

        // Add class name if it exists, i.e. ' main'
        if (record.getSourceMethodName() != null)
        {
//            sb.append(" ");
            sb.append("<TD>");
            sb.append(record.getSourceMethodName());
            sb.append("</TD>");
        }

        sb.append("<TD>");
        if (record.getLevel().intValue() >= Level.WARNING.intValue())
        {
            sb.append("<b>");
            // sb.append(" ");
            sb.append(record.getLevel());
            sb.append("</b>");
        }
        else
        {
            // sb.append(" ");
            sb.append(record.getLevel());
        }
        sb.append("</TD>");

        return sb;
    }

    // This method is called just after the handler using this
    // formatter is created
    public String getHead(Handler h)
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append("<HTML><HEAD>\n" + (new Date()) + "\n</HEAD><BODY><PRE>\n");
        buffer.append("<TABLE CELLPADDING=6 RULES=GROUPS  FRAME=BOX>\n");
        buffer.append("<THEAD>\n");
        buffer.append("<TR align=left BGCOLOR=\"#FFEEDD\"><TH>Timestamp</TH> <TH>Source Class Name</TH> <TH>Source Method Name</TH>  <TH>Level</TH> <TH>Message</TH></TR>\n");
        buffer.append("</THEAD>\n");
        return buffer.toString();
    }

    // This method is called just after the handler using this
    // formatter is closed
    public String getTail(Handler h)
    {
        StringBuffer buffer = new StringBuffer();

        buffer.append("<TFOOT>\n");
        buffer.append("<TR BGCOLOR=\"#FFEEDD\"><TD COLSPAN=5>That's it</TD></TR>\n");
        buffer.append("</TFOOT>");
        buffer.append("</TABLE>\n");
        buffer.append("</PRE></BODY></HTML>\n");

        return buffer.toString();
    }

}
