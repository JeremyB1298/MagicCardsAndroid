package com.example.lpiem.magiccards;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import Views.UserCardList;

public class MenuActivity extends AppCompatActivity {

    private TextView tvUserName;
    private TextView tvUserEmail;
    private TextView tvTokenId;
    private TextView tvId;
    private ImageView ivUserPicture;
    private JSONObject response, profile_pic_data, profile_pic_url;
    private Button bLogOut;

    private DrawerLayout mDrawerLayout;

    private GoogleApiClient mGoogleApiClient;

    private GoogleSignInAccount acct;

    @Override
    protected void onStart() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.menu_icon);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                         switch (menuItem.getItemId())
                        {
                            case R.id.logOutNavDraw:
                                logOut(findViewById(android.R.id.content));
                                break;

                            case R.id.listCardPage:
                                goToCardList(findViewById(android.R.id.content));
                                break;
                            default:
                                //Action;
                        }
                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });

        Intent intent = getIntent();


        bLogOut=(Button) findViewById(R.id.bLogOut);
        tvUserName = (TextView) findViewById(R.id.tvUserName);
        ivUserPicture = (ImageView) findViewById(R.id.ivUserPicture);
        tvUserEmail = (TextView) findViewById(R.id.tvUserEmail);

        try {
            String jsondata = intent.getStringExtra("userProfile");
            response = new JSONObject(jsondata);
            tvUserEmail.setText(response.get("email").toString());
            tvUserName.setText(response.get("name").toString());
            profile_pic_data = new JSONObject(response.get("picture").toString());
            profile_pic_url = new JSONObject(profile_pic_data.getString("data"));
           Picasso.with(this).load(profile_pic_url.getString("url"))
                   .into(ivUserPicture);

           connexionToTheAppWithGoogle("47892378546");


        } catch(Exception e){
            e.printStackTrace();
        }

        try {
            acct = intent.getParcelableExtra("ACCOUNT");
            tvUserEmail.setText(acct.getDisplayName().toString());
            tvUserName.setText(acct.getEmail().toString());

            profile_pic_url = new JSONObject(acct.getPhotoUrl().toString());
            Picasso.with(this).load(profile_pic_url.getString("url"))
                    .into(ivUserPicture);


        }

        catch (Exception e){
            e.printStackTrace();
        }




    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void logOut(View view){

        if(response != null){
        AccessToken.setCurrentAccessToken(null);
        LoginManager.getInstance().logOut();
        Intent intent = new Intent(MenuActivity.this,MainActivity.class);
        startActivity(intent);
        }

        if(acct != null) {
            Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                    new ResultCallback<Status>() {
                        @Override
                        public void onResult(Status status) {
                            // ...
                            Toast.makeText(getApplicationContext(), "Logged Out", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    });
        }
    }

    public void goToCardList (View view) {
        Intent intent = new Intent(MenuActivity.this, UserCardList.class);
        startActivity(intent);
    }

    

    private void connexionToTheAppWithGoogle(String googleId){

    }




}
