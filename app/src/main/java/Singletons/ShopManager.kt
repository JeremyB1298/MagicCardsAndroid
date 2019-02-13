package Singletons

import Models.Card
import android.util.Log
import controllers.InterfaceCallBackController
import controllers.MagicCardRetrofitController

object ShopManager : InterfaceCallBackController {

    override fun onWorkDone(result: Any) {
        if (result is ArrayList<*>) {
            cardsAlea = result as ArrayList<Card>
            cardsAlea!!.forEach {
                Log.i("CARDNAME",it.name)
            }
        }
    }

    var cardsAlea : ArrayList<Card>? = null
        private set

    init {
    }

    fun initialize() {
        val controller = MagicCardRetrofitController(this)
        controller.getRandomCards()
    }

    fun getRandomCard(i: Int): String? {
        return cardsAlea!!.get(i).name
    }
}