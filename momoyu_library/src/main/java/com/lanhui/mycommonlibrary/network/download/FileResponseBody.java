package com.lanhui.mycommonlibrary.network.download;

import com.lanhui.mycommonlibrary.model.bean.DownloadBean;
import com.lanhui.mycommonlibrary.rx.RxBus;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;

/**
 * Created by ${momoyu} on 2018/11/13.
 */
public class FileResponseBody extends ResponseBody {

    private Response originalResponse;//原结果

    public FileResponseBody(Response originalResponse) {
        this.originalResponse = originalResponse;
    }

    //返回内容类型
    @Override
    public MediaType contentType() {
        return originalResponse.body().contentType();
    }

    //返回内容长度，没有则返回-1
    @Override
    public long contentLength() {
        return originalResponse.body().contentLength();
    }

    //返回缓存源，类似于io中的BufferedReader
    @Override
    public BufferedSource source() {
        return Okio.buffer(new ForwardingSource(originalResponse.body().source()) {
            long bytesReaded = 0;

            //返回读取到的长度
            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                long bytesRead = super.read(sink, byteCount);
                bytesReaded += bytesRead == -1 ? 0 : bytesRead;
                long totleLength = contentLength();
                // 通过RxBus发布进度信息
                RxBus.getDefault().send(new DownloadBean(totleLength, bytesReaded));
                return bytesRead;
            }
        });
    }
}
