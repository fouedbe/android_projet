package com.example.minifood.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.minifood.Interface.ChangeNumberitems;

import com.example.minifood.R;
import com.example.minifood.helper.ManagemenTocart;
import com.example.minifood.recommande_domain;

import java.util.ArrayList;

public class cartlistAdapter extends RecyclerView.Adapter<cartlistAdapter.ViewHolder> {
        private ArrayList<recommande_domain> recommande_domains;
        private ManagemenTocart managemenTocart;
        private ChangeNumberitems changeNumberitemsp;

    public cartlistAdapter(ArrayList<recommande_domain> recommande_domains, Context context, ChangeNumberitems changeNumberitemsp) {
        this.recommande_domains = recommande_domains;
        this.managemenTocart = new ManagemenTocart(context);
        this.changeNumberitemsp = changeNumberitemsp;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View inflate=LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart,parent,false);


        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull cartlistAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.title.setText(recommande_domains.get(position).getTitle());
        holder.feeerchitem.setText(String.valueOf(recommande_domains.get(position).getFee()));
        try {
            holder.totalerchitem.setText(String.valueOf(Math.round((recommande_domains.get(position).getNumb() * recommande_domains.get(position).getFee() * 100) / 100)));
        }catch(NullPointerException ex){
            ex.printStackTrace();
        }
        holder.num.setText(String.valueOf(recommande_domains.get(position).getNumb()));

        int drawableResourceId=holder.itemView.getContext().getResources()
                .getIdentifier(recommande_domains.get(position).getPic(),"drawable",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.pic);
        holder.plusitems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           managemenTocart.plusNumberfood(recommande_domains, position, new ChangeNumberitems() {
               @Override
               public void changed() {
                   notifyDataSetChanged();
                   changeNumberitemsp.changed();
               }
           });
            }
        });
        holder.minitems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                managemenTocart.minusNumberfood(recommande_domains, position, new ChangeNumberitems() {


                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberitemsp.changed();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return recommande_domains.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

       TextView title,feeerchitem;
       ImageView pic,plusitems,minitems;
        TextView totalerchitem,num;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title=itemView.findViewById(R.id.title12);
            feeerchitem=itemView.findViewById(R.id.feerchitems);
            pic=itemView.findViewById(R.id.piccart);
            totalerchitem=itemView.findViewById(R.id.totalitems);
            plusitems=itemView.findViewById(R.id.pluscartbtn);
            minitems=itemView.findViewById(R.id.minuscartbtn);
            num=itemView.findViewById(R.id.numberitem);
        }
    }
}
