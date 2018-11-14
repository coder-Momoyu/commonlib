package com.momoyu.lytest.model;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import com.momoyu.lytest.TestBean;
import com.momoyu.lytest.contract.AppContract;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ${momoyu} on 2018/10/10.
 */
@Module
public class AppModel implements AppContract.Model {

    private Context mContext;

    public AppModel(Application application) {
        this.mContext = application;
    }

    @Provides
    @Singleton
    public Context provideAppContext(){
        return mContext;
    }

    @Provides
    @Singleton
    public Resources provideAppResources(){
        return mContext.getResources();
    }

    @Provides
    @Singleton
    public TestBean provideTestBean(){
        return new TestBean();
    }
}
