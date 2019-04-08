package Managers

import Models.Card
import Models.Deck
import Models.User
import androidx.lifecycle.MutableLiveData

object UserManager {

    var user: User? = null
    var deckManager : DeckManager? = null
    var listCards: MutableLiveData<ArrayList<Card>>? = null
    var listDeck: MutableLiveData<ArrayList<Deck>>? = null


    fun initialize() {
        this.user = User()
        this.deckManager = DeckManager
        this.listCards = MutableLiveData<ArrayList<Card>>()
        this.listDeck = MutableLiveData<ArrayList<Deck>>()
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

    fun getCardsOfDeck( deckName: String): ArrayList<Card>? {
        var deckList = this.listDeck!!.value

        return this.deckManager?.getCardById(deckList?.find { deck -> deck.name == deckName },this.listCards!!.value!!)
    }

}