package com.stgutah.playground.ObserverPattern;

import java.util.*;

/**
 * Integer adder implementation.
 * <p/>
 * User: dqromney
 * Date: Nov 29, 2010
 * Time: 5:20:59 PM
 */
public class IntegerAdder implements Observer {

    private IntegerDataBag bag;

    public IntegerAdder(IntegerDataBag bag) {
        this.bag = bag;
        bag.addObserver(this);
    }

    public void update(Subject o) {
        if (o == bag) {
            System.out.println("The contents of the IntegerDataBag have changed.");
            int counter = 0;
            Iterator i = bag.iterator();
            while (i.hasNext()) {
                Integer integer = (Integer) i.next();
                counter += integer.intValue();
            }
            System.out.println("The new sum of the integers is: " + counter);
        }
    }
}
