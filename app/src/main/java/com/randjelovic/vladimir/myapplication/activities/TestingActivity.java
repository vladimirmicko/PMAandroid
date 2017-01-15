package com.randjelovic.vladimir.myapplication.activities;

import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.randjelovic.vladimir.myapplication.R;
import com.randjelovic.vladimir.myapplication.common.MyApplication;

import java.io.ByteArrayInputStream;
import java.util.Timer;
import java.util.TimerTask;

import data.models.Test;

public class TestingActivity extends AppCompatActivity {

    private ImageView image = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);
        int testSelected = getIntent().getIntExtra("TEST_SELECTED", 1);
        final Test test = MyApplication.getTests().get(testSelected);
        image = (ImageView) findViewById(R.id.testImage);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                image.setImageBitmap(BitmapFactory.decodeStream(new ByteArrayInputStream(test.getSlideList().get(1).getTestImage())));
            }
        }, 1000);
    }
}
