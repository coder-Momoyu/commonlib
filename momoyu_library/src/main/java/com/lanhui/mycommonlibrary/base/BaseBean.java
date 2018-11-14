package com.lanhui.mycommonlibrary.base;

import java.util.List;

/**
 * Created by Administrator on 2016/10/27.
 */
public class  BaseBean<T>  {

    /**
     * rspCode : 1
     * count : 5019
     * list : [{"id":"5437","title":"低电量报警","content":"小So设备电量低","time":"2018-09-21 17:56:30.0","type":3,"status":0,"childID":null,"deviceID":"866256030004619","userID":"13888888888","areaID":null,"lon":0,"lat":0,"address":null},{"id":"5438","title":"低电量报警","content":"小So设备电量低","time":"2018-09-21 17:56:30.0","type":3,"status":0,"childID":null,"deviceID":"866256030004619","userID":"139524727b7949c3858b844c4ac126e1","areaID":null,"lon":0,"lat":0,"address":null},{"id":"5439","title":"低电量报警","content":"小So设备电量低","time":"2018-09-21 17:56:30.0","type":3,"status":0,"childID":null,"deviceID":"866256030004619","userID":"5e68a5dd56144b05a78922617598e3f6","areaID":null,"lon":0,"lat":0,"address":null},{"id":"5440","title":"低电量报警","content":"小So设备电量低","time":"2018-09-21 17:56:30.0","type":3,"status":0,"childID":null,"deviceID":"866256030004619","userID":"685137b3144244a48034a592c44c2f89","areaID":null,"lon":0,"lat":0,"address":null},{"id":"5441","title":"低电量报警","content":"小So设备电量低","time":"2018-09-21 17:56:30.0","type":3,"status":0,"childID":null,"deviceID":"866256030004619","userID":"8dfcbf739a9a4043a9cf66c654d7a3ea","areaID":null,"lon":0,"lat":0,"address":null},{"id":"5442","title":"低电量报警","content":"小So设备电量低","time":"2018-09-21 17:56:30.0","type":3,"status":0,"childID":null,"deviceID":"866256030004619","userID":"a01a1c2cf73f4ac28a54aba9ff3bb8bf","areaID":null,"lon":0,"lat":0,"address":null},{"id":"5443","title":"低电量报警","content":"小So设备电量低","time":"2018-09-21 17:56:30.0","type":3,"status":0,"childID":null,"deviceID":"866256030004619","userID":"a6c97724941845d5a901e6352afd7a24","areaID":null,"lon":0,"lat":0,"address":null},{"id":"5444","title":"低电量报警","content":"小So设备电量低","time":"2018-09-21 17:56:30.0","type":3,"status":0,"childID":null,"deviceID":"866256030004619","userID":"e7913b78342941d69a763db867d9893a","areaID":null,"lon":0,"lat":0,"address":null},{"id":"5429","title":"低电量报警","content":"小So设备电量低","time":"2018-09-21 17:46:30.0","type":3,"status":0,"childID":null,"deviceID":"866256030004619","userID":"13888888888","areaID":null,"lon":0,"lat":0,"address":null},{"id":"5430","title":"低电量报警","content":"小So设备电量低","time":"2018-09-21 17:46:30.0","type":3,"status":0,"childID":null,"deviceID":"866256030004619","userID":"139524727b7949c3858b844c4ac126e1","areaID":null,"lon":0,"lat":0,"address":null}]
     */

    private int rspCode;
    private int count;
    private T list;

    public int getRspCode() {
        return rspCode;
    }

    public void setRspCode(int rspCode) {
        this.rspCode = rspCode;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public T getList() {
        return list;
    }

    public void setList(T list) {
        this.list = list;
    }

}
