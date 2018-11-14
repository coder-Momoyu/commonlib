package com.lanhui.mycommonlibrary.utils;

import android.util.Log;
import android.webkit.DownloadListener;

import com.lanhui.mycommonlibrary.network.RetrofitCallback;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by ${momoyu} on 2018/11/12.
 */
public class DownloadFileUtil {
    private static final String TIME_FORMAT = "_yyyyMMdd_HHmmss";
    private final static String TAG = "DownloadFileUtil";
    public static String getTimeFormatName(String timeFormatHeader) {
        final Date date = new Date(System.currentTimeMillis());
        //必须要加上单引号
        final SimpleDateFormat dateFormat = new SimpleDateFormat("'" + timeFormatHeader +
                "'" + TIME_FORMAT, Locale.getDefault());
        return dateFormat.format(date);
    }


    //将下载的文件写入本地存储
    public static void writeFile2Disk(ResponseBody body, File file, RetrofitCallback callback,Disposable disposable) {
        callback.start(disposable);
        long currentLength = 0;
        OutputStream os = null;

        InputStream is = body.byteStream(); //获取下载输入流
        long totalLength = body.contentLength();

        try {
            os = new FileOutputStream(file); //输出流
            int len;
            byte[] buff = new byte[1024];
            while ((len = is.read(buff)) != -1) {
                os.write(buff, 0, len);
                currentLength += len;
                Log.e(TAG, "当前进度: " + currentLength);
                //计算当前下载百分比，并经由回调传出
                callback.progress((int) (100 * currentLength / totalLength));
                //当百分比为100时下载结束，调用结束回调，并传出下载后的本地路径
                if ((int) (100 * currentLength / totalLength) == 100) {
                    callback.finish(); //下载完成
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close(); //关闭输出流
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close(); //关闭输入流
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
