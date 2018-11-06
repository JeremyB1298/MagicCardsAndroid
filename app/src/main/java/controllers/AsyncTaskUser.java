package controllers;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AsyncTaskUser extends AsyncTask<Void, Void, String> {
    @Override
    protected String doInBackground(Void... voids) {

        try {

            URL url=new URL("10.0.0.2:8080/web/index.php/");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                return stringBuilder.toString();
            }
            finally{
                urlConnection.disconnect();
            }

        } catch (MalformedURLException e) {
            return null;
        } catch (IOException e) {
            return null;
        }

    }

    protected void onPostExecute(String response) {

        if(response == null) {
            response = "THERE WAS AN ERROR";
        }



    }

}
