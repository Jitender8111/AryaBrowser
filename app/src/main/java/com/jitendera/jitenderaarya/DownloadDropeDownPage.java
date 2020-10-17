package com.jitendera.jitenderaarya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class DownloadDropeDownPage extends AppCompatActivity {

    String title = getIntent().getStringExtra("myurll"); // get title from main activity
    ListView downloadingDropeDownlist_one, downloadingDropeDownlist_two;
    String[] name = {title, "neera_ishq.mp4"};
    int[] myImage = {R.drawable.ic_file_download_black_24dp, R.drawable.ic_pause_black_24dp};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_drope_down_page);



        downloadingDropeDownlist_one = findViewById(R.id.listviewDropedown_One);
        downloadingDropeDownlist_two = findViewById(R.id.listviewDropedown_Two);


    }

    class myAdapter extends BaseAdapter {

     String[] title;
     int[] img;
     Context context;

        public myAdapter(String[] title, int[] img, Context context) {
            this.title = title;
            this.img = img;
            this.context = context;
        }

        @Override
        public int getCount() {
            return title.length;
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View v = getLayoutInflater().inflate(R.layout.downloadingcustomeviewlayout,null);

            return null;
        }
    }
}



