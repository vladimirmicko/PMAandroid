package com.randjelovic.vladimir.myapplication.services;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.randjelovic.vladimir.myapplication.MyApplication;
import com.randjelovic.vladimir.myapplication.R;
import com.randjelovic.vladimir.myapplication.activities.IntroActivity;
import com.randjelovic.vladimir.myapplication.activities.Login;
import com.randjelovic.vladimir.myapplication.activities.MainActivity;

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

    private static final String TAG = "LoginService";
    private static final String AUTHENTICATION_TAG = "Basic ";
    private static final String AUTHENTICATION_HEADER = "Authorization";

    @Override
    protected String doInBackground(String... strings) {
        publishProgress(0);
        String message = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(MyApplication.getAppContext().getResources().getString(R.string.url_authenticate));
            urlConnection = (HttpURLConnection) url.openConnection();
            String userCredentials = strings[0]+":"+strings[1];
            String basicAuth = AUTHENTICATION_TAG + new String(Base64.encode(userCredentials.getBytes(), Base64.NO_WRAP));
            urlConnection.setRequestProperty(AUTHENTICATION_HEADER, basicAuth);
            urlConnection.setRequestMethod("GET");

            publishProgress(1);
            Integer responseCode = urlConnection.getResponseCode();
            if (responseCode==200){
                message=(MyApplication.getAppContext().getResources().getString(R.string.login_successful));
                MyApplication.setAuthenticated(true);
            }
            else if(responseCode==401){
                message=(MyApplication.getAppContext().getResources().getString(R.string.unauthorised));
                MyApplication.setAuthenticated(false);
            }
            else{
                message=(MyApplication.getAppContext().getResources().getString(R.string.authentication_error));
                MyApplication.setAuthenticated(false);
            }
            Log.d(TAG, "HTTP response:"+responseCode.toString());
            publishProgress(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        publishProgress(3);
        return message;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Log.d(TAG, "Progress: " + values[0]);
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Log.d(TAG, "GET - Result: " + result);
        Toast.makeText(MyApplication.getAppContext(), result, Toast.LENGTH_LONG).show();
    }
}
