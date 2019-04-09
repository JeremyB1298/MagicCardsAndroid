package Fragments

import Adapter.DeckRcyclViewAdapter
import Managers.DeckManager
import Managers.UserManager
import Models.Deck
import Models.DeckCard
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lpiem.magiccards.R
import controllers.InterfaceCallBackController
import controllers.MagicCardRetrofitController
import kotlinx.android.synthetic.main.fragment_deck.*

class DeckRecyclerViewFragment : androidx.fragment.app.Fragment(),InterfaceCallBackController {
    override fun onWorkDone(result: Any) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private lateinit var recyclerView: androidx.recyclerview.widget.RecyclerView
    private lateinit var viewAdapter: DeckRcyclViewAdapter
    private lateinit var viewManager: androidx.recyclerview.widget.RecyclerView.LayoutManager
    private lateinit var controller: MagicCardRetrofitController


    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewAdapter = DeckRcyclViewAdapter(activity!!)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_deck, container, false)

        viewAdapter.addDeckList(UserManager.listDeck?.value!!)

        controller = MagicCardRetrofitController(this)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        deckRcyclView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
        viewAdapter.setClick {
            onClickCell(it)
        }




        deckFab.setOnClickListener {
            var tmp = ArrayList<Deck>()
            var deck = Deck()
            var cards = ArrayList<DeckCard>()
            var card1 = DeckCard()
            card1.cardId = "testest"
            card1.deckId = 0
            var card2 = DeckCard()
            card2.cardId = "testest2"
            card2.deckId = 0
            deck.name = "Deck1"
            deck.userId = UserManager.user!!.id
            deck.cards!!.add(card1)
            deck.cards!!.add(card2)
            tmp.add(deck)
            //controller.addDecks(tmp)
            UserManager.listDeck?.value?.add(deck)
        }

        deckRcyclView.adapter = viewAdapter
    }

    fun onClickCell(deck: Deck) {
        DeckManager.currentDeck = deck
        val nextFrag = DeckDetailFragment()
        val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.content, nextFrag)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    override fun onResume() {
        super.onResume()
        deckRcyclView.adapter
    }
}