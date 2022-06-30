package com.example.foodapp;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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
        holder.foodName.setText(model.getName());
        holder.foodPrice.setText(model.getPrice());
        Glide.with(holder.imageView.getContext()).load(model.getPurl()).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(holder.context, Cart.class);
                intent.putExtra("name",model.getName());
                intent.putExtra("price",model.getPrice());
                intent.putExtra("purl",model.getPurl());
                holder.context.startActivity(intent);
            }
        });


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
        Context context;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            foodName=(TextView) itemView.findViewById(R.id.nameText);
            foodPrice=(TextView) itemView.findViewById(R.id.priceText);
            imageView =(CircleImageView) itemView.findViewById(R.id.img1);
            context=itemView.getContext();

        }
    }
    }