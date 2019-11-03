package com.sheva.studentmanager.annotation;

import java.lang.annotation.*;

/**
 * @author Sheva
 * @data 2019/11/1  下午8:33
 * @Version 1.0
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUser {
}
