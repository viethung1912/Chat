package com.example.demo.chat.adapter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPageAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;
    private List<String> titlelist;

    public ViewPageAdapter(@NonNull FragmentManager fm) {
        super(fm);
        fragmentList = new ArrayList<>();
        titlelist = new ArrayList<>();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void addFragment(Fragment fragment, String title) {
        fragmentList.add(fragment);
        titlelist.add(title);
        Log.d("kiemtra", fragmentList.size() + "");
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titlelist.get(position);

    }
}
