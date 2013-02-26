package com.stgutah.playground.builderPattern;

/**
 * Non Enclosing Chef
 * User: dqromney
 * Date: Jan 29, 2010
 * Time: 5:43:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class NonEnclosingChef implements Chef
{
    private Refrigerator m_ChefsFridge = new Refrigerator();

    public NonEnclosingChef()
    {
    }

    public NonEnclosingChef(Refrigerator fridge)
    {
        while (!fridge.inventory.isEmpty()) m_ChefsFridge.inventory.add(fridge.inventory.remove(0));
    }

    public Meal buildMeal()
    {
        ChefConstructedMeal meal = new ChefConstructedMeal();
        //rules for building entree
        if (m_ChefsFridge.inventory.contains("steak")) {
            meal.courses.add("steak");
            m_ChefsFridge.inventory.remove("steak");
        } else if (m_ChefsFridge.inventory.contains("fish")) {
            meal.courses.add("fish");
            m_ChefsFridge.inventory.remove("fish");
        }
        //rules for building side
        if (m_ChefsFridge.inventory.contains("potato")) {
            meal.courses.add("potato");
            m_ChefsFridge.inventory.remove("potato");
        } else if (m_ChefsFridge.inventory.contains("asparagus")) {
            meal.courses.add("asparagus");
            m_ChefsFridge.inventory.remove("asparagus");
        }
        //rules for building dessert
        if (m_ChefsFridge.inventory.contains("cobbler")) {
            meal.courses.add("cobbler");
            m_ChefsFridge.inventory.remove("cobbler");
        } else if (m_ChefsFridge.inventory.contains("cake")) {
            meal.courses.add("cake");
            m_ChefsFridge.inventory.remove("cake");
        }
        return meal;
    }
}
