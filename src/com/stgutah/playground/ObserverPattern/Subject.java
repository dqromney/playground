package com.stgutah.playground.ObserverPattern;

/**
 * Subject interface.
 *
 * User: dqromney
 * Date: Nov 29, 2010
 * Time: 5:14:28 PM
 */
public interface Subject {

    public void addObserver(Observer o);
    public void removeObserver(Observer o);
}
