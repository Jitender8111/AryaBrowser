package com.jitendera.jitenderaarya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;


public class Downloader extends AppCompatActivity {

  /*  TextView downloading,downloaded;*/
    ViewPager viewPager;
    PagerViewAdepter pagerViewAdepter;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downloader);


        viewPager = (ViewPager) findViewById(R.id.myViewPager);
        tabLayout = (TabLayout) findViewById(R.id.DownloadingtabLayout);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

       /* viewPager.setAdapter(pagerViewAdepter);*/


    }

    private void setupViewPager(ViewPager viewPager){

        pagerViewAdepter = new PagerViewAdepter(getSupportFragmentManager());
        pagerViewAdepter.addFragment(new DownloadingFragments(),"Downloading");
        pagerViewAdepter.addFragment(new DownloadedFragments(),"Downloaded");
        viewPager.setAdapter(pagerViewAdepter);

    }
}
