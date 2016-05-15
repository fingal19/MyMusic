package com.fingal.mymusic;

import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.fingal.mymusic.API.ShowAPI;
import com.fingal.mymusic.class_file.Search_music;
import com.fingal.mymusic.fragment.Fragment_Adapter;
import com.fingal.mymusic.fragment.adapter.Adapter_search;
import com.fingal.mymusic.http.HttpTools_Search;
import com.fingal.mymusic.http.HttpUtils;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView song_list;

    private TabLayout mtab;
    private ViewPager mvp;
    private Toolbar mtoolbar;

    private View msearch;
    private View hot_fragment;

    private Button search_btn;
    private Button mcancel;
    private EditText met;

    private ImageView play_stop;

    private int i = 1;

    private RecyclerView mev_search;
    private Adapter_search madapter_search;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    i = (int) msg.obj + 1;
                    if (i % 2 == 0){
                        play_stop.setImageResource(R.mipmap.play);
                    }else {
                        play_stop.setImageResource(R.mipmap.stop);
                    }

                    break;

                case 2:

                    List<Search_music.ShowapiResBodyBean.PagebeanBean.ContentlistBean> mlist = (List<Search_music.ShowapiResBodyBean.PagebeanBean.ContentlistBean>) msg.obj;
                    madapter_search = new Adapter_search(mlist);
                    LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this);
                    mev_search.setAdapter(madapter_search);
                    mev_search.setLayoutManager(manager);



                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init_view();
    }

    private void init_view() {
        mtoolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);


        mvp = (ViewPager) findViewById(R.id.viewpager);
        Fragment_Adapter fragment_adapter = new Fragment_Adapter(getSupportFragmentManager());
        mvp.setAdapter(fragment_adapter);

        mtab = (TabLayout) findViewById(R.id.tab);
        mtab.setupWithViewPager(mvp);


        msearch = findViewById(R.id.search);
        hot_fragment = findViewById(R.id.hot_fragment);

        search_btn = (Button) findViewById(R.id.search_btn);
        mcancel = (Button) findViewById(R.id.cancel);
        met = (EditText) findViewById(R.id.et);

        mev_search = (RecyclerView) findViewById(R.id.recycler_search);

        search_btn.setOnClickListener(this);
        mcancel.setOnClickListener(this);


        play_stop = (ImageView) findViewById(R.id.play_stop);

        play_stop.setOnClickListener(this);

        song_list = (ImageView) findViewById(R.id.song_list);
        song_list.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.search:
                msearch.setVisibility(View.VISIBLE);
                hot_fragment.setVisibility(View.GONE);
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cancel:
                hot_fragment.setVisibility(View.VISIBLE);
                msearch.setVisibility(View.GONE);
                break;
            case R.id.search_btn:
                ShowAPI showAPI = new ShowAPI();
                final String url_search = showAPI.getSEARCH_MUSIC();

                Log.e("url",url_search);
                new Thread(new Runnable() {
                    /**
                     * url_search为接口
                     */
                    @Override
                    public void run() {
                        /**
                         * 有中文乱码问题
                         */
                        HttpUtils httpUtils = new HttpUtils("http://route.showapi.com/213-1?keyword=her&page=1&showapi_appid=19009&showapi_timestamp=20160515155922&showapi_sign=290d83f29eaa4e5c95059b48a621a517");
                        String json_str = httpUtils.getJson_string();
                        HttpTools_Search httpTools_search = new HttpTools_Search();
                        Log.e("json_2",json_str);
                        List<Search_music.ShowapiResBodyBean.PagebeanBean.ContentlistBean> list = httpTools_search.getMlist(json_str);

                        Message message = new Message();
                        message.what = 2;
                        message.obj = list;
                        handler.sendMessage(message);

                    }
                }).start();


                break;
            case R.id.play_stop:

                Message message = new Message();
                message.what = 1;
                message.obj = i;
                handler.sendMessage(message);
                break;

            case R.id.song_list:
                Toast.makeText(MainActivity.this,"点击成功",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
