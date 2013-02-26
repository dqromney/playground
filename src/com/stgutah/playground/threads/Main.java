package com.stgutah.playground.threads;

import java.text.MessageFormat;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

/**
 * Using Regular Expressions Example.
 * <p/>
 * Note(s):
 * The Java standard regular expression library supports the following metacharacters: ([{\^$|)?*+.
 * User: dqromney
 * Date: May 21, 2010
 * Time: 3:22:55 PM
 */
public class Main
{
    Logger log = Logger.getLogger(Main.class.getName());

    public void execute()
    {
        MessageTask messageTask = new MessageTask("Init");

        //log.info("execute() :: Enter");

        System.out.println("Create two instances of TimerDemo...");
        // run task 1 time after waiting 2 seconds:
        messageTask.TimerDemo("Run Only Once", 2);
        // wait 5 seconds, then run task once every 1 second:
        messageTask.TimerDemo("Execute Job 5 times", 5, 1);
        System.out.println("Threads started: wait for completion");

        //log.info("execute() :: Exit");
    }

    public void init()
    {
        //log.info("init() :: Enter");

        //log.info("init() :: Exit");
    }

    /**
     * Main driver.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Main main = new Main();
        main.init();
        main.execute();
    }

}
// Inner class

class MessageTask extends TimerTask
{
    private int max_prints = 3;
    private int count = 0;
    Timer timer;
    String job;

    MessageTask(String pJob)
    {
        this.job = pJob;
    }

    public void run()
    {

        // This is where the work goes
        System.out.println("TimerTask [" + job + "] " + this + ", count=" + count);
        if (count++ > max_prints)
        {
            // Cancel Task
            this.cancel();
            // User this to stop thread without causing a problem.
            System.out.println(MessageFormat.format("Stopping current thread [{0}]", Thread.currentThread().getName()));
            Thread.currentThread().stop();
            // Causes a NullPointerException; timer is null
            // timer.cancel();
        }
    }

    /**
     * Run only once, after n seconds is up.
     *
     * @param pSeconds the number of seconds to wait
     */
    public void TimerDemo(String pJob, int pSeconds)
    {
        timer = new Timer(pJob, true);
        // run task just one time:
        timer.schedule(new MessageTask(pJob), pSeconds * 1000);
    }

    /**
     * Run multiple times, waiting n wait_seconds, and executing every n seconds.
     *
     * @param pWaitSeconds the number of seconds to wait
     * @param pSeconds     execute every n seconds
     */
    public void TimerDemo(String pJob, int pWaitSeconds, int pSeconds)
    {
        timer = new Timer(pJob);
        // run the task repetitively:
        timer.schedule(new MessageTask(pJob), pWaitSeconds * 1000, pSeconds * 1000);
    }

}
