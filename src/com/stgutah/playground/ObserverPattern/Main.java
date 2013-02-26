package com.stgutah.playground.ObserverPattern;

import java.io.IOException;

/**
 * Observer pattern (also known as Listener, or Publish-Subscribe).
 * <p/>
 * User: dqromney
 * Date: Nov 29, 2010
 * Time: 4:49:05 PM
 */
public class Main {

    private Integer i1;
    private Integer i2;
    private Integer i3;
    private Integer i4;
    private Integer i5;
    private Integer i6;
    private Integer i7;
    private Integer i8;
    private Integer i9;

    private IntegerDataBag bag; // The bag here is the Scheduler Executive?

    private void execute() {
        IntegerAdder adder = new IntegerAdder(bag);
        IntegerPrinter printer = new IntegerPrinter(bag);
        // adder and printer add themselves to the bag
        System.out.println("About to add another integer to the bag:");
        bag.add(i9);
        System.out.println("");
        System.out.println("About to remove an integer from the bag:");
        bag.remove(0);
    }

    private void init() {
        i1 = new Integer(1);
        i2 = new Integer(2);
        i3 = new Integer(3);
        i4 = new Integer(4);
        i5 = new Integer(5);
        i6 = new Integer(6);
        i7 = new Integer(7);
        i8 = new Integer(8);
        i9 = new Integer(9);
        IntegerDataBag bag = new IntegerDataBag();
        bag.add(i1);
        bag.add(i2);
        bag.add(i3);
        bag.add(i4);
        bag.add(i5);
        bag.add(i6);
        bag.add(i7);
        bag.add(i8);
    }

    // Driver
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.init();
        main.execute();
    }
}
