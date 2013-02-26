package com.stgutah.playground.performance;

/**
 * Utility class to hold the begin and end identifier ranges.
 *
 * User: dqromney
 * Date: Aug 13, 2010
 * Time: 10:44:28 AM
 */
public class ConsecutiveRange
{
    private Long beginRange;
    private Long endRange;

    ConsecutiveRange()
    {
        beginRange = -1L;
        endRange = -1L;
    }

    ConsecutiveRange(Long pBeginRange, Long pEndRange)
    {
        this.beginRange = pBeginRange;
        this.endRange = pEndRange;
    }

    // Accessors

    public Long getBeginRange()
    {
        return beginRange;
    }

    public void setBeginRange(Long beginRange)
    {
        this.beginRange = beginRange;
    }

    public Long getEndRange()
    {
        return endRange;
    }

    public void setEndRange(Long endRange)
    {
        this.endRange = endRange;
    }

    @Override
    public String toString()
    {
        return "ConsecutiveRange{" +
                "beginRange=" + beginRange +
                ", endRange=" + endRange +
                '}';
    }
}
