package com.stgutah.playground.performance;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * Consecutive Range Utility.
 * <p/>
 * Note(s):
 * This utility is helpful when data order of identifiers is mostly consecutive. Rather than
 * using the SQL "IN" operator, this utility is useful to generate a list of identifier ranges,
 * i.e. {{2003,3450}, {3456, 5032}, {5034, 20001}}. These ranges can be easily added to a SQL
 * query, using the "between" operator or the like, that will improve proformance over using
 * the "IN" operator (limited to 1000 elements).
 * <p/>
 * I didn't end up using this code, but thought it might be useful for others.
 * <p/>
 * <p/>
 * User: dqromney
 * Date: Aug 13, 2010
 * Time: 10:31:00 AM
 */
public class ConsecutiveRangeUtil
{
    private static final java.util.logging.Logger LOGGER = Logger.getLogger(ConsecutiveRangeUtil.class.toString());

    public ConsecutiveRangeUtil() {}

    /**
     * Create a consecutive range list based upon a list of Long objects provided.
     *
     * @param pList         a list of database table identifiers, i.e. keys
     * @param sortAscending set to true if identifiers are unsorted
     * @return a list of ConsecutiveRange objects
     */
    private List<ConsecutiveRange> getConsecutiveRangeList(List<Long> pList, boolean sortAscending)
    {
        ConsecutiveRange consecutiveRange = new ConsecutiveRange();
        List<ConsecutiveRange> consecutiveRangeList = new ArrayList<ConsecutiveRange>(0);
        
        // Optional sort of identifiers; some callers may not have in sorted order.
        if (sortAscending)
        {
            Collections.sort(pList);
        }

        for(Long identifier : pList)
        {
            // Determine if a new consecutive range was created; initially begin range is -1L.
            if (consecutiveRange.getBeginRange() < 0L)
            {
                // set consecutiveRange.beginRange and endRange value to identifier
                consecutiveRange.setBeginRange(identifier);
                consecutiveRange.setEndRange(identifier);
                LOGGER.fine(String.format("-- Setting new ConsecutiveRange -> both begin and end range: Begin=[%1$d] End=[%2$d]",
                        consecutiveRange.getBeginRange(), consecutiveRange.getEndRange()));
            }
            // otherwise
            else
            {
                // Determine if identifier equal to (consecutiveRange.endRange + 1); is consecutive?
                if (identifier.compareTo(consecutiveRange.getEndRange() + 1L) == 0)
                {
                    // set consecutiveRange.endRange to identifier; set new end point
                    consecutiveRange.setEndRange(identifier);
                    LOGGER.fine(String.format("-- Setting end range to [%1$d]", consecutiveRange.getEndRange()));
                }
                // otherwise
                else
                {
                    // Determine if consecutiveRange.endRange is greater zero
                    if (consecutiveRange.getEndRange() < 0)
                    {
                        // Only one identifier in the range; not too efficient; set the endRange to beginRange
                        consecutiveRange.setEndRange(consecutiveRange.getBeginRange());
                        LOGGER.fine(String.format("**** Only one identifier in the range; not too efficient ****"));
                    }
                    // Add consecutiveRange to consecutiveRangeList
                    consecutiveRangeList.add(consecutiveRange);
                    LOGGER.fine(String.format("-- Adding consecutiveRange to list: : Begin=[%1$d] End=[%2$d]",
                            consecutiveRange.getBeginRange(), consecutiveRange.getEndRange()));
                    // create new ConsecutiveRange object and set beginRange and endRange value to identifier
                    consecutiveRange = new ConsecutiveRange(identifier, identifier);
                    LOGGER.fine(String.format("-- Creating new consecutiveRange to list: : Begin=[%1$d] End=[%2$d]",
                            consecutiveRange.getBeginRange(), consecutiveRange.getEndRange()));
                }
            }
        } // End of for loop

        // Check if object has been modified
        if (consecutiveRange.getBeginRange() > 0L)
        {
            // Add the last consecutiveRange to consecutiveRangeList
            consecutiveRangeList.add(consecutiveRange);
            LOGGER.fine(String.format("-- Adding LAST consecutiveRange to list: : Begin=[%1$d] End=[%2$d]",
                    consecutiveRange.getBeginRange(), consecutiveRange.getEndRange()));
        }

        // DEBUG code
        for(ConsecutiveRange item : consecutiveRangeList)
        {
            LOGGER.fine(String.format("-- [%s]", item.toString()));
        }

        return consecutiveRangeList;
    }


    // TEST driver
    public static void main(String[] args) throws IOException
    {
        ConsecutiveRangeUtil main = new ConsecutiveRangeUtil();
        main.init();
        main.execute();
    }

    private void execute()
    {
        List<ConsecutiveRange> crNoSort = getConsecutiveRangeList(getTestSortedList(), false);
        System.out.println("-- crNoSort list...");
        for(ConsecutiveRange cr : crNoSort)
        {
            System.out.printf("-- begin=[%1$d] end=[%2$d]\n", cr.getBeginRange(), cr.getEndRange());
        }

        List<ConsecutiveRange> crSorted = getConsecutiveRangeList(getTestUnSortedList(), true);
        System.out.println("-- crSorted list...");
        for(ConsecutiveRange cr : crSorted)
        {
            System.out.printf("-- begin=[%1$d] end=[%2$d]\n", cr.getBeginRange(), cr.getEndRange());
        }
    }

    private void init() {}

    private List<Long> getTestSortedList()
    {
        List<Long> list = new ArrayList<Long>(0);

        list.addAll(createConsecutiveSequence(1L, 5L));
        list.addAll(createConsecutiveSequence(12L, 33L));
        list.addAll(createConsecutiveSequence(35, 41));
        list.addAll(createConsecutiveSequence(61L, 79L));
        list.addAll(createConsecutiveSequence(83L, 97L));

        System.out.println(String.format("-- Sorted List: [%s]", StringUtils.join(list, ',')));
        return list;
    }

    private List<Long> getTestUnSortedList()
    {
        List<Long> list = new ArrayList<Long>(0);

        list.addAll(createConsecutiveSequence(35, 41));
        list.addAll(createConsecutiveSequence(83L, 97L));
        list.addAll(createConsecutiveSequence(12L, 33L));
        list.addAll(createConsecutiveSequence(61L, 79L));
        list.addAll(createConsecutiveSequence(1L, 5L));

        System.out.println(String.format("-- Unsorted List: [%s]", StringUtils.join(list, ',')));
        return list;
    }

    private List<Long> createConsecutiveSequence(long pBegin, long pEnd)
    {
        List<Long> list = new ArrayList<Long>(0);

        for(Long identifier  = pBegin; identifier <= pEnd; identifier ++)
        {
            list.add(identifier);
        }
        return list;
    }
}
