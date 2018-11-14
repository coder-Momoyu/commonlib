package com.momoyu.lytest;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.lanhui.mycommonlibrary.network.RequestType;
import com.lanhui.mycommonlibrary.network.RetrofitCallback;
import com.lanhui.mycommonlibrary.network.RetrofitClientBuilder;
import com.lanhui.mycommonlibrary.network.RetrofitCreator;
import com.lanhui.mycommonlibrary.network.RxRetrofitService;
import com.lanhui.mycommonlibrary.service.DownloadService;
import com.lanhui.mycommonlibrary.update.DownloadTask;
import com.momoyu.lytest.presenter.MainActivityPresenter;


import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;


import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observables.GroupedObservable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;


public class MainActivity extends AppCompatActivity {


    @Inject
    MainActivityPresenter mPresenter;
    @Inject
    TestBean mTestBean;


    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RetrofitCreator.setBaseUrl("http://www.winsaf.com:9999/");


        Intent intent = new Intent(this,DownloadService.class);
        intent.putExtra("downloadUrl","version/Safone_Student.apk");
        intent.putExtra("icon",R.mipmap.ic_launcher);
        startService(intent);
        //sendNotification();

        /*WeakHashMap<String, Object> map = new WeakHashMap<>();
        map.put("username", "8888");
        map.put("password", "123456");

        RetrofitClientBuilder.builder()
                .url("http://www.winsaf.com:9999/version/Safone_Student.apk")
                .addParams(map)
                .build()
                .download()
                .subscribeOn(Schedulers.io())
                .subscribe(responseBody -> {

                });*/
        /*RetrofitClientBuilder.builder()
                .url("/attendance/Att_app_user/userLogin")
                .loading(this)
                .addParams(map)
                .build()
                .request(new RetrofitCallback() {

                    @Override
                    public void success(String s) {

                    }
                });*/
    }


    private void sendNotification() {
        //获取NotificationManager实例
        NotificationManager notifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //实例化NotificationCompat.Builde并设置相关属性
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                //设置小图标
                .setSmallIcon(R.mipmap.ic_launcher)
                //设置通知标题
                .setContentTitle("最简单的Notification")
                //设置通知内容
                .setContentText("只有小图标、标题、内容");
        //设置通知时间，默认为系统发出通知的时间，通常不用设置
        //.setWhen(System.currentTimeMillis());
        //通过builder.build()方法生成Notification对象,并发送通知,id=1
        notifyManager.notify(1, builder.build());
    }
    /**
     * 去重
     */
    private void distinct() {
        Observable.just(1, 1, 1, 2, 2, 3)
                .distinct()
                .subscribe(integer -> Log.d("TEST", "distinct:" + integer));
    }

    /**
     * 依次发送
     */
    private void just() {
        Observable.just(1, 2, 3)
                .subscribe(integer -> Log.d("TEST", "take:" + integer));
    }

    /**
     * 只接收 take 个消息
     */
    private void take() {
        Observable.just(1, 2, 3, 4, 6)
                .take(3)
                .subscribe(integer -> Log.d("TEST", "take:" + integer));
    }

    /**
     * 跳过 skip 个消息才接收
     */
    private void skip() {
        Observable.just(1, 2, 3, 4, 5)
                .skip(2)
                .subscribe(integer -> Log.d("TEST", "SKIP:" + integer));
    }

    /**
     * 每次接收消息前先执行doOnNext
     */
    private void doOnNext() {
        Observable.just(1, 2, 3, 4)
                .doOnNext(integer -> Log.d("TEST", "doOnNext"))
                .subscribe(integer -> Log.d("TEST", "accpt:" + integer));
    }

    /**
     * 将消息按每组count个 步长 skip 发送消息
     */
    private void buffer() {
        Observable.just(1, 2, 3, 4, 5, 6)
                .buffer(2, 3)
                .subscribe(integers -> Log.d("TEST", "buffer : " + integers));
    }

    /**
     * 根据filter条件过滤消息
     */
    private void filter() {
        Observable.just(1, 30, 20, 21, 22, 100)
                .filter(integer -> integer > 21)
                .subscribe(integer -> Log.d("TEST", "integer :" + integer));
    }

    /**
     * 延迟delay时间发送消息  只发送一次
     */
    @SuppressLint("CheckResult")
    private void timer() {
        Observable.timer(10, TimeUnit.SECONDS)
                .subscribe(aLong -> Log.d("TEST", "延时"));
    }

    /**
     * 每隔period秒发送一次消息
     */
    private void interval() {
        Observable.interval(2, TimeUnit.SECONDS)
                .subscribe(aLong -> Log.d("TEST", "哈哈哈哈"));
    }

    private void map() {
        Observable.fromArray(1, 2, 3, 4)
                .map(integer -> integer + "哈哈哈").subscribe(o -> Log.d("TEST", o));
    }

    private void groupBy() {
        Observable.fromArray(1, 2, 3, 4)
                .groupBy(new Function<Integer, Object>() {
                    @Override
                    public Object apply(Integer integer) throws Exception {
                        return integer % 2;
                    }
                }).subscribe(new Consumer<GroupedObservable<Object, Integer>>() {
            @Override
            public void accept(GroupedObservable<Object, Integer> objectIntegerGroupedObservable) throws Exception {
                objectIntegerGroupedObservable.subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d("TEST", "分组" + objectIntegerGroupedObservable.getKey() + "值" + integer);
                    }
                });
            }
        });
    }
}
