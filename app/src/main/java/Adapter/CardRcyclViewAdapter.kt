package Adapter

import Models.Card
import Utils.inflate
import android.view.View
import android.view.ViewGroup
import com.example.lpiem.magiccards.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.rcycl_view_item.view.*

class CardRcyclViewAdapter() : androidx.recyclerview.widget.RecyclerView.Adapter<CardRcyclViewHolder>() {

    private var myDataset =  ArrayList<Card>()
    private lateinit var onClick: (Card)->Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardRcyclViewHolder {
        val cellForRow = parent.inflate(R.layout.rcycl_view_item, false)
        return CardRcyclViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CardRcyclViewHolder, position: Int) {
        val card = myDataset.get(position)
        holder.bindCard(card, onClick);
    }

    override fun getItemCount() = myDataset.size

    fun addCardList(cardList: ArrayList<Card>){
        this.myDataset.clear()
        notifyDataSetChanged()
        this.myDataset.addAll(cardList)
        notifyDataSetChanged()

    }

    fun setClick(onClick:(Card)->Unit){
        this.onClick = onClick
    }
}



class CardRcyclViewHolder(val v: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(v){
    fun bindCard(myDataset: Card, onClick: (Card) -> Unit){


        v.cardTitle.text = myDataset.name

        Picasso.get().load(myDataset.imageUris!!.png).into(v.cardDetailImageView)


        itemView.setOnClickListener{
            onClick?.let {
                it(myDataset)
            }
        }

    }
}