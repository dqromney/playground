package com.stgutah.playground.threads.timer;

import java.util.Timer;

/**
 * Created by IntelliJ IDEA.
 * User: dqromney
 * Date: Apr 21, 2010
 * Time: 4:50:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class Main
{
    public static void main(String[] args)
    {
        Timer timer1 = new Timer();             // Get timer 1
        Timer timer2 = new Timer();             // get timer 2

        long delay1 = 5 * 1000;                   // 5 seconds delay
        long delay2 = 3 * 1000;                   // 3 seconds delay

        // Schedule the two timers to run with different delays.
        timer1.scheduleAtFixedRate(new Task("object1"), 0L, delay1);
        timer2.scheduleAtFixedRate(new Task("Object2"), 0L, delay2);
//        timer1.schedule(new Task("object1"), delay1);
//        timer2.schedule(new Task("Object2"), delay2);
    }
}
