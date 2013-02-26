package com.stgutah.playground.builderPattern;

/**
 * Self Composing Meal
 * User: dqromney
 * Date: Jan 29, 2010
 * Time: 5:34:04 PM
 * @see http://www.javaworld.com/javaworld/jw-04-2007/jw-04-nested-classes.html
 */
public class SelfComposingMeal implements Meal
{
    private java.util.ArrayList<String> courses = new java.util.ArrayList<String>();

    public SelfComposingMeal(Refrigerator fridge)
    {
        //rules for selecting entree
        if (fridge.inventory.contains("steak")) {
            courses.add("steak");
            fridge.inventory.remove("steak");
        } else if (fridge.inventory.contains("fish")) {
            courses.add("fish");
            fridge.inventory.remove("fish");
        }
        //rules for selecting side
        if (fridge.inventory.contains("potato")) {
            courses.add("potato");
            fridge.inventory.remove("potato");
        } else if (fridge.inventory.contains("asparagus")) {
            courses.add("asparagus");
            fridge.inventory.remove("asparagus");
        }
        //rules for selecting dessert
        if (fridge.inventory.contains("cobbler")) {
            courses.add("cobbler");
            fridge.inventory.remove("cobbler");
        } else if (fridge.inventory.contains("cake")) {
            courses.add("cake");
            fridge.inventory.remove("cake");
        }
    }

    public String nextCourse()
    {
        if (!courses.isEmpty()) return courses.remove(0);
        return null;
    }
}
