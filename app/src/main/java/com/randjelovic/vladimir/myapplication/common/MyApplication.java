package com.randjelovic.vladimir.myapplication.common;

import android.app.Application;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import data.dao.TestDao;
import data.dto.StimulusResult;
import data.dto.TestScore;
import data.models.Slide;
import data.models.Test;

/**
 * Created by Vladimir on 1/2/2017.
 */

public class MyApplication extends Application {

    private static Context appContext;
    private static boolean authenticated;
    private static String basicAuth;
    private static String token;
    private static List<Test> testList;
    private static Integer selectedTestNo;
    private static TestScore testScore;
    private static TestDao testDao;
    private static String lastResults;
    private static String lastStatistics;


    public void onCreate() {
        super.onCreate();
        MyApplication.appContext = getApplicationContext();
        authenticated=false;
        testScore = new TestScore();
    }

    public static List<Test> loadTestsFromDb(){
        testDao = new TestDao();
        testList = testDao.getAll();
        return testList;
    }

    public static Context getAppContext() {
        return MyApplication.appContext;
    }

    public static boolean isAuthenticated() {
        return MyApplication.authenticated;
    }

    public static void setAuthenticated(boolean value) {
        MyApplication.authenticated = value;
    }

    public static String getBasicAuth() {
        return MyApplication.basicAuth;
    }

    public static void setBasicAuth(String basicAuth) {
        MyApplication.basicAuth = basicAuth;
    }

    public static List<Test> getTestList() {
        return testList;
    }

    public static void setTestList(List<Test> testList) {
        MyApplication.testList = testList;
    }

    public static TestScore getTestScore() {
        return testScore;
    }

    public static void setTestScore(TestScore testScore) {
        MyApplication.testScore = testScore;
    }

    public static Integer getSelectedTestNo() {
        return selectedTestNo;
    }

    public static void setSelectedTestNo(Integer selectedTestNo) {
        MyApplication.selectedTestNo = selectedTestNo;
    }

    public static List<Slide> getSlideList() {
        return MyApplication.getTestList().get(MyApplication.getSelectedTestNo()).getSlideList();
    }

    public static Test getSelectedTest() {
        return MyApplication.getTestList().get(MyApplication.getSelectedTestNo());
    }

    public static String getLastResults() {
        return MyApplication.lastResults;
    }

    public static void setLastResults(String lastResults) {
        MyApplication.lastResults = lastResults;
    }

    public static String getLastStatistics() {
        return MyApplication.lastStatistics;
    }

    public static void setLastStatistics(String lastStatistics) {
        MyApplication.lastStatistics = lastStatistics;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        MyApplication.token = token;
    }
}



