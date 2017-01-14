package com.randjelovic.vladimir.myapplication.common;

import android.app.Application;
import android.content.Context;

import com.randjelovic.vladimir.myapplication.expandableadapter.Group;

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
    private static TestDao testDao;


    public void onCreate() {
        super.onCreate();
        MyApplication.appContext = getApplicationContext();
        authenticated=false;
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
    public static void setAuthenticated(boolean value) {MyApplication.authenticated=value;}

    public static String getBasicAuth() {
        return MyApplication.basicAuth;
    }
    public static void setBasicAuth(String basicAuth) {MyApplication.basicAuth=basicAuth;}
    public static List<Test> getTests() { return testList; }
    public static void setTests(List<Test> tests) { MyApplication.testList = tests; }
}



