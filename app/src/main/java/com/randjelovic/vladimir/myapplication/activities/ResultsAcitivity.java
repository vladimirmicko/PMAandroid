package com.randjelovic.vladimir.myapplication.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.randjelovic.vladimir.myapplication.R;
import com.randjelovic.vladimir.myapplication.common.MyApplication;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import data.dto.Result;

public class ResultsAcitivity extends AppCompatActivity {

    private TextView textViewResults;
    private Result result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_acitivity);
        textViewResults = (TextView) findViewById(R.id.textViewResults);
        result = MyApplication.getResult();
//        List<String> stringResults = new ArrayList<String>(stimulusResults.size());
//        for (Answer stimulusResult : stimulusResults) {
//            stringResults.add(String.valueOf(stimulusResult.getAnswer()));
//        }
        new SendResults().execute();
        new GetStatistics().execute();
        Button buttonResults = (Button) findViewById(R.id.button_results);
        buttonResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ResultsAcitivity.this.finish();
            }
        });
    }


    public class SendResults extends AsyncTask<String, Integer, String> {

        private final String TAG = this.getClass().getName();
        private final String COOKIE_HEADER = "Cookie";
        private String results = "";

        @Override
        protected String doInBackground(String... strings) {
            publishProgress(0);
            String message = null;
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.set(COOKIE_HEADER, "JSESSIONID = "+MyApplication.getToken());
            requestHeaders.setContentType(MediaType.APPLICATION_JSON);

            Result result = MyApplication.getResult();
            HttpEntity<Result> requestEntity = new HttpEntity<Result>(result, requestHeaders);
//            Answer stimulusResult = new Answer();
//            stimulusResult.setStimulusNo(12);
//            HttpEntity<Answer> requestEntity = new HttpEntity<Answer>(stimulusResult, requestHeaders);

            RestTemplate restTemplate = new RestTemplate(true);
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            ResponseEntity<String> responseEntity = null;


            try {
                responseEntity = restTemplate.exchange(MyApplication.getAppContext().getResources().getString(R.string.url_tests)+"/"+MyApplication.getSelectedTest().getId()+"/results", HttpMethod.POST, requestEntity, String.class);
                results= responseEntity.getBody();
            } catch (Exception e) {
                Log.v(TAG, "Exception: " + e.getMessage());
                throw e;
            }
            return results;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Log.d(TAG, "Progress: " + values[0]);
        }

        @Override
        protected void onPostExecute(String results) {
            super.onPostExecute(results);
            Log.d(TAG, "POST - Result: " + results);
            textViewResults.setText(results);
            MyApplication.setLastResults(results);
        }
    }


    public class GetStatistics extends AsyncTask<String, Integer, String> {

        private final String TAG = this.getClass().getName();
        private final String COOKIE_HEADER = "Cookie";
        private String results = "";

        @Override
        protected String doInBackground(String... strings) {
            publishProgress(0);
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.set(COOKIE_HEADER, "JSESSIONID = "+MyApplication.getToken());
            requestHeaders.setContentType(MediaType.APPLICATION_JSON);

            Result result = new Result();
            HttpEntity<Result> requestEntity = new HttpEntity<Result>(result, requestHeaders);

            RestTemplate restTemplate = new RestTemplate(true);
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            ResponseEntity<String> responseEntity = null;


            try {
                responseEntity = restTemplate.exchange(MyApplication.getAppContext().getResources().getString(R.string.url_tests)+"/statistics/"+MyApplication.getSelectedTest().getId(), HttpMethod.POST, requestEntity, String.class);
                results= responseEntity.getBody();
            } catch (Exception e) {
                Log.v(TAG, "Exception: " + e.getMessage());
                throw e;
            }
            return results;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Log.d(TAG, "Progress: " + values[0]);
        }

        @Override
        protected void onPostExecute(String results) {
            super.onPostExecute(results);
            Log.d(TAG, "POST - Statistics: " + results);
            MyApplication.setLastStatistics(results);
        }
    }
}
