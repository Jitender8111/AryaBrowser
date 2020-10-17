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

public class abpnewsLive extends AppCompatActivity {

    private String abpNews = "https://abp-i.akamaihd.net/hls/live/765529/abphindi/masterhls_1564.m3u8";
    private ProgressDialog pd;
    VideoView VideoViewLivetvz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //----------------------hide status bar with this code-----------------------------------------------------
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //-------------------------------------end ----------------------------------------------------

        setContentView(R.layout.activity_abpnews_live);
        MediaController mediaController =  new MediaController(this);


        VideoViewLivetvz = findViewById(R.id.VideoViewAbpLiveTv);
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
            mediaController.setAnchorView(VideoViewLivetvz);
            mediaController.setMediaPlayer(VideoViewLivetvz);

            Uri videourls = Uri.parse(abpNews);
            VideoViewLivetvz.setMediaController(mediaController);

            VideoViewLivetvz.setVideoURI(videourls);
            VideoViewLivetvz.requestFocus();
            VideoViewLivetvz.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {

                    pd.dismiss();

                    VideoViewLivetvz.start();

                }
            });
            VideoViewLivetvz.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
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
