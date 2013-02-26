package com.stgutah.playground.reflection;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Reflection Test.
 * <p/>
 * User: dqromney
 * Date: 6/7/12
 * Time: 4:10 PM
 */
public class ReflectionTest {

    private Class clazz;
    private Method[] methodArray;
    private List<Detail> detailList;
    private List<DetailItemAction> detailItemActionList;
    private Map<DetailType, String> detailValueMap;
    private SomeMethods someMethods;

    public ReflectionTest() {
        detailItemActionList = new ArrayList<DetailItemAction>(0);
    }

    // ----------------------------------------------------------------
    // Object execution
    // ----------------------------------------------------------------
    private void execute() {
        Object retValue = null;
        ResultPropertyInspection01 pir01 = new ResultPropertyInspection01();

        for (DetailItemAction detailItemAction : detailItemActionList) {
            System.out.println(String.format("[%1$s] [%2$s]", detailItemAction.getSomeType(), detailItemAction.getMethod()));
            if(detailItemAction.getSomeType().equals(SomeType.SIMPLE)) {
                retValue = executeMethod(detailItemAction.getMethod());
                System.out.println(String.format("retValue=[%1$s]", retValue));
            }
            if(detailItemAction.getSomeType().equals(SomeType.ONE_ARG)) {
//                System.out.println(String.format("annotation=[%1$d]", detailItemAction.getMethod()));
                retValue = executeMethod(detailItemAction.getMethod(), pir01);
                System.out.println(String.format("retValue=[%1$s]", retValue));
            }
            if(detailItemAction.getSomeType().equals(SomeType.TWO_ARG)) {
                retValue = executeMethod(detailItemAction.getMethod(), "Hello", "World");
                System.out.println(String.format("retValue=[%1$s]", retValue));
            }
        }
    }

    // ----------------------------------------------------------------
    // Initialize
    // ----------------------------------------------------------------

    private void init() throws NoSuchFieldException {
        try {
            clazz = Class.forName("com.stgutah.playground.reflection.SomeMethods");
            methodArray = clazz.getDeclaredMethods();
        } catch (Throwable e) {
            System.err.println(e);
        }

        // Set up array
        String annotationName;
        for (Method method : methodArray) {
            int i = 0;
            for(Annotation annotation : method.getDeclaredAnnotations()) {
                annotationName = annotation.annotationType().getName();
                System.out.println(String.format("annotation Name=[%1$s]", annotationName));
                Annotation[] annotationArray = annotation.annotationType().getAnnotations();
                for(Annotation annotationItem : annotationArray)  {
                    System.out.println(String.format("annotation=[%1$s]", annotationItem));
                }

                annotationArray = annotation.annotationType().getAnnotations();
                for(Annotation annotationItem : annotationArray) {
                    System.out.println(String.format("annotation item=[%1$s]", annotationItem));
                }

                annotationArray = annotation.annotationType().getDeclaredAnnotations();
                for(Annotation annotationItem : annotationArray) {
                    System.out.println(String.format("declared annotation item=[%1$s]", annotationItem));
                    for(Annotation item : annotationItem.annotationType().getAnnotations()) {
                        System.out.println(String.format("sub annotation item=[%1$s]", item));
                    }
                }

                Field[] fieldArray = annotation.annotationType().getDeclaredFields();
                if(fieldArray != null) {
                    for(Field field : fieldArray) {
                        System.out.println(String.format("field item=[%1$s]", field));
                    }
                }

                Class[] classArray = annotation.annotationType().getClasses();
                if(classArray != null) {
                    for(Class clazz : classArray) {
                        System.out.println(String.format("clazz item=[%1$s]", clazz));
                    }
                }
            }

            switch (method.getParameterTypes().length) {
                case 0:
                    detailItemActionList.add(new DetailItemAction(DetailType.CONSTRUCTION_TYPE, method));
                    break;
                case 1:
                    detailItemActionList.add(new DetailItemAction(DetailType.CONSTRUCTION_TYPE, method));
                    break;
                case 2:
                    detailItemActionList.add(new DetailItemAction(DetailType.CONSTRUCTION_TYPE, method));
                    break;
                default:
                    System.out.println("Unsupported method");
                    break;
            }
        }

        Detail detail = new Detail();
        detailList = new ArrayList<Detail>(0);
        for(DetailType dt : DetailType.values()) {
            if(dt.equals(DetailType.CONSTRUCTION_TYPE)) {
                detail.setDetailType(dt);
                detail.setValue("Frame");
                detailList.add(detail);
            }
        }

        detailValueMap = getDetailValueMap(detailList);
        someMethods = new SomeMethods(detailValueMap);
    }

    private Object executeMethod(Method method, Object... args) {
        Object retValue = null;
        try {
            for(Annotation annotation : method.getDeclaredAnnotations()) {
                System.out.println(String.format("%1$s", annotation.toString()));
            }
            retValue = method.invoke(someMethods, args);

//            Class cls = Class.forName("method2", argList);
//            Class partypes[] = new Class[2];
//            partypes[0] = Integer.TYPE;
//            partypes[1] = Integer.TYPE;
//            Method meth = cls.getMethod(
//                    "add", partypes);
//            method2 methobj = new method2();
//            Object arglist[] = new Object[2];
//            arglist[0] = new Integer(37);
//            arglist[1] = new Integer(47);
//            Object retobj
//                    = meth.invoke(methobj, arglist);
//            Integer retval = (Integer) retobj;
//            System.out.println(retval.intValue());
        } catch (Throwable e) {
            System.err.println(e);
        }
        return retValue;
    }

    /**
     * Get Detail value map with the DetailType as the key.
     *
     * @param pDetailList the Detail object list
     * @return a Map<DetailType, String> object where DetailType is the key, String is the value
     */
    private Map<DetailType, String> getDetailValueMap(List<Detail> pDetailList) {
        Map<DetailType, String> detailValueMap = new HashMap<DetailType, String>(0);
        for(Detail detail : pDetailList) {
            detailValueMap.put(detail.getDetailType(), detail.getValue());
        }
        return detailValueMap;
    }

    // ----------------------------------------------------------------
    // Driver
    // ----------------------------------------------------------------

    public static void main(String[] args) throws IOException, NoSuchFieldException {
        ReflectionTest main = new ReflectionTest();
        main.init();
        main.execute();
    }

    // ----------------------------------------------------------------
    // Methods
    // ----------------------------------------------------------------

}
