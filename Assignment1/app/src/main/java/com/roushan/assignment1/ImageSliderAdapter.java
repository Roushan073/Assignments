package com.roushan.assignment1;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

class ImageSliderAdapter extends PagerAdapter {

    Context mContext;
    LayoutInflater layoutInflater;
    int[] img_res;

    public ImageSliderAdapter(Context context, int[] img_res) {
        mContext = context;
        this.img_res = img_res;
    }

    @Override
    public int getCount() {
        return img_res.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = layoutInflater.inflate(R.layout.pager_image, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.pager_iv);
        imageView.setImageResource(img_res[position]);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
