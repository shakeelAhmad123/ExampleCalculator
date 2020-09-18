package com.example.examplecalculator;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;

public class My_Gallery_Adapter extends RecyclerView.Adapter<My_Gallery_Adapter.ViewHolder> {


    ArrayList<String> list;
    Context context;


    public My_Gallery_Adapter(Context context, ArrayList<String> list){

       this.context = context;
        this.list = list;

    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_items,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context)
                .load(new File(list.get(position)))
                .into(holder.myimageview);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView myimageview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            myimageview = itemView.findViewById(R.id.myitemsview);

        }
    }
}
