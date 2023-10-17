package com.example.minifood.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.minifood.R;
import com.example.minifood.category_domain;

import java.time.Instant;
import java.util.ArrayList;

public class categoryAdapter extends RecyclerView.Adapter<categoryAdapter.viewHolder> {
      ArrayList<category_domain>category_domains;

    public categoryAdapter(ArrayList<category_domain> category_domains) {
        this.category_domains = category_domains;
    }

    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_categoru,parent,false);
        return new viewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
    holder.categoryname.setText(category_domains.get(position).getTitle());
/*
        String picurl="";
    switch (position){
        case 0:{
            picurl="cat_1";
            holder.mainlayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.background_category));
            break;
        }
        case 1:{
            picurl="cat_2";
            break;
        }
        case 2:{
            picurl="cat_3";
            break;
        }
        case 3:{
            picurl="cat_4";
            break;
        }
        case 4:{
            picurl="cat_5";
            break;
        }
    }
    int drawableResourceId=holder.itemView.getContext().getResources()
            .getIdentifier(picurl,"drawable",holder.itemView.getContext().getPackageName());


        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.categorypic);
*/

        int drawableResourceId = holder.itemView.getContext().getResources()
                .getIdentifier(category_domains.get(position).getPic(), "drawable", holder.itemView.getContext().getPackageName());


        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.categorypic);

    }

    @Override
    public int getItemCount() {
        return category_domains.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView categoryname;
        ImageView categorypic;
        ConstraintLayout mainlayout;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            categoryname=itemView.findViewById(R.id.categoryname);
            categorypic=itemView.findViewById(R.id.categorypic);
            mainlayout=itemView.findViewById(R.id.mainlayaout);
        }
    }
}
