package com.stgutah.playground.sftp;

/**
 * File Transfer Exception.
 * <p/>
 * User: dqromney
 * Date: Oct 18, 2010
 * Time: 5:29:35 PM
 */
public class FileTransferException extends Exception {

    //private static final long serialVersionUID=-5616888495583253811L;

    public int id;
    private Throwable cause = null;

    public FileTransferException(int pId, String pMessage) {
        super(pMessage);
        this.id = pId;
    }

    public FileTransferException(int pId, String pMessage, Throwable pThrowable) {
        super(pMessage, pThrowable);
        this.id = pId;
        this.cause = pThrowable;
    }

    public FileTransferException(Throwable ex) {
        super(ex);
    }

    public String toString() {
        return id + ": " + getMessage();
    }

    public Throwable getCause() {
        return this.cause;
    }

}
