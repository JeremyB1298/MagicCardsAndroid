package Adapter

import Models.Card
import Utils.inflate
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lpiem.magiccards.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.power_indicator.view.*
import kotlinx.android.synthetic.main.rcycl_view_item.view.*
import kotlinx.android.synthetic.main.toughness_indicator.view.*

class DeckRcyclViewAdapter (val act: Activity) : androidx.recyclerview.widget.RecyclerView.Adapter<DeckRcyclViewAdapter.DeckRcyclViewHolder>() {


    private var myDataset =  ArrayList<Card>()
    private lateinit var onClick: (Card)->Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeckRcyclViewAdapter.DeckRcyclViewHolder {
        val cellForRow = parent.inflate(R.layout.rcycl_view_item, false)
        return DeckRcyclViewHolder(cellForRow)
    }

    override fun getItemCount() = myDataset.size

    override fun onBindViewHolder(holder: DeckRcyclViewHolder, position: Int) {
        val card = myDataset.get(position)
        holder.bindCard(card, onClick)
    }

    fun setClick(onClick:(Card)->Unit){
        this.onClick = onClick
    }

    fun addDeckList(cardList: ArrayList<Card>){
        this.myDataset.clear()
        notifyDataSetChanged()
        this.myDataset.addAll(cardList)
        notifyDataSetChanged()

    }

    inner class DeckRcyclViewHolder(val v: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(v) {
        fun bindCard(myDataset: Card, onClick: (Card) -> Unit) {

            val layoutRarity = LayoutInflater.from(act).inflate(this.getLayout(myDataset.rarity), null)

            val layoutPower = LayoutInflater.from(act).inflate(R.layout.power_indicator, null)

            val layoutToughness = LayoutInflater.from(act).inflate(R.layout.toughness_indicator, null)

            if (myDataset.power === null) {
                layoutPower.tvPowerInd.text = "-"
            } else {
                layoutPower.tvPowerInd.text = myDataset.power
            }

            if (myDataset.toughness === null) {
                layoutToughness.tvToughnessInd.text = "-"
            } else {
                layoutToughness.tvToughnessInd.text = myDataset.toughness
            }

            v.rcyclViewPowerCl.addView(layoutPower)

            v.rcyclViewRaretyCl.addView(layoutRarity)

            v.rcyclViewToughnessCl.addView(layoutToughness)


            v.cardTitle.text = myDataset.name

            Picasso.get().load(myDataset.imageUris!!.png).into(v.cardDetailImageView)


            itemView.setOnClickListener {
                onClick?.let {
                    it(myDataset)
                }
            }

        }

        fun getLayout(rarety: String?): Int {
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
}