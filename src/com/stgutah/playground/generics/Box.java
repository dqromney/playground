package com.stgutah.playground.generics;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Generic version of the Box class.
 * <p/>
 * <p/>
 * Type Parameter Naming Conventions
 * <p/>
 * By convention, 'type parameter' names are single, uppercase letters. This stands in sharp contrast to the variable
 * naming conventions that you already know about, and with good reason: Without this convention, it would be difficult to tell the difference between a type variable and an ordinary class or interface name.
 * The most commonly used type parameter names are:
 * <p/>
 * E - Element (used extensively by the Java Collections Framework)
 * K - Key
 * N - Number
 * T - Type
 * V - Value
 * S,U,V etc. - 2nd, 3rd, 4th types
 * <p/>
 * User: dqromney
 * Date: Feb 2, 2011
 * Time: 9:54:14 PM
 */
public class Box<T> {
    private T t; // T stands for "Type"; any kind of type (even an interface) except for primatives

    public void add(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }

    /**
     * Inspect 'type parameter' information; no restrictions.
     *
     * @param u   type parameter
     * @param <U> type; could have used any list above
     */
    public <U> void inspect(U u) {
        System.out.println("T: " + t.getClass().getName());
        System.out.println("U: " + u.getClass().getName());
    }

    /**
     * Inpect 'type parameters' restricted the kinds of types that are allowed to be passed to a type parameter.
     * In this case Number.
     *
     * @param u paramenter type variable to inspect
     * @param <U> parameter type of inspection
     */
    public <U extends Number> void inspect(U u) { // Can use also <U extends Number & MyInterface>
        System.out.println("T: " + t.getClass().getName());
        System.out.println("U: " + u.getClass().getName());
    }

    /**
     * Static method access.
     *
     * @param u     parameter type variable to fill boxes with
     * @param boxes a list of Boxes using parameter type
     * @param <U>   the parameter type the parameter type to prefix call with Box<U>.fillBoxes(...)
     */
    public static <U> void fillBoxes(U u, List<Box<U>> boxes) {
        for (Box<U> box : boxes) {
            box.add(u);
        }
    }

    public static void main(String[] args) {
        Box<Integer> integerBox = new Box<Integer>();
        integerBox.add(new Integer(10));
        Integer someInteger = integerBox.get(); // no cast needed!
        System.out.println(someInteger);

        integerBox.inspect(new String("some text")); // unrestricted
        integerBox.inspect(new BigDecimal(1.021)); // restricted to Number

        // Let us cause an error; add(java.lang.Integer) in com.stgutah.playground.generics.Box<java.lang.Integer> cannot be applied to (java.lang.String)
        // integerBox.add("Hello");

        Box<String> stringBox = new Box<String>();
        stringBox.add(new String("Hello"));
        String someString = stringBox.get(); // no cast needed!
        System.out.println(someString);

        Box<BigDecimal> BigDecimalBox = new Box<BigDecimal>();
        BigDecimalBox.add(new BigDecimal(1.0002310).round(MathContext.DECIMAL32));
        BigDecimal someBigDecimal = BigDecimalBox.get(); // no cast needed!
        System.out.println(someBigDecimal);

        // Type inference
        List<Box<Crayon>> redCrayonBoxes = new ArrayList<Box<Crayon>>(0);
        List<Box<Crayon>> blueCrayonBoxes = new ArrayList<Box<Crayon>>(0);
        // 5 Red Crayon's per box
        redCrayonBoxes.add(new Box<Crayon>());
        redCrayonBoxes.add(new Box<Crayon>());
        redCrayonBoxes.add(new Box<Crayon>());
        redCrayonBoxes.add(new Box<Crayon>());
        redCrayonBoxes.add(new Box<Crayon>());
        // 5 Blue Crayon's per box
        blueCrayonBoxes.add(new Box<Crayon>());
        blueCrayonBoxes.add(new Box<Crayon>());
        blueCrayonBoxes.add(new Box<Crayon>());
        blueCrayonBoxes.add(new Box<Crayon>());
        blueCrayonBoxes.add(new Box<Crayon>());
        // Without type inference
        Box.<Crayon>fillBoxes(new Crayon("red"), redCrayonBoxes);
        // or with Type inference; for Crayon type; compiler infers that U is Crayon
        Box.fillBoxes(new Crayon("blue"), blueCrayonBoxes);
        // Print results
        System.out.println("List contents of Red Box...");
        for (Box<Crayon> box : redCrayonBoxes) {
            System.out.println(box.get().toString());
        }
        System.out.println("List contents of Blue Box...");
        for (Box<Crayon> box : blueCrayonBoxes) {
            System.out.println(box.get().toString());
        }


    }
}

class Crayon {
    String color;

    Crayon(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Crayon{" +
                "color='" + color + '\'' +
                '}';
    }
}

