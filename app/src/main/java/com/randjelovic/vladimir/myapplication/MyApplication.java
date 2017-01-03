package com.randjelovic.vladimir.myapplication;

import android.app.Application;
import android.content.Context;

/**
 * Created by Vladimir on 1/2/2017.
 */

public class MyApplication extends Application {

    private static Context appContext;
    private static boolean authenticated;

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
    public static void setAuthenticated(boolean value) {authenticated=value;}
}

