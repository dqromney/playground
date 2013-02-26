package com.stgutah.playground.stringUtils;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;

/**
 * Returns a list of Long values in a comma-delimited String
 *
 * User: dqromney
 * Date: Nov 13, 2009
 * Time: 1:53:40 PM
 */
public class ListOfLongs {

    /**
     * Convert list of Long's to comma separarted string list
     *
     * @param list
     * @return String of comma separated long values
     */
    private String convertToStringList(List<Long> list) {
        StringBuffer buf = new StringBuffer(0);

        for(Long item: list) {
            buf.append(item.longValue()).append(',');
        }
        if (buf.length() > 0) {
            // Remove last comma
            buf.setLength(buf.length()-1);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        ListOfLongs lol = new ListOfLongs();
        List<Long> list = new ArrayList<Long>();
        HashMap map = new HashMap();
        list.add(1L); map.put(1, 1L);
        list.add(2L); map.put(2, 2L);
        list.add(3L); map.put(3, 3L);
        Set<Long> listSet = map.keySet();

        String stringList = StringUtils.join(listSet, ',');
        System.out.println("apache keySet: stringList: [" + stringList + "]");
        stringList = StringUtils.join(list, ',');
        System.out.println("apache: stringList: [" + stringList + "]");
        stringList = lol.convertToStringList(list);
        System.out.println("mine: stringList: [" + stringList + "]");
    }
}
