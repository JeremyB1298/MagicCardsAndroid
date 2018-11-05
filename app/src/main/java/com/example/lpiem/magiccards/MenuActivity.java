package com.example.lpiem.magiccards;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.facebook.AccessToken;
import com.facebook.FacebookSdk;

import com.facebook.login.LoginManager;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.List;

import Models.User;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class MenuActivity extends AppCompatActivity {

    private TextView tvUserName;
    private TextView tvUserEmail;
    private ImageView ivUserPicture;
    private JSONObject response, profile_pic_data, profile_pic_url;
    private Button bLogOut;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        String jsondata = intent.getStringExtra("userProfile");
        Log.w("Jsondata", jsondata);
        bLogOut=(Button) findViewById(R.id.bLogOut);
        tvUserName = (TextView) findViewById(R.id.tvUserName);
        ivUserPicture = (ImageView) findViewById(R.id.ivUserPicture);
        tvUserEmail = (TextView) findViewById(R.id.tvUserEmail);
        try {
            response = new JSONObject(jsondata);
            tvUserEmail.setText(response.get("email").toString());
            tvUserName.setText(response.get("name").toString());
            profile_pic_data = new JSONObject(response.get("picture").toString());
            profile_pic_url = new JSONObject(profile_pic_data.getString("data"));
           Picasso.with(this).load(profile_pic_url.getString("url"))
                   .into(ivUserPicture);

        } catch(Exception e){
            e.printStackTrace();
        }

        initUser();

        Controller controller = new Controller();
        controller.start();

    }

    public void logOut(View view){


        AccessToken.setCurrentAccessToken(null);
        LoginManager.getInstance().logOut();
        Intent intent = new Intent(MenuActivity.this,MainActivity.class);
        startActivity(intent);

    }

    private void initUser(){



    }


}
