package com.roushan.assignment1;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ImageSliderActivity extends AppCompatActivity {

    ViewPager viewPager;
    ImageSliderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_slider);

        viewPager = (ViewPager) findViewById(R.id.image_view_pager);
        adapter = new ImageSliderAdapter(ImageSliderActivity.this, NavDrawerActivity.images_drawable);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(CarouselRVAdapter.carousel_selected_pos);
    }
}
