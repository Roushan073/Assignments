package com.roushan.assignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class FacebookActivity extends AppCompatActivity implements View.OnClickListener {

    Button skip_btn;
    LoginButton loginButton;
    CallbackManager callbackManager;
    AccessTokenTracker accessTokenTracker;
    ProfileTracker profileTracker;
    Session session;

    public static int login_status = 0;
    public static int user_fb_profile_pic;
    public static String user_name;
    public static Long user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());

        setContentView(R.layout.activity_facebook);

        session = new Session(FacebookActivity.this);
        if(session.checkLogin()) {     // session.checkLogin() == true
            startActivity(new Intent(FacebookActivity.this, NavDrawerActivity.class));
            finish();
        }

        loginButton = (LoginButton) findViewById(R.id.fb_login_btn);
    //    loginButton.setReadPermissions("public_profile");
    //    loginButton.setReadPermissions("user_friends");
        loginButton.setReadPermissions(Arrays.asList("public_profile", "user_birthday", "user_friends"));
        callbackManager = CallbackManager.Factory.create();

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken old_at, AccessToken new_at) {

            }
        };
        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile old_profile, Profile new_profile) {

            }
        };

        accessTokenTracker.startTracking();
        profileTracker.startTracking();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                login_status = 1;
                AccessToken accessToken = loginResult.getAccessToken();
                Profile profile = Profile.getCurrentProfile();

        // USING GRAPH API TO RETRIEVE PUBLIC PROFILE OF LOGGED IN USER IN FACEBOOK ................
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        try {
                            user_name = object.getString("name");
                            user_id = object.getLong("id");
                            Log.d("FACEBOOK_USER_ID : ", user_id.toString());
                            Log.d("FACEBOOK_USER_NAME : ", user_name);

                            session.setLoggedIn(true);
                            session.setUserDetails(user_name, user_id.toString());

                            Intent i = new Intent(FacebookActivity.this, NavDrawerActivity.class);
                            startActivity(i);
                            finish();
                        //    Toast.makeText(FacebookActivity.this,
                        //            "Logged in as : " + user_name, Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });   request.executeAsync();


            /*    Intent i = new Intent(FacebookActivity.this, NavDrawerActivity.class);
                i.putExtra("USER_NAME", user_name);
                startActivity(i);
                finish();   */
            }

            @Override
            public void onCancel() {
                Toast.makeText(FacebookActivity.this, "Login Cancelled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(FacebookActivity.this, "Error while login....please try again",
                        Toast.LENGTH_SHORT).show();
            }
        });

        skip_btn = (Button) findViewById(R.id.btn_skip);
        skip_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.btn_skip) {
            login_status = 0;
            Intent i = new Intent(this, NavDrawerActivity.class);
            startActivity(i);
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onStop() {
        super.onStop();
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();
    }
}
