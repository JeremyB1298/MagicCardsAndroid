package Fragments

import Adapter.CardRcyclViewAdapter
import Managers.DeckManager
import Managers.UserManager
import Models.Card
import Models.User
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.lpiem.magiccards.R
import controllers.InterfaceCallBackController
import controllers.MagicCardRetrofitController
import kotlinx.android.synthetic.main.fragment_deck_detail.*

class DeckDetailFragment : androidx.fragment.app.Fragment(),InterfaceCallBackController {

    override fun onWorkDone(result: Any) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

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

        viewAdapter.addCardList(UserManager.getCardsOfDeck(DeckManager.currentDeck.name!!)!!)

        controller = MagicCardRetrofitController(this)

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

        val act = activity as AppCompatActivity?

        val toolbar = act!!.supportActionBar

        toolbar!!.setDisplayHomeAsUpEnabled(true)

        viewAdapter.setClick {
            onClickCell(it)
        }
        viewAdapter.setLongClick {
            onLongClickCell(it)
        }

        deckDetailRcyclView.adapter = viewAdapter
    }

    fun onClickCell(card: Card) {
        val nextFrag = CardDetailFragment()
        val args = Bundle()
        args.putSerializable("Card", card)
        nextFrag.setArguments(args)
        val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.content, nextFrag)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }


    private fun onLongClickCell(card : Card) {

        var dialogs = Dialog(activity)
        dialogs.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialogs.setCancelable(true)
        dialogs.setContentView(R.layout.delete_card_dialog)
        val okBtn = dialogs.findViewById(R.id.deleteCardBtnOk) as Button
        val cancelBtn = dialogs.findViewById(R.id.deleteCardBtnCancel) as Button
        val text = dialogs.findViewById(R.id.deleteCardTv) as TextView

        okBtn.text = "OK"
        cancelBtn.text = "Annuler"
        text.text = "Voulez vous suprimer la carte " + card.name

        okBtn.setOnClickListener{
            DeckManager.removeDeckCardByName(DeckManager.currentDeck,card)
            dialogs.dismiss()
            viewAdapter.addCardList(UserManager.getCardsOfDeck(DeckManager.currentDeck.name!!)!!);
            viewAdapter.notifyDataSetChanged()
            controller.updateDecks(UserManager.listDeck?.value!!)
        }
        cancelBtn.setOnClickListener{
            dialogs.dismiss()
        }
        dialogs.show()
    }

    override fun onResume() {
        super.onResume()
        deckDetailRcyclView.adapter
        val act = activity as AppCompatActivity?

        val toolbar = act!!.supportActionBar

        toolbar!!.setDisplayHomeAsUpEnabled(true)
    }

}