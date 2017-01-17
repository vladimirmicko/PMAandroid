package com.randjelovic.vladimir.myapplication.activities;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.randjelovic.vladimir.myapplication.R;
import com.randjelovic.vladimir.myapplication.common.MyApplication;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import data.models.Slide;
import data.dto.TestScore;

public class ResultsAcitivity extends AppCompatActivity {

    private TextView textViewResults;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_acitivity);
        textViewResults = (TextView) findViewById(R.id.textViewResults);
        List<Integer> integerResults = MyApplication.getTestScore();
        List<String> stringResults = new ArrayList<String>(integerResults.size());
        for (Integer myInt : integerResults) {
            stringResults.add(String.valueOf(myInt));
        }
        new SendResults().execute(stringResults.toArray(new String[0]));
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
        private final String AUTHENTICATION_HEADER = "Authorization";
        private String results = "";

        @Override
        protected String doInBackground(String... strings) {
            publishProgress(0);
            String message = null;
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.set(AUTHENTICATION_HEADER, MyApplication.getBasicAuth());
            requestHeaders.setContentType(MediaType.APPLICATION_JSON);

            TestScore testScore = new TestScore(Arrays.asList(strings));
            HttpEntity<TestScore> requestEntity = new HttpEntity<TestScore>(testScore, requestHeaders);

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
        }
    }
}
