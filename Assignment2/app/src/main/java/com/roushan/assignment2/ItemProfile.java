package com.roushan.assignment2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemProfile extends AppCompatActivity {

    TextView tv, tv2;
    ImageView prof;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_profile);

        tv = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        prof = (ImageView) findViewById(R.id.profile_img);

        tv.setText("Name : " + RVAdapter.row_num);
        tv2.setText("Profile : " + RVAdapter.row_num);
        prof.setImageResource(NavDrawerActivity.data.get(Integer.valueOf(RVAdapter.row_num)).getImg_center());

    }
}
