package com.lanhui.mycommonlibrary.utils;

import java.util.TimerTask;

/**
 * Created by ${Momoyu} on 2018/8/5 0005.
 */

public class ImgUtil {

    private static ImgUtil mImgUtil;

    public static ImgUtil getInstance() {
        if (mImgUtil == null) {
            synchronized (ImgUtil.class) {
                if (mImgUtil == null) {
                    mImgUtil = new ImgUtil();
                }
            }
        }
        return mImgUtil;
    }
}
