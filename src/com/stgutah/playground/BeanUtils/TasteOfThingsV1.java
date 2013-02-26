package com.stgutah.playground.BeanUtils;

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.lang.reflect.Method;

import org.apache.commons.collections.Bag;
import org.apache.commons.collections.bag.HashBag;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import org.apache.commons.lang.StringUtils;

/**
 * Apache Bean Utils test code.
 *
 * User: dqromney
 * Date: 6/25/12
 * Time: 8:20 AM
 */

public class TasteOfThingsV1 {

  private static Map testMap;
  private static TestBean testBean;

  public static void main(String args[]) throws Exception {
    prepareData();

    HashBag myBag = new HashBag(testMap.values());

    System.err.println("How many Boxes? " + myBag.getCount("Boxes"));
    myBag.add("Boxes", 5);
    System.err.println("How many Boxes now? " + myBag.getCount("Boxes"));

    Method method =
      testBean.getClass().getDeclaredMethod("getTestMap", new Class[0]);
    HashMap reflectionMap =
      (HashMap)method.invoke(testBean, new Object[0]);
    System.err.println("The value of the 'squ' key using reflection: " +
      reflectionMap.get("squ"));

    String squ = BeanUtils.getMappedProperty(testBean, "testMap", "squ");
    squ = StringUtils.capitalize(squ);

    PropertyUtils.setMappedProperty(testBean, "testMap", "squ", squ);

    System.err.println("The value of the 'squ' key is: " +
      BeanUtils.getMappedProperty(testBean, "testMap", "squ"));

    String box = (String)testMap.get("box");
    String caps =
      Character.toTitleCase(box.charAt(0)) +
      box.substring(1, box.length());
    System.err.println("Capitalizing boxes by Java: " + caps);
  }

  private static void prepareData() {
    testMap = new HashMap();
    testMap.put("box", "boxes");
    testMap.put("squ", "squares");
    testMap.put("rect", "rectangles");
    testMap.put("cir", "circles");

    testBean = new TestBean();
    testBean.setTestMap(testMap);
  }
}