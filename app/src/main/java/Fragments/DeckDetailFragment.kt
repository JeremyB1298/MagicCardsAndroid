package Fragments

import Adapter.CardRcyclViewAdapter
import Managers.DeckManager
import Managers.UserManager
import Models.Card
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lpiem.magiccards.R
import controllers.MagicCardRetrofitController
import kotlinx.android.synthetic.main.fragment_deck_detail.*

class DeckDetailFragment : androidx.fragment.app.Fragment() {

    private lateinit var recyclerView: androidx.recyclerview.widget.RecyclerView
    private lateinit var viewAdapter: CardRcyclViewAdapter
    private lateinit var viewManager: androidx.recyclerview.widget.RecyclerView.LayoutManager
    private lateinit var controller: MagicCardRetrofitController


    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewAdapter = CardRcyclViewAdapter(activity!!)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_deck_detail, container, false)

        viewAdapter.addCardList(UserManager.getCardsOfDeck(DeckManager.currentDeck.name!!)!!);

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        deckDetailRcyclView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)

        deckDetailFab.setOnClickListener {
            val nextFrag = AddDeckCardFragment()
            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.content, nextFrag)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        viewAdapter.setClick {
            onClickCell(it)
        }

        deckDetailRcyclView.adapter = viewAdapter
    }

    fun onClickCell(card: Card) {
        val nextFrag = CardDetailFragment()


    }

    override fun onResume() {
        super.onResume()
        deckDetailRcyclView.adapter
    }

}