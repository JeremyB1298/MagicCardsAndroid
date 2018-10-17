package com.example.lpiem.magiccards;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.login.LoginResult;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

public class MenuActivity extends AppCompatActivity {

    private TextView tvUserName;
    private TextView tvUserEmail;
    private ImageView ivUserPicture;
    private JSONObject response, profile_pic_data, profile_pic_url;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        String jsondata = intent.getStringExtra("userProfile");
        Log.w("Jsondata", jsondata);
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

    }


}
