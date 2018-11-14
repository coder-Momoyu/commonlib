package com.lanhui.mycommonlibrary.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanhui.mycommonlibrary.R;

/**
 * Created by Administrator on 2018/3/12 0012.
 */
public class DialogUtil {
    private static DialogUtil mDialogUtil;
    private View mView;

    public static DialogUtil getInstance() {
        if (mDialogUtil == null) {
            synchronized (DialogUtil.class) {
                if (mDialogUtil == null) {
                    mDialogUtil = new DialogUtil();
                }
            }
        }
        return mDialogUtil;
    }


    /*public MaterialDialog.Builder builderNormalDialog(Context context, String title, String content) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context);
        builder.content(content)
                .positiveText("确定")
                .negativeText("取消");

        return builder;
    }*/


    /**
     * 加载的dialog
     *
     * @return
     */
    public Dialog builderLoddingDialog(Activity activity) {
        Dialog dialog = new Dialog(activity, R.style.MyDialog);
        dialog.setContentView(R.layout.dialog_common_loadding);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        return dialog;
    }


    /**
     * 登录过期
     *
     * @param clazz 需要弹框的class
     */
    public void builderPastDialog(final Context context, final Class clazz) {
        builderCommonDialog(context, "登录过期", "登录过期，请重新登录!").addDialogBtCallback(new IDialogBtCallback() {
            @Override
            public void onCancle(Dialog dialog) {
                dialog.dismiss();
            }

            @Override
            public void onAffirm(Dialog dialog) {
                //TODO 处理清空逻辑
                SharedPreUtil.clear(context);
                Intent intent = new Intent(context, clazz);
                context.startActivity(intent);
                for (Activity activity : CommonActivityManager.sActivityList) {
                    if (!activity.isFinishing()) {
                        activity.finish();
                    }
                }
            }
        });
    }


    public DialogUtil builderCommonDialog(Context context, String title, String content) {
        final Dialog dialog = new Dialog(context, R.style.MyDialog);
        dialog.setContentView(R.layout.dialog_common_iosstyle);
        dialog.show();
        TextView dialogTitle =  dialog.findViewById(R.id.dialog_iosStyle_title);
        dialogTitle.setText(title);
        TextView dialogContent =  dialog.findViewById(R.id.dialog_iosStyle_content);
        dialogContent.setText(content);
        TextView cancle =  dialog.findViewById(R.id.dialog_iosStyle_cancle);
        TextView affirm =  dialog.findViewById(R.id.dialog_iosStyle_affirm);
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIDialogBtCallback.onCancle(dialog);
            }
        });
        affirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIDialogBtCallback.onAffirm(dialog);
            }
        });
        return mDialogUtil;
    }

    public DialogUtil builderSubmitDialog(Context context, String title, int answerCompleted, int answerTotal) {
        final Dialog dialog = new Dialog(context, R.style.MyDialog);
        dialog.setContentView(R.layout.dialog_common_clean);
        dialog.show();
        TextView dialogTitle = (TextView) dialog.findViewById(R.id.dialog_iosStyle_title);
        TextView dialogContent = (TextView) dialog.findViewById(R.id.dialog_iosStyle_content);
        dialogTitle.setText(title);

        String content1 = "共有试题 " + answerTotal + " 题,已做 ";
        String content2 = answerCompleted + " 题,您确认要交卷吗？";
        String content = content1 + content2;
        int i = UiUtil.sp2px(context, 19);
        SpanUtil.newInstance(context).appendText(content)
                .setFontColor(Color.parseColor("#282828"), 0, content.length())
                .setFontColor(Color.parseColor("#1390fd"),
                        5, 5 + String.valueOf(answerTotal).length())
                .setFontSize(i, 5, 5 + String.valueOf(answerTotal).length())
                .setFontColor(Color.parseColor("#00c356"),
                        content1.length(), content1.length() + String.valueOf(answerCompleted).length())
                .setFontSize(i, content1.length(), content1.length() + String.valueOf(answerCompleted).length())
                .build(dialogContent);
        TextView cancle = (TextView) dialog.findViewById(R.id.dialog_iosStyle_cancle);
        TextView affirm = (TextView) dialog.findViewById(R.id.dialog_iosStyle_affirm);
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIDialogBtCallback.onCancle(dialog);
            }
        });
        affirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIDialogBtCallback.onAffirm(dialog);
            }
        });
        return mDialogUtil;
    }

    public DialogUtil builderReset(Context context, String title, int answerTrue, int answerFalse) {
        final Dialog dialog = new Dialog(context, R.style.MyDialog);
        dialog.setContentView(R.layout.dialog_common_clean);
        dialog.show();

        int total = answerTrue + answerFalse;
        TextView dialogTitle = (TextView) dialog.findViewById(R.id.dialog_iosStyle_title);
        dialogTitle.setText(title);
        TextView dialogContent = (TextView) dialog.findViewById(R.id.dialog_iosStyle_content);
        String content1 = "您已经完整学习该题库，正确 " + answerTrue + " 题,错误 ";
        String content2 = answerFalse + " 题,您是否需要清空做题记录再次练习？";
        String content = content1 + content2;
        int i = UiUtil.sp2px(context, 19);
        SpanUtil.newInstance(context).appendText(content)
                .setFontColor(Color.parseColor("#282828"), 0, content.length())
                .setFontColor(Color.parseColor("#00c356"),
                        14, 14 + String.valueOf(answerTrue).length())
                .setFontSize(i, 14, 14 + String.valueOf(answerTrue).length())
                .setFontColor(Color.parseColor("#ff3030"),
                        content1.length(), content1.length() + String.valueOf(answerFalse).length())
                .setFontSize(i, content1.length(), content1.length() + String.valueOf(answerFalse).length())
                .build(dialogContent);
        TextView cancle = (TextView) dialog.findViewById(R.id.dialog_iosStyle_cancle);
        TextView affirm = (TextView) dialog.findViewById(R.id.dialog_iosStyle_affirm);
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIDialogBtCallback.onCancle(dialog);
            }
        });
        affirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIDialogBtCallback.onAffirm(dialog);
            }
        });
        return mDialogUtil;
    }

    public DialogUtil builderCleanDialog(Context context, String title, int answerTrue, int answerFalse, int answerNone, int answerUnkonw) {
        final Dialog dialog = new Dialog(context, R.style.MyDialog);
        dialog.setContentView(R.layout.dialog_common_clean);
        dialog.show();
        int total = answerTrue + answerFalse + answerNone + answerUnkonw;
        TextView dialogTitle = (TextView) dialog.findViewById(R.id.dialog_iosStyle_title);
        dialogTitle.setText(title);
        TextView dialogContent = (TextView) dialog.findViewById(R.id.dialog_iosStyle_content);
        String content1 = "您一共做了 " + total + "\t道题,做对 ";
        String content2 = answerTrue + "\t道,做错\t";
        String content3 = answerFalse + "\t道,未知\t";
        String content4 = answerUnkonw + "\t道,您确定清空所有题目的做题记录吗？";
        String content = content1 + content2 + content3 + content4;
        int i = UiUtil.sp2px(context, 19);
        SpanUtil.newInstance(context).appendText(content)
                .setFontColor(Color.parseColor("#282828"), 0, content.length())
                .setFontColor(Color.parseColor("#1390fd"),
                        6, 6 + String.valueOf(total).length()).setFontSize(i, 6, 6 + String.valueOf(total).length())
                .setFontColor(Color.parseColor("#00c356"),
                        content1.length(), content1.length() + String.valueOf(answerTrue).length())
                .setFontSize(i, content1.length(), content1.length() + String.valueOf(answerTrue).length())
                .setFontColor(Color.parseColor("#ff3030"), content1.length() + content2.length(),
                        content1.length() + content2.length() + String.valueOf(answerFalse).length())
                .setFontSize(i, content1.length() + content2.length(),
                        content1.length() + content2.length() + String.valueOf(answerFalse).length())
                .setFontColor(Color.parseColor("#ffb046"), content1.length() + content2.length() + content3.length(),
                        content1.length() + content2.length() + content3.length() + String.valueOf(answerUnkonw).length())
                .setFontSize(i, content1.length() + content2.length() + content3.length(),
                        content1.length() + content2.length() + content3.length() + String.valueOf(answerUnkonw).length())
                .build(dialogContent);
        TextView cancle = (TextView) dialog.findViewById(R.id.dialog_iosStyle_cancle);
        TextView affirm = (TextView) dialog.findViewById(R.id.dialog_iosStyle_affirm);
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIDialogBtCallback.onCancle(dialog);
            }
        });
        affirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIDialogBtCallback.onAffirm(dialog);
            }
        });
        return mDialogUtil;
    }

    public void buildNoDredgeDialog(Context context) {
        final Dialog dialog = new Dialog(context, R.style.MyDialog);
        dialog.setContentView(R.layout.dialog_no_dredge);
        dialog.show();
        ImageView close = (ImageView) dialog.findViewById(R.id.dialog_dredge_close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }


    public Dialog buildTimeExceptionDialog(Context context) {
        final Dialog dialog = new Dialog(context, R.style.MyDialog);
        dialog.setContentView(R.layout.dialog_common_exception);
        dialog.show();
        TextView cancle = (TextView) dialog.findViewById(R.id.dialog_exception_cancle);
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        return dialog;
    }

    public Dialog buildAutoSubmitDialog(Context context, int dialogType) {
        final Dialog dialog = new Dialog(context, R.style.MyDialog);
        dialog.setContentView(R.layout.dialog_common_submit);
        dialog.show();
        TextView content = (TextView) dialog.findViewById(R.id.dialog_exception_content);
        TextView title = (TextView) dialog.findViewById(R.id.dialog_exception_title);
        ImageView icon = (ImageView) dialog.findViewById(R.id.dialog_warning_icon);
        TextView cancle = (TextView) dialog.findViewById(R.id.dialog_exception_cancle);
        return dialog;
    }

    public DialogUtil disablePlatformDialog(Context context, String title, String content) {
        final Dialog dialog = new Dialog(context, R.style.MyDialog);
        dialog.setContentView(R.layout.dialog_disable_platform);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        dialog.show();
        TextView dialogTitle = (TextView) dialog.findViewById(R.id.dialog_iosStyle_title);
        dialogTitle.setText(title);
        TextView dialogContent = (TextView) dialog.findViewById(R.id.dialog_iosStyle_content);
        dialogContent.setText(content);
        TextView affirm = (TextView) dialog.findViewById(R.id.dialog_iosStyle_affirm);
        affirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIDialogBtCallback.onAffirm(dialog);
            }
        });
        return mDialogUtil;
    }


    public interface IDialogBtCallback {
        void onCancle(Dialog dialog);

        void onAffirm(Dialog dialog);
    }

    private IDialogBtCallback mIDialogBtCallback;

    public void addDialogBtCallback(IDialogBtCallback dialogBtCallback) {
        this.mIDialogBtCallback = dialogBtCallback;
    }
}
