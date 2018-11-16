package controllers

import Models.Example
import Models.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface InterfaceMagicCardAPI {
    @GET("/MagicCard/web/index.php/userCards/{id}")
    fun getCard(
            @Path("id") id: Int
    ): Call<List<Example>>

    @GET("/MagicCard/web/index.php/googleConnexion/{googleId}")
    fun getUserByGoogle(
            @Path("googleId") googleId: Int
    ): Call<User>

}
