package viewModel

import Managers.UserManager
import Models.Card
import Models.CardDB
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import controllers.InterfaceCallBackController
import controllers.MagicCardRetrofitController

object ShopViewModel : InterfaceCallBackController {

    override fun onWorkDone(result: Any) {

    }

    var cardsAlea : MutableLiveData<ArrayList<Card>>? = null
    var cardsAleaId: ArrayList<Int>? = null

    init {
    }

    fun initialize() {
        cardsAlea = MutableLiveData<ArrayList<Card>>()
        val controller = MagicCardRetrofitController(this)
        controller.getRandomCards()
        cardsAleaId = ArrayList()
        for (i in 0..3) {
            cardsAleaId!!.add(i)
        }
    }

    fun addCard(card: CardDB, id: Int) {
        if (this.cardsAleaId!!.get(id) != -1){
            val controller = MagicCardRetrofitController(this)
            controller.addCard(card)
            UserManager.listCards!!.value!!.add(ShopViewModel.cardsAlea!!.value!!.get(id))
            UserManager.user!!.money = UserManager.user!!.money!! - 500
            cardsAleaId!![id] = -1
        }
    }
}