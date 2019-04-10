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

    fun getCardNotInDeck(deck : Deck, cards : ArrayList<Card>) : ArrayList<Card>{
        var cardListTmp = cards

        if (deck?.cards != null && deck?.cards!!.count() != 0){
            for (deckCard in deck?.cards!!)
            {
                cardListTmp = cardListTmp.filter { card -> card.id != deckCard.cardId } as ArrayList<Card>

            }
        }
        else{
            return cards
        }
        return cardListTmp
    }

    fun removeDeckCardByName(deck: Deck,card : Card) {

            val tmp = deck.cards!!.find{deckCard -> deckCard.cardId.toString() == card.id }
            deck.cards!!.remove(tmp)

    }
}