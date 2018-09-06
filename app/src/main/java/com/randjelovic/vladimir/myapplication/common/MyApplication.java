package com.randjelovic.vladimir.myapplication.common;

import android.app.Application;
import android.content.Context;

import java.util.List;

import data.dao.TestDao;
import data.dto.Result;
import data.dto.Statistics;
import data.dto.UserAccount;
import data.models.Slide;
import data.models.Test;

/**
 * Created by Vladimir on 1/2/2017.
 */

public class MyApplication extends Application {

    private static Context appContext;
    private static boolean authenticated;
    private static UserAccount userAccount;
    private static List<Test> testList;
    private static Integer selectedTestNo;
    private static Result result;
    private static TestDao testDao;
    private static String lastResults;
    private static Statistics statistics;

    public void onCreate() {
        super.onCreate();
        MyApplication.appContext = getApplicationContext();
        authenticated=false;
        result = new Result();
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

    public static List<Test> getTestList() {
        return testList;
    }

    public static void setTestList(List<Test> testList) {
        MyApplication.testList = testList;
    }

    public static Result getResult() {
        return result;
    }

    public static void setResult(Result result) {
        MyApplication.result = result;
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

    public static UserAccount getUserAccount() {
        return userAccount;
    }

    public static void setUserAccount(UserAccount userAccount) {
        MyApplication.userAccount = userAccount;
    }

    public static TestDao getTestDao() {
        return testDao;
    }

    public static void setTestDao(TestDao testDao) {
        MyApplication.testDao = testDao;
    }

    public static Statistics getStatistics() {
        return statistics;
    }

    public static void setStatistics(Statistics statistics) {
        MyApplication.statistics = statistics;
    }
}



