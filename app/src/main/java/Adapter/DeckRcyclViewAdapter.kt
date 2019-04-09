package Adapter

import Models.Card
import Models.Deck
import Utils.inflate
import android.app.Activity
import android.view.View
import android.view.ViewGroup
import com.example.lpiem.magiccards.R
import kotlinx.android.synthetic.main.deck_rcycl_view_item.view.*

class DeckRcyclViewAdapter (val act: Activity) : androidx.recyclerview.widget.RecyclerView.Adapter<DeckRcyclViewAdapter.DeckRcyclViewHolder>() {


    private var myDataset =  ArrayList<Deck>()
    private lateinit var onClick: (Deck)->Unit
    private lateinit var onLongClick: (Deck)->Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeckRcyclViewHolder {
        val cellForRow = parent.inflate(R.layout.deck_rcycl_view_item, false)
        return DeckRcyclViewHolder(cellForRow)
    }

    override fun getItemCount() = myDataset.size

    override fun onBindViewHolder(holder: DeckRcyclViewHolder, position: Int) {
        val deck = myDataset.get(position)
        holder.bindCard(deck, onClick)
    }

    fun setClick(onClick:(Deck)->Unit){
        this.onClick = onClick
    }
    fun setLongClick(onLongClick:(Deck)->Unit){
        this.onLongClick = onLongClick
    }
    fun addDeckList(deckList: ArrayList<Deck>){
        this.myDataset.clear()
        notifyDataSetChanged()
        this.myDataset.addAll(deckList)
        notifyDataSetChanged()

    }

    inner class DeckRcyclViewHolder(val v: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(v) {
        fun bindCard(myDataset: Deck, onClick: (Deck) -> Unit) {

            v.deckTitleTv.text = myDataset.name
            v.numberOfCardsTv.text = myDataset.cards?.size.toString()

            itemView.setOnClickListener{
                onClick?.let {
                    it(myDataset)
                }
            }
            itemView.setOnLongClickListener{
                if(onLongClick?.let {
                            it(myDataset)

                        } != null){
                    true
                }
                else{
                    false
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