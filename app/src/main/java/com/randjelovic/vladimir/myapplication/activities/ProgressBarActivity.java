package com.randjelovic.vladimir.myapplication.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.randjelovic.vladimir.myapplication.R;

public class ProgressBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);

        Intent mainIntent = new Intent(this, LoginActivity.class);
        startActivity(mainIntent);
    }
}
