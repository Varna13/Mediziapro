package com.example.medizia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterOrder extends RecyclerView.Adapter<AdapterOrder.OwnerViewHolder>
{
    private ArrayList<Ordering> listorder;

    Context context;

    AdapterOrder(Context context, ArrayList<Ordering> itemList)
    {
        this.context = context;
        listorder = itemList;
    }

    @NonNull
    @Override
    public OwnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater=LayoutInflater.from((Context) context);
        View view=layoutInflater.inflate(R.layout.cardview_layout,null);
        return new OwnerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final OwnerViewHolder holder, final int position)
    {
        holder.t1.setText(listorder.get(position).getPrescribtion());
        holder.t2.setText(listorder.get(position).getNo_of_medicine());
        holder.t3.setText(listorder.get(position).getDldate());

    }

    @Override
    public int getItemCount()

    {
        return listorder.size();
    }

    class OwnerViewHolder extends RecyclerView.ViewHolder
    {
        TextView t1,t2,t3,t4;


        public OwnerViewHolder(@NonNull View ownerView) {
            super(ownerView);
            t1=(TextView) ownerView.findViewById(R.id.ordermedpre);
            t2=(TextView)ownerView.findViewById(R.id.ordermednum);
            t3=(TextView)ownerView.findViewById(R.id.ordermeddate);
            t4=(TextView)ownerView.findViewById(R.id.orderdeldate);

        }
    }
}

