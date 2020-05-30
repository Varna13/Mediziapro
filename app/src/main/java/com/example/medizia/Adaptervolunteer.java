package com.example.medizia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adaptervolunteer extends RecyclerView.Adapter<Adaptervolunteer.OwnerViewHolder>
{

    private ArrayList<Volunteerlist> listvolunteer;
    Context context;

    Adaptervolunteer(Context context, ArrayList<Volunteerlist> itemList)
    {
        this.context = context;
        listvolunteer = itemList;
    }

    @NonNull
    @Override
    public OwnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater= LayoutInflater.from((Context) context);
        View view=layoutInflater.inflate(R.layout.cardview_volunteer,null);
        return new OwnerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final OwnerViewHolder holder, final int position)
    {
        holder.t1.setText(listvolunteer.get(position).getVname());
        holder.t2.setText(listvolunteer.get(position).getVid());
        holder.t3.setText(listvolunteer.get(position).getVphn());



    }

    @Override
    public int getItemCount()

    {
        return listvolunteer.size();
    }

    class OwnerViewHolder extends RecyclerView.ViewHolder
    {
        TextView t1,t2,t3;


        public OwnerViewHolder(@NonNull View ownerView) {
            super(ownerView);
            t1=(TextView) ownerView.findViewById(R.id.vonam);
            t2=(TextView)ownerView.findViewById(R.id.voidd);
            t3=(TextView)ownerView.findViewById(R.id.vomob);

        }
    }
}





