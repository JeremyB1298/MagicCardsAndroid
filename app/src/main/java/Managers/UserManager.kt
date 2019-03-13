package Managers

import Models.Card
import Models.User
import androidx.lifecycle.MutableLiveData

object UserManager {

    var user: User? = null
    var listCards: MutableLiveData<ArrayList<Card>>? = null

    fun initialize() {
        this.user = User()
        this.listCards = MutableLiveData<ArrayList<Card>>()
    }
}