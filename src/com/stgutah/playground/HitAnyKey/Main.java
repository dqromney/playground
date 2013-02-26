package com.stgutah.playground.HitAnyKey;

import java.util.logging.Logger;
import java.io.IOException;

/**
 * Press any key utility.
 *
 * User: dqromney
 * Date: Apr 21, 2010
 * Time: 3:56:18 PM
 */
public class Main
{

    Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Main main = new Main();
        main.init();
        main.execute();
    }

    public void init() {
    }

    public void execute() {
        StringBuffer sb = new StringBuffer().append("\n").
                append("********************************************************\n").
                append("* Invoke Java Remote DEBUG listening to port 8000, and *\n").
                append("* press the Enter key...                               *\n").
                append("********************************************************\n");

        if (pressEnterToContinue(sb.toString())) {
            log.info("Enter was pressed");
        }
    }


    public boolean pressEnterToContinue(String prompt)
    {
        boolean enterIsPressed = false;
        System.out.println(prompt);

        try
        {
            System.in.read();
            enterIsPressed = true;
        }
        catch (IOException e)
        {
            System.out.println("Program error.");
            System.exit(1);
        }
        return enterIsPressed;
    }

}
