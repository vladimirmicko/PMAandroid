package com.randjelovic.vladimir.myapplication.activities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.randjelovic.vladimir.myapplication.AsyncTasks.SynchDatabase;
import com.randjelovic.vladimir.myapplication.AsyncTasks.TaskListener;
import com.randjelovic.vladimir.myapplication.R;
import com.randjelovic.vladimir.myapplication.common.MyApplication;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import data.dto.UserAccount;

public class MyProfileActivity extends AppCompatActivity {
    private Button btSubmit;
    private EditText etUsername;
    private RadioButton rbMale;
    private RadioButton rbFemale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        btSubmit = (Button) findViewById(R.id.btSubmit);
        etUsername = (EditText) findViewById(R.id.etUsername);
        rbMale = (RadioButton) findViewById(R.id.rbMale);
        rbFemale = (RadioButton) findViewById(R.id.rbFemale);

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MyProfileActivity.MyProfileService().execute(etUsername.getText().toString());
            }
        });
    }

    public class MyProfileService extends AsyncTask<String, Integer, String> implements TaskListener {
        private final String TAG = this.getClass().getName();
        private String results = "";
        private UserAccount userAccount = null;
        private Intent starterIntent = null;

        @Override
        protected String doInBackground(String... strings) {
            publishProgress(0);

            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setContentType(MediaType.APPLICATION_JSON);

            userAccount = MyApplication.getUserAccount();
            HttpEntity<UserAccount> requestEntity = new HttpEntity<UserAccount>(userAccount, requestHeaders);
            RestTemplate restTemplate = new RestTemplate(true);
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            ResponseEntity<UserAccount> responseEntity = null;

            try {
                responseEntity = restTemplate.exchange(MyApplication.getAppContext().getResources().getString(R.string.url_authenticate), HttpMethod.POST, requestEntity, UserAccount.class);
                userAccount= responseEntity.getBody();
                HttpStatus responseCode = responseEntity.getStatusCode();

                if (responseCode.value()==200){
                    MyApplication.setAuthenticated(true);
                    MyApplication.setUserAccount(userAccount);
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
                starterIntent = new Intent(MyProfileActivity.this, SelectorActivity.class);
                new SynchDatabase(this).execute("");
            }
        }

        @Override
        public Context getContext() {
            return MyProfileActivity.this;
        }

        @Override
        public Intent getStarterIntent() {
            return starterIntent;
        }
    }
}
