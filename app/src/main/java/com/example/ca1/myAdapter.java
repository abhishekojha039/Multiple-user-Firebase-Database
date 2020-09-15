package com.example.ca1;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter<myAdapter.ExampleViewHolder>{

    private Context mContext;
    RequestOptions options;
    private ArrayList<recycler> mlist;

    public myAdapter(Context context,ArrayList<recycler> list)
    {
        mContext=context;
        mlist=list;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.notify_layout,parent,false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        recycler currentItem=mlist.get(position);
        String imageurl=currentItem.getImage();
        String text=currentItem.getText();
        Picasso.get().load(imageurl).fit().centerInside().into(holder.image);
        holder.txt1.setText(text);
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder{
        public ImageView image;
        public TextView txt1;
        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.imageMath);
            txt1=itemView.findViewById(R.id.txtMath);

        }
    }
}
