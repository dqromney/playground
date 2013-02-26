package com.stgutah.playground.initializerBlocks;

/**
 * User HashMap
 * User: dqromney
 * Date: Jan 29, 2010
 * Time: 3:22:53 PM
 */
public class User extends java.util.HashMap<Long, String>
{
    {
        put(123L, "Joe Deusher");
        put(124L, "John Doe");
    }
}
