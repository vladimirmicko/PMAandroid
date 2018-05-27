package com.randjelovic.vladimir.myapplication.AsyncTasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
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
    private final String COOKIE_HEADER = "Cookie";
    private TaskListener taskListener;
    private List<Test> testList = null;

    public SynchDatabase(TaskListener taskListener) {
        this.taskListener = taskListener;
    }

    @Override
    protected List<Test> doInBackground(String... strings) {
        publishProgress(0);
        String message = null;
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set(COOKIE_HEADER, "JSESSIONID = "+MyApplication.getUserAccount().getSessionId());
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
        RestTemplate restTemplate = new RestTemplate(true);
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        ResponseEntity<Test[]> responseEntityTests = null;

        try {
            responseEntityTests = restTemplate.exchange(MyApplication.getAppContext().getResources().getString(R.string.url_tests), HttpMethod.GET, requestEntity, Test[].class);
            testList= Arrays.asList(responseEntityTests.getBody());
        } catch (Exception e) {
            Log.v(TAG, "Exception: " + e.getMessage());
            throw e;
        }

        TestDao testDao = new TestDao();
        testDao.getDbHelper().onUpgrade(testDao.getDb(), 1, 2);
        testDao.insertAll(testList);
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
        Toast.makeText(MyApplication.getAppContext(), R.string.test_synchronized, Toast.LENGTH_SHORT).show();
        taskListener.getContext().startActivity(taskListener.getStarterIntent());
        ((Activity) taskListener.getContext()).finish();
    }
}



