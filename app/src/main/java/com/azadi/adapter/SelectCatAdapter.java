package com.azadi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.azadi.databinding.RowselectcatlayoutBinding;
import com.azadi.model.SelectCatModel;

import java.util.ArrayList;

public class SelectCatAdapter extends RecyclerView.Adapter<SelectCatAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<SelectCatModel> catList;

    public SelectCatAdapter(Context mContext,ArrayList<SelectCatModel> catList){

        this.mContext=mContext;
        this.catList=catList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(RowselectcatlayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        SelectCatModel model=catList.get(position);
        holder.rowselectcatlayoutBinding.ivCategory.setImageResource(model.getImage());

    }

    @Override
    public int getItemCount() {
        return catList == null ? 0 : catList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private RowselectcatlayoutBinding rowselectcatlayoutBinding;
        public MyViewHolder(RowselectcatlayoutBinding rowselectcatlayoutBinding) {
            super(rowselectcatlayoutBinding.getRoot());
            this.rowselectcatlayoutBinding=rowselectcatlayoutBinding;
        }
    }
}
