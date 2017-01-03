package com.randjelovic.vladimir.myapplication.services;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;
import com.randjelovic.vladimir.myapplication.MyApplication;
import com.randjelovic.vladimir.myapplication.R;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Vladimir on 1/2/2017.
 */

public class LoginService extends AsyncTask<String, Integer, String> {
    @Override
    protected String doInBackground(String... strings) {
        publishProgress(0);
        StringBuilder sb = new StringBuilder();
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(MyApplication.getAppContext().getResources().getString(R.string.url_authenticate));
            urlConnection = (HttpURLConnection) url.openConnection();
            String userCredentials = "vlada:vlada";
            String basicAuth = "Basic " + new String(Base64.encode(userCredentials.getBytes(), Base64.NO_WRAP));
            urlConnection.setRequestProperty("Authorization", basicAuth);
            urlConnection.setRequestMethod("GET");

            publishProgress(1);
            Integer responseCode = urlConnection.getResponseCode();
            if ((responseCode>=200) && (responseCode<210)){
                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    sb.append(inputLine);
                }
                in.close();
            }
            else{
                Log.d("HTTP response: ", responseCode.toString());
            }
            publishProgress(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        publishProgress(3);
        return sb.toString();
    }


    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Log.d("This is progress update", "  -------------- " + values[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.d("GET - Result:", s);
    }


}
