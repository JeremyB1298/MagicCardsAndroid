package Fragments

import Singletons.ShopManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.lpiem.magiccards.R
import kotlinx.android.synthetic.main.fragment_shop.*

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
            Log.i("BUTTONCARD",ShopManager.getRandomCard(0))
        }
        bUnCommon.setOnClickListener {
            Log.i("BUTTONCARD",ShopManager.getRandomCard(1))
        }
        bRare.setOnClickListener {
            Log.i("BUTTONCARD",ShopManager.getRandomCard(2))
        }
        bMythc.setOnClickListener {
            Log.i("BUTTONCARD",ShopManager.getRandomCard(3))
        }

        return rootView
    }
}

