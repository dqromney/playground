package com.stgutah.playground.randomNumbers;

import java.util.Calendar;
import java.util.Random;

/**
 * Random Number test.
 *
 * User: dqromney
 * Date: Feb 2, 2011
 * Time: 1:46:47 PM
 */
public class Main {

    private Random random = new Random(Calendar.getInstance().getTimeInMillis());

    private void execute() {
        int number = random.nextInt(100);
        System.out.println(String.format("Random Number: [%1$s]", number));
    }

    private void init() {
        random = new Random(Calendar.getInstance().getTimeInMillis());
    }

    public static void main(String[] args) throws Exception
    {
        Main main = new Main();
        main.init();
        main.execute();
    }
}
