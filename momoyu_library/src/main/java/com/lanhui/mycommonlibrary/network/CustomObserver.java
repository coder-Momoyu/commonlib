package com.lanhui.mycommonlibrary.network;

import android.app.Activity;
import android.app.Dialog;
import android.content.res.Resources;
import android.net.ParseException;
import android.util.Log;

import com.google.gson.JsonParseException;
import com.lanhui.mycommonlibrary.R;
import com.lanhui.mycommonlibrary.base.BaseBean;
import com.lanhui.mycommonlibrary.constant.HttpConstant;
import com.lanhui.mycommonlibrary.utils.ToastUtil;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * Created by momoyu on 2018/9/21.
 */

public abstract class CustomObserver<T extends BaseBean> implements Observer<T> {

    private Dialog mCustomDialog;
    private boolean isShowLoadding;
    private Activity mActivity;


    public CustomObserver(Activity activity, boolean isShowLoadding) {
        this.isShowLoadding = isShowLoadding;
        if (activity != null) {
            this.mActivity = activity;
            if (mCustomDialog == null) {
                mCustomDialog = new Dialog(mActivity);
            }
        }
    }

    @Override
    public void onSubscribe(Disposable d) {
        if (isShowLoadding) {
            if (mCustomDialog != null && !mCustomDialog.isShowing()) {
                mCustomDialog.show();
            }
        }
    }

    @Override
    public void onNext(T t) {
        mCustomDialog.dismiss();
        int rspCode = t.getRspCode();
        if (rspCode == HttpConstant.RESPONSE_SUCCESS) {
            onSuccess(t);
        } else if (rspCode == HttpConstant.RESPONSE_ERROR) {
            onFail();
        } else if (rspCode == HttpConstant.RESPONSE_PAST) {
            onPast();
        }
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {
        Log.d("momoyu","onComplete---------------------------------");
        mCustomDialog.dismiss();
    }

    /**
     * token过期
     */
    public void onPast() {

    }

    /**
     * 请求成功  返回数据
     *
     * @param t
     */
    public abstract void onSuccess(T t);

    /**
     * 请求成功 没返回数据
     *
     * @param
     */
    public void onFail() {

    }


}
