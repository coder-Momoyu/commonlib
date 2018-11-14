package com.lanhui.mycommonlibrary.network;

import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by ${momoyu} on 2018/11/9.
 */

public interface RxRetrofitService {

    @GET
    Observable<String> get(@Url String url, @QueryMap WeakHashMap<String,Object> params);

    @FormUrlEncoded
    @POST
    Observable<String> post(@Url String url, @FieldMap WeakHashMap<String,Object> params);

    @Streaming
    @GET
    Observable<ResponseBody> download(@Url String url);

    @Multipart
    @POST
    Observable<String> upload(@Url String url, @Part List<MultipartBody.Part> fileList);
}
