package com.example.minifood.Adapter;

import static androidx.core.content.ContextCompat.startActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import com.example.minifood.MainActivity;
import com.example.minifood.R;
import com.example.minifood.category_domain;
import com.example.minifood.recommande_domain;
import com.example.minifood.showDetailsActivity;

import java.util.ArrayList;

public class RecommandAdapter extends RecyclerView.Adapter<RecommandAdapter.viewHolder> {
      ArrayList<recommande_domain>recommande_domains;

    public RecommandAdapter(ArrayList<recommande_domain> recommande_domains) {
        this.recommande_domains= recommande_domains;
    }

    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_recommande,parent,false);
        return new viewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, @SuppressLint("RecyclerView") int position) {
    holder.title.setText(recommande_domains.get(position).getTitle());
    holder.fee.setText(String.valueOf(recommande_domains.get(position).getFee()));


    int drawableResourceId=holder.itemView.getContext().getResources()
            .getIdentifier(recommande_domains.get(position).getPic(),"drawable",holder.itemView.getContext().getPackageName());


        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.pic);
        holder.addbn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(holder.itemView.getContext(), showDetailsActivity.class);
                intent.putExtra("object",recommande_domains.get(position));
                holder.itemView.getContext().startActivity(intent);
            }




        });
    }

    @Override
    public int getItemCount() {
        return recommande_domains.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView title,fee;
        ImageView pic;
        TextView addbn;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title1);
            fee=itemView.findViewById(R.id.fee);
            pic=itemView.findViewById(R.id.pic);
            addbn=itemView.findViewById(R.id.addbn);
        }
    }
}
