package com.jitendera.jitenderaarya;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class LiveTv extends AppCompatActivity {

     private String videoUrl = "https://www.radiantmediaplayer.com/media/bbb-360p.mp4";
    private String News24Live = "https://vidcdn.vidgyor.com/news24-origin/liveabr/playlist.m3u8";




 /*  private String zeeNews = "https://pockettv.xyz/api/zee.m3u8?c-zeenews";
    private String DelhiAjjatk = "https://vidcdn.vidgyor.com/dilliaajtak-origin/liveabr/dilliaajtak-origin/live1/chunks.m3u8";
  /*  private String NewsNation = "https://hls.newsnation.in/NN_HTTP_LIVE/ngrp:mystream_all/playlist.m3u8";
    private String abpNews = "https://abp-i.akamaihd.net/hls/live/765529/abphindi/masterhls_1564.m3u8";
*/
     private ProgressDialog pd;
     VideoView VideoViewLivetv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //----------------------hide status bar with this code-----------------------------------------------------
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //-------------------------------------end ----------------------------------------------------
        setContentView(R.layout.activity_live_tv);
        MediaController mediaController =  new MediaController(this);


        VideoViewLivetv = findViewById(R.id.VideoViewLivetv);
        pd = new ProgressDialog(this);
        pd.setMessage("Buffering...");
        pd.setCancelable(true);

        Toast.makeText(this, "Loading Please Wait...", Toast.LENGTH_LONG).show();



        playVideo();
    }

    private void playVideo() {

        try{

            getWindow().setFormat(PixelFormat.TRANSLUCENT);
            MediaController mediaController =  new MediaController(this);
            mediaController.setAnchorView(VideoViewLivetv);
            mediaController.setMediaPlayer(VideoViewLivetv);



            Uri videourls = Uri.parse(videoUrl);
            VideoViewLivetv.setMediaController(mediaController);

            VideoViewLivetv.setVideoURI(videourls);
            VideoViewLivetv.requestFocus();
            VideoViewLivetv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {

                    pd.dismiss();

                    VideoViewLivetv.start();

                }
            });
            VideoViewLivetv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {

                }
            });


        }catch (Exception e){
            pd.dismiss();
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
