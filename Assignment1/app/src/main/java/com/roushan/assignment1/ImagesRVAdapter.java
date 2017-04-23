package com.roushan.assignment1;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ImagesRVAdapter extends RecyclerView.Adapter<ImagesRVAdapter.ImagesRViewHolder> {

    private Context context;
    private int[] images;
    private View view;
    public static int selected_pos;
 //   private NavDrawerActivity nav_activity;
    FragmentTransaction fragmentTransaction;

    public static class ImagesRViewHolder extends RecyclerView.ViewHolder {

		ImageView img;

        public ImagesRViewHolder(View itemView) {
            super(itemView);
            this.img = (ImageView) itemView.findViewById(R.id.card_img);
        }
    }

    public ImagesRVAdapter(int[] images, Context context) {
        this.images = images;
        this.context = context;
    }

    @Override
    public ImagesRViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.img_card_layout,
                    parent, false);

    //    view.setOnClickListener(MainActivity.myOnClickListener);

        ImagesRViewHolder viewHolder = new ImagesRViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ImagesRViewHolder holder, final int listPosition) {

        holder.img.setImageResource(images[listPosition]);
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected_pos = listPosition;
                Intent i = new Intent(context, CarouselView.class);
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
