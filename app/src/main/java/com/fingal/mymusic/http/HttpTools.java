package com.fingal.mymusic.http;


import com.fingal.mymusic.class_file.Hot_music;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by FX50J on 2016/5/14.
 */
public class HttpTools {
    private List<Hot_music.ShowapiResBodyBean.PagebeanBean.SonglistBean> mlist;

    public List<Hot_music.ShowapiResBodyBean.PagebeanBean.SonglistBean> getMlist(String json_str){
        Gson gson = new Gson();
        Hot_music hot_music = gson.fromJson(json_str,Hot_music.class);
        mlist =hot_music.getShowapi_res_body().getPagebean().getSonglist();
        return mlist;
    }
}
