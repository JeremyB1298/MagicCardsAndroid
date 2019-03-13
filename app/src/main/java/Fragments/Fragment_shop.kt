package Fragments

import Managers.UserManager
import Models.CardDB
import Singletons.ShopManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.lpiem.magiccards.R

class Fragment_shop: androidx.fragment.app.Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView = inflater!!.inflate(R.layout.fragment_shop, container, false)

        val bCommon: Button = rootView.findViewById(R.id.bCommonCard)
        val bUnCommon: Button = rootView.findViewById(R.id.bUncommonCard)
        val bRare: Button = rootView.findViewById(R.id.bRareCard)
        val bMythc: Button = rootView.findViewById(R.id.bMythicrareCard)

        bCommon.setOnClickListener {
            Log.i("CARDADD",ShopManager.getRandomCard(0).id+ "  /  " +ShopManager.getRandomCard(0).name)
            ShopManager.addCard(CardDB(null,ShopManager.getRandomCard(0).id,ShopManager.getRandomCard(0).name, UserManager.getInstance().getUser()!!.id))
        }
        bUnCommon.setOnClickListener {
            ShopManager.addCard(CardDB(null,ShopManager.getRandomCard(1).id,ShopManager.getRandomCard(1).name))
        }
        bRare.setOnClickListener {
            ShopManager.addCard(CardDB(null,ShopManager.getRandomCard(2).id,ShopManager.getRandomCard(2).name))
        }
        bMythc.setOnClickListener {
            ShopManager.addCard(CardDB(null,ShopManager.getRandomCard(3).id,ShopManager.getRandomCard(3).name))
        }

        return rootView
    }
}
