package com.randjelovic.vladimir.myapplication.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

import java.io.ByteArrayInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import data.models.Slide;
import data.models.Test;

public class GetTestWithMapper extends AppCompatActivity {

    Button buttonTest;
    ImageView image;
    Button buttonSlide;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_test_with_mapper);
        image = (ImageView) findViewById(R.id.imageTest);
        buttonTest = (Button) findViewById(R.id.buttonTest);
        buttonSlide = (Button) findViewById(R.id.buttonSlide);

        buttonTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetTestWithMapper.GetTestService().execute("test");
            }
        });

        buttonSlide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetTestWithMapper.GetTestService().execute("slide");
            }
        });
    }

    public class GetTestService extends AsyncTask<String, Integer, ResponseEntity<Slide>> {

        private static final String TAG = "GetTestWithMapper";
        private static final String AUTHENTICATION_HEADER = "Authorization";
        private Bitmap tempImage;
        URL urlTest;
        URL urlSlide;

        @Override
        protected ResponseEntity doInBackground(String... strings) {
            publishProgress(0);
            String message = null;
            try {
                urlTest = new URL("http://10.0.2.2:8092/PMAspring/rest/tests");
                urlSlide = new URL("http://10.0.2.2:8092/PMAspring/rest/tests/slides");
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

            ResponseEntity response=null;

            try {
                if(strings[0].equals("test")) response = restTemplate.exchange("http://10.0.2.2:8092/PMAspring/rest/tests/1", HttpMethod.GET, requestEntity, Test.class);
                if(strings[0].equals("slide")) response = restTemplate.exchange("http://10.0.2.2:8092/PMAspring/rest/tests/1/slides", HttpMethod.GET, requestEntity, (new ArrayList<Slide>()).getClass());
            }
            catch(HttpClientErrorException e){
                throw e;
            }
            catch(ResourceAccessException e){
                Log.v(TAG, "exception "+ e.getMessage());
                throw e;
            }catch(HttpServerErrorException e)
            {
                Log.v(TAG, "exception "+ e.getMessage());
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
        protected void onPostExecute(ResponseEntity<Slide> response) {
            super.onPostExecute(response);
            Log.d(TAG, "GET - Result: " + response);


            Toast.makeText(MyApplication.getAppContext(), "Slika je prikazana: "+response.getBody().getSlideName(), Toast.LENGTH_LONG).show();
            tempImage = BitmapFactory.decodeStream(new ByteArrayInputStream(response.getBody().getPrimingImage()));
            ImageView iii = GetTestWithMapper.this.image;
            iii.setImageBitmap(tempImage);
        }
    }

}
