package com.lanhui.mycommonlibrary.utils;


import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;

/**
 * Created by ${momoyu} on 2018/9/28.
 */

public class CustomDialog  {

    private AlertDialog mAlertDialog;

    public void singleBtDialog() {

    }

    public void doubleBtDialog(Context context) {
        mAlertDialog = new AlertDialog.Builder(context).create();
        mAlertDialog.setTitle("版本更新");
        mAlertDialog.setMessage("1.测试 \n2.测试 \n3.测试");
        mAlertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mAlertDialog.dismiss();
            }
        });

        mAlertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mAlertDialog.dismiss();
            }
        });

        boolean b = !mAlertDialog.isShowing();
        if (mAlertDialog != null && b) {
            mAlertDialog.show();
            Log.d("momoyu", "showing!!!!");
        }
    }
}
