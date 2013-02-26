package com.stgutah.playground.builderPattern;

/**
 * Chef Constructed Meal
 * User: dqromney
 * Date: Jan 29, 2010
 * Time: 5:40:37 PM
 */
public class ChefConstructedMeal implements Meal
{
    public java.util.ArrayList<String> courses = new java.util.ArrayList<String>();

    public ChefConstructedMeal()
    {
    }

    public String nextCourse()
    {
        if (!courses.isEmpty()) return courses.remove(0);
        return null;
    }

}
