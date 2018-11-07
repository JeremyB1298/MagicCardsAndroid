package controllers;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import Models.Card;
import Models.Example;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MagicCardRetrofitController {
    String message;
    int nextPage = 1, nbPages = 100;
    ArrayList<String> listName ;
    InterfaceMagicCardAPI magicCardAPI;
    InterfaceCallBackController interfaceCallBackController;

    public MagicCardRetrofitController(InterfaceCallBackController interfaceCallBackController, ArrayList<String> listName) {
        this.interfaceCallBackController = interfaceCallBackController;
        this.listName = listName;
    }

    public void callWS() {

        magicCardAPI = MagicCardRetrofitSingleton.getInstance();


        Call<List<Example>> callExemple = magicCardAPI.getCard(1);
        callExemple.enqueue(new Callback<List<Example>>() {
            @Override
            public void onResponse(Call<List<Example>> call, Response<List<Example>> response) {
                if (response.isSuccessful()) {
                    List<Example> listExample = response.body();
                    fetchData(response);
                    Card card = listExample.get(0).getCard();
                    // changesList.forEach(rawPeople -> System.out.println(rawPeople.name));  // lambda expression (enable java 1.8 in project structure  - available only since AP 24...
                    Log.d("SwapiRetrofitController", "card name : " + card.getName());

                } else {
                    Log.d("SwapiRetrofitController", "error : " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<Example>> call, Throwable t) {
                t.printStackTrace();
            }
        });
        interfaceCallBackController.onWorkDone(true);

    }

    private synchronized void fetchData(Response<List<Example>> response) {

        for (int i = 0; i<response.body().size() ; i++){
            listName.add(response.body().get(i).getCard().getName());
        }
        interfaceCallBackController.onWorkDone(true);
    }


}
