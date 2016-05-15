package com.fingal.mymusic.fragment.adapter;

import android.graphics.Bitmap;
import android.util.Log;

import com.fingal.mymusic.class_file.Hot_music;
import com.fingal.mymusic.http.Image_pasre;

import java.util.List;

/**
 * Created by FX50J on 2016/5/15.
 */
public class GetBitmap {

    public List<Hot_music.ShowapiResBodyBean.PagebeanBean.SonglistBean> GetBit(final List<Hot_music.ShowapiResBodyBean.PagebeanBean.SonglistBean> song_list){

        List<Hot_music.ShowapiResBodyBean.PagebeanBean.SonglistBean> mlist;
        mlist = song_list;
        for (int i = 0 ; i < song_list.size();i++) {


            String url_image = song_list.get(i).getAlbumpic_small();

            if (url_image != null){

                Log.e("url_image", url_image);
                Image_pasre image_pasre = new Image_pasre();
                Bitmap bitmap = image_pasre.getbitmap(url_image);

                mlist.get(i).setBitmap(bitmap);
            }else {
                Image_pasre image_pasre = new Image_pasre();
                String url = "http://i.gtimg.cn/music/photo/mid_album_90/S/b/003Nhwxk1KQDSb.jpg";
                Bitmap bitmap = image_pasre.getbitmap(url);
                mlist.get(i).setBitmap(bitmap);
            }

        }
        return mlist;
    }
}
