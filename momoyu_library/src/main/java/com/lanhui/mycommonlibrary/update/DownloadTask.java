package com.lanhui.mycommonlibrary.update;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;


import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.FileUtils;
import com.lanhui.mycommonlibrary.network.RetrofitCallback;
import com.lanhui.mycommonlibrary.utils.DownloadFileUtil;

import java.io.File;


import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

/**
 * Created by ${momoyu} on 2018/11/12.
 */
public class DownloadTask extends AsyncTask<Object, Void, File> {

    private final String PATH_DOWNFILE = Environment.getExternalStorageState() + "/download_file";
    private String mLoadPath = null;
    private final String TAG = "DownloadTask";
    private File mFile;

    @Override
    protected File doInBackground(Object... params) {
        /*String downloadUrl =
        String extension = (String) params[1];

        final String name = (String) params[3];
        final InputStream is = body.byteStream();
        if (downloadUrl == null || downloadUrl.equals("")) {
            downloadUrl = "updateDir";
        }
        if (extension == null || extension.equals("")) {
            extension = "";
        }
        if (name == null) {
            return FileUtil.writeToDisk(is, downloadUrl, extension.toUpperCase(), extension);
        } else {
            return FileUtil.writeToDisk(is, downloadUrl, name);
        }*/
        String name = (String) params[0];
        RetrofitCallback callbacks = (RetrofitCallback) params[1];
        final ResponseBody body = (ResponseBody) params[2];
        final Disposable disposable = (Disposable) params[3];
        //通过Url得到文件并创建本地文件
        if (FileUtils.createOrExistsDir(PATH_DOWNFILE)) {
            int i = name.lastIndexOf('/');//一定是找最后一个'/'出现的位置
            if (i != -1) {
                name = name.substring(i);
                mLoadPath = PATH_DOWNFILE + name;
            }
        }

        if (TextUtils.isEmpty(mLoadPath)) {
            Log.e(TAG, "存储路径为空了");
            String loadPath = DownloadFileUtil.getTimeFormatName(PATH_DOWNFILE);
            mFile = new File(loadPath);
        } else {
            //建立一个文件
            mFile = new File(mLoadPath);
        }

        DownloadFileUtil.writeFile2Disk(body,mFile,callbacks,disposable);
        return mFile;
    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        autoInstallApk(file);
    }

    private void autoInstallApk(File file) {
        /*if (FileUtil.getExtension(file.getPath()).equals("apk")) {

        }*/

        final Intent install = new Intent();
        install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        install.setAction(Intent.ACTION_VIEW);
        install.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        ActivityUtils.startActivity(install);
    }
}
