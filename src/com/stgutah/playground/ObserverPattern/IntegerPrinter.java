package com.stgutah.playground.ObserverPattern;

import java.util.Iterator;


/**
 * Integer printer implementation.
 * <p/>
 * User: dqromney
 * Date: Nov 29, 2010
 * Time: 5:23:18 PM
 */
public class IntegerPrinter implements Observer {
    private IntegerDataBag bag;

    public IntegerPrinter(IntegerDataBag bag) {
        this.bag = bag;
        bag.addObserver(this);
    }

    public void update(Subject o) {
        if (o == bag) {
            System.out.println("The contents of the IntegerDataBag have changed.");
            System.out.println("The new contents of the IntegerDataBag contains:");
            Iterator i = bag.iterator();
            while (i.hasNext()) {
                System.out.println(i.next());
            }
        }
    }
}
