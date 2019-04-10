package Fragments

import Adapter.CardRcyclViewAdapter
import Models.User
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lpiem.magiccards.R
import controllers.MagicCardRetrofitController


private const val INTENT_HOME_ID_USER = "User"
class FragmentDecks : androidx.fragment.app.Fragment() {

    private lateinit var recyclerView: androidx.recyclerview.widget.RecyclerView
    private lateinit var viewAdapter: CardRcyclViewAdapter
    private lateinit var viewManager: androidx.recyclerview.widget.RecyclerView.LayoutManager
    private lateinit var controller: MagicCardRetrofitController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView = inflater!!.inflate(R.layout.fragment_home, container, false)

        return rootView
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewAdapter = CardRcyclViewAdapter(activity!!)
    }

    companion object {
        fun newInstance(user: User): Fragment_home {
            val fragment = Fragment_home()
            val args = Bundle()
            args.putSerializable(INTENT_HOME_ID_USER,user)
            fragment.arguments = args

            return fragment
        }
    }
}