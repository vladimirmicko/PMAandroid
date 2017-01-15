package com.randjelovic.vladimir.myapplication.activities;

import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.randjelovic.vladimir.myapplication.R;
import com.randjelovic.vladimir.myapplication.common.MyApplication;

import java.io.ByteArrayInputStream;
import java.util.Timer;
import java.util.TimerTask;

import data.models.Test;

public class TestingActivity extends AppCompatActivity {

    private ImageView image = null;
    private Test test = null;
    private int imageIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);
        int testSelected = getIntent().getIntExtra("TEST_SELECTED", 1);
        test = MyApplication.getTests().get(testSelected);
        image = (ImageView) findViewById(R.id.testImage);


        final Handler mHandler = new Handler();

        final Runnable mUpdateResults = new Runnable() {
            public void run() {
                show();
            }
        };


        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                mHandler.post(mUpdateResults);
            }
        }, 1000);}


    private void show() {
        image.setImageBitmap(BitmapFactory.decodeStream(new ByteArrayInputStream(test.getSlideList().get(imageIndex).getTestImage())));
        imageIndex++;

        Animation fade1 = new AlphaAnimation(0.0f, 1.0f);
        fade1.setDuration(3000);
        image.startAnimation(fade1);
    }
}
