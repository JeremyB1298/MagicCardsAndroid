package com.example.lpiem.magiccards;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Models.Example;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller implements Callback<Example> {

    static final String BASE_URL = "https://api.magicthegathering.io/v1/";

    public void start() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        GerritAPI gerritAPI = retrofit.create(GerritAPI.class);

        Call<Example> call = gerritAPI.loadChanges(386616);
        call.enqueue(this);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onResponse(Call<Example> call, Response<Example> response) {
        if(response.isSuccessful()) {
            Example example = response.body();
            System.out.println("SUJETS !!!!!!!!!!!!!!!!!");
             System.out.println(example.getCard().getName());
        } else {
            System.out.println("FAILURE !!!!!!!!!!!!!!!!!");
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<Example> call, Throwable t) {
        System.out.println("FAILURE 2 !!!!!!!!!!!!!!!!!");
        t.printStackTrace();
    }
}
