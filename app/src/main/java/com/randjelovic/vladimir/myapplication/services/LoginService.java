package com.randjelovic.vladimir.myapplication.services;

import android.app.Application;
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
        HttpURLConnection urlConnection=null;
        try {
            //URL url = new URL(MyApplication.getAppContext().getResources().getString(R.string.url_authenticate));
            URL url = new URL("http://10.0.2.2:8092/PMAspring/rest/demo/users");

            urlConnection = (HttpURLConnection) url.openConnection();
            String userCredentials = "vlada:vlada";
            String basicAuth = "Basic " + new String(Base64.encode(userCredentials.getBytes(), Base64.NO_WRAP));

            basicAuth="Basic dmxhZGE6dmxhZGE=";
            urlConnection.setRequestProperty ("Authorization", basicAuth);

            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("GET");
            urlConnection.setUseCaches(false);
            urlConnection.setConnectTimeout(10000);
            urlConnection.setReadTimeout(10000);
//            urlConnection.setRequestProperty("Content-Type","application/json");
            urlConnection.connect();

//            JSONObject jsonParam = new JSONObject();
//            try {
//                jsonParam.put("username", strings[0]);
//                jsonParam.put("password", strings[1]);
//            }
//            catch (Exception e){
//                e.printStackTrace();
//                throw e;
//            }

            publishProgress(1);

            OutputStreamWriter out = new OutputStreamWriter(urlConnection.getOutputStream());
            out.flush();
//            out.write(jsonParam.toString());
            out.close();

            int httpResult = urlConnection.getResponseCode();
            if(httpResult ==HttpURLConnection.HTTP_OK){
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        urlConnection.getInputStream(),"utf-8"));
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();
                Log.d("Token: ", sb.toString());

            }else{
                Log.d("HTTP result: ", new Integer(httpResult).toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(urlConnection!=null) urlConnection.disconnect();
        }
        publishProgress(3);
        return sb.toString();
    }


    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Log.d("This is progress update","  -------------- "+values[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }


}
