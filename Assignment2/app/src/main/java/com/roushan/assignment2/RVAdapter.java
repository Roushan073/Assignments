package com.roushan.assignment2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.RVViewHolder> {

    private ArrayList<DataModel> dataSet;
    private Context context;

    public static String row_num;

    public static class RVViewHolder extends RecyclerView.ViewHolder {

        TextView t1,  t2, t3;
        ImageView left, center, right;
        CardView cv;

        public RVViewHolder(View itemView) {
            super(itemView);
            this.t1 = (TextView) itemView.findViewById(R.id.t1);
            this.t2 = (TextView) itemView.findViewById(R.id.t2);
            this.t3 = (TextView) itemView.findViewById(R.id.t3);
            this.left = (ImageView) itemView.findViewById(R.id.img_left);
            this.center = (ImageView) itemView.findViewById(R.id.img_center);
            this.right = (ImageView) itemView.findViewById(R.id.img_right);
            this.cv = (CardView) itemView.findViewById(R.id.cv);
        }
    }

    public RVAdapter(ArrayList<DataModel> data, Context context) {
        this.dataSet = data;
        this.context = context;
    }

    @Override
    public RVViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);

        RVViewHolder viewHolder = new RVViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RVViewHolder holder, final int listPosition) {

        holder.t1.setText(dataSet.get(listPosition).getText1());
        holder.t2.setText(dataSet.get(listPosition).getText2());
        holder.t3.setText(dataSet.get(listPosition).getText3());

        holder.left.setImageResource(dataSet.get(listPosition).getImg_left());
        holder.center.setImageResource(dataSet.get(listPosition).getImg_center());
        holder.right.setImageResource(dataSet.get(listPosition).getImg_right());

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row_num = String.valueOf(listPosition);
                Intent i = new Intent(context, ItemProfile.class);
                i.setFlags(i.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
