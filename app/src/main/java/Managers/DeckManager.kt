package Managers

import Models.Card
import Models.Deck

class DeckManager {
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
}