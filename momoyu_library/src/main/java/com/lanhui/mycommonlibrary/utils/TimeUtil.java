package com.lanhui.mycommonlibrary.utils;

import android.util.Log;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ${Momoyu} on 2018/6/27 0027.
 */

public class TimeUtil {

    public static String time2HMS(int _ms){
        String HMStime;
        int hour=_ms / 3600;
        int mint=(_ms % 3600)/60;
        int sed=_ms % 60;
        String hourStr=String.valueOf(hour);
        if(hour<10){
            hourStr="0"+hourStr;
        }
        String mintStr=String.valueOf(mint);
        if(mint<10){
            mintStr="0"+mintStr;
        }
        String sedStr=String.valueOf(sed);
        if(sed<10){
            sedStr="0"+sedStr;
        }
        if (hourStr.equals("00")) {
            HMStime= mintStr+"'"+sedStr + "\"";
        } else {
            HMStime= hourStr+"h"+mintStr+"'"+sedStr + "\"";
        }

        return HMStime;
    }

    public static String ms2DHMS(Long ms){
        Integer ss = 1000;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;

        Long day = ms / dd;
        Long hour = (ms - day * dd) / hh;
        Long minute = (ms - day * dd - hour * hh) / mi;
        Long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        Long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        StringBuffer sb = new StringBuffer();
        if(day > 0) {
            sb.append(day+"天");
        }
        if(hour >= 0) {
            sb.append(hour+"时");
        }
        if(minute >= 0) {
            sb.append(minute+"分");
        }
        if(second >= 0) {
            sb.append(second + "秒");
        }
        return sb.toString();
    }

    public static String ms2HMS(Long ms){
        Integer ss = 1000;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;

        Long day = ms / dd;
        Long hour = (ms - day * dd) / hh;
        Long minute = (ms - day * dd - hour * hh) / mi;
        Long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        Long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        StringBuffer sb = new StringBuffer();
        if(day > 0) {
            sb.append(day+"天");
        }
        if(hour > 0) {
            if(hour < 10) {
                sb.append("0" + hour +"时");
            } else {
                sb.append(hour+"时");
            }
        }
        if(minute >= 0) {
            if(minute < 10) {
                sb.append("0" + minute +"分");
            } else {
                sb.append(minute+"分");
            }
        }
        if(second >= 0) {
            if( second < 10) {
                sb.append("0" + second +"秒");
            } else {
                sb.append(second + "秒");
            }
        }
        return sb.toString();
    }


    public static String formatTimeS(long seconds) {
        int temp = 0;
        StringBuffer sb = new StringBuffer();
        if (seconds > 3600) {
            temp = (int) (seconds / 3600);
            sb.append((seconds / 3600) < 10 ? "0" + temp + ":" : temp + ":");
            temp = (int) (seconds % 3600 / 60);
            changeSeconds(seconds, temp, sb);
        } else {
            temp = (int) (seconds % 3600 / 60);
            changeSeconds(seconds, temp, sb);
        }
        return sb.toString();
    }

    private static void changeSeconds(long seconds, int temp, StringBuffer sb) {
        sb.append((temp < 10) ? "0" + temp + "." : "" + temp + ".");
        temp = (int) (seconds % 3600 % 60);
        sb.append((temp < 10) ? "0" + temp : "" + temp);
    }


    public static String formatHsTime(long seconds) {
        int l = (int)seconds / 60;
        int i1 = l % 60;
        int i = l / 60;
        float i2 = 0;
        if (i1 != 0) {
            i2 = (float) i1  / 60;
        }
        float v = i + i2;
        float v1 = new BigDecimal(v).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
        String s = String.valueOf(v1);
        Log.d("TimeUtil","计算时长：" + s);
        if (s.length() > 4) {
            s = s.substring(0, s.length()-2);
        }

        return s;
    }

    public static String getCurrentTimeHm() {
        long l = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd,HH:mm");
        String format = simpleDateFormat.format(new Date(l));
        String[] split = format.split(",");
        return split[1];
    }

}
