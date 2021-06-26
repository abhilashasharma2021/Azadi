package com.azadi.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.azadi.R;
import com.azadi.activity.ProfileActivity;
import com.azadi.activity.ShowPostActivity;
import com.azadi.adapter.HomeAdapter;
import com.azadi.databinding.FragmentHomeBinding;
import com.azadi.model.HomeModel;

import java.util.ArrayList;


public class HomeFrag extends Fragment {

  FragmentHomeBinding binding;
    private Context context;
    private View view;
    ArrayList<HomeModel>homeModelArrayList=new ArrayList<>();
    StaggeredGridLayoutManager staggeredGridLayoutManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentHomeBinding.inflate(getLayoutInflater(),container,false);
        view=binding.getRoot();
        context=getActivity();
      binding.cdProfile.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              startActivity(new Intent(getActivity(), ProfileActivity.class));
          }
      });
        binding.card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),ShowPostActivity.class));
            }
        });

        staggeredGridLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        binding.rvHome.setLayoutManager(staggeredGridLayoutManager);


        showData();
        return view;
    }

    private void showData(){
        HomeModel model=new HomeModel(R.drawable.images);
        for (int i = 0; i <12 ; i++) {
            homeModelArrayList.add(model);
        }
        HomeAdapter adapter=new HomeAdapter(getActivity(),homeModelArrayList);
        binding.rvHome.setAdapter(adapter);

    }
}