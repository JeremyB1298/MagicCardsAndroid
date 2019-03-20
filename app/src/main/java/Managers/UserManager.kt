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

    fun nbrCommonCard(): Int {

        var nbr = 0
        for (card in listCards!!.value!!) {
            if (card.rarity == "common")
                nbr++
        }

        return nbr
    }

    fun nbrUnCommonCard(): Int {

        var nbr = 0
        for (card in listCards!!.value!!) {
            if (card.rarity == "uncommon")
                nbr++
        }

        return nbr
    }

    fun nbrRareCard(): Int {

        var nbr = 0
        for (card in listCards!!.value!!) {
            if (card.rarity == "rare")
                nbr++
        }

        return nbr
    }

    fun nbrMythicrareCard(): Int {

        var nbr = 0
        for (card in listCards!!.value!!) {
            if (card.rarity == "mythicrare")
                nbr++
        }

        return nbr
    }

}