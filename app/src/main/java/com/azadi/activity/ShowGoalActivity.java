package com.azadi.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.azadi.adapter.TablayoutProfileAdapter;
import com.azadi.databinding.ActivityShowGoalBinding;
import com.google.android.material.tabs.TabLayout;

public class ShowGoalActivity extends AppCompatActivity {
ActivityShowGoalBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivityShowGoalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.cardgoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShowGoalActivity.this,GoalDescriptionActivity.class));
            }
        });
        binding.txCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShowGoalActivity.this, CreateLifeGoalActivity.class));
            }
        });


        binding.tabLayout.addTab( binding.tabLayout.newTab().setText("Goal "));
        binding.tabLayout.addTab( binding.tabLayout.newTab().setText("Challenge "));
        binding.tabLayout.addTab( binding.tabLayout.newTab().setText("Dream "));
        TablayoutProfileAdapter tablayoutProfileAdapter = new TablayoutProfileAdapter(ShowGoalActivity.this.getSupportFragmentManager(), binding.tabLayout.getTabCount());
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