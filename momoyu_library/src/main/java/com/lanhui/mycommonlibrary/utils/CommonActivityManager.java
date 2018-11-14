package com.lanhui.mycommonlibrary.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${Momoyu} on 2018/4/9 0009.
 */

public class CommonActivityManager {

    public static List<Activity> sActivityList = new ArrayList<>();
    private static Class mClazz;

    public static void setLoginClass(Class clazz){
        mClazz = clazz;
    }

    public static Class getLoginClass(){
        return mClazz;
    }


    public static void finishAllActivity() {
        for (int i = 0; i < sActivityList.size(); i++) {
            sActivityList.get(i).finish();
            sActivityList.remove(i);
        }
    }
}
