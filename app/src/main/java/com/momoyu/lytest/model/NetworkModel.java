package com.momoyu.lytest.model;

import android.app.Application;
import android.content.Context;

import com.lanhui.mycommonlibrary.BuildConfig;
import com.lanhui.mycommonlibrary.base.BaseBean;
import com.momoyu.lytest.ApiService;
import com.momoyu.lytest.Constants.NetworkConstant;
import com.momoyu.lytest.HttpService;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ${momoyu} on 2018/10/11.
 */
@Module
public class NetworkModel<T> {


}
