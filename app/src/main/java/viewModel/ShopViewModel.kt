package viewModel

import Managers.UserManager
import Models.Card
import Models.CardDB
import android.util.Log
import controllers.InterfaceCallBackController
import controllers.MagicCardRetrofitController

object ShopViewModel : InterfaceCallBackController {

    override fun onWorkDone(result: Any) {
        if (result is ArrayList<*>) {
            cardsAlea = result as ArrayList<Card>
            cardsAlea!!.forEach {
                Log.i("CARDNAME",it.name)
            }
        }
    }

    var cardsAlea : ArrayList<Card>? = null
    var cardsAleaId: ArrayList<Int>? = null
        private set

    init {
    }

    fun initialize() {
        val controller = MagicCardRetrofitController(this)
        controller.getRandomCards()
        cardsAleaId = ArrayList()
        for (i in 0..3) {
            cardsAleaId!!.add(i)
        }
    }

    fun getRandomCard(i: Int): Card {
        return cardsAlea!!.get(i)
    }

    fun addCard(card: CardDB, id: Int) {
        if (this.cardsAleaId!!.get(id) != -1){
            val controller = MagicCardRetrofitController(this)
            controller.addCard(card)
            UserManager.user!!.money = UserManager.user!!.money!! - 500
            cardsAleaId!![id] = -1
        }
    }
}