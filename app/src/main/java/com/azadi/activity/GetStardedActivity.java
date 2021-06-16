package com.azadi.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.azadi.R;
import com.azadi.databinding.ActivityForgotPasswordBinding;
import com.azadi.databinding.ActivityGetStardedBinding;

public class GetStardedActivity extends AppCompatActivity {
ActivityGetStardedBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityGetStardedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GetStardedActivity.this,MainActivity.class));
            }
        });

    }
}