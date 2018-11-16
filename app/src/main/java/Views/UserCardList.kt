package Views

import Models.Card
import Views.Adapters.CardListAdapter
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.lpiem.magiccards.R
import controllers.InterfaceCallBackController
import controllers.MagicCardRetrofitController
import kotlinx.android.synthetic.main.activity_user_card_list.*

class UserCardList : AppCompatActivity(), InterfaceCallBackController {
    val animals: ArrayList<String> = ArrayList()
    var listCard = ArrayList<Card>()
    override fun onWorkDone(result: Any) {
        if (result as Boolean){
            rv_animal_list.adapter = CardListAdapter(listCard, this);
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_card_list)

        val controller = MagicCardRetrofitController(this as InterfaceCallBackController)

        rv_animal_list.layoutManager = LinearLayoutManager(this);
        controller.callWS(listCard)
    }


}




