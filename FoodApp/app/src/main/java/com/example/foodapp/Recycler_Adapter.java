package com.example.foodapp;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class Recycler_Adapter extends FirebaseRecyclerAdapter<model,Recycler_Adapter.myViewHolder> {
    public Recycler_Adapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull model model) {
        holder.foodName.setText(model.getFoodName());
        holder.foodPrice.setText(model.getFoodPrice());
//        Glide.with(holder.imageView.getContext()).load(model.getPurl()).into(holder.imageView);
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singleview,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        CircleImageView imageView;
        TextView foodName,foodPrice;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            foodName=(TextView) itemView.findViewById(R.id.nameText);
            foodPrice=(TextView) itemView.findViewById(R.id.priceText);
            imageView =(CircleImageView) itemView.findViewById(R.id.img1);
        }
    }
    }