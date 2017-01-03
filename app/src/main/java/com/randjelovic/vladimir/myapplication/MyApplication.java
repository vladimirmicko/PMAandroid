package com.randjelovic.vladimir.myapplication;

import android.app.Application;
import android.content.Context;

/**
 * Created by Vladimir on 1/2/2017.
 */

public class MyApplication extends Application {

    private static Context appContext;

    public void onCreate() {
        super.onCreate();
        MyApplication.appContext = getApplicationContext();
    }

    public static Context getAppContext() {
        return MyApplication.appContext;
    }

}

