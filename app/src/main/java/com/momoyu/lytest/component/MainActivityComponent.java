package com.momoyu.lytest.component;

import com.momoyu.lytest.ForActivity;
import com.momoyu.lytest.MainActivity;
import com.momoyu.lytest.model.MainActivityModel;
import com.momoyu.lytest.presenter.MainActivityPresenter;

import dagger.Subcomponent;

/**
 * Created by ${momoyu} on 2018/10/10.
 */
@ForActivity
@Subcomponent(modules = MainActivityModel.class)
public interface MainActivityComponent {
    void inject(MainActivity activity);
}
