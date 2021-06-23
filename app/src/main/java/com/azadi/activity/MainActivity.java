package com.azadi.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.azadi.R;
import com.azadi.databinding.ActivityMainBinding;
import com.azadi.fragment.HomeFrag;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFrag()).commit();
        }




        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFrag()).commit();
            }
        });


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFrag()).commit();
                break;
        }
        return true;
    }
}