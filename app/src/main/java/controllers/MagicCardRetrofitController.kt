package controllers

import Models.Example
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MagicCardRetrofitController(internal var interfaceCallBackController: InterfaceCallBackController, internal var listName: ArrayList<String>) {
    internal var message: String? = null
    internal var nextPage = 1
    internal var nbPages = 100


    fun callWS() {

        var magicCardAPI: InterfaceMagicCardAPI  = MagicCardRetrofitSingleton.instance!!


        val callExemple = magicCardAPI.getCard(1)
        callExemple.enqueue(object : Callback<List<Example>> {
            override fun onResponse(call: Call<List<Example>>, response: Response<List<Example>>) {
                if (response.isSuccessful) {
                    val listExample = response.body()
                    fetchData(response)
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

    @Synchronized
    private fun fetchData(response: Response<List<Example>>) {

        for (i in 0 until response.body()!!.size) {
            listName.add(response.body()!![i].card!!.name!!)
        }
        interfaceCallBackController.onWorkDone(true)
    }


}
