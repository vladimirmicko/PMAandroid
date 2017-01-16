package com.randjelovic.vladimir.myapplication.common;

import android.app.Application;
import android.content.Context;

import com.randjelovic.vladimir.myapplication.expandableadapter.Group;

import java.util.ArrayList;
import java.util.List;

import data.dao.SlideDao;
import data.dao.TestDao;
import data.models.Slide;
import data.models.Test;

/**
 * Created by Vladimir on 1/2/2017.
 */

public class MyApplication extends Application {

    private static Context appContext;
    private static boolean authenticated;
    private static String basicAuth;
    private static List<Test> testList;
    private static Integer selectedTest;
    private static List<Integer> testScore;
    private static List<Slide> slideList;
    private static TestDao testDao;


    public void onCreate() {
        super.onCreate();
        MyApplication.appContext = getApplicationContext();
        authenticated=false;
        testScore = new ArrayList();
    }

    public static List<Test> loadTestsFromDb(){
        testDao = new TestDao();
        testList = testDao.getAll();
        return testList;
    }

    public static Context getAppContext() {
        return MyApplication.appContext;
    }
    public static boolean isAuthenticated() { return MyApplication.authenticated; }
    public static void setAuthenticated(boolean value) {MyApplication.authenticated=value;}
    public static String getBasicAuth() {
        return MyApplication.basicAuth;
    }
    public static void setBasicAuth(String basicAuth) {MyApplication.basicAuth=basicAuth;}
    public static List<Test> getTestList() { return testList; }
    public static void setTestList(List<Test> testList) { MyApplication.testList = testList; }
    public static List<Integer> getTestScore() { return testScore; }
    public static void setTestScore(List<Integer> testScore) { MyApplication.testScore = testScore; }
    public static Integer getSelectedTest() { return selectedTest; }
    public static void setSelectedTest(Integer selectedTest) { MyApplication.selectedTest = selectedTest; }
    public static List<Slide> getSlideList() { return MyApplication.getTestList().get(MyApplication.getSelectedTest()).getSlideList(); }
}



