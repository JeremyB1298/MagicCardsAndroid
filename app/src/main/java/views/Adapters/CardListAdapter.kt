package views.Adapters

import Models.Card
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lpiem.magiccards.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.rcycl_view_item.view.*


class CardListAdapter(val items : ArrayList<Card>, val context: Context) : androidx.recyclerview.widget.RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.rcycl_view_item, parent, false))

    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.tvCardName?.text = items[position].name
        Picasso
                .get() // give it the context
                .load(items[position].imageUris!!.artCrop) // load the image
                .into(holder.cardImageView) // select the ImageView to load it into


    }



    override fun getItemCount(): Int {
        return items.size
    }

    fun clear() {
        val size = items.size
        items.clear()
        notifyItemRangeRemoved(0, size)
    }





}
class ViewHolder (view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val tvCardName = view.cardTitle
    val cardImageView = view.cardDetailImageView
}
