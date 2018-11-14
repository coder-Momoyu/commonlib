package com.lanhui.mycommonlibrary.constant;

/**
 * Created by zhangjiahao on 2018/9/21.
 */

public class HttpConstant {
    //   HTTP错误
    public final static int HTTP_ERROR = 4001;
    //   连接错误
    public final static int CONNECT_ERROR = 4002;
    //  连接超时
    public final static int CONNECT_TIMEOUT_ERROR = 4003;
    //  解析错误
    public final static int JSON_ERROR = 4004;
    //  其它错误
    public final static int OTHER_ERROR = 4005;
    //  请求返回成功
    public final static int RESPONSE_SUCCESS = 1;
    //  请求返回失败
    public final static int RESPONSE_ERROR = 0;
    //  请求返回过期
    public final static int RESPONSE_PAST = 500;
    //  超时时间
    public final static long CONNECT_TIMEOUT = 10;

    public static final String BASE_URL = "http://edu.winsaf.com:9999/";

    //拦截器类型  普通
    public final static int INTERCEPORT_NORMAL = 0;
    //拦截器类型  下载
    public final static int INTERCEPORT_DOWNLOAD = 1;


}
