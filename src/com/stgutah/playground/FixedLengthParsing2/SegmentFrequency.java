package com.stgutah.playground.FixedLengthParsing2;

/**
 * Segment Frequency.
 *
 * User: dqromney
 * Date: 6/19/12
 * Time: 11:15 AM
 */
public enum SegmentFrequency {
    ZERO("Zero", 0),
    ONCE("Once", 1),
    UNLIMITED("Unlimited", -1);

    private String name;
    private Integer numberOfTimes;

    private SegmentFrequency(String pName, Integer pNumberOfTimes) {
        this.name = pName;
        this.numberOfTimes = pNumberOfTimes;
    }
}
