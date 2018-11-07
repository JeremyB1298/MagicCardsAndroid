package com.example.lpiem.magiccards;

import Models.Card;
import Models.Example;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GerritAPI {
    @GET("cards/{id}")
    Call<Example> loadChanges(@Path("id") int id);
}
