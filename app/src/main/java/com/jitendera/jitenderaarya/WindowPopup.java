package com.jitendera.jitenderaarya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class WindowPopup extends AppCompatActivity {

    Button btnnews,btnyoutube,btnMoviesflix,btnSarkari,btnfacebook,btnDown;
    ImageButton btnScan,btnShareurl,btnfeedback,btncontact,btndownLoad,btnLivetv;
    String email,subject,body;
    Context context;
    BottomSheetDialog bottom_sheet_dialog;
    TextView txtMailLink,txtWebsiteLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_popup);

        btnnews = findViewById(R.id.btnNews);
        btnyoutube = findViewById(R.id.btnYoutube);
        btnMoviesflix = findViewById(R.id.btnMoviezflix);
        btnSarkari = findViewById(R.id.btnSarkariresult);
        btnfacebook = findViewById(R.id.btnFacebook);
        btnDown = findViewById(R.id.btndown);
        btnScan = findViewById(R.id.btnScanBarCode);
        btnShareurl = findViewById(R.id.btnShareurl);
        btnfeedback = findViewById(R.id.helpAndFeedback);
        btncontact = findViewById(R.id.btnContactUs);
        btndownLoad = findViewById(R.id.btndownLoad);
        btnLivetv = findViewById(R.id.btnLiveTv);


        createBottomSheetDialog();

        DisplayMetrics dm = new DisplayMetrics();   // this object use to show the resolution of current activity or window
        getWindowManager().getDefaultDisplay().getMetrics(dm);   // get resolutions and pass in this method

        int width = dm.widthPixels;
        int height = dm.heightPixels;


        getWindow().setLayout( (int) (width*.9),(int) (height*.6));  // .6 means height in 60% and .9 means width is 90% show on screen

 //-----------------Click listener Event       //----------------------------------------------

        btnnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(WindowPopup.this, "latest News upcoming soon", Toast.LENGTH_SHORT).show();
                Intent netnews = new Intent(WindowPopup.this,NewsInWrittenForm.class);
                startActivity(netnews);
            }
        });

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent itss = new Intent(WindowPopup.this,ReaderActivityForScanQrCode.class);
                startActivity(itss);
            }
        });

        btnShareurl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent itShare = new Intent(Intent.ACTION_SEND);
                itShare.setType("text/plain");
                String shareBody ="Fast And Secure Browser";
                String shareSHub = "Arya Browser";
                itShare.putExtra(Intent.EXTRA_SUBJECT,shareSHub);
                itShare.putExtra(Intent.EXTRA_TEXT,shareBody);
                startActivity(Intent.createChooser(itShare,"Arya Browser Share With"));

            }
        });

        btnfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  fd = new Intent(WindowPopup.this,FeedBack.class);
                startActivity(fd);
                finish();





            }
        });

        btncontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bottom_sheet_dialog.show();

            }
        });

        btnyoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent ytb = new Intent(Intent.ACTION_VIEW);
                ytb.setData(Uri.parse("https://www.youtube.com/"));
                startActivity(ytb);

            }
        });

        btnMoviesflix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent mf = new Intent(Intent.ACTION_VIEW);
                mf.setData(Uri.parse("https://moviezflix.co.in/"));
                startActivity(mf);

            }
        });

        btnSarkari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent s = new Intent(Intent.ACTION_VIEW);
                s.setData(Uri.parse("https://www.sarkariresult.com"));
                startActivity(s);

            }
        });
        btnfacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent fb = new Intent(Intent.ACTION_VIEW);
                fb.setData(Uri.parse("https://www.facebook.com/"));
                startActivity(fb);

            }
        });
        btndownLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent downloadd = new Intent(WindowPopup.this,Downloader.class);

                startActivity(downloadd);
            }
        });

        btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WindowPopup.this.finish();
            }
        });

        btnLivetv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent livetv = new Intent(WindowPopup.this, LiveTvList.class);
                startActivity(livetv);

            }
        });



    }

    private void createBottomSheetDialog(){

        if (bottom_sheet_dialog==null){

            View vw = LayoutInflater.from(this).inflate(R.layout.contactactivitybottomsheet,null);
            /*history =vw.findViewById(R.id.history);
            Exithistory = vw.findViewById(R.id.Exit_history);
            newTab = vw.findViewById(R.id.newTab);

            newTab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                }
            });

            history.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent its = new Intent(MainActivity.this,HistoryList.class);
                    startActivity(its);
                }
            });*/
            txtMailLink = vw.findViewById(R.id.txtLinkMail);

            txtWebsiteLink = vw.findViewById(R.id.txtWebsiteLink);
            TextView txtMyresume = vw.findViewById(R.id.txtResumeLink);

//------------------------------used for link clickable -----------------------------------------------------
            /*String text = "My Website Link";

             SpannableString ss = new SpannableString(text);
             ClickableSpan clickableSpan1 = new ClickableSpan() {
                 @Override
                 public void onClick(@NonNull View view) {
                     Toast.makeText(context, "Mail Selected", Toast.LENGTH_SHORT).show();
                 }
             };

            ClickableSpan clickableSpan2 = new ClickableSpan() {
                @Override
                public void onClick(@NonNull View view) {

                }
            };

            ss.setSpan(clickableSpan1,0,16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
             txtMailLink.setText(ss);   */
            txtMyresume.setMovementMethod(LinkMovementMethod.getInstance());
             txtMailLink.setMovementMethod(LinkMovementMethod.getInstance());
            txtWebsiteLink.setMovementMethod((LinkMovementMethod.getInstance()));

            //-------------------------------end of link clickable--------------------------------------------------------

            bottom_sheet_dialog = new BottomSheetDialog(this);
            bottom_sheet_dialog.setContentView(vw);



        }

    }


}
