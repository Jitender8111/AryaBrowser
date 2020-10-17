package com.jitendera.jitenderaarya;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DownloadingFragments extends Fragment {

     //     filename.setText(getIntent().getStringExtra("myurll"));


   String[] name ={"hello.mp3","neera_ishq.mp4"};
   int[] myImage = {R.drawable.ic_file_download_black_24dp,R.drawable.ic_pause_black_24dp};

    // Progress dialog type (0 - for Horizontal progress bar)
    public static final int progress_bar_type = 0;

    ListView listView;
    public DownloadingFragments() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_downloading_fragments, container, false);
        listView = view.findViewById(R.id.listViewDownloading);
        //     filename.setText(getIntent().getStringExtra("myurll"));

       ImageButton imgButtonDownloadingcustome = view.findViewById(R.id.imgButtonDownloadingcustome);
       TextView txttitleforDownloading = view.findViewById(R.id.txttitleforDownloading);
       ProgressBar downloadingProgressbar = view.findViewById(R.id.Downloadingprogressbar);

        /*fileurl.setText(getIntent().getStringExtra("myurl"));*/

        customeListVw vv = new customeListVw();
        listView.setAdapter(vv);
        return view;


    }
    class customeListVw extends BaseAdapter{

        @Override
        public int getCount() {
            return myImage.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
           View v = getLayoutInflater().inflate(R.layout.downloadingcustomeviewlayout,null);
            ImageButton img = v.findViewById(R.id.imgButtonDownloadingcustome);
            TextView tv = v.findViewById(R.id.txtPerprogressshow);
            TextView t = v.findViewById(R.id.txttitleforDownloading);
            ProgressBar p = v.findViewById(R.id.Downloadingprogressbar);


            img.setImageResource(myImage[position]);
            t.setText(name[position]);
            return v;
        }
    }


}




