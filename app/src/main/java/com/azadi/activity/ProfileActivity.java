package com.azadi.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.azadi.R;
import com.azadi.adapter.TablayoutProfileAdapter;
import com.azadi.databinding.ActivityProfileBinding;
import com.azadi.databinding.ActivityShowPostBinding;
import com.google.android.material.tabs.TabLayout;

public class ProfileActivity extends AppCompatActivity {
ActivityProfileBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.tabLayout.addTab( binding.tabLayout.newTab().setText("Goal "));
        binding.tabLayout.addTab( binding.tabLayout.newTab().setText("Challenge "));
        binding.tabLayout.addTab( binding.tabLayout.newTab().setText("Dream "));
        TablayoutProfileAdapter tablayoutProfileAdapter = new TablayoutProfileAdapter(ProfileActivity.this.getSupportFragmentManager(), binding.tabLayout.getTabCount());
        binding.viewPager.setAdapter(tablayoutProfileAdapter);
        binding.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener( binding.tabLayout));
        binding.tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }
}