package com.fingal.mymusic.API;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * Created by FX50J on 2016/5/14.
 */
public class ShowAPI {
    private String appid = "19009";
    private String showapi_sign = "290d83f29eaa4e5c95059b48a621a517";
    private int Euramerican_topid = 3;
    private int Mainland_topid = 5;
    private String showapi_timestamp;

    /**
     * musicid  keyword 在其他方法里获取
     */
    private String musicid = "4833285";
    private String keyword = "海阔天空";
    private int page = 1;

    public static final String HOT = "http://route.showapi.com/213-4";
    public static final String LYRICS = "http://route.showapi.com/213-2";
    public static final String SEARCH = "http://route.showapi.com/213-1";

    public void  setShowapi_timestamp(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        Date curDate =  new Date(System.currentTimeMillis());//获取当前时间
        showapi_timestamp = formatter.format(curDate);
    }
    public String getShowapi_timestamp(){
        setShowapi_timestamp();
        return showapi_timestamp;
    }
    public String getHot_MUSIC_Euramerican(){
        String HOT_MUSIC_EURAMERICAN = HOT + "?showapi_appid=" + appid + "&showapi_timestamp=" + getShowapi_timestamp() + "&topid=" + Euramerican_topid + "&showapi_sign=" + showapi_sign;
        return HOT_MUSIC_EURAMERICAN;
    }
    public String getHot_MUSIC_Mainland(){
        String HOT_MUSIC_MAINLAND = HOT + "?showapi_appid=" + appid + "&showapi_timestamp=" + getShowapi_timestamp() + "&topid=" + Mainland_topid + "&showapi_sign=" + showapi_sign;
        return HOT_MUSIC_MAINLAND;
    }

    public String getLYRICS_MUSIC(){
        String LYRICS_MUSIC = LYRICS + "?musicid=" + musicid + "&showapi_appid=" + appid + "&showapi_timestamp=" + getShowapi_timestamp() + "&showapi_sign=" + showapi_sign;
        return LYRICS_MUSIC;
    }
    public String getSEARCH_MUSIC(){
        String SEARCH_MUSIC = SEARCH + "?keyword=" + keyword + "&page=" + page + "&showapi_appid=" + appid + "&showapi_timestamp=" + getShowapi_timestamp() + "&showapi_sign=" + showapi_sign;
        return SEARCH_MUSIC;
    }
}
