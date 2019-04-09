package Managers

import Models.Card
import Models.Deck
import Models.DeckCard

object DeckManager {

        lateinit var currentDeck : Deck

       fun getCardById(deck: Deck?,cardList:ArrayList<Card>) : ArrayList<Card>{

        var cardListTmp = ArrayList<Card>()

            if (deck?.cards != null){
                for (deckCard in deck?.cards!!)
                {
                    val tmp = cardList.find{card -> card.id == deckCard.cardId }
                    if (tmp != null) {
                        cardListTmp.add(tmp)
                    }
                }
        }
        return cardListTmp
    }

    fun convertCardToDeckCard(cardList: ArrayList<Card>,deck : Deck) : ArrayList<DeckCard> {
        var tmp : ArrayList<DeckCard> = ArrayList()
        val deckId = deck.id
        for (card in cardList){
            var tmpCard = DeckCard()
            tmpCard.deckId = deckId
            tmpCard.cardId = card.id
            tmp.add(tmpCard)
        }
        return tmp
    }
}