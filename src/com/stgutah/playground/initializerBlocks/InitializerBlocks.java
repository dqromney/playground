package com.stgutah.playground.initializerBlocks;

import java.util.Set;
import java.util.Map.*;

/**
 * Initializer Blocks example.
 * <p/>
 * Description: A class can have any number of static initialization blocks, and they can appear anywhere in the class body. The
 * runtime system guarantees that static initialization blocks are called in the order that they appear in the source code.
 * <p/>
 *
 * User: dqromney
 * Date: Jan 29, 2010
 * Time: 10:11:58 AM
 */
public class InitializerBlocks
{
    int x = 0;

    public InitializerBlocks()
    {
        x = 3;
        System.out.println(x);
    }

    // Compiler executes Initializer blocks in the same order as they appear in the code,
    // then the constructor.

    // Initializer blocks for instance variables look just like static initializer blocks, but without the static keyword
    // Initializer Block - 1
    {
        x = 1;
        System.out.println(x);
    }

    // Initializer Block - 2
    {
        x = 2;
        System.out.println(x);
    }

    public static void main(String[] args)
    {
        InitializerBlocks ib = new InitializerBlocks();
        ib.x++;
        System.out.println(ib.x);

        InitializationWithInitializerBlock iwib = new InitializationWithInitializerBlock();
        System.out.println("InitializationWithInitializerBlock.staticIntField=[" + InitializationWithInitializerBlock.staticIntField + "]");
        System.out.println("iwib.isInstanceBoolField()=[" + iwib.isInstanceBoolField() + "]");

        InitializationWithPrivateInstanceMethod iwpim = new com.stgutah.playground.initializerBlocks.InitializationWithPrivateInstanceMethod("PRD");
        System.out.println("InitializationWithPrivateInstanceMethod.staticIntField=[" + InitializationWithPrivateInstanceMethod.staticIntField + "]");
        System.out.println("iwpim.isInstanceBoolField()=[" + iwpim.isInstanceBoolField() + "]");
        System.out.println("iwpim.getEnvironment()=[" + iwpim.getEnvironment() + "]");

        // Display's should read 
        NonStaticInitializerBlock nsib1 = new com.stgutah.playground.initializerBlocks.NonStaticInitializerBlock();
        nsib1.Display();
        NonStaticInitializerBlock nsib2 = new com.stgutah.playground.initializerBlocks.NonStaticInitializerBlock();
        nsib2.Display();

        StaticInitializerBlock sib1 = new com.stgutah.playground.initializerBlocks.StaticInitializerBlock();
        sib1.Display();
        StaticInitializerBlock sib2 = new com.stgutah.playground.initializerBlocks.StaticInitializerBlock();
        sib2.Display();

        // How about this with HashMap
        java.util.HashMap<String, String> map = new java.util.HashMap<String, String>() {
            {
                put("item1", "This is item1");
                put("item2", "This is item2");
            }
        };
        for(Entry entry : map.entrySet()) {
            System.out.println("HashMap Key=[" + entry.getKey() + "] Value[" + entry.getValue() + "]");
        }

        User user = new User();
        for(Entry entry : user.entrySet()) {
            System.out.println("User HashMap Key=[" + entry.getKey() + "] Value[" + entry.getValue() + "]");
        }

    }
}
