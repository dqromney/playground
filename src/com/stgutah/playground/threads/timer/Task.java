package com.stgutah.playground.threads.timer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

/**
 * This is a timertask because it extends the class java.util.TimerTask. This class
 * will be given to the timer (java.util.Timer) as the code to be executed.
 *
 * User: dqromney
 * Date: Apr 21, 2010
 * Time: 4:51:58 PM
 *
 * @see java.util.Timer
 * @see java.util.TimerTask
 */
public class Task extends TimerTask 
{
    private String _objectName;                 // A string to output

    /**
     * Constructs the object, sets the string to be output in function run()
     *
     * @param objectName the ObjectName string
     */
    Task(String objectName)
    {
        this._objectName = objectName;
    }

    /**
     * When the timer executes, this code is run.
     */
    public void run()
    {
        // Get current date/time and format it for output
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd.mm.yyyy hh:mm:ss");
        String current_time = format.format(date);

        // Output to user the name of the object and the current time
        System.out.println(_objectName + " - Current time: " + current_time);
    }
}
