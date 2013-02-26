package com.stgutah.playground.builderPattern;

/**
 * Enaclosing Chef
 * User: dqromney
 * Date: Jan 29, 2010
 * Time: 5:45:44 PM
 */
public class EnclosingChef implements Chef
{
    private Refrigerator m_ChefsFridge = new Refrigerator();

    public EnclosingChef()
    {
    }

    public EnclosingChef(Refrigerator fridge)
    {
        while (!fridge.inventory.isEmpty()) m_ChefsFridge.inventory.add(fridge.inventory.remove(0));
    }

    public Meal buildMeal()
    {
        return this.new NestedMeal();
    }

    public class NestedMeal implements Meal
    {
        private java.util.ArrayList<String> courses = new java.util.ArrayList<String>();

        public NestedMeal()
        {
            //rules for building entree
            if (m_ChefsFridge.inventory.contains("steak")) {
                courses.add("steak");
                m_ChefsFridge.inventory.remove("steak");
            } else if (m_ChefsFridge.inventory.contains("fish")) {
                courses.add("fish");
                m_ChefsFridge.inventory.remove("fish");
            }
            //rules for building side
            if (m_ChefsFridge.inventory.contains("potato")) {
                courses.add("potato");
                m_ChefsFridge.inventory.remove("potato");
            } else if (m_ChefsFridge.inventory.contains("asparagus")) {
                courses.add("asparagus");
                m_ChefsFridge.inventory.remove("asparagus");
            }
            //rules for building dessert
            if (m_ChefsFridge.inventory.contains("cobbler")) {
                courses.add("cobbler");
                m_ChefsFridge.inventory.remove("cobbler");
            } else if (m_ChefsFridge.inventory.contains("cake")) {
                courses.add("cake");
                m_ChefsFridge.inventory.remove("cake");
            }
        }

        public String nextCourse()
        {
            if (!courses.isEmpty()) return courses.remove(0);
            return null;
        }
    }
}
