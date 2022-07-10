package com.example.myapplication_project1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter<myAdapter.myViewHolder> {


    Context context;
    ArrayList<Bus> list;

    public myAdapter(Context context, ArrayList<Bus> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.list,parent,false);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        Bus buses=list.get(position);
        holder.bno.setText(buses.getTextno());
        holder.dri.setText(buses.getTextdriver());
        holder.route.setText(buses.getTextroute());
        holder.phone.setText(buses.getTextphone());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder{
        TextView bno,dri,route,phone;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            bno=itemView.findViewById(R.id.tbno);
            dri=itemView.findViewById(R.id.tdri);
            route=itemView.findViewById(R.id.troute);
            phone=itemView.findViewById(R.id.tphone);
        }
    }
}
