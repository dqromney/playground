package com.stgutah.playground.batchProcessing;

/**
 * Created by IntelliJ IDEA.
 * User: dqromney
 * Date: Apr 12, 2010
 * Time: 1:08:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class Worker extends Thread
{
    // Special end-of-stream marker. If a worker retrieves an
    // Integer that equals this marker, the worker will terminate. 
    static final Object NO_MORE_WORK = new Object();
    WorkQueue q;

    Worker(WorkQueue q)
    {
        this.q = q;
    }

    public void run()
    {
        try
        {
            while (true)
            {
                // Retrieve some work; block if the queue is empty
                Object x = q.getWork();
                // Terminate if the end-of-stream marker was retrieved
                if (x == NO_MORE_WORK)
                {
                    break;
                }
                // Compute the square of x
                int y = ((Long) x).intValue() * ((Long) x).intValue();
                System.out.println("Square of [" + x + "] = [" + y + "]");
            }
        } catch (InterruptedException e)
        {
        }
    }
}
