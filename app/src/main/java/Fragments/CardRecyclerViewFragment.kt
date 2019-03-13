package Fragments

import Adapter.CardRcyclViewAdapter
import Managers.UserManager
import Models.Card
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lpiem.magiccards.R
import controllers.MagicCardRetrofitController
import kotlinx.android.synthetic.main.recycler_view_fragment.*


class CardRecyclerViewFragment: androidx.fragment.app.Fragment() {

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
        val rootView = inflater.inflate(R.layout.recycler_view_fragment, container, false)

        viewAdapter.addCardList(UserManager.listCards!!.value!!);

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CardRcyclView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)



        viewAdapter.setClick {
            onClickCell(it)
        }

        CardRcyclView.adapter = viewAdapter
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

    override fun onResume() {
        super.onResume()
        CardRcyclView.adapter
    }
}



