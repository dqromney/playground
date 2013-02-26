package com.stgutah.playground.builderPattern;

import com.stgutah.playground.builderPattern.*;

/**
 * Created by IntelliJ IDEA.
 * User: dqromney
 * Date: Jan 29, 2010
 * Time: 6:05:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class Main
{
    SelfComposingMeal selfComposingMeal;
    Refrigerator refridgerator;
    NonEnclosingChef nonEnclosingChef;
    EnclosingChef enclosingChef;
    ChefConstructedMeal chefConstructedMeal;

    public static void main(String[] args) {
        Main main = new Main();
        main.init();
        main.execute();
    }

    public void init() {
        refridgerator = new Refrigerator();
        selfComposingMeal = null;
        nonEnclosingChef = new com.stgutah.playground.builderPattern.NonEnclosingChef();
        enclosingChef = new com.stgutah.playground.builderPattern.EnclosingChef(refridgerator);
        chefConstructedMeal = new com.stgutah.playground.builderPattern.ChefConstructedMeal();
    }

    public void execute() {
        // Initialize inventory
        refridgerator.inventory.add("steak");
        refridgerator.inventory.add("fish");
        refridgerator.inventory.add("potato");
        refridgerator.inventory.add("cobbler");
        refridgerator.inventory.add("cake");

        System.out.println("Items in the Refridgerator ...");
        for (String item : refridgerator.inventory) {
            System.out.println("item=[" + item + "]");
        }

        selfComposingMeal = new SelfComposingMeal(refridgerator);
        System.out.println("selfComposingMeal.nextCourse()=[" + selfComposingMeal.nextCourse() + "]");
        System.out.println("selfComposingMeal.nextCourse()=[" + selfComposingMeal.nextCourse() + "]");
        System.out.println("selfComposingMeal.nextCourse()=[" + selfComposingMeal.nextCourse() + "]");
        nonEnclosingChef.buildMeal();
        System.out.println("chefConstructedMeal.nextCourse()=[" + chefConstructedMeal.nextCourse() + "]");
        enclosingChef.buildMeal();
        System.out.println("chefConstructedMeal.nextCourse()=[" + chefConstructedMeal.nextCourse() + "]");
        System.out.println("Course(s): " + org.apache.commons.lang.StringUtils.join(chefConstructedMeal.courses, ','));
    }
}
