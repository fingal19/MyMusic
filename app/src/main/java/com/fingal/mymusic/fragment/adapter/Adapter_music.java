package com.fingal.mymusic.fragment.adapter;

import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fingal.mymusic.ItemClickListener;
import com.fingal.mymusic.MainActivity;
import com.fingal.mymusic.R;
import com.fingal.mymusic.class_file.Hot_music;
import com.fingal.mymusic.http.Image_pasre;
import com.fingal.mymusic.mediaplayer.Media;

import java.io.IOException;
import java.util.List;


/**
 * Created by FX50J on 2016/5/14.
 */
public class Adapter_music extends RecyclerView.Adapter<Adapter_music.MyHolder> {

    private List<Hot_music.ShowapiResBodyBean.PagebeanBean.SonglistBean> mlist;
    private ItemClickListener mitemClickListener;


    public Adapter_music(List<Hot_music.ShowapiResBodyBean.PagebeanBean.SonglistBean> list){
        this.mlist = list;
    }
    public void setItemClickListener(ItemClickListener mitemClickListener){
        this.mitemClickListener = mitemClickListener;
    }
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.music_item,parent,false);
        MyHolder holder = new MyHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {


        holder.mhead.setImageBitmap(mlist.get(position).getBitmap());
        holder.song_name.setText(mlist.get(position).getSongname());
        holder.singer_name.setText(mlist.get(position).getSingername());



        /**
         * 在这里设置歌曲的链接
         */
        holder.mlisten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mitemClickListener){
                    Log.e("点击了：", mlist.get(position).getUrl());
                    //播放

                    Media media = new Media();
                    media.init_mediaplayer(mlist.get(position).getUrl());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder{
        private TextView song_name;
        private TextView singer_name;

        private ImageView mhead;
        private ImageView mlisten;

        public MyHolder(View itemView) {
            super(itemView);

            song_name = (TextView) itemView.findViewById(R.id.song_name);
            singer_name = (TextView) itemView.findViewById(R.id.singer_name);

            mlisten = (ImageView) itemView.findViewById(R.id.listen);

            mhead = (ImageView) itemView.findViewById(R.id.head);
        }
    }
}
