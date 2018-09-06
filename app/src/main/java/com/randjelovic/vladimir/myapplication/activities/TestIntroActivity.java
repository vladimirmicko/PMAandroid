package com.randjelovic.vladimir.myapplication.activities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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

public class TestIntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_intro);
        final int testSelected = getIntent().getIntExtra("TEST_SELECTED", 1);

        Button startButton = (Button) findViewById(R.id.button_start_test);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyApplication.getResult().getAnswerList().clear();
                Intent intent = new Intent(TestIntroActivity.this, TestingActivity.class);
                intent.putExtra("TEST_SELECTED", testSelected);
                TestIntroActivity.this.startActivity(intent);
                TestIntroActivity.this.finish();
            }
        });

        new SynchronizationService().execute();
    }


    public class SynchronizationService extends AsyncTask<String, Integer, String> {
        private final String TAG = this.getClass().getName();
        private final String COOKIE_HEADER = "Cookie";
        private UserAccount userAccount = null;
        private Intent starterIntent = null;
        private String results;
        private Long serverTime=0L;
        private Long deltaT=0L;

        @Override
        protected String doInBackground(String... strings) {
            publishProgress(0);

            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.set(COOKIE_HEADER, "JSESSIONID = "+MyApplication.getUserAccount().getSessionId());
            requestHeaders.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> requestEntity = new HttpEntity<String>("", requestHeaders);
            RestTemplate restTemplate = new RestTemplate(true);
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            ResponseEntity<Long> responseEntity = null;

            try {
                long time1=System.nanoTime();
                responseEntity = restTemplate.exchange(MyApplication.getAppContext().getResources().getString(R.string.url_base)+MyApplication.getAppContext().getResources().getString(R.string.url_server_time), HttpMethod.GET, requestEntity, Long.class);
                long time2=System.nanoTime();
                serverTime = responseEntity.getBody();
                HttpStatus responseCode = responseEntity.getStatusCode();

                if (responseCode.value()==200){
                    deltaT=(time2-time1)/2000000;
                }
                publishProgress(1);
            } catch (Exception e) {
                Log.v(TAG, "Exception: " + e.getMessage());
            }

            try {
                responseEntity = restTemplate.exchange(MyApplication.getAppContext().getResources().getString(R.string.url_base)+MyApplication.getAppContext().getResources().getString(R.string.url_synchronization)+"/"+System.nanoTime()+"/"+deltaT, HttpMethod.GET, requestEntity, Long.class);
                HttpStatus responseCode = responseEntity.getStatusCode();
                publishProgress(2);
            } catch (Exception e) {
                Log.v(TAG, "Exception: " + e.getMessage());
            }
            return serverTime.toString();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Log.d(TAG, "Progress: " + values[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d(TAG, "Server time: " + result);
            Log.d(TAG, "Delta T: " + deltaT);
        }
    }
}
