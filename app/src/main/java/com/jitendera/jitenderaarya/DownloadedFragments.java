package com.jitendera.jitenderaarya;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;


public class DownloadedFragments extends Fragment {

    String[] names ={"sayad.mp3"};
    int[] myImages = {R.drawable.ic_check_black_24dp};
    ListView listViews;

    public DownloadedFragments() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vw = inflater.inflate(R.layout.fragment_downloaded_fragments, container, false);


        listViews = vw.findViewById(R.id.listViewDownloaded);

        customeListVws vv = new customeListVws();
        listViews.setAdapter(vv);


        return vw;
    }

    class customeListVws extends BaseAdapter {

        @Override
        public int getCount() {
            return myImages.length;
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
            View v = getLayoutInflater().inflate(R.layout.downloadedcustomeviewlayout,null);
            ImageButton img = v.findViewById(R.id.imgButtonDownloadedcustome);
            TextView t = v.findViewById(R.id.txttitleforDownloaded);
            ProgressBar p = v.findViewById(R.id.Downloadedprogressbar);
            img.setImageResource(myImages[position]);
            t.setText(names[position]);
            return v;
        }
    }

}
