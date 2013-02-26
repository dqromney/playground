package com.stgutah.playground.factoryPattern;

import java.util.logging.Logger;

/**
 * Main driver class.
 * <p/>
 * User: dqromney
 * Date: Sep 21, 2010
 * Time: 10:51:52 AM
 */
public class Main
{
    private static final java.util.logging.Logger LOGGER = Logger.getLogger(Main.class.toString());

    // TEST driver
    public static void main(String[] args) throws Exception
    {
        Main main = new Main();
        main.init();
        main.execute();
    }


    private void init()
    {
    }

    private void execute() throws Exception
    {
        Dog dog = DogFactory.getDog("small");
        dog.speak();
        dog = DogFactory.getDog("big");
        dog.speak();
        dog = DogFactory.getDog("working");
        dog.speak();
        dog = DogFactory.getDog("");
        if(dog != null) {
            dog.speak();
        }
    }
}
