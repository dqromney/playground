package com.stgutah.playground.reflection;

import java.lang.reflect.Method;

/**
 * Detail Item Action.
 *
 * User: dqromney
 * Date: 6/7/12
 * Time: 4:33 PM
 */
public class DetailItemAction {
    DetailType detailType;
    Method method;

    public DetailItemAction(DetailType pDetailType, Method pMethod) {
        this.detailType = pDetailType;
        this.method = pMethod;
    }

    public DetailType getSomeType() {
        return detailType;
    }

    public Method getMethod() {
        return method;
    }
}
