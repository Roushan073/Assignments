package com.roushan.assignment2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.widget.ProfilePictureView;

import java.util.ArrayList;
import java.util.HashMap;

public class NavDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ProfilePictureView nav_header_iv;
    TextView nav_header_tv;
    Toolbar toolbar;
    NavigationView navigationView;
    FragmentTransaction fragmentTransaction;
    Session session;
    private static RecyclerView.Adapter adapter;
    private static RecyclerView recyclerView;

    Button load_more_btn;
    ProgressDialog p, p1;
    int next, view_flag = 0;
    public static ArrayList<DataModel> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_nav_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.nav_toolbar);
        setSupportActionBar(toolbar);

        session = new Session(NavDrawerActivity.this);

        p1 = new ProgressDialog(NavDrawerActivity.this);
        p1.setMessage("Loading ...");
        p1.setCancelable(false);
        p1.show();
        // DISPLAY PROGRESS FOR 2 SECONDS ---
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                p1.dismiss();
            }
        }, 2500);

        load_more_btn = (Button) findViewById(R.id.load_more_btn);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

    // setting the recycler view ---
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_items);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        p = new ProgressDialog(NavDrawerActivity.this);
        p.setMessage("Loading items ...");
        p.setCancelable(false);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#000000"));
        }

        data = new ArrayList<>();

        int i;
        for(i = 0;i < 10;i++){
            data.add(new DataModel("Item " + i +" Text 1", "Item " + i +" Text 2", "Item " + i +" Text 3",
                    R.drawable.i1, R.drawable.i2, R.drawable.i3));
        }
        next = i;
        adapter = new RVAdapter(data, NavDrawerActivity.this);
        recyclerView.setAdapter(adapter);

        load_more_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadMore();
            }
        });

    // getting and setting the navigation drawer header image and text
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);

        nav_header_iv = (ProfilePictureView) headerView.findViewById(R.id.nav_header_image);
        nav_header_tv = (TextView) headerView.findViewById(R.id.nav_header_text);

        if(session.checkLogin()) {
            HashMap<String, String> user = session.getUserDetails();
            String user_name = user.get(Session.KEY_NAME);
            String user_id = user.get(Session.KEY_USER_ID);

            nav_header_iv.setProfileId(user_id);
            nav_header_tv.setText(user_name);
            Toast.makeText(this, "Logged in as : " + user_name, Toast.LENGTH_SHORT).show();
        } else {
            nav_header_tv.setText("Not Logged In");
        }
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
    //    getMenuInflater().inflate(R.menu.nav_drawer, menu);
        return true;
    }

 /*   @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
     /*   if (id == R.id.change_view) {
            if(view_flag == 0) {
                GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
                recyclerView.setLayoutManager(layoutManager);
                view_flag = 1;
            } else if(view_flag == 1) {
                LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(layoutManager);
                view_flag = 0;
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }  */

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void loadMore() {
        if(data.size() < 499) {
            p.show();
            int j;
            for(j = next;j < next+10;j++) {
                data.add(new DataModel("Item " + j +" Text 1", "Item " + j +" Text 2", "Item " + j +" Text 3",
                    R.drawable.i1, R.drawable.i1, R.drawable.i1));
            } next = j;

            // DISPLAY PROGRESS FOR 2 SECONDS ---
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    p.dismiss();
                    // UPDATE THE CHANGES TO THE ADAPTER ---
                    adapter.notifyDataSetChanged();

                    Toast.makeText(NavDrawerActivity.this, "More items loaded !", Toast.LENGTH_SHORT).show();
                }
            }, 2000);

        } else {
            load_more_btn.setVisibility(View.GONE);
            Toast.makeText(NavDrawerActivity.this, "End of list occurerd !", Toast.LENGTH_LONG).show();
        }
    }
}
