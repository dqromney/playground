package com.stgutah.playground.reflection;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Some Type enum.
 *
 * User: dqromney
 * Date: 6/7/12
 * Time: 4:27 PM
 */
public enum SomeType {
    SIMPLE("Simple Method"),
    ONE_ARG("One Argument Method", "Hello"),
    TWO_ARG("Two Argument Method", "Hello", "World");

    String name;
    List<Object> varList;

    private SomeType(String pName) {
        this.name = pName;
    }

    private SomeType(String pName, Object... vars) {
        this.name = pName;
        varList = new ArrayList<Object>(0);
        for(Object obj : vars) {
            varList.add(obj);
        }
    }

    public String getName() {
        return name;
    }

    public List<Object> getVarList() {
        return varList;
    }
}
