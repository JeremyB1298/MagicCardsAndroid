package Singletons

import Models.Card
import Models.CardDB
import Models.UserCard
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

    fun getRandomCard(i: Int): Card {
        return cardsAlea!!.get(i)
    }

    fun addCard(card: CardDB) {
        val controller = MagicCardRetrofitController(this)
        controller.addCard(card)
    }

    fun addUserCard(userCard: UserCard) {
        val controller = MagicCardRetrofitController(this)
        controller.addUserCard(userCard)
    }
}