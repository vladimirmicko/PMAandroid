package com.randjelovic.vladimir.myapplication.activities;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.randjelovic.vladimir.myapplication.R;
import com.randjelovic.vladimir.myapplication.common.MyApplication;

import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import data.models.Test;

public class GetTestWithMapper extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_test);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetTestWithMapper.GetTestService().execute();
            }
        });
    }

    public class GetTestService extends AsyncTask<String, Integer, ResponseEntity<Test>> {

        private static final String TAG = "GetTest";
        private static final String AUTHENTICATION_TAG = "Basic ";
        private static final String AUTHENTICATION_HEADER = "Authorization";
        private Bitmap image;
        URL url;

        @Override
        protected ResponseEntity<Test> doInBackground(String... strings) {
            publishProgress(0);
            String message = null;
            try {
                url = new URL("http://10.0.2.2:8092/PMAspring/rest/tests");
            }
            catch (Exception e){
            }
//            HttpAuthentication authHeader = new HttpBasicAuthentication("v", "v");
            HttpHeaders requestHeaders = new HttpHeaders();
//            requestHeaders.setAuthorization(authHeader);
            requestHeaders.set(AUTHENTICATION_HEADER, MyApplication.getBasicAuth());
            requestHeaders.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);

            RestTemplate restTemplate = new RestTemplate(true);
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

            ResponseEntity<Test> response;

            try {
                response = restTemplate.exchange("http://10.0.2.2:8092/PMAspring/rest/tests/1", HttpMethod.GET, requestEntity, Test.class);
            }
            catch(HttpClientErrorException e){
                throw e;
            }
            catch(ResourceAccessException e){
                Log.v("getData() ", "exception "+ e.getMessage());
                throw e;
            }catch(HttpServerErrorException e)
            {
                Log.v("getData() ", "exception "+ e.getMessage());
                throw e;
            }
            return response;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Log.d(TAG, "Progress: " + values[0]);
        }

        @Override
        protected void onPostExecute(ResponseEntity<Test> result) {
            super.onPostExecute(result);
            Log.d(TAG, "GET - Result: " + result);
            Toast.makeText(MyApplication.getAppContext(), "Slika je prikazana: "+result.getBody().getTestName(), Toast.LENGTH_LONG).show();
        }
    }

}
