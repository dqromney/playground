package com.stgutah.playground.joda;

import net.objectlab.kit.datecalc.common.DateCalculator;
import net.objectlab.kit.datecalc.common.DefaultHolidayCalendar;
import net.objectlab.kit.datecalc.common.HolidayHandlerType;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import net.objectlab.kit.datecalc.joda.LocalDateKitCalculatorsFactory;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;
import java.util.*;

/**
 * Joda Business Day test.
 *
 * User: dqromney
 * Date: Jan 31, 2011
 * Time: 1:50:56 PM
 */
public class BizDayTest {

    private DateCalculator<LocalDate> dateCalculator;
    private Integer currentYear = Calendar.getInstance(Locale.getDefault()).get(Calendar.YEAR);
    private final LocalDate startDate = new LocalDate(2010, 12, 23);
    private HashSet<LocalDate> holidays;
    
    // ----------------------------------------------------------------
    // Object execution
    // ----------------------------------------------------------------
    private void execute() {
        int BUSINESS_DAYS = 8;
        DateTime dtEnd;
        DateTimeFormatter fmt = DateTimeFormat.forPattern("MM/dd/yyyy");
        DateTime dtStart = convertLocalDate(startDate);
        Date dateEnd;
        Calendar calStart = dtStart.toCalendar(Locale.getDefault());
        Calendar calEnd;

        BUSINESS_DAYS = 8;
        dateEnd = addBusinessDays(convertLocalDate(startDate).toDate(), BUSINESS_DAYS);
        System.out.println(fmt.print(dtStart) + " moved " + BUSINESS_DAYS + " business days to end date of " + fmt.print(dateEnd.getTime()));

        BUSINESS_DAYS = 8;
        calEnd = addBusinessDays(calStart, BUSINESS_DAYS);
        System.out.println(fmt.print(calStart.getTimeInMillis()) + " moved " + BUSINESS_DAYS + " business days to end date of " + fmt.print(calEnd.getTimeInMillis()));

        BUSINESS_DAYS = 3;
        dateEnd = addBusinessDays(convertLocalDate(startDate).toDate(), BUSINESS_DAYS);
        System.out.println(fmt.print(dtStart) + " moved " + BUSINESS_DAYS + " business days to end date of " + fmt.print(dateEnd.getTime()));

        BUSINESS_DAYS = 3;
        calEnd = addBusinessDays(calStart, BUSINESS_DAYS);
        System.out.println(fmt.print(calStart.getTimeInMillis()) + " moved " + BUSINESS_DAYS + " business days to end date of " + fmt.print(calEnd.getTimeInMillis()));

        BUSINESS_DAYS = 14;
        dateEnd = addBusinessDays(convertLocalDate(startDate).toDate(), BUSINESS_DAYS);
        System.out.println(fmt.print(dtStart) + " moved " + BUSINESS_DAYS + " business days to end date of " + fmt.print(dateEnd.getTime()));

        BUSINESS_DAYS = 14;
        calEnd = addBusinessDays(calStart, BUSINESS_DAYS);
        System.out.println(fmt.print(calStart.getTimeInMillis()) + " moved " + BUSINESS_DAYS + " business days to end date of " + fmt.print(calEnd.getTimeInMillis()));

        BUSINESS_DAYS = -5;
        dateEnd = addBusinessDays(convertLocalDate(startDate).toDate(), BUSINESS_DAYS);
        System.out.println(fmt.print(dtStart) + " moved back " + BUSINESS_DAYS + " business days to end date of " + fmt.print(dateEnd.getTime()));

        BUSINESS_DAYS = -5;
        calEnd = addBusinessDays(calStart, BUSINESS_DAYS);
        System.out.println(fmt.print(calStart.getTimeInMillis()) + " moved back " + BUSINESS_DAYS + " business days to end date of " + fmt.print(calEnd.getTimeInMillis()));

        System.out.println("\nHoliday list for 2010, 2011, and 2012");
        System.out.println("-------------------------------------");                                     
        for(LocalDate ld: holidays) {
            System.out.println(fmt.print(convertLocalDate(ld)));
        }
//        Holiday list for 2010, 2011, and 2012
//        -------------------------------------
//        Fri Jan 01 00:00:00 MST 2010
//        Mon May 31 00:00:00 MDT 2010
//        Mon Jul 05 00:00:00 MDT 2010
//        Mon Sep 06 00:00:00 MDT 2010
//        Thu Nov 25 00:00:00 MST 2010
//        Fri Dec 24 00:00:00 MST 2010
//
//        Fri Dec 31 00:00:00 MST 2010
//        Mon May 30 00:00:00 MDT 2011
//        Mon Jul 04 00:00:00 MDT 2011
//        Mon Sep 05 00:00:00 MDT 2011
//        Thu Nov 24 00:00:00 MST 2011
//        Mon Dec 26 00:00:00 MST 2011
//
//        Mon Jan 02 00:00:00 MST 2012
//        Mon May 28 00:00:00 MDT 2012
//        Wed Jul 04 00:00:00 MDT 2012
//        Mon Sep 03 00:00:00 MDT 2012
//        Thu Nov 22 00:00:00 MST 2012
//        Tue Dec 25 00:00:00 MST 2012

        Calendar nextBusinessDayCal = nextBusinessDay(calStart);
        System.out.println(fmt.print(calStart.getTimeInMillis()) + " to " + fmt.print(nextBusinessDayCal.getTimeInMillis()));
    }

    /**
     * Add number of Business Days to startDate.
     *
     * @param pStartDate       the Starting Date object
     * @param pNumBusinessDays the number of Business days to add, negative numbers allowed
     * @return a Date object
     */
    public Date addBusinessDays(Date pStartDate, Integer pNumBusinessDays) {
        dateCalculator.setStartDate(new LocalDate(pStartDate));
        return convertLocalDate(dateCalculator.moveByBusinessDays(pNumBusinessDays).getCurrentBusinessDate()).toDate();
    }

    /**
     * Add number of Business Days to Calendar startDate, using default Locale.
     *
     * @param pCalendar        the Starting Calendar object
     * @param pNumBusinessDays the number of Business days to add, negative numbers allowed
     * @return a Calendar object
     */
    public Calendar addBusinessDays(Calendar pCalendar, Integer pNumBusinessDays) {
        dateCalculator.setStartDate(LocalDate.fromDateFields(pCalendar.getTime()));
        return convertLocalDate(dateCalculator.moveByBusinessDays(pNumBusinessDays).getCurrentBusinessDate()).toCalendar(Locale.getDefault());
    }

    /**
     * Convert LocalDate object to Joda's DateTime object.
     *
     * @param pLocalDate the LocalDate object to convert
     * @return a converted DateTime object
     */
    private DateTime convertLocalDate(LocalDate pLocalDate) {
        return new DateTime(pLocalDate.getYear(), pLocalDate.getMonthOfYear(), pLocalDate.getDayOfMonth(), 0, 0, 0, 0);
    }

    /**
     * Get Holidays for a list of year(s).
     * <p/>
     * Defined Holiday's
     * ------------------------------------------
     * January 1	    New Year’s Day
     * May ?	        Memorial Day; calculated as last Monday of May
     * July 4	        Independence Day
     * September ?	    Labor Day; calculated as first Monday of September
     * November ?	    Thanksgiving Day; calculated as third Thursday of November
     * December 25	    Christmas Day
     *
     * @param pYearList the list of year(s) requested
     * @return a Set of LocalDate holidays
     */
    private HashSet<LocalDate> getHolidaysByYear(int... pYearList) {
        HashSet<LocalDate> holidayList = new HashSet<LocalDate>(0);
        for(int year : pYearList) {
            holidayList.add(calcForHolidayWeekend(new LocalDate(year, 1, 1)));
            holidayList.add(calcMemorialDayByYear(year));
            holidayList.add(calcForHolidayWeekend(new LocalDate(year, 7, 4)));
            holidayList.add(calcLaborDayByYear(year));
            holidayList.add(calcThanksgivingDayByYear(year));
            holidayList.add(calcForHolidayWeekend(new LocalDate(year, 12, 25)));
        }
        return holidayList;
    }

    /**
     * Calculate Holiday LocalDate if lands on a Saturday or Sunday.
     *
     * @param pLocalDate the LocalDate object
     * @return modified date if argument date is on Saturday or Sunday, otherwise unchanged. 
     */
    private LocalDate calcForHolidayWeekend(LocalDate pLocalDate) {
        if(pLocalDate.getDayOfWeek() == DateTimeConstants.SATURDAY) {
            pLocalDate = pLocalDate.minusDays(1); // Rollback to Friday
        }
        else if (pLocalDate.getDayOfWeek() == DateTimeConstants.SUNDAY) {
            pLocalDate = pLocalDate.plusDays(1); // Forward to Monday
        }
        return pLocalDate;
    }

    /**
     * Calculate Memorial Day by year; the last Monday of May.
     *
     * @param pYear the year to calculate
     * @return a LocalDate object for Memorial Day for the requested year
     */
    private LocalDate calcMemorialDayByYear(int pYear) {

        int runawayCount = 0;

        // Start with the last day of May
        LocalDate localDate = new LocalDate(pYear, 5, 31);

        // Go backwards until I find the last Monday of May
        while(true) {
            if (localDate.getDayOfWeek() == DateTimeConstants.MONDAY) {
                break;
            }
            else {
                // Go back one day; test again
                localDate = localDate.minusDays(1);
                runawayCount ++;
            }

            // Just in case emergency break;
            if(runawayCount > 8) {
                break;
            }
        }
        return localDate;
    }

    /**
     * Calculate Labor Day by year; the first Monday of September.
     *
     * @param pYear the year to calculate
     * @return a LocalDate object for Labor Day for the requested year
     */
    private LocalDate calcLaborDayByYear(int pYear) {
        int runawayCount = 0;
        // Start with the last day of May
        LocalDate localDate = new LocalDate(pYear, 9, 1);

        // Go backwards until I find the last Monday of May
        while(true) {
            if (localDate.getDayOfWeek() == DateTimeConstants.MONDAY) {
                break;
            }
            else {
                // Go back one day; test again
                localDate = localDate.plusDays(1);
                runawayCount ++;
            }
            // Just in case emergency break;
            if(runawayCount > 8) {
                break;
            }
        }
        return localDate;
    }

    /**
     * Calculate Thanksgiving Day by year; the fourth Thursday of November.
     *
     * @param pYear the year to calculate
     * @return a LocalDate object for Labor Day for the requested year
     */
    private LocalDate calcThanksgivingDayByYear(int pYear) {
        int runawayCount = 0;
        // Start with the last day of May
        LocalDate localDate = new LocalDate(pYear, 11, 1);

        // Go forwards until I find the first Thursday of November, the add 3 weeks.
        while(true) {
            if (localDate.getDayOfWeek() == DateTimeConstants.THURSDAY) {
                localDate = localDate.plusWeeks(3);
                break;
            }
            else {
                // Go forward one day; test again
                localDate = localDate.plusDays(1);
                runawayCount ++;
            }
            // Just in case emergency break;
            if(runawayCount > 31) {
                break;
            }
        }
        return localDate;
    }

    /**
     * Get next business day using Calendar startDate, using default Locale.
     *
     * @param pCalendar        the Starting Calendar object
     * @return a forwarded Business date using Starting Calendar object
     */
    public Calendar nextBusinessDay(Calendar pCalendar) {
        // Set start date
        dateCalculator.setStartDate(LocalDate.fromDateFields(pCalendar.getTime()));
        // Determine if StartDate is NOT a non-working day day, i.e. a working day
        if(!dateCalculator.isNonWorkingDay(LocalDate.fromDateFields(pCalendar.getTime()))) {
            // Move by one business day
            dateCalculator.moveByBusinessDays(1).getStartDate();
        }
        // else use current business date calculated automatically.
        return convertLocalDate(dateCalculator.getCurrentBusinessDate()).toCalendar(Locale.getDefault());

    }

    // ----------------------------------------------------------------
    // Initialize
    // ----------------------------------------------------------------
    private void init() {
        holidays = getHolidaysByYear(currentYear - 1, currentYear, currentYear + 1);

        DefaultHolidayCalendar<LocalDate> holidayCalendar = new DefaultHolidayCalendar<LocalDate>(holidays);

        LocalDateKitCalculatorsFactory.getDefaultInstance().registerHolidays("bizDayTest", holidayCalendar);

        dateCalculator = LocalDateKitCalculatorsFactory.getDefaultInstance().getDateCalculator("bizDayTest", HolidayHandlerType.FORWARD_UNLESS_MOVING_BACK);
    }

    // ----------------------------------------------------------------
    // Driver
    // ----------------------------------------------------------------
    public static void main(String[] args) throws IOException {
        BizDayTest main = new BizDayTest();
        main.init();
        main.execute();
    }

}
