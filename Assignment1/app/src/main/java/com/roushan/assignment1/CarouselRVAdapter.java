package com.roushan.assignment1;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class CarouselRVAdapter extends RecyclerView.Adapter<CarouselRVAdapter.CarouselRViewHolder> {

    private Context context;
    private int[] images;
    private View view;
    public static int carousel_selected_pos;
 //   private NavDrawerActivity nav_activity;

    public static class CarouselRViewHolder extends RecyclerView.ViewHolder {

		ImageView img;

        public CarouselRViewHolder(View itemView) {
            super(itemView);
            this.img = (ImageView) itemView.findViewById(R.id.carousel_img);
        }
    }

    public CarouselRVAdapter(int[] images, Context context) {
        this.images = images;
        this.context = context;
    }

    @Override
    public CarouselRViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.carousel_item,
                    parent, false);

    //    view.setOnClickListener(MainActivity.myOnClickListener);

        CarouselRViewHolder viewHolder = new CarouselRViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CarouselRViewHolder holder, final int listPosition) {

        holder.img.setImageResource(images[listPosition]);
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carousel_selected_pos = listPosition;
                Intent i = new Intent(context, ImageSliderActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return images.length;
    }
}
