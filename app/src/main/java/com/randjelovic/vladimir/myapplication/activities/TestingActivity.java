package com.randjelovic.vladimir.myapplication.activities;

import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
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
    private Button buttonBad;
    private Button buttonGood;
    private Button buttonStop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);
        int testSelected = getIntent().getIntExtra("TEST_SELECTED", 1);
        test = MyApplication.getTests().get(testSelected);
        image = (ImageView) findViewById(R.id.testImage);

        buttonBad = (Button) findViewById(R.id.button_bad);
        buttonGood = (Button) findViewById(R.id.button_good);
        buttonStop = (Button) findViewById(R.id.button_stop_test);

        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TestingActivity.this.finish();
            }
        });

        buttonGood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        final Handler primeImageHandler = new Handler();
        final Handler testImageHandler = new Handler();

        final Runnable primeImageUpdateResults = new Runnable() {
            public void run() {
                showPrimeImage();
            }
        };

        final Runnable testImageUpdateResults = new Runnable() {
            public void run() {
                showTestImage();
            }
        };

        Timer primeTimer = new Timer();
        primeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                primeImageHandler.post(primeImageUpdateResults);
            }
        },  300);

        Timer testTimer = new Timer();
        testTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                testImageHandler.post(testImageUpdateResults);
            }
        },  1000);
    }

    private void showPrimeImage() {
        image.setImageBitmap(BitmapFactory.decodeStream(new ByteArrayInputStream(test.getSlideList().get(imageIndex).getPrimingImage())));
        int i = 1;
    }



    private void showTestImage() {
        image.setImageBitmap(BitmapFactory.decodeStream(new ByteArrayInputStream(test.getSlideList().get(imageIndex).getTestImage())));
        imageIndex++;
//
//        Animation fade1 = new AlphaAnimation(0.0f, 1.0f);
//        fade1.setDuration(3000);
//        image.startAnimation(fade1);
    }
}
