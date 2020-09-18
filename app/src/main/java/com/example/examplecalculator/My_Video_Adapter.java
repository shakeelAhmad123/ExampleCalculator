package com.example.examplecalculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;

public class My_Video_Adapter extends RecyclerView.Adapter<My_Video_Adapter.ViewHolder> {


    ArrayList<String> list;
    Context context;

    public My_Video_Adapter(Context context, ArrayList<String> list){

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
                .into(holder.myimageview_videos);

    }

    @Override
    public int getItemCount() {


        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView myimageview_videos;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            myimageview_videos = itemView.findViewById(R.id.myitemsview);

        }
    }
}
