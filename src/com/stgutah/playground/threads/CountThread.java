package com.stgutah.playground.threads;

/**
 * Created by IntelliJ IDEA.
 * User: dqromney
 * Date: May 21, 2010
 * Time: 8:37:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class CountThread extends Thread
{
    private int count = 5; // number of times to print message

    public void run()
    {
        while (true)

        {
            if (count-- > 0)
            {
                System.out.println("thread " + this + " count=" + count);
                try
                {

                    Thread.sleep(1000L);
                } catch (Exception e)
                {
                } // sleep 1 second
            }
            else
            {
                break; // out of the ‘while’ loop
            }
        }
    }

    public static void main(String[] args) throws Exception
    {
        new CountThread().start();
        Thread.sleep(2000); // sleep 2 seconds
        new CountThread().start();
    }
}

