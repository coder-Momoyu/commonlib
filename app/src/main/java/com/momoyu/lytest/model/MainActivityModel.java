package com.momoyu.lytest.model;

import android.app.Activity;

import com.momoyu.lytest.ForActivity;
import com.momoyu.lytest.HttpService;
import com.momoyu.lytest.MainActivity;
import com.momoyu.lytest.MainBean;
import com.momoyu.lytest.presenter.MainActivityPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ${momoyu} on 2018/10/10.
 */
@Module
public class MainActivityModel {



    @ForActivity
    @Provides
    public Activity provideMainActivityr(){
        return null;
    }


    @ForActivity
    @Provides
    public MainActivityPresenter provideMainPresenter(HttpService service){
        return new MainActivityPresenter(service);
    }
}
