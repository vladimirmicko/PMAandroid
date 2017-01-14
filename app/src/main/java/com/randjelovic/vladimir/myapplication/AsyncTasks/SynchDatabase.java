package com.randjelovic.vladimir.myapplication.AsyncTasks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.fasterxml.jackson.core.type.TypeReference;
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
import java.util.Arrays;
import java.util.List;

import data.dao.SlideDao;
import data.dao.TestDao;
import data.models.Slide;
import data.models.Test;

/**
 * Created by Vladimir on 1/14/2017.
 */


public class SynchDatabase extends AsyncTask<String, Integer, List<Test>> {


    private final String TAG = this.getClass().getName();
    private final String AUTHENTICATION_HEADER = "Authorization";
    private Context context;
    private List<Test> testList = null;

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
        ResponseEntity<Test[]> responseEntityTests = null;

        try {
            responseEntityTests = restTemplate.exchange(MyApplication.getAppContext().getResources().getString(R.string.url_tests), HttpMethod.GET, requestEntity, Test[].class);
            testList= Arrays.asList(responseEntityTests.getBody());
//            testList=responseEntityTests.getBody();
        } catch (Exception e) {
            Log.v(TAG, "Exception: " + e.getMessage());
            throw e;
        }


        TestDao testDao = new TestDao();
        SlideDao slideDao = new SlideDao();
        testDao.getDbHelper().onUpgrade(testDao.getDb(), 1, 2);

        for(Test test : testList){
            Long testId = testDao.insert(test);
            for(Slide slide : test.getSlideList()){
                slide.setTestId(testId);
                slideDao.insert(slide);
            }
        }
        return testList;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Log.d(TAG, "Progress: " + values[0]);
    }

    @Override
    protected void onPostExecute(List<Test> testList) {
        super.onPostExecute(testList);
        MyApplication.loadTestsFromDb();
        Log.d(TAG, "GET - Result: " + testList);
        Toast.makeText(MyApplication.getAppContext(), "All tests are synchronized! ", Toast.LENGTH_LONG).show();
//        tempImage = BitmapFactory.decodeStream(new ByteArrayInputStream(response.getBody().getPrimingImage()));
//        ImageView iii = GetTestWithMapper.this.image;
//        iii.setImageBitmap(tempImage);
    }
}



