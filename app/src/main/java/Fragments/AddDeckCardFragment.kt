package Fragments

import Adapter.DeckDetailRcyclViewAdapter
import Managers.DeckManager
import Managers.UserManager
import Models.Card
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.lpiem.magiccards.R
import controllers.MagicCardRetrofitController
import kotlinx.android.synthetic.main.fragment_add_deck_card.*

class AddDeckCardFragment : androidx.fragment.app.Fragment() {

    private lateinit var recyclerView: androidx.recyclerview.widget.RecyclerView
    private lateinit var viewAdapter: DeckDetailRcyclViewAdapter
    private lateinit var viewManager: androidx.recyclerview.widget.RecyclerView.LayoutManager
    private lateinit var controller: MagicCardRetrofitController


    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewAdapter = DeckDetailRcyclViewAdapter(activity!!)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_add_deck_card, container, false)

        viewAdapter.addCardList(DeckManager.getCardNotInDeck(DeckManager.currentDeck,UserManager.listCards!!.value!!));

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addDeckCardRcyclView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)

        val act = activity as AppCompatActivity?

        val toolbar = act!!.supportActionBar

        toolbar!!.setDisplayHomeAsUpEnabled(true)

        viewAdapter.setClick {
            onClickCell(it)
        }

        viewAdapter.setLongClick {
            onLongClickCell(it)
        }

        addDeckCardRcyclView.adapter = viewAdapter

        addDeckCardFab.setOnClickListener {
            for (deckCard in DeckManager.convertCardToDeckCard(viewAdapter.selectedCards,DeckManager.currentDeck)) {
                DeckManager.currentDeck.cards!!.add(deckCard)
            }
            fragmentManager!!.popBackStackImmediate()
        }
    }

    fun onClickCell(card: Card) {
        viewAdapter.selectedCards
    }

    fun onLongClickCell(card: Card) {
        val nextFrag = CardDetailFragment()
        val args = Bundle()
        args.putSerializable("Card", card)
        nextFrag.setArguments(args)
        val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.content, nextFrag)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    override fun onResume() {
        super.onResume()
        addDeckCardRcyclView.adapter
    }

}