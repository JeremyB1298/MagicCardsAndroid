package controllers

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MagicCardRetrofitSingleton {
    internal val BASE_URL = "http://17ruecroixberthet.freeboxos.fr/"
    //internal val BASE_URL = "http://10.0.2.2:8888/"


    private var magicCardAPIInstance: InterfaceMagicCardAPI? = null

    val instance: InterfaceMagicCardAPI?
        get() {
            if (magicCardAPIInstance == null)
                createApiBuilder()

            return magicCardAPIInstance
        }

    private fun createApiBuilder() {
        val gson = GsonBuilder()
                .setLenient()
                .create()

        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        magicCardAPIInstance = retrofit.create(InterfaceMagicCardAPI::class.java)
    }
}
