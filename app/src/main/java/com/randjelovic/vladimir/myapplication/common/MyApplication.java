package com.randjelovic.vladimir.myapplication.common;

import android.app.Application;
import android.content.Context;

/**
 * Created by Vladimir on 1/2/2017.
 */

public class MyApplication extends Application {

    private static Context appContext;
    private static boolean authenticated;
    private static String basicAuth;


    public void onCreate() {
        super.onCreate();
        MyApplication.appContext = getApplicationContext();
        authenticated=false;
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

}



