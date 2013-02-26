package com.stgutah.playground.Dates;

/**
 * Created with IntelliJ IDEA.
 * User: dqromney
 * Date: 4/27/12
 * Time: 1:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class Calendar {

    public static void main(String[] args) {
        int[] m = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        String[] d = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        int a, b, c = 0, j, i;
        a = 12; // Month
        b = 1; // Day of next month
        {
            for (i = 0; i < (a - 1); i++) {
                c = c + m[i];
            }
            System.out.println(String.format("Day Count for [%1$d] month(s): [%2$s]", a, c));
            c = c + b - 1;
            System.out.println(String.format("Total Day Count for [%1$d] Months and [%2$d] day(s): [%3$d]", a, b, c));
            j = c % 7;
            System.out.println(String.format("Day of Week: [%1$s]", d[j]));
        }
    }
}
