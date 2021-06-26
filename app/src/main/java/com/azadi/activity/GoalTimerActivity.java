package com.azadi.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.azadi.R;
import com.azadi.databinding.ActivityGoalDescriptionBinding;
import com.azadi.databinding.ActivityGoalTimerBinding;

public class GoalTimerActivity extends AppCompatActivity {
ActivityGoalTimerBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityGoalTimerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GoalTimerActivity.this,GoalMapActivity.class));
            }
        });
    }
}