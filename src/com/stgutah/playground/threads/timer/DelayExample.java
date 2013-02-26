package com.stgutah.playground.threads.timer;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: dqromney
 * Date: Apr 21, 2010
 * Time: 5:35:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class DelayExample
{
    public static void main(String[] args)
    {
        Date now;
        System.out.println("Hi");
        for(int i = 0; i < 10; i++)
        {
            System.out.println("Number of iteration = " + i);
            System.out.println("Wait:");
            try
            {
                now = new Date();
                System.out.println("now=["+now+"]");
                // 1 second = 1000 milliseconds
                Thread.sleep(1000);
                now = new Date();
                System.out.println("now=["+now+"]");

            } catch (InterruptedException ie)
            {
                System.out.println(ie.getMessage());
            }
        }
    }
}
