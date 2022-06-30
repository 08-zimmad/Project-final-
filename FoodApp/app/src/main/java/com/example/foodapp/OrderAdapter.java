package com.example.foodapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.viewholder>
{
  ArrayList<OrderModel> dataholder;
  Context context;

    public OrderAdapter(ArrayList<OrderModel> dataholder, Context context) {
        this.dataholder = dataholder;
        this.context=context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(context).inflate(R.layout.order_single_view,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.viewholder holder, int position) {
        holder.foodName.setText(dataholder.get(position).getName());
        holder.foodPrice.setText(dataholder.get(position).getPrice());
        holder.foodQuantity.setText(dataholder.get(position).getQuantity());

    }


    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    class viewholder extends RecyclerView.ViewHolder
    {
        TextView foodName,foodPrice,foodQuantity;
        public viewholder(@NonNull View itemView)
        {
            super(itemView);
            foodName=(TextView)itemView.findViewById(R.id.orderNameText);
            foodPrice=(TextView)itemView.findViewById(R.id.OrderPriceText2);
            foodQuantity=(TextView)itemView.findViewById(R.id.orderQuantityText);

        }
    }

}
