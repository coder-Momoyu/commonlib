package com.momoyu.lytest;

import android.app.Application;



import com.momoyu.lytest.component.AppComponent;



/**
 * Created by ${momoyu} on 2018/10/10.
 */

public class MyApplication extends Application {

    private AppComponent mAppComponent;


    @Override
    public void onCreate() {
        super.onCreate();

    }


    public AppComponent getAppComponent(){
        return mAppComponent;
    }

}
