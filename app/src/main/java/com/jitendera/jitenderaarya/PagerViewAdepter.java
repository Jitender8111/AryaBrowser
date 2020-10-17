package com.jitendera.jitenderaarya;




import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

class PagerViewAdepter extends FragmentPagerAdapter {

     private final ArrayList<Fragment> fragmentsList =new ArrayList<>();
    private final ArrayList<String> fragmentsTitle =new ArrayList<>();

    public PagerViewAdepter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {


        return fragmentsList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentsList.size();
    }

    public  void addFragment (Fragment fragment,String title){

        fragmentsList.add(fragment);
        fragmentsTitle.add(title);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentsTitle.get(position);
    }
}
