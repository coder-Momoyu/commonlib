package com.momoyu.lytest;

import com.lanhui.mycommonlibrary.base.BaseBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by zhangjiahao on 2018/9/18.
 */

public interface HttpService {

    @Headers({"Content-type:application/json;charset=UTF-8"})//需要添加头
    @POST("edu/deviceMgmt/getPushMsgList")
    Observable<BaseBean<List<TestBean>>> SafoneStudent(@Body Map map);
}
