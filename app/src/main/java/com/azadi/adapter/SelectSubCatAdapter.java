package com.azadi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.azadi.databinding.RowselectcatlayoutBinding;
import com.azadi.model.SelectCatModel;
import com.azadi.model.SelectSubCatModel;

import java.util.ArrayList;

public class SelectSubCatAdapter extends RecyclerView.Adapter<SelectSubCatAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<SelectSubCatModel> subCatList;

    public SelectSubCatAdapter(Context mContext, ArrayList<SelectSubCatModel> subCatList){

        this.mContext=mContext;
        this.subCatList=subCatList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(RowselectcatlayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        SelectSubCatModel model=subCatList.get(position);
        holder.rowselectcatlayoutBinding.ivCategory.setImageResource(model.getImage());

    }

    @Override
    public int getItemCount() {
        return subCatList == null ? 0 : subCatList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private RowselectcatlayoutBinding rowselectcatlayoutBinding;
        public MyViewHolder(RowselectcatlayoutBinding rowselectcatlayoutBinding) {
            super(rowselectcatlayoutBinding.getRoot());
            this.rowselectcatlayoutBinding=rowselectcatlayoutBinding;
        }
    }
}
