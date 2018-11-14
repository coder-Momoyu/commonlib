package com.lanhui.mycommonlibrary.network;

import io.reactivex.disposables.Disposable;

/**
 * Created by ${momoyu} on 2018/11/9.
 */
public interface NetworkCallback<T> {
    void start(Disposable d);

    void success(String s);

    void error(Throwable e);

    void finish();

    void progress(int progress);
}
