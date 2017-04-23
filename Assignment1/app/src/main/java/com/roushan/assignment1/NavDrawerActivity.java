package com.roushan.assignment1;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.widget.ProfilePictureView;

import java.util.HashMap;

public class NavDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ProfilePictureView nav_header_iv;
    TextView nav_header_tv;
    Toolbar toolbar;
    NavigationView navigationView;
    Session session;
    private int view_flag = 0;

    private static RecyclerView.Adapter adapter;
    private static RecyclerView recyclerView;

    public static int[] images_drawable = {R.drawable.header_img1, R.drawable.header_img2,
            R.drawable.header_img3, R.drawable.header_img4, R.drawable.header_img5, R.drawable.header_img6,
            R.drawable.header_img7, R.drawable.header_img8, R.drawable.header_img9, R.drawable.header_img10,
            R.drawable.header_img11, R.drawable.header_img12, R.drawable.header_img13, R.drawable.header_img14,
            R.drawable.header_img15, R.drawable.header_img16, R.drawable.header_img17, R.drawable.header_img18,
            R.drawable.header_img19, R.drawable.header_img20, R.drawable.header_img21};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_nav_drawer);

        session = new Session(NavDrawerActivity.this);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_img);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        adapter = new ImagesRVAdapter(images_drawable, this);
        recyclerView.setAdapter(adapter);

        toolbar = (Toolbar) findViewById(R.id.nav_toolbar);
        setSupportActionBar(toolbar);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this , R.color.nav_statusbar_color));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);

        nav_header_iv = (ProfilePictureView) headerView.findViewById(R.id.nav_header_image);
        nav_header_tv = (TextView) headerView.findViewById(R.id.nav_header_text);

    /*    if(FacebookActivity.login_status == 0) {
        //    nav_header_iv.setImageResource(R.drawable.header_img1);
            nav_header_tv.setText("Not Logged In");
        }
        else if(FacebookActivity.login_status == 1) {
            nav_header_iv.setProfileId(FacebookActivity.user_id.toString());
            nav_header_tv.setText("Welcome : " + FacebookActivity.user_name);
            Toast.makeText(this, "Logged in as : " + FacebookActivity.user_name, Toast.LENGTH_SHORT).show();
        }      */

        if(session.checkLogin()) {
            HashMap<String, String> user = session.getUserDetails();
            String user_name = user.get(Session.KEY_NAME);
            String user_id = user.get(Session.KEY_USER_ID);

            nav_header_iv.setProfileId(user_id);
            nav_header_tv.setText(user_name);
            Toast.makeText(this, "Logged in as : " + user_name, Toast.LENGTH_SHORT).show();
        } else {
            String show_text = "Not Logged In";
            nav_header_tv.setText(show_text);
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
        getMenuInflater().inflate(R.menu.nav_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.change_view) {
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
    }

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
}
