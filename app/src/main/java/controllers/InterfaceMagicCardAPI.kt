package controllers

import Models.Card
import Models.User
import retrofit2.Call
import retrofit2.http.*


interface InterfaceMagicCardAPI {
    @GET("/MagicCard/web/index.php/userCards/{id}")
    fun getUserCards(
            @Path("id") id: Int
    ): Call<List<Card>>

    @GET("/MagicCard/web/index.php/googleConnexion/{googleId}")
    fun getUserByGoogle(
            @Path("googleId") googleId: String
    ): Call<User>

    @GET("/MagicCard/web/index.php/facebookConnexion/{fbId}")
    fun getUserByFacebook(
            @Path("fbId") fbId: String
    ): Call<User>

    @POST("/MagicCard/web/index.php/inscription")
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun createUser(@Body user: User): Call<String>

    @GET("/MagicCard/web/index.php/randomCard")
    fun getRandomCards(): Call<List<Card>>
}
