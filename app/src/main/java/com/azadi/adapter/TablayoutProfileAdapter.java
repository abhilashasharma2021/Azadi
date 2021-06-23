package com.azadi.adapter;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.azadi.fragment.ChallengeFrag;
import com.azadi.fragment.DreamFrag;
import com.azadi.fragment.GoalFrag;


public class TablayoutProfileAdapter extends FragmentPagerAdapter {
    int mNumOfTabs;
    public TablayoutProfileAdapter(FragmentManager fm, int NoofTabs){
        super(fm);
        this.mNumOfTabs = NoofTabs;
    }
    @Override
    public int getCount() {
        return mNumOfTabs;
    }
    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0:
                Log.e("sdcscdsad", position+"" );
                GoalFrag goalFrag = new GoalFrag();
                return goalFrag;
            case 1:
                Log.e("sdcscdsad", position+"" );
                ChallengeFrag challengeFrag = new ChallengeFrag();
                return challengeFrag;
            case 2:
                Log.e("sdcscdsad", position+"" );
                DreamFrag dreamFrag = new DreamFrag();
                return dreamFrag;


        }
        return null;
    }
}