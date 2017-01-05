package com.randjelovic.vladimir.myapplication.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.randjelovic.vladimir.myapplication.MyApplication;
import com.randjelovic.vladimir.myapplication.R;

import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    Button btLogin;
    EditText etUsername;
    EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btLogin = (Button) findViewById(R.id.btLogin);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);

         btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new LoginService().execute(etUsername.getText().toString(),etPassword.getText().toString());
            }
        });
    }


    public class LoginService extends AsyncTask<String, Integer, String> {

        private static final String TAG = "Login";
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
                MyApplication.setBasicAuth(basicAuth);
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

            if(MyApplication.isAuthenticated()){
                Intent mainIntent = new Intent(LoginActivity.this,SelectorActivity.class);
                LoginActivity.this.startActivity(mainIntent);
                LoginActivity.this.finish();
            }
        }
    }



}
