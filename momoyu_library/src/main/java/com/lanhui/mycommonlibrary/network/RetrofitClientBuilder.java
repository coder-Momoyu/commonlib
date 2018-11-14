package com.lanhui.mycommonlibrary.network;

import android.content.Context;

import java.io.File;
import java.util.List;
import java.util.WeakHashMap;

/**
 * Created by ${momoyu} on 2018/10/19.
 */

public class RetrofitClientBuilder {

    private String mUrl;
    private WeakHashMap<String,Object> mMap;
    private List<File> mFileList;
    private RequestType mRequestType = RequestType.POST;
    private Context mContext;

    public static RetrofitClientBuilder builder() {
        return RetrofitClientBuilderInstance.builder;
    }


    public RetrofitClientBuilder loading(Context context) {
        this.mContext = context;
        return this;
    }

    public RetrofitClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public RetrofitClientBuilder addParams(WeakHashMap<String,Object> params) {
        this.mMap = params;
        return this;
    }

    public RetrofitClientBuilder setRequestType(RequestType type) {
        this.mRequestType = type;
        return this;
    }

    public RetrofitClientBuilder addFileList(List<File> files) {
        this.mFileList = files;
        return this;
    }

    public RetrofitClient build() {
        return new RetrofitClient(mUrl,mMap,mRequestType,mFileList,mContext);
    }

    private static class RetrofitClientBuilderInstance {
        private static final RetrofitClientBuilder builder = new RetrofitClientBuilder();
    }

}
