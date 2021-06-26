package com.azadi.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.azadi.R;
import com.azadi.databinding.ActivityGoalDescriptionBinding;
import com.azadi.databinding.ActivityGoalMapBinding;
import com.azadi.databinding.ActivityGoalTimerBinding;

public class GoalMapActivity extends AppCompatActivity {
    ActivityGoalMapBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= ActivityGoalMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.flat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GoalMapActivity.this,GoalPhotoActivity.class));
            }
        });
    }
}