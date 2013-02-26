package com.stgutah.playground.initializerBlocks;

import java.util.Arrays;
import org.apache.commons.lang.StringUtils;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: dqromney
 * Date: Jan 29, 2010
 * Time: 2:37:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class NonStaticInitializerBlock
{
    List<Integer> valueList = new java.util.ArrayList<Integer>(0);

    // Initialization block
    {
        System.out.println("In Non-Static initialization block\n");
        for(int i = 0; i < 10; i++) {
            valueList.add((int) (100.0 * Math.random()));
        }
    }

    void Display()
    {
        System.out.println("Numbers: [" + StringUtils.join(valueList,',') + "]");
    }
}
