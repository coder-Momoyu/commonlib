package com.momoyu.lytest.presenter;

import com.lanhui.mycommonlibrary.base.BaseBean;
import com.momoyu.lytest.HttpService;
import com.momoyu.lytest.MainActivity;
import com.momoyu.lytest.TestBean;

import java.util.List;
import java.util.WeakHashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ${momoyu} on 2018/10/10.
 */

public class MainActivityPresenter {
    private MainActivity mMainActivity;
    private HttpService mHttpService;

    public MainActivityPresenter(HttpService httpService) {
        this.mHttpService = httpService;
    }

    public void requestLogin() {
        /*UpdateVersionHelper.downDialog(mMainActivity);
        UpdateVersionHelper.downDialog(mMainActivity);*/
        WeakHashMap<String, Object> map = new WeakHashMap<>();
        map.put("pageIndex", 1);
        map.put("pageSize", 10);

        mHttpService
                .SafoneStudent(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::test, this::test1);
    }

    private void test1(Throwable throwable) {
    }

    private void test(BaseBean<List<TestBean>> listBaseBean) {
        //ToastUtil.showToast(mMainActivity, listBaseBean.getList().get(0).getTitle());
    }
}
