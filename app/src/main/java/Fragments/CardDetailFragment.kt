package Fragments

import Models.Card
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lpiem.magiccards.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_card_detail.*
import kotlinx.android.synthetic.main.power_indicator.view.*
import kotlinx.android.synthetic.main.toughness_indicator.view.*






class CardDetailFragment : androidx.fragment.app.Fragment() {

    lateinit var card: Card;

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        this.card = arguments?.getSerializable("Card") as Card
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card_detail, container, false)



    }


    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {

        android.R.id.home ->{
            Toast.makeText(requireContext(),"Home action",Toast.LENGTH_LONG).show()
            true
        }

        else -> {
            Toast.makeText(requireContext(),"Home action" + item.itemId,Toast.LENGTH_LONG).show()
            Log.d("ItemId",item.itemId as String)

            super.onOptionsItemSelected(item)
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val act = activity as AppCompatActivity?

        val toolbar = act!!.supportActionBar

        toolbar!!.setDisplayHomeAsUpEnabled(true)


        val layoutRarity = LayoutInflater.from(act).inflate(getLayout(card.rarity), null)

        val layoutPower = LayoutInflater.from(act).inflate(R.layout.power_indicator,null)

        val layoutToughness = LayoutInflater.from(act).inflate(R.layout.toughness_indicator,null)

        if (card.power === null){
            layoutPower.tvPowerInd.text = "-"
        }
        else{
            layoutPower.tvPowerInd.text = card.power
        }

        if (card.toughness === null){
            layoutToughness.tvToughnessInd.text = "-"
        }
        else{
            layoutToughness.tvToughnessInd.text = card.toughness
        }

        powerClCardDetail.addView(layoutPower)

        raretyClCardDetail.addView(layoutRarity)

        toughnessClCardDetail.addView(layoutToughness)

        descriptionTvCardDetail.text = card.oracleText

        Picasso.get().load(card.imageUris!!.normal).into(cardDetailImageView)
        cardDetailTitle.text = card.name

    }

    fun getLayout(rarety:String?) : Int {
        val layout = when (rarety) {
            "common" -> R.layout.common_rarety_indicator
            "uncommon" -> R.layout.uncommon_rarety_indicator
            "rare" -> R.layout.rare_rarety_indicator
            "mythicrare" -> R.layout.mythicrare_rarety_indicator

            else -> { // Note the block
                R.layout.common_rarety_indicator
            }
        }
        return layout
    }

    
}
