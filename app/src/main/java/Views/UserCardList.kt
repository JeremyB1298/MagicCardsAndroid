package Views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import com.example.lpiem.magiccards.R
import controllers.InterfaceCallBackController
import controllers.MagicCardRetrofitController

class UserCardList : AppCompatActivity(), InterfaceCallBackController {
    override fun onWorkDone(result: Any) {
        if (result as Boolean){
            adapter!!.notifyDataSetChanged()
        }    }



    var listName = ArrayList<String>()
    var tv : TextView? = null;
    private var adapter: ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_card_list)
        var listView : ListView = findViewById(R.id.listView)
        adapter = ArrayAdapter<String>(this@UserCardList, android.R.layout.simple_list_item_1, listName)
        listView.adapter = adapter
        val controller = MagicCardRetrofitController(this as InterfaceCallBackController, listName)
        controller.callWS()
    }


}
