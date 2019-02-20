package Fragments

import Managers.UserManager
import Models.CardDB
import android.app.Dialog
import viewModel.ShopViewModel
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import com.example.lpiem.magiccards.R
import kotlinx.android.synthetic.main.buy_dialog.*

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
            showDialog(0)
        }
        bUnCommon.setOnClickListener {
            showDialog(1)
        }
        bRare.setOnClickListener {
            showDialog(2)
        }
        bMythc.setOnClickListener {
            showDialog(3)
        }

        return rootView
    }

    private fun showDialog(id: Int) {

        var dialogs = Dialog(activity)
        dialogs.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialogs.setCancelable(true)
        dialogs.setContentView(R.layout.buy_dialog)
        val priceBtn = dialogs.findViewById(R.id.bPrice) as Button
        val text = dialogs.findViewById(R.id.tvBuy) as TextView

        if (UserManager.user!!.money!! > 500) {

            text.text = "Carte aléatoire"

            priceBtn.text = "500"

            priceBtn.setOnClickListener {
                ShopViewModel.addCard(CardDB(null, ShopViewModel.getRandomCard(id).id, ShopViewModel.getRandomCard(id).name,
                        UserManager.getCurrentUser()!!.id), id)
                dialogs.dismiss()
            }

        } else {
            text.text = "Pas assez d'argent"
            priceBtn.text = "ok"
                dialogs.dismiss()
            }

        dialogs.show()
        }


}

