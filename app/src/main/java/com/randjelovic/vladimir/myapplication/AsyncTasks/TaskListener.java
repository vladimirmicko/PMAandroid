package com.randjelovic.vladimir.myapplication.AsyncTasks;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Vladimir on 1/15/2017.
 */

public interface TaskListener {

    Context getContext();
    Intent getStarterIntent();
}
