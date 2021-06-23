package com.azadi.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.azadi.activity.ShowPostActivity;
import com.azadi.databinding.RowhomelayoutBinding;
import com.azadi.model.HomeModel;
import com.azadi.model.SelectCatModel;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<HomeModel> homeList;

    public HomeAdapter(Context mContext, ArrayList<HomeModel> homeList){

        this.mContext=mContext;
        this.homeList=homeList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(RowhomelayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        HomeModel model=homeList.get(position);
        holder.rowhomelayoutBinding.image.setImageResource(model.getImage());
        holder.rowhomelayoutBinding.llContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, ShowPostActivity.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return homeList == null ? 0 : homeList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private RowhomelayoutBinding rowhomelayoutBinding;
        public MyViewHolder(RowhomelayoutBinding rowhomelayoutBinding) {
            super(rowhomelayoutBinding.getRoot());
            this.rowhomelayoutBinding=rowhomelayoutBinding;
        }
    }
}
