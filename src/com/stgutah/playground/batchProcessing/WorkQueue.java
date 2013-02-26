package com.stgutah.playground.batchProcessing;

import java.util.LinkedList;

/**
 * A work queue is used to coordinate work between a producer  and a set of worker threads. When some work needs to be performed,
 * the producer adds an object containing the work information to the work queue. One of the worker threads then removes the object
 * from the work queue and acts upon the information. If the queue is empty, a worker thread will block until a new object is added
 * to the queue.
 *
 * User: dqromney
 * Date: Apr 12, 2010
 * Time: 1:09:26 PM
 */
public class WorkQueue
{
    LinkedList queue = new LinkedList();

    // Add work to work queue
    public synchronized void addWork(Object o)
    {
        queue.addLast(o);
        notify();
    }

    // Retrieve work from the work queue; block if the queue is empty
    public synchronized Object getWork() throws InterruptedException
    {
        while (queue.isEmpty())
        {
            wait();
        }
        return queue.removeFirst();
    }
}
