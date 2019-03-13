package Fragments

import Managers.UserManager
import Models.CardDB
import android.app.Dialog
import viewModel.ShopViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.lpiem.magiccards.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.buy_dialog.*
import kotlinx.android.synthetic.main.fragment_card_detail.*


class FragmentShop: androidx.fragment.app.Fragment() {

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
            showBuyDialog(0)
        }
        bUnCommon.setOnClickListener {
            showBuyDialog(1)
        }
        bRare.setOnClickListener {
            showBuyDialog(2)
        }
        bMythc.setOnClickListener {
            showBuyDialog(3)
        }

        return rootView
    }

    private fun showBuyDialog(id: Int) {

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
                ShopViewModel.addCard(CardDB(null, ShopViewModel.cardsAlea!!.value!!.get(id).id, ShopViewModel.cardsAlea!!.value!!.get(id).name,
                        UserManager.user!!.id), id)
                dialogs.dismiss()
                showCardDialog(id)
            }

        } else {
            text.text = "Pas assez d'argent"
            priceBtn.text = "ok"
                dialogs.dismiss()
            }

        dialogs.show()
    }

    private fun showCardDialog(id: Int) {
        var dialogs = Dialog(activity)
        dialogs.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialogs.setCancelable(true)
        dialogs.setContentView(R.layout.card_dialog)
        val okBtn = dialogs.findViewById(R.id.bCardOk) as Button
        val iv = dialogs.findViewById(R.id.ivCard) as ImageView
        Picasso.get().load(ShopViewModel.cardsAlea!!.value!![id].imageUris!!.png).into(iv)
            okBtn.setOnClickListener {

                dialogs.dismiss()
            }

        dialogs.show()
    }


}

