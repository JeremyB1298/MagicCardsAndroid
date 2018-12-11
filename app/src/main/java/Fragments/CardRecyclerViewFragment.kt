package Fragments

import Adapter.CardRcyclViewAdapter
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lpiem.magiccards.R


class CardRecyclerViewFragment: Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.recycler_view_fragment, container, false)
        val CardRcyclView = rootView.findViewById(R.id.CardRcyclView) as RecyclerView
        CardRcyclView.layoutManager = LinearLayoutManager(activity)
        CardRcyclView.adapter = CardRcyclViewAdapter(arrayOf("a", "b", "c"))
        return rootView
    }
    }



