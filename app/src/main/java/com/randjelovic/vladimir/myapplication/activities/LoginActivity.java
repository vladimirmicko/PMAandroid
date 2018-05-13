package com.randjelovic.vladimir.myapplication.activities;

import android.content.Context;
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

import com.randjelovic.vladimir.myapplication.AsyncTasks.SynchDatabase;
import com.randjelovic.vladimir.myapplication.AsyncTasks.TaskListener;
import com.randjelovic.vladimir.myapplication.common.MyApplication;
import com.randjelovic.vladimir.myapplication.R;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;

import data.dto.TestScore;
import data.dto.UserLogin;

public class LoginActivity extends AppCompatActivity {

    private Button btLogin;
    private EditText etUsername;
    private EditText etPassword;

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


    public class LoginService extends AsyncTask<String, Integer, String> implements TaskListener {
        private final String TAG = this.getClass().getName();
        private final String AUTHENTICATION_HEADER = "Authorization";
        private static final String AUTHENTICATION_TAG = "Basic ";
        private String results = "";
        private UserLogin userLogin = new UserLogin();
        private Intent starterIntent = null;

        @Override
        protected String doInBackground(String... strings) {
            publishProgress(0);

            HttpHeaders requestHeaders = new HttpHeaders();
//            String userCredentials = strings[0]+":"+strings[1];
//            String basicAuth = AUTHENTICATION_TAG + new String(Base64.encode(userCredentials.getBytes(), Base64.NO_WRAP));
//            MyApplication.setBasicAuth(basicAuth);
//            requestHeaders.set(AUTHENTICATION_HEADER, MyApplication.getBasicAuth());
            requestHeaders.setContentType(MediaType.APPLICATION_JSON);

            UserLogin userLogin = new UserLogin(strings[0], strings[1]);
            HttpEntity<UserLogin> requestEntity = new HttpEntity<UserLogin>(userLogin, requestHeaders);
            RestTemplate restTemplate = new RestTemplate(true);
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            ResponseEntity<UserLogin> responseEntity = null;

            try {
                responseEntity = restTemplate.exchange(MyApplication.getAppContext().getResources().getString(R.string.url_authenticate), HttpMethod.POST, requestEntity, UserLogin.class);
                userLogin= responseEntity.getBody();
                HttpStatus responseCode = responseEntity.getStatusCode();

                if (responseCode.value()==200){
                    MyApplication.setAuthenticated(true);
                    MyApplication.setToken(userLogin.getToken());
                    results=(MyApplication.getAppContext().getResources().getString(R.string.login_successful));
                }
                else if(responseCode.value()==401){
                    results=(MyApplication.getAppContext().getResources().getString(R.string.unauthorised));
                    MyApplication.setAuthenticated(false);
                }
                else{
                    results=(MyApplication.getAppContext().getResources().getString(R.string.authentication_error));
                    MyApplication.setAuthenticated(false);
                }
                Log.d(TAG, "HTTP response:"+responseCode.toString());
                publishProgress(2);

            } catch (Exception e) {
                Log.v(TAG, "Exception: " + e.getMessage());

                if (e.getMessage().contains("200")){
                    results=(MyApplication.getAppContext().getResources().getString(R.string.login_successful));
                    MyApplication.setAuthenticated(true);
                }
                else if(e.getMessage().contains("401")){
                    results=(MyApplication.getAppContext().getResources().getString(R.string.unauthorised));
                    MyApplication.setAuthenticated(false);
                }
                else{
                    results=(MyApplication.getAppContext().getResources().getString(R.string.authentication_error));
                    MyApplication.setAuthenticated(false);
                }
            }
            return results;
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
            Toast.makeText(MyApplication.getAppContext(), result, Toast.LENGTH_SHORT).show();

            if(MyApplication.isAuthenticated()){
                starterIntent = new Intent(LoginActivity.this, SelectorActivity.class);
                new SynchDatabase(this).execute("");
            }
        }

        @Override
        public Context getContext() {
            return LoginActivity.this;
        }

        @Override
        public Intent getStarterIntent() {
            return starterIntent;
        }
    }
}
