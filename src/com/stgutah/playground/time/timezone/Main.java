package com.stgutah.playground.time.timezone;

import java.util.Date;
import java.util.TimeZone;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by IntelliJ IDEA.
 * User: dqromney
 * Date: Feb 26, 2009
 * Time: 12:19:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    private Date now;
    private Locale[] locales;
    private TimeZone timeZone;
    private Calendar cal;


    public static void main (String[] args) {
        Main main = new Main();
        main.init();
        main.execute();
    }

    private void init() {
        now = new Date();
        locales = Calendar.getAvailableLocales();
        timeZone = TimeZone.getDefault();
        cal = Calendar.getInstance(timeZone);
    }

    private void execute() {
        StringBuffer sb = new StringBuffer();
        sb.append("Now: [").append(now.toString()).append("]\n");
        sb.append("TimeZone: [").append(timeZone.getDisplayName()).append("]\n");
        sb.append("Calendar: [").append(cal.getTime().toString()).append("] ");
        sb.append("Locales: [");
        for (Locale locale: Locale.getAvailableLocales()) {
            for (String zone: TimeZone.getAvailableIDs()) {
                if (locale.getDisplayCountry().equalsIgnoreCase(zone)) {
                    sb.append("\r[").append(cal.getTime().toString()).append(" : ").append(zone).append(" ] ");
                }
            }
        }
        sb.append("\n]");

        System.out.println(sb.toString());
    }

}
