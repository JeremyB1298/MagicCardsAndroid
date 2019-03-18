package controllers

import Models.Card
import Models.CardDB
import Models.User
import retrofit2.Call
import retrofit2.http.*


interface InterfaceMagicCardAPI {
    @GET("/MagicCard/web/index.php/userCards/{id}")
    fun getUserCards(
            @Path("id") id: Int
    ): Call<ArrayList<Card>>

    @GET("/MagicCard/web/index.php/googleConnexion/{googleId}")
    fun getUserByGoogle(
            @Path("googleId") googleId: String
    ): Call<User>

    @GET("/MagicCard/web/index.php/facebookConnexion/{fbId}")
    fun getUserByFacebook(
            @Path("fbId") fbId: String
    ): Call<User>

    @POST("/MagicCard/web/index.php/inscriptionGoogle")
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun createGoogleUser(@Body user: User): Call<String>

    @POST("/MagicCard/web/index.php/inscriptionFacebook")
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun createFacebookUser(@Body user: User): Call<String>

    @GET("/MagicCard/web/index.php/randomCard")
    fun getRandomCards(): Call<List<Card>>

    @POST("/MagicCard/web/index.php/addCard")
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun addCard(@Body card: CardDB): Call<String>

    @POST("/MagicCard/web/index.php/updateAccount")
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun updateAccount(@Body user: User): Call<String>
}
