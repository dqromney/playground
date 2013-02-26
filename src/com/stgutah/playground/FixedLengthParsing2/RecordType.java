package com.stgutah.playground.FixedLengthParsing2;

/**
 * OneWest Request Record Types.
 *
 * User: dqromney
 * Date: Dec 23, 2010
 * Time: 5:57:36 PM
 */
public enum RecordType {
    UNDEFINED("UNDEFINED"),
    HEADER("HDR"),
    DETAIL("INS"),
    TRAILER("TRL");

    private String value;

    RecordType(String pValue) {
        this.value = pValue;
    }

    public String getValue() {
        return value;
    }
}
