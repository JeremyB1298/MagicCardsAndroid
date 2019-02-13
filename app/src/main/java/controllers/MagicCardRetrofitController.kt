package controllers

import Models.Card
import Models.CardDB
import Models.User
import Models.UserCard
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MagicCardRetrofitController(internal var interfaceCallBackController: InterfaceCallBackController) {
    internal var message: String? = null
    internal var nextPage = 1
    internal var nbPages = 100
    val magicCardAPI: InterfaceMagicCardAPI  = MagicCardRetrofitSingleton.instance!!

    fun callWS(listCard: ArrayList<Card>) {
        val callExemple = magicCardAPI.getUserCards(1)
        callExemple.enqueue(object : Callback<List<Card>> {
            override fun onResponse(call: Call<List<Card>>, response: Response<List<Card>>) {
                if (response.isSuccessful) {
                    val listExample = response.body()
                    fetchData(response, listCard)
                    val card = listExample!![0]
                    // changesList.forEach(rawPeople -> System.out.println(rawPeople.name));  // lambda expression (enable java 1.8 in project structure  - available only since AP 24...

                    Log.d("SwapiRetrofitController", "card name : " + card!!.name!!)

                } else {
                    Log.d("SwapiRetrofitController", "error : " + response.errorBody()!!)
                }
            }

            override fun onFailure(call: Call<List<Card>>, t: Throwable) {
                t.printStackTrace()
            }
        })

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
                    val readWriteMap = hashMapOf("google" to false)
                    val map: Map<String, Boolean> = HashMap(readWriteMap)
                    interfaceCallBackController.onWorkDone(map)
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                t.printStackTrace()
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
        callUser.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful && response.body().equals("OK")) {
                    val readWriteMap = hashMapOf("google" to true)
                    val map: Map<String, Boolean> = HashMap(readWriteMap)
                    interfaceCallBackController.onWorkDone(map)
                    Log.d("INSCRIPTION GOOD", response.errorBody()!!.toString())
                } else {
                    Log.d("INSCRIPTION FAILED", response.errorBody()!!.toString())
                }
            }
            override fun onFailure(call: Call<String>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    fun getRandomCards() {
        val callRandomCard = magicCardAPI.getRandomCards()
        callRandomCard.enqueue(object : Callback<List<Card>>{
            override fun onResponse(call: Call<List<Card>>, response: Response<List<Card>>) {
               if (response.isSuccessful) {
                       interfaceCallBackController.onWorkDone(response.body() as ArrayList<Card>)
               } else {

               }
            }

            override fun onFailure(call: Call<List<Card>>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }

    fun addCard(card: CardDB) {
        val callAddCard = magicCardAPI.addCard(card)
        callAddCard.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful && response.body().equals("OK")) {
                } else {
                }
            }
            override fun onFailure(call: Call<String>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    fun addUserCard(userCard: UserCard) {
        val callAddUserCard = magicCardAPI.addUserCard(userCard)
        callAddUserCard.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful && response.body().equals("OK")) {
                } else {
                }
            }
            override fun onFailure(call: Call<String>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    @Synchronized
    private fun fetchData(response: Response<List<Card>>,listCard: ArrayList<Card>) {

        for (i in 0 until response.body()!!.size) {
            listCard.add(response.body()!![i])
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
