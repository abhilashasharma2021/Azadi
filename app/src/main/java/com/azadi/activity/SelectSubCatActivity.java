package com.azadi.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.azadi.R;
import com.azadi.adapter.SelectCatAdapter;
import com.azadi.adapter.SelectSubCatAdapter;
import com.azadi.databinding.ActivityChooseCategoryBinding;
import com.azadi.databinding.ActivityChooseSubCatBinding;
import com.azadi.model.SelectCatModel;
import com.azadi.model.SelectSubCatModel;

import java.util.ArrayList;

public class SelectSubCatActivity extends AppCompatActivity {
    ActivityChooseSubCatBinding binding;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<SelectSubCatModel> subCatList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityChooseSubCatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.rvSubCat.setHasFixedSize(true);
        layoutManager=new GridLayoutManager(SelectSubCatActivity.this,2, RecyclerView.VERTICAL,false);
        binding.rvSubCat.setLayoutManager(layoutManager);

        binding.btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectSubCatActivity.this,LoginActivity.class));
            }
        });

        subCatList=new ArrayList<>();
        showSubCategory();
    }
    private void showSubCategory(){
        SelectSubCatModel model=new SelectSubCatModel(R.drawable.running);

        for (int i = 0; i <4 ; i++) {

            subCatList.add(model);

        }
        SelectSubCatAdapter adapter = new SelectSubCatAdapter(SelectSubCatActivity.this, subCatList);
        binding.rvSubCat.setAdapter(adapter);


    }
}