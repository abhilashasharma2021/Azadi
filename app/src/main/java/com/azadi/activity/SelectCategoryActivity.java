package com.azadi.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.azadi.R;
import com.azadi.adapter.SelectCatAdapter;
import com.azadi.databinding.ActivityChooseCategoryBinding;
import com.azadi.databinding.ActivitySignUpBinding;
import com.azadi.model.SelectCatModel;

import java.util.ArrayList;

public class SelectCategoryActivity extends AppCompatActivity {
    ActivityChooseCategoryBinding binding;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<SelectCatModel>catList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityChooseCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.rvCat.setHasFixedSize(true);
        layoutManager=new GridLayoutManager(SelectCategoryActivity.this,3, RecyclerView.VERTICAL,false);
        binding.rvCat.setLayoutManager(layoutManager);

        catList=new ArrayList<>();
        showCategory();


        binding.btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectCategoryActivity.this,SelectSubCatActivity.class));
            }
        });

    }

    private void showCategory(){
        SelectCatModel model=new SelectCatModel(R.drawable.running);

        for (int i = 0; i <6 ; i++) {

            catList.add(model);

        }
        SelectCatAdapter adapter = new SelectCatAdapter(SelectCategoryActivity.this, catList);
        binding.rvCat.setAdapter(adapter);


    }
}