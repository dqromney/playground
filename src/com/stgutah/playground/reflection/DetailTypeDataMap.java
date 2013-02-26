package com.stgutah.playground.reflection;

import java.lang.annotation.*;

/**
 * Service Detail Type Data Map Annotation.
 *
 * User: dqromney
 * Date: 6/8/12
 * Time: 11:40 AM
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DetailTypeDataMap {
    DetailType detailType() default DetailType.UNDEFINED;
}
