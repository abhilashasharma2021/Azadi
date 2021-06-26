package com.azadi.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.azadi.R;
import com.azadi.databinding.ActivityGoalDescriptionBinding;

public class GoalDescriptionActivity extends AppCompatActivity {
ActivityGoalDescriptionBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityGoalDescriptionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GoalDescriptionActivity.this,GoalTimerActivity.class));
            }
        });

    }
}