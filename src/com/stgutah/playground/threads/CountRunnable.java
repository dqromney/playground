package com.stgutah.playground.threads;

/**
 * Created by IntelliJ IDEA.
 * User: dqromney
 * Date: May 21, 2010
 * Time: 8:43:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class CountRunnable implements Runnable
{
    public void run()
    {
        for(int i = 0; i < 5; i++)
        {
            System.out.println("thread " + this + " i = " + i);
            try
            {
                Thread.sleep(200L);
            } catch (Exception e)
            {
            }
        }
    }

    public static void main(String[] args)
    {
        new Thread(new CountRunnable()).start();
        for(int k = 0; k < 5; k++)
        {
            System.out.println("thread main k=" + k);
            try
            {
                Thread.sleep(100L);
            } catch (Exception e)
            {
            }
        }
    }
}
