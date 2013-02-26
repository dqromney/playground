package com.stgutah.playground.initializerBlocks;

/**
 * Initialize with static and non-static Initializer blocks 
 * User: dqromney
 * Date: Jan 29, 2010
 * Time: 10:10:12 AM
 * To change this template use File | Settings | File Templates.
 */
public class InitializationWithInitializerBlock
{
    public static int staticIntField = 100;
    private boolean instanceBoolField;

    // Initializer blocks for instance variables look just like static initializer blocks, but without the static keyword, and
    // you have access to instance variables like 'instanceBoolField', otherwise nope.
    {
        boolean y;
        
        y = true; // or, y = <some expression return a boolean value>;

        instanceBoolField = y;
    }

    // A class can have any number of static initialization blocks, and they can appear anywhere in the class body. The runtime
    // system guarantees that static initialization blocks are called in the order that they appear in the source code.
    static {
        staticIntField = 101;    
    }

    public boolean isInstanceBoolField()
    {
        return instanceBoolField;
    }

}
