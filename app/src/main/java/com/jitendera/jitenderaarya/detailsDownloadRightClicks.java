package com.jitendera.jitenderaarya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URLConnection;
import java.sql.Connection;

public class detailsDownloadRightClicks extends AppCompatActivity {
  private Button btnfileName,btnfileUrls;
  private TextView filename,fileurl,filesize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_download_right_clicks);

        btnfileName = findViewById(R.id.btncopyfilename);
        btnfileUrls = findViewById(R.id.btnfileurl);
        filename = findViewById(R.id.fileName);
        filesize = findViewById(R.id.fileSize);
        fileurl = findViewById(R.id.fileurl);


       fileurl.setText(getIntent().getStringExtra("myurl"));
       filename.setText(getIntent().getStringExtra("myurll"));

        btnfileUrls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("EditText",fileurl.getText().toString());

                clipboardManager.setPrimaryClip(clipData);
                clipData.getDescription();
                Toast.makeText(detailsDownloadRightClicks.this, "Text Copied", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
