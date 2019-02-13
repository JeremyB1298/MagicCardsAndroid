package Fragments

import Models.Card
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lpiem.magiccards.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_card_detail.*





class CardDetailFragment : androidx.fragment.app.Fragment() {

    lateinit var card: Card;

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        this.card = arguments?.getSerializable("Card") as Card
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Picasso.get().load(card.imageUris!!.png).into(cardDetailImageView)
        cardDetailTitle.text = card.name
    }

    
}
