package com.lanhui.mycommonlibrary.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.FileIOUtils;
import com.blankj.utilcode.util.FileUtils;
import com.lanhui.mycommonlibrary.R;
import com.lanhui.mycommonlibrary.model.bean.DownloadBean;
import com.lanhui.mycommonlibrary.network.RetrofitCreator;
import com.lanhui.mycommonlibrary.network.download.UpdateManager;
import com.lanhui.mycommonlibrary.rx.RxBus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by ${momoyu} on 2018/11/13.
 */
public class DownloadService extends IntentService {

    private static final String ACTION_DOWNLOAD = "intentservice.action.download";

    private static final String DOWNLOAD_URL = "downloadUrl";
    private static final String APK_PATH = "apkPath";
    private static final String NOTIFICATION_ICON = "icon";
    private final String PATH_DOWNFILE = Environment.getExternalStorageDirectory().getPath();

    private CompositeDisposable cd = new CompositeDisposable();
    private android.support.v4.app.NotificationCompat.Builder builder;
    private NotificationManager notificationManager;

    public DownloadService() {
        super("DownloadService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            String url = intent.getStringExtra(DOWNLOAD_URL);
            String apkPath = null;
            String fileName;
                int i = url.lastIndexOf('/');//一定是找最后一个'/'出现的位置
                if (i != -1) {
                    fileName = url.substring(i);
                    apkPath = PATH_DOWNFILE + fileName;
                }
            int icon = intent.getIntExtra(NOTIFICATION_ICON,0);
                notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                builder =  new NotificationCompat.Builder(this)
                        .setSmallIcon(icon)
                        .setContentTitle("开始下载")
                        .setAutoCancel(true)
                        .setContentText("版本更新");

                notificationManager.notify(0, builder.build());
                handleUpdate(url, apkPath);
        }
    }

    private void handleUpdate(String url, String apkPath) {
        subscribeEvent();//订阅下载进度
        UpdateManager.downloadApk(this, url, apkPath, cd);
    }

    private void subscribeEvent() {
        RxBus.getDefault().toObservable(DownloadBean.class)
                .subscribe(new Observer<DownloadBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        cd.add(d);
                    }

                    @Override
                    public void onNext(DownloadBean downloadBean) {
                        int progress = (int) Math.round(downloadBean.getBytesReaded() / (double) downloadBean.getTotal() * 100);
                        builder.setContentInfo(String.valueOf(progress) + "%").setProgress(100, progress, false);
                        notificationManager.notify(0, builder.build());

                        if (progress == 100)
                            notificationManager.cancel(0);
                    }

                    @Override
                    public void onError(Throwable e) {
                        subscribeEvent();
                    }

                    @Override
                    public void onComplete() {
                        subscribeEvent();
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("onDestory____DownloadService");
    }

}
