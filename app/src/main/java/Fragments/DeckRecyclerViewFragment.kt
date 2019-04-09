package Fragments

import Adapter.DeckRcyclViewAdapter
import Managers.DeckManager
import Managers.UserManager
import Models.Card
import Models.CardDB
import Models.Deck
import Models.DeckCard
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.lpiem.magiccards.R
import controllers.InterfaceCallBackController
import controllers.MagicCardRetrofitController
import kotlinx.android.synthetic.main.fragment_deck.*
import viewModel.ShopViewModel

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

        viewAdapter.setLongClick {
            onLongClickCell(it)
        }


        deckFab.setOnClickListener {
            var dialogs = Dialog(activity)
            dialogs.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialogs.setCancelable(true)
            dialogs.setContentView(R.layout.dialog_new_deck)

            val btnValid = dialogs.findViewById(R.id.bValidNameDeck) as Button
            val teName = dialogs.findViewById(R.id.etDeckName) as EditText

            btnValid.setOnClickListener {
                if (teName.text.length > 0) {
                    var tmp = ArrayList<Deck>()
                    var deck = Deck()
                    deck.name = teName.text.toString()
                    deck.userId = UserManager.user!!.id
                    tmp.add(deck)
                    controller.addDecks(tmp)
                    UserManager.listDeck?.value?.add(deck)
                }
                dialogs.dismiss()
                viewAdapter.addDeckList(UserManager.listDeck?.value!!)
                viewAdapter.notifyDataSetChanged()
        }
            dialogs.show()
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

    private fun onLongClickCell(deck : Deck) {

        var dialogs = Dialog(activity)
        dialogs.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialogs.setCancelable(true)
        dialogs.setContentView(R.layout.delete_card_dialog)
        val okBtn = dialogs.findViewById(R.id.deleteCardBtnOk) as Button
        val cancelBtn = dialogs.findViewById(R.id.deleteCardBtnCancel) as Button
        val text = dialogs.findViewById(R.id.deleteCardTv) as TextView

        okBtn.text = "OK"
        cancelBtn.text = "Annuler"
        text.text = "Voulez vous suprimer le deck " + deck.name

        okBtn.setOnClickListener{
            controller.deleteDeck(deck.id!!)
            UserManager.listDeck?.value?.remove(deck)
            dialogs.dismiss()
            viewAdapter.addDeckList(UserManager.listDeck?.value!!)
            viewAdapter.notifyDataSetChanged()
        }

        cancelBtn.setOnClickListener{
            dialogs.dismiss()
        }

        dialogs.show()
    }

    override fun onResume() {
        super.onResume()
        deckRcyclView.adapter
    }
}