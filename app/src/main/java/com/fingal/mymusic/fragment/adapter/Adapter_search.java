package com.fingal.mymusic.fragment.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fingal.mymusic.ItemClickListener;
import com.fingal.mymusic.R;
import com.fingal.mymusic.class_file.Hot_music;
import com.fingal.mymusic.class_file.Search_music;

import java.util.List;

/**
 * Created by FX50J on 2016/5/15.
 */
public class Adapter_search extends RecyclerView.Adapter<Adapter_search.MyHolder>{
    private List<Search_music.ShowapiResBodyBean.PagebeanBean.ContentlistBean> mlist;
    private ItemClickListener mitemClickListener;

    public Adapter_search(List<Search_music.ShowapiResBodyBean.PagebeanBean.ContentlistBean> list){
        this.mlist = list;
    }
    public void setItemClickListener(ItemClickListener mitemClickListener){
        this.mitemClickListener = mitemClickListener;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item,parent,false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        holder.miv.setImageResource(R.drawable.song);
        holder.song_name.setText(mlist.get(position).getSongname());
        holder.singer_name.setText(mlist.get(position).getSingername());

        holder.mlisten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mitemClickListener){
                    Log.e("点击了：", mlist.get(position).getSongname());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{
        private ImageView miv;
        private TextView song_name;
        private TextView singer_name;

        private ImageView mlisten;

        public MyHolder(View itemView) {
            super(itemView);
            miv = (ImageView) itemView.findViewById(R.id.picture);
            song_name = (TextView) itemView.findViewById(R.id.song_name);
            singer_name = (TextView) itemView.findViewById(R.id.singer_name);

            mlisten = (ImageView) itemView.findViewById(R.id.listen);
        }
    }
}
