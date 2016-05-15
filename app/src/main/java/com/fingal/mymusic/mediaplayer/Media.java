package com.fingal.mymusic.mediaplayer;

import android.media.MediaPlayer;

import java.io.IOException;

/**
 * Created by FX50J on 2016/5/15.
 */
public class Media {
    private MediaPlayer mediaPlayer;

    public void init_mediaplayer(String url_m4a){
        mediaPlayer = new MediaPlayer();
        try {

            mediaPlayer.setDataSource(url_m4a);

            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaPlayer.start();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
