package com.lanhui.mycommonlibrary.network;


import com.lanhui.mycommonlibrary.constant.HttpConstant;
import com.lanhui.mycommonlibrary.network.download.FileResponseBody;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

import static com.lanhui.mycommonlibrary.constant.HttpConstant.CONNECT_TIMEOUT;

/**
 * Created by ${momoyu} on 2018/11/9.
 */

public class RetrofitCreator {

    private static String BASE_URL;
    private static int interceptorType;

    private static Interceptor provideInterceptor() {
        Interceptor interceptor = null;
        if (interceptorType == HttpConstant.INTERCEPORT_NORMAL) {
            interceptor = new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY);
        } else if (interceptorType == HttpConstant.INTERCEPORT_DOWNLOAD) {
            interceptor = chain -> {
                Response originalResponse = chain.proceed(chain.request());//对结果重新处理
                return originalResponse
                        .newBuilder()
                        .body(new FileResponseBody(originalResponse))//将自定义的ResposeBody设置给它
                        .build();
            };
        }
        return interceptor;
    }

    private static OkHttpClient provideOkhttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(provideInterceptor())
                .build();
    }


    private static RxRetrofitService provideRetrofitService() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(FastJsonConverterFactory.create())
                .client(provideOkhttpClient())
                .build()
                .create(RxRetrofitService.class);
    }

    private static class RetrofitServiceInstance {
        private static final RxRetrofitService retrofitService = provideRetrofitService();
    }

    public static void setBaseUrl(String baseUrl) {
        BASE_URL = baseUrl;
    }

    public static void setInterceptorType(int type) {
        interceptorType = type;
    }
    /**
     * 获取retrofitService
     * @param base_url baseUrl
     * @param type 拦截器类型
     * @return
     */
    public static RxRetrofitService getRetrofitService() {
        return RetrofitServiceInstance.retrofitService;
    }
}
