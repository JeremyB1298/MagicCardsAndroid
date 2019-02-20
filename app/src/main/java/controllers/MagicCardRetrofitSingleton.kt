package controllers

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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


    private val interceptorLog = HttpLoggingInterceptor().let {
        it.level = HttpLoggingInterceptor.Level.BODY
        it
    }

    private fun createApiBuilder() {
        val gson = GsonBuilder()
                .setLenient()
                .create()


        //OkhttpClient for building http request url
         val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptorLog)
                .build()

        val retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        magicCardAPIInstance = retrofit.create(InterfaceMagicCardAPI::class.java)
    }
}
