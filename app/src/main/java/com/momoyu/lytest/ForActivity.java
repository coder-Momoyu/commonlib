package com.momoyu.lytest;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by ${momoyu} on 2018/10/10.
 */
@Scope
@Retention(RUNTIME)
public @interface ForActivity {
}
