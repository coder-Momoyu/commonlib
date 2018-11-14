package com.momoyu.lytest.component;


import android.content.Context;

import com.momoyu.lytest.model.AppModel;
import com.momoyu.lytest.model.MainActivityModel;
import com.momoyu.lytest.model.NetworkModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ${momoyu} on 2018/10/10.
 */
@Singleton
@Component(modules = {
        AppModel.class,
        NetworkModel.class})
public interface AppComponent {

    MainActivityComponent addSub(MainActivityModel mainActivityModel);
}
