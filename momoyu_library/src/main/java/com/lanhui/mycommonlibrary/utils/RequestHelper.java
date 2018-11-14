package com.lanhui.mycommonlibrary.utils;




import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/23 0023.
 */

public class RequestHelper {

    /*public static <T> void callRequest(String Url, Map<String, String> map, JsonCallback callback){
        OkGo.<String>post(Url)
                .tag(Url)
                .params(map)
                .execute(callback);
    }

    public static <T> void callFileRequest(String Url, Map<String, String> map, List<File> list,JsonCallback callback){
        PostRequest<String> params = OkGo.<String>post(Url)
                .tag(Url)
                .params(map);

        if (list != null) {
            params.addFileParams("photo[]",list).execute(callback);
        } else {
            params.execute(callback);
        }
    }


    public static void cancleRequest(String tag){
        OkGo.getInstance().cancelTag(tag);
    }*/
}
