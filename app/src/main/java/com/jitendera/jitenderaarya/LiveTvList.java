package com.jitendera.jitenderaarya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.ImageButton;

public class LiveTvList extends AppCompatActivity {

    private ImageButton btnDemoLiveStreaming, btnAbpNews,btnNewsTwentyFour,btnDelhiAjjatak,BtnAajTak,DDnational;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_tv_list);

        btnDemoLiveStreaming = findViewById(R.id.Btndemo);
        btnAbpNews = findViewById(R.id.BtnAbpNews);
        btnNewsTwentyFour = findViewById(R.id.BtnNews24);
        BtnAajTak = findViewById(R.id.BtnAajTak);
        btnDelhiAjjatak = findViewById(R.id.BtnDelhiAAjTak);
        DDnational = findViewById(R.id.BtnDDnational);

       btnDemoLiveStreaming.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent demolive = new Intent(LiveTvList.this,LiveTv.class);
               startActivity(demolive);

           }
       });

        btnAbpNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent demolive = new Intent(LiveTvList.this,abpnewsLive.class);
                startActivity(demolive);
            }
        });

        btnNewsTwentyFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent NewsTwenty4 = new Intent(LiveTvList.this,NewsTwentyFour.class);
                startActivity(NewsTwenty4);
            }
        });

        btnDelhiAjjatak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent demolive = new Intent(LiveTvList.this,delhiajjatkLivenews.class);
                startActivity(demolive);
            }
        });

        BtnAajTak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent aajtak = new Intent(LiveTvList.this,AajtakLiveNews.class);
                startActivity(aajtak);
            }
        });
        DDnational.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent aajtak = new Intent(LiveTvList.this,DDNationaLive.class);
                startActivity(aajtak);
            }
        });

    }
}
