package com.stgutah.playground.initializerBlocks;

/**
 * Created by IntelliJ IDEA.
 * User: dqromney
 * Date: Jan 29, 2010
 * Time: 10:24:01 AM
 * To change this template use File | Settings | File Templates.
 */
public class InitializationWithPrivateInstanceMethod
{
    public static int staticIntField = 100;
    private String environment = "DEV"; // Swap these two and see what happens
    private boolean instanceBoolField = privInstanceMethod();
    // private String environment = "DEV";

    // These values are not available during call privInstanceMethod()
    public InitializationWithPrivateInstanceMethod()
    {
        environment = "DEV";
    }

    // These values are not available during call privInstanceMethod()
    public InitializationWithPrivateInstanceMethod(String pEnvironment)
    {
        environment = pEnvironment;   
    }

    // There is an alternative to static blocks Ñyou can write a private static method.
    private final boolean privInstanceMethod() {
        boolean isDevelopment;

        // Could just a well read this compare value to something in a file
        System.out.println("InitializationWithPrivateInstanceMethod.environment=[" + environment + "]");
        // computing the value of y
        isDevelopment = this.environment.compareToIgnoreCase("dev") == 0;

        return isDevelopment;
    }

    public boolean isInstanceBoolField()
    {
        return instanceBoolField;
    }

    public String getEnvironment()
    {
        return environment;
    }
}
