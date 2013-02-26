package com.stgutah.playground.factoryPattern;

/**
 * The Dog Factory class.
 *
 * User: dqromney
 * Date: Sep 21, 2010
 * Time: 10:49:16 AM
 */
public class DogFactory
{
    public static Dog getDog(String criteria)
    {
        if (criteria.equals("small"))
            return new Poodle();
        else if (criteria.equals("big"))
            return new Rottweiler();
        else if (criteria.equals("working"))
            return new SiberianHusky();
        else {
            System.err.println(Dog.UNRECOGNIZED_BARK);
        }

        return null;
    }

}
