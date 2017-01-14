package com.randjelovic.vladimir.myapplication.AsyncTasks;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.randjelovic.vladimir.myapplication.R;
import com.randjelovic.vladimir.myapplication.activities.GetTestWithMapper;
import com.randjelovic.vladimir.myapplication.common.MyApplication;

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
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import data.models.Slide;
import data.models.Test;

/**
 * Created by Vladimir on 1/14/2017.
 */


public class SynchDatabase extends AsyncTask<String, Integer, List<Test>> {


    private final String TAG = this.getClass().getName();
    private final String AUTHENTICATION_HEADER = "Authorization";
    private Context context;
    private List<Test> allTests = null;

    public SynchDatabase(Context context) {
        this.context = context;
    }

    @Override
    protected List<Test> doInBackground(String... strings) {
        publishProgress(0);
        String message = null;
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set(AUTHENTICATION_HEADER, MyApplication.getBasicAuth());
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
        RestTemplate restTemplate = new RestTemplate(true);
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        ResponseEntity<? extends ArrayList> responseEntityTests = null;

        try {
            responseEntityTests = restTemplate.exchange(MyApplication.getAppContext().getResources().getString(R.string.url_tests), HttpMethod.GET, requestEntity, (new ArrayList<Test>()).getClass());
            allTests=responseEntityTests.getBody();
        } catch (Exception e) {
            Log.v(TAG, "Exception: " + e.getMessage());
            throw e;
        }
        return allTests;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Log.d(TAG, "Progress: " + values[0]);
    }

    @Override
    protected void onPostExecute(List<Test> allTests) {
        super.onPostExecute(allTests);
        Log.d(TAG, "GET - Result: " + allTests);


        Toast.makeText(MyApplication.getAppContext(), "All tests are synchronized! ", Toast.LENGTH_LONG).show();
//        tempImage = BitmapFactory.decodeStream(new ByteArrayInputStream(response.getBody().getPrimingImage()));
//        ImageView iii = GetTestWithMapper.this.image;
//        iii.setImageBitmap(tempImage);
    }
}



