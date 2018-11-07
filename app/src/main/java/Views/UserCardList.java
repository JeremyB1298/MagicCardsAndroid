package Views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lpiem.magiccards.R;

import java.util.ArrayList;

import controllers.InterfaceCallBackController;
import controllers.MagicCardRetrofitController;

public class UserCardList extends AppCompatActivity implements InterfaceCallBackController<Boolean> {
    ArrayList<String> listName = new ArrayList<>();
    TextView tv;
    private ArrayAdapter<String>  adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_card_list);
        ListView listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(UserCardList.this, android.R.layout.simple_list_item_1,listName);
        listView.setAdapter(adapter);

        MagicCardRetrofitController controller = new MagicCardRetrofitController((InterfaceCallBackController)this, listName);
        controller.callWS();
    }

    @Override
    public void onWorkDone(Boolean result) {
        if (result) adapter.notifyDataSetChanged();
        else Toast.makeText(UserCardList.this, "probleme de retour de l'API", Toast.LENGTH_SHORT).show();
    }
}
