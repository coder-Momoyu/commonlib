package com.lanhui.mycommonlibrary.network;


import java.util.WeakHashMap;

/**
 * Created by momoyu on 2018/9/25.
 */

public class RequestHelper {

    private static WeakHashMap<String,Object> publicParamsMap;

    public static void initPublicParams (WeakHashMap<String,Object> map) {
        publicParamsMap = map;
    }

    public static WeakHashMap<String,Object> addPublicParams (WeakHashMap<String,Object> map) {
        if (publicParamsMap == null) {
            map.putAll(publicParamsMap);
        }
        return map;
    }

}
