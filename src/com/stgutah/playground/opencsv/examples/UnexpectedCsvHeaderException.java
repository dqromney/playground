package com.stgutah.playground.opencsv.examples;

/**
 * Unexpected CSV Header exception.
 *
 * User: dqromney
 * Date: Oct 14, 2010
 * Time: 2:25:42 PM
 */
public class UnexpectedCsvHeaderException extends Exception {

    private static final long serialVersionUID = 1L;

    public UnexpectedCsvHeaderException() {
    }

    public UnexpectedCsvHeaderException(String message) {
        super(message);
    }

    public UnexpectedCsvHeaderException(String message, Throwable cause) {
        super(message, cause);
    }
}
