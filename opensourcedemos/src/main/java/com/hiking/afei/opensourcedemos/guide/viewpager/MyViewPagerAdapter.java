package com.hiking.afei.opensourcedemos.guide.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Afei on 2017/11/28.
 */

public class MyViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments=new ArrayList<Fragment>();

    public MyViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    public MyViewPagerAdapter(FragmentManager fm,List<Fragment> fragments) {
        super(fm);
        this.fragments=fragments;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }
}
