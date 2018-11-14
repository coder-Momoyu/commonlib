package com.lanhui.mycommonlibrary.network;

import android.content.Context;
import android.content.res.Resources;
import android.net.ParseException;
import android.util.Log;
import com.google.gson.JsonParseException;
import com.lanhui.mycommonlibrary.R;
import com.lanhui.mycommonlibrary.constant.HttpConstant;
import com.lanhui.mycommonlibrary.utils.ToastUtil;
import org.json.JSONException;

import java.io.File;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

/**
 * Created by ${momoyu} on 2018/11/9.
 */
public class RetrofitClient {
    private final String TAG = "RetrofitClient";
    private String mUrl;
    private WeakHashMap<String,Object> mMap;
    private RequestType mType;
    private List<File> mFile;
    private Context mContext;


    RetrofitClient (String url,
                    WeakHashMap<String,Object> map,
                    RequestType type,
                    List<File> file,
                    Context context) {
        this.mUrl = url;
        this.mMap = map;
        this.mType = type;
        this.mFile = file;
        this.mContext = context;
    }

    /**
     * 发起请求
     * @param callback  回调
     */
    public void request(RetrofitCallback callback) {
        RetrofitCreator.setInterceptorType(HttpConstant.INTERCEPORT_NORMAL);
        RxRetrofitService rxRetrofitService = RetrofitCreator.getRetrofitService();
        Observable<String> observable = null;

        switch (mType) {
            case GET:
                observable = rxRetrofitService.get(mUrl, mMap);
                break;
            case POST:
                observable = rxRetrofitService.post(mUrl, mMap);
                break;
            case UPLOAD:
                List<MultipartBody.Part> fileBodyList = new ArrayList<>();
                for (int i = 0; i < mFile.size(); i++) {
                    final RequestBody requestBody =
                            RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), mFile.get(i));
                    final MultipartBody.Part body =
                            MultipartBody.Part.createFormData("file", mFile.get(i).getName(), requestBody);
                    fileBodyList.add(body);
                }
                observable = rxRetrofitService.upload(mUrl, fileBodyList);
                break;
                default:
                    break;
        }

        if (observable != null) {
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<String>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            callback.start(d);
                        }

                        @Override
                        public void onNext(String s) {
                            Log.d(TAG,"success:" + s);
                            callback.success(s);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.d(TAG,"error:" + e);
                            callback.error(e);
                            if (e instanceof HttpException) {     //   HTTP错误
                                onException(HttpConstant.HTTP_ERROR);
                            } else if (e instanceof ConnectException
                                    || e instanceof UnknownHostException) {   //   连接错误
                                onException(HttpConstant.CONNECT_ERROR);
                            } else if (e instanceof InterruptedIOException) {   //  连接超时
                                onException(HttpConstant.CONNECT_TIMEOUT_ERROR);
                            } else if (e instanceof JsonParseException
                                    || e instanceof JSONException
                                    || e instanceof ParseException) {   //  解析错误
                                onException(HttpConstant.JSON_ERROR);
                            } else {
                                onException(HttpConstant.OTHER_ERROR);
                            }
                        }

                        @Override
                        public void onComplete() {
                            callback.finish();
                            Log.d(TAG,"onComplete: ----- end");
                        }
                    });
        }
    }


    public void updateVersion() {

    }


    /**
     * 网络请求异常提示
     *
     * @param otherError
     */
    private void onException(int otherError) {
        if (mContext != null) {
            Resources resources = mContext.getResources();
            switch (otherError) {
                case HttpConstant.HTTP_ERROR:
                    ToastUtil.showToast(mContext, "");
                    break;
                case HttpConstant.CONNECT_ERROR:
                    ToastUtil.showToast(mContext, resources.getString(R.string.connect_http_error));
                    break;
                case HttpConstant.CONNECT_TIMEOUT_ERROR:
                    ToastUtil.showToast(mContext, resources.getString(R.string.connect_timeout_http_error));
                    break;
                case HttpConstant.JSON_ERROR:
                    ToastUtil.showToast(mContext, resources.getString(R.string.json_http_error));
                    break;
                case HttpConstant.OTHER_ERROR:
                    ToastUtil.showToast(mContext, resources.getString(R.string.other_http_error));
                    break;
            }
        }
    }
}
