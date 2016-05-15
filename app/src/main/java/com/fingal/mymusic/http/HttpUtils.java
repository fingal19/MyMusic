package com.fingal.mymusic.http;

import android.util.Log;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by FX50J on 2016/5/14.
 */
public class HttpUtils {

    String json_string = "";
    private String url_path;
    public HttpUtils(String url_path){
        this.url_path = url_path;
    }

    public String getJson_string(){
        try {
            URL url = new URL(url_path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(8000);

            int connectioncode = connection.getResponseCode();
            if (connectioncode == 200){
                InputStream is = connection.getInputStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

                byte[] data = new byte[1024];
                int len = 0;
                while ((len = is.read(data)) != -1){
                    outputStream.write(data,0,len);
                }
                json_string = outputStream.toString();
                Log.e("json",json_string);

            }else {

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json_string;
    }
}
