package controllers

import Models.Example
import Models.User
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList
import android.icu.lang.UCharacter.GraphemeClusterBreak.V
import kotlin.collections.HashMap


class MagicCardRetrofitController(internal var interfaceCallBackController: InterfaceCallBackController) {
    internal var message: String? = null
    internal var nextPage = 1
    internal var nbPages = 100
    val magicCardAPI: InterfaceMagicCardAPI  = MagicCardRetrofitSingleton.instance!!

    fun callUserCards(res: ArrayList<String>) {
        val callExemple = magicCardAPI.getUserCards(1)
        callExemple.enqueue(object : Callback<List<Example>> {
            override fun onResponse(call: Call<List<Example>>, response: Response<List<Example>>) {
                if (response.isSuccessful) {
                    val listExample = response.body()
                    fetchData(response, res)
                    val card = listExample!![0].card
                    // changesList.forEach(rawPeople -> System.out.println(rawPeople.name));  // lambda expression (enable java 1.8 in project structure  - available only since AP 24...
                    Log.d("SwapiRetrofitController", "card name : " + card!!.name!!)

                } else {
                    Log.d("SwapiRetrofitController", "error : " + response.errorBody()!!)
                }
            }

            override fun onFailure(call: Call<List<Example>>, t: Throwable) {
                t.printStackTrace()
            }
        })
        interfaceCallBackController.onWorkDone(true)
    }

    fun callUserGoogleId(googleId: String, user: User?){

        val callUser = magicCardAPI.getUserByGoogle(googleId)


        callUser.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {

                if (response.isSuccessful) {
                    fetchUser(response,user)
                    val readWriteMap = hashMapOf("google" to true)
                    val map: Map<String, Boolean> = HashMap(readWriteMap)
                    interfaceCallBackController.onWorkDone(map)
                    // changesList.forEach(rawPeople -> System.out.println(rawPeople.name));  // lambda expression (enable java 1.8 in project structure  - available only since AP 24...
                    Log.d("SwapiRetrofitController", user!!.name )

                } else {
                    Log.d("SwapiRetrofitController", "error : " + response.errorBody()!!)
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                t.printStackTrace()
                val readWriteMap = hashMapOf("google" to false)
                val map: Map<String, Boolean> = HashMap(readWriteMap)
                interfaceCallBackController.onWorkDone(map)
            }
        })

    }

    fun callUserFbId(fbId: String, user: User?){

        val callUser = magicCardAPI.getUserByFacebook(fbId)


        callUser.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    fetchUser(response,user)
                    val readWriteMap = hashMapOf("facebook" to true)
                    val map: Map<String, Boolean> = HashMap(readWriteMap)
                    interfaceCallBackController.onWorkDone(map)
                    // changesList.forEach(rawPeople -> System.out.println(rawPeople.name));  // lambda expression (enable java 1.8 in project structure  - available only since AP 24...
                    Log.d("SwapiRetrofitController", user!!.name )

                } else {
                    Log.d("SwapiRetrofitController", "error : " + response.errorBody()!!)
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                t.printStackTrace()
            }
        })

    }

    fun createUser(user: User) {
        val callUser = magicCardAPI.createUser(user)
        callUser.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    Log.d("INSCRIPTION GOOD", response.errorBody()!!.toString())
                } else {
                    Log.d("INSCRIPTION FAILED", response.errorBody()!!.toString())
                }
            }
            override fun onFailure(call: Call<User>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    @Synchronized
    private fun fetchData(response: Response<List<Example>>,res: ArrayList<String>) {

        for (i in 0 until response.body()!!.size) {
            res.add(response.body()!![i].card!!.name!!)
        }

        interfaceCallBackController.onWorkDone(true)
    }

    @Synchronized
    private fun fetchUser(response: Response<User>, user: User?){
        user!!.id = response.body()!!.id
        user!!.googleId = response.body()!!.googleId
        user!!.fbId = response.body()!!.fbId
        user!!.name = response.body()!!.name
        user!!.isNew = response.body()!!.isNew
        user!!.lvl = response.body()!!.lvl
        user!!.exp = response.body()!!.exp
    }

}
