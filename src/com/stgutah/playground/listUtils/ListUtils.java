package com.stgutah.playground.listUtils;

import org.joda.time.DateTime;

import java.io.IOException;
import java.util.*;

/**
 * Some List Utilities.
 *
 * User: dqromney
 * Date: Apr 16, 2012
 * Time: 2:24:51 PM
 */
public class ListUtils {

    private List<String> aStringList;
    private List<String> anNullStringList;

    // ----------------------------------------------------------------
    // Object execution
    // ----------------------------------------------------------------
    private void execute() {
        // Enhanced For Loop
        System.out.println("List of aStringList (BEGIN)...");
        for(String item: aStringList) {
            System.out.println(item);
        }
        System.out.println("List of aStringList (END)...");

        System.out.println("List of anNullStringList (BEGIN)...");
        for(String item: safe(anNullStringList)) {
            System.out.println(item);
        }
        System.out.println("List of aStringList (END)...");
    }

    // ----------------------------------------------------------------
    // Initialize
    // ----------------------------------------------------------------

    private void init() {
        aStringList = new ArrayList<String>(0);
        aStringList.add("one");
        aStringList.add("two");
        aStringList.add("three");
        aStringList.add("four");
        aStringList.add("five");
    }

    // ----------------------------------------------------------------
    // Driver
    // ----------------------------------------------------------------

    public static void main(String[] args) throws IOException {
        ListUtils main = new ListUtils();
        main.init();
        main.execute();
    }

    // ----------------------------------------------------------------
    // Methods
    // ----------------------------------------------------------------

    /**
     * Converts an array to a list.
     *
     * @param <T>
     * @param args
     * @return
     */
    public static <T> List<T> toList(T... args) {
        if (args != null)
            return Arrays.asList(args);
        else
            return Collections.emptyList();
    }

    /**
     * Converts unknown list of arguments to an array.
     *
     * @param <T>
     * @param args
     * @return
     */
    public static <T> T[] toArray(T... args) {
        return args;
    }

    /**
     * Compares the contents of two lists and returns the items which are in both lists.
     *
     * @param <T>
     * @param source
     * @param target
     * @return
     */
    public static <T> List<T> matchLists(List<T> source, List<T> target) {
        List<T> matchedList = new ArrayList<T>();
        for (T obj : source) {
            if (target.contains(obj)) {
                matchedList.add(obj);
            }
        }
        return matchedList;
    }

    /**
     * Returns the first indexed item of a list
     *
     * @param <T>
     * @param source
     * @return
     */
    public static <T> T getFirstItem(List<T> source) {
        if (source.size() > 0) {
            return source.get(0);
        } else {
            return null;
        }
    }

    /**
     * Returns an empty list when List or Collection is null. Useful in enhanced for loops.
     *
     * @param source the List object
     * @return a Collections.EMPTY list
     */
    public static <T> List<T> safe(List<T> source) {
        return source == null ? Collections.EMPTY_LIST : source;
    }
}
