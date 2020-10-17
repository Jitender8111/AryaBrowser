package com.jitendera.jitenderaarya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;

public class BrowserSettings extends AppCompatActivity {
    ImageButton btnBackSetting;
    Switch btnSwitchDayNightMode,btnSwitchNotification,btnSwitchDeskTopMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser_settings);
        btnBackSetting = findViewById(R.id.btnCancelSetting);
        btnSwitchDayNightMode = findViewById(R.id.btnSwitchDayNightMode);
        btnSwitchDeskTopMode = findViewById(R.id.btnSwitchDeskTopMode);
        btnSwitchNotification = findViewById(R.id.btnSwitchNotification);


        btnBackSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                BrowserSettings.this.finish();

            }
        });

        btnSwitchDayNightMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




            }
        });

        btnSwitchDeskTopMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (btnSwitchDeskTopMode.isChecked()){



                }else{

                }
            }
        });

        btnSwitchNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });




    }
}
