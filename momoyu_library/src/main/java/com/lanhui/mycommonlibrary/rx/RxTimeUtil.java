package com.lanhui.mycommonlibrary.rx;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.concurrent.TimeUnit;


/**
 * Created by ${Momoyu} on 2018/6/25 0025.
 */

public class RxTimeUtil {
    /*private static Disposable mDisposable;
    private final static String TAG = "RxTimeUtil";

    *//** milliseconds毫秒后执行next操作
     *
     * @param milliseconds
     * @param next
     *//*
    public static void timer(long milliseconds,final IRxNext next) {
        Observable.timer(milliseconds, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable disposable) {
                        mDisposable=disposable;
                    }

                    @Override
                    public void onNext(@NonNull Long number) {
                        if(next!=null){
                            next.doNext(number);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        //取消订阅
                        cancel();
                    }

                    @Override
                    public void onComplete() {
                        //取消订阅
                        cancel();
                    }
                });
    }


    *//** 每隔milliseconds毫秒后执行next操作
     *
     * @param milliseconds
     * @param next
     *//*
    public static void interval(long milliseconds,final IRxNext next){
        Observable.interval(milliseconds, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable disposable) {
                        mDisposable=disposable;
                    }

                    @Override
                    public void onNext(@NonNull Long number) {
                        if(next!=null){
                            next.doNext(number);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    *//**
     * 每隔一秒执行一次操作
     * @param time 执行多少次
     * @param next
     *//*

    public static void countDown(Long time, final IRxNext next) {
        Observable.intervalRange(1, time + 1, 0, 1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(Long value) {
                        if(next!=null){
                            next.doNext(value);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    *//**
     * 取消订阅
     *//*
    public static void cancel(){
        if(mDisposable!=null&&!mDisposable.isDisposed()){
            mDisposable.dispose();
            Log.e(TAG,"定时器取消");
        }
    }

    public interface IRxNext{
        void doNext(long number);
    }*/

}
