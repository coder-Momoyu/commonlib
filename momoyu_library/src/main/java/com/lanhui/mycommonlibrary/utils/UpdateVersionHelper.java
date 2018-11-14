package com.lanhui.mycommonlibrary.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;

/**
 * Created by ${momoyu} on 2018/9/28.
 */

public class UpdateVersionHelper {

    private static AlertDialog alertDialog;

    public void isUpdateVersion(Context context, int serviceVersion, int currentVersion) {
        if (serviceVersion > currentVersion) {
            downDialog(context);
        } else {
            ToastUtil.showToast(context, "没有新版本");
        }
    }

    public static void downDialog(Context context) {
        if (alertDialog == null) {
            alertDialog = new AlertDialog.Builder(context).create();
        }

        alertDialog.setTitle("版本更新");
        alertDialog.setMessage("1.测试 \n2.测试 \n3.测试");
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });

        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });

        boolean b = !alertDialog.isShowing();
        if (alertDialog != null && b) {
            alertDialog.show();
            Log.d("momoyu","showing!!!!");
        }
    }
}
