package com.fingal.mymusic.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import com.fingal.mymusic.API.ShowAPI;
import com.fingal.mymusic.ItemClickListener;
import com.fingal.mymusic.R;
import com.fingal.mymusic.class_file.Hot_music;
import com.fingal.mymusic.fragment.adapter.Adapter_music;
import com.fingal.mymusic.fragment.adapter.GetBitmap;
import com.fingal.mymusic.http.HttpTools;
import com.fingal.mymusic.http.HttpUtils;

import java.util.List;

/**
 * Created by FX50J on 2016/5/14.
 */
public class Euramerican_fragment extends Fragment {

    private List<Hot_music.ShowapiResBodyBean.PagebeanBean.SonglistBean> mlist;

    private RecyclerView mrv;

    private Adapter_music madapter;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:

                    break;
                case 2:
                    mlist = (List<Hot_music.ShowapiResBodyBean.PagebeanBean.SonglistBean>) msg.obj;

                    LinearLayoutManager manager = new LinearLayoutManager(getContext());

                    madapter = new Adapter_music(mlist);

                    madapter.setItemClickListener(new ItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Log.e("点击了",position + "hello");
                        }
                    });


                    mrv.setLayoutManager(manager);
                    mrv.setAdapter(madapter);

                    break;
                default:
                    break;
            }
        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.euramerican,container,false);

        init_list();
        mrv = (RecyclerView) view.findViewById(R.id.recycler_euramerican);


        return view;
    }

    private void init_list() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ShowAPI showAPI = new ShowAPI();
                String url_hot = showAPI.getHot_MUSIC_Euramerican();

                HttpUtils httpUtils = new HttpUtils(url_hot);
                String json_string = httpUtils.getJson_string();
                HttpTools httpTools = new HttpTools();
                List<Hot_music.ShowapiResBodyBean.PagebeanBean.SonglistBean> list = httpTools.getMlist(json_string);

                GetBitmap getBitmap = new GetBitmap();
                List<Hot_music.ShowapiResBodyBean.PagebeanBean.SonglistBean> list1 = getBitmap.GetBit(list);

                Message message = new Message();
                message.what = 2;
                message.obj = list1;
                handler.sendMessage(message);
            }
        }).start();
    }

}
