package com.stgutah.playground.generics;

import java.util.logging.Logger;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Calendar;
import java.math.BigDecimal;

/**
 * Convert a array of objects to a Collection of objects.
 *
 * User: dqromney
 * Date: Aug 30, 2010
 * Time: 4:01:42 PM
 */
public class FromArrayToCollection
{
    Logger log = Logger.getLogger(FromArrayToCollection.class.getName());
    String newline = System.getProperty("line.separator");

    // Works
    static <T> void fromArrayToCollection(T[] pArray, Collection<T> pCollection) {
        for(T object : pArray) {
            pCollection.add(object);
        }
    }

    // Does NOT work
//    static void fromArrayToCollectionBad(Object[] a, Collection<?> c) {
//        for(Object o : a) {
//            c.add(o);   // Compile time error
//        }
//    }

    // ----------------------------------------------------------------
    // Driver
    // ----------------------------------------------------------------
    private String[] stringArray;
    private Long[] longArray;
    private Object[] objectArray;
    private Integer[] integerArray;
    private Float[] floatArray;
    private Number[] numberArray = new Number[5];

    public static void main(String[] args) {
        FromArrayToCollection main = new FromArrayToCollection();
        main.init();
        main.execute();
    }

    public void init() {
        stringArray = new String[] {
                "One", "Two", "Three", "Four", "Five"
        };
        integerArray = new Integer[] {
           1,2,3,4,5
        };
        floatArray = new Float[] {
            0.1f, 0.2f, 0.3f, 0.4f, 0.4f, 0.5f
        };
        numberArray = new Number[] {
            0.001f, 2L, 3, 0.0000040d, 5
        };
        longArray = new Long[] {
            1L, 2L, 3L, 4L, 5L
        };
        objectArray = new Object[] {
            new BigDecimal(1),
                0.02f,
                3L,
                stringArray[3],
                Calendar.getInstance(),
                new Character('A'),
                (char) 0x43, // 'C'
                (byte) 48
        };
                
    }

    public void execute() {
        Collection<Object> objectCollection = new ArrayList<Object>(0);
        Collection<String> stringCollection = new ArrayList<String>(0);
        Collection<Number> numberCollection = new ArrayList<Number>(0);

        // Objects
        FromArrayToCollection.fromArrayToCollection(objectArray, objectCollection);
        showCollection(objectCollection, "OBJECT");
        objectCollection.clear();

        // Strings
        FromArrayToCollection.fromArrayToCollection(stringArray, stringCollection);
        showCollection(stringCollection, "STRING");
        stringCollection.clear();

        // Numbers
        FromArrayToCollection.fromArrayToCollection(integerArray, numberCollection);
        showCollection(numberCollection, "NUMBER-INTEGER");
        numberCollection.clear();
        FromArrayToCollection.fromArrayToCollection(floatArray, numberCollection);
        showCollection(numberCollection, "NUMBER-FLOAT");
        numberCollection.clear();
        FromArrayToCollection.fromArrayToCollection(numberArray, numberCollection);
        showCollection(numberCollection, "NUMBER");
        numberCollection.clear();
        FromArrayToCollection.fromArrayToCollection(longArray, numberCollection);
        showCollection(numberCollection, "NUMBER-LONG");
        numberCollection.clear();

        FromArrayToCollection.fromArrayToCollection(numberArray, objectCollection); // T inferred to be number
        showCollection(objectCollection, "NUMBER->OBJECT");
        objectCollection.clear();
        FromArrayToCollection.fromArrayToCollection(stringArray, objectCollection); // T inferred to be number
        showCollection(objectCollection, "STRING->OBJECT");
        objectCollection.clear();
        FromArrayToCollection.fromArrayToCollection(objectArray, objectCollection); // T inferred to be number
        showCollection(objectCollection, "OBJECT->OBJECT");
        objectCollection.clear();

    }

    private void showCollection(Collection<?> pCollection, String pDisplayType)
    {
        System.out.printf("BEGIN %1$s List...%2$s", pDisplayType, newline);
        for(Object o : pCollection)
        {
            if((pDisplayType.compareTo("NUMBER") == 0) || (pDisplayType.contains("->")))
            {
                String type = o.getClass().toString();
                System.out.printf("o=[%1$s] Type[%2$s]%3$s", o, type, newline);
            }
            else
            {
                System.out.printf("o=[%1$s]%2$s", o, newline);
            }
        }
        System.out.printf("END %1$s List...%2$s", pDisplayType, newline);
    }
}
