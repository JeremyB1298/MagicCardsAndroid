package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MagicCardRetrofitSingleton {
    static final String BASE_URL = "http://10.0.2.2:8888/";

    private static InterfaceMagicCardAPI magicCardAPIInstance;

    public static InterfaceMagicCardAPI getInstance (){
        if ( magicCardAPIInstance== null)
            synchronized (InterfaceMagicCardAPI.class){
                createApiBuilder();
            }
        return magicCardAPIInstance;
    }

    private static void createApiBuilder() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        magicCardAPIInstance = retrofit.create(InterfaceMagicCardAPI.class);
    }
}
