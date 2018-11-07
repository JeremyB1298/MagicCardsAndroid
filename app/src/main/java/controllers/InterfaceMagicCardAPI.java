package controllers;

import java.util.List;

import Models.Example;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface InterfaceMagicCardAPI {
    @GET("/MagicCard/web/index.php/userCards/{id}")
    Call<List<Example>> getCard(
            @Path("id") int id
    );


}
