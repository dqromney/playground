package com.stgutah.playground.joda;

import org.joda.time.DateTime;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;


/**
 * Testing with JODA library.
 * <p/>
 * User: dqromney
 * Date: Jan 31, 2011
 * Time: 1:34:33 PM
 */
public class JodaTest {

    // ----------------------------------------------------------------
    // Object execution
    // ----------------------------------------------------------------

    private void execute() {
        DateTime dt = new DateTime();
        
        Calendar nextBusinessDay = nextBusinessDate(Calendar.getInstance());
        System.out.println("today=[" + dt.toDateTime() + "] nextBusinessDay=[" + nextBusinessDay.getTime() + "]");
    }

    // ----------------------------------------------------------------
    // Initialize
    // ----------------------------------------------------------------

    private void init() {

    }

    /**
     * An alternative way to calculate next Business Date.
     *
     * @param cal an instance of the Calender
     * @return the next business date based upon the parameter
     */
    public Calendar nextBusinessDate(Calendar cal) {

        List<Calendar> holidays = new ArrayList<Calendar>(0);
        // Here get list of holidays from DB or some other service...

        GregorianCalendar calCp = new GregorianCalendar();
        calCp.setTime(cal.getTime());
        calCp.add(Calendar.DAY_OF_MONTH, 1);

        boolean isSaturday = (calCp.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY);
        boolean isSunday = (calCp.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY);
        boolean isHoliday = holidays.contains(calCp);

        while (isSaturday || isSunday || isHoliday) {
            if (isSaturday) {
                calCp.add(Calendar.DAY_OF_MONTH, +2); // is saturday, make it monday
            } else {
                if (isSunday) {
                    calCp.add(Calendar.DAY_OF_MONTH, +1); // is sunday, make it monday
                } else {
                    if (isHoliday) {
                        calCp.add(Calendar.DAY_OF_MONTH, +1); // is holiday, make it next day
                    }
                }
            }
            calCp = new GregorianCalendar();
            calCp.setTime(cal.getTime());
            isSaturday = (calCp.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY);
            isSunday = (calCp.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY);
            isHoliday = holidays.contains(calCp);
        } // end while

        return calCp;
    }
    // ----------------------------------------------------------------
    // Driver
    // ----------------------------------------------------------------

    public static void main(String[] args) throws IOException {
        JodaTest main = new JodaTest();
        main.init();
        main.execute();
    }
}
