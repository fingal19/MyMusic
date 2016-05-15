package com.fingal.mymusic.http;

import com.fingal.mymusic.class_file.Search_music;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by FX50J on 2016/5/14.
 */
public class HttpTools_Search {
    private List<Search_music.ShowapiResBodyBean.PagebeanBean.ContentlistBean> mlist;

    public List<Search_music.ShowapiResBodyBean.PagebeanBean.ContentlistBean> getMlist(String json_str) {
        Gson gson = new Gson();
        Search_music search_music = gson.fromJson(json_str,Search_music.class);
        mlist = search_music.getShowapi_res_body().getPagebean().getContentlist();
        return mlist;
    }
}
