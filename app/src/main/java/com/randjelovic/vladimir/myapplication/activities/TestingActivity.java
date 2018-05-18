package com.randjelovic.vladimir.myapplication.activities;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.randjelovic.vladimir.myapplication.R;
import com.randjelovic.vladimir.myapplication.common.MyApplication;

import java.io.ByteArrayInputStream;
import java.util.Timer;
import java.util.TimerTask;

import data.dto.Answer;
import data.dto.Result;
import data.models.Test;

public class TestingActivity extends AppCompatActivity {

    private ImageView image = null;
    private Test test = null;
    private int imageIndex = 0;
    private Button buttonBad;
    private Button buttonGood;
    private Button buttonStop;

    private Timer primeTimer;
    private Timer testTimer;
    private Handler primeImageHandler = null;
    private Handler testImageHandler = null;
    private Runnable primeImageUpdateResults;
    private Runnable testImageUpdateResults;

    private Result result;
    private long primeShowTime;
    private long testShowTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);
        int testSelected = getIntent().getIntExtra("TEST_SELECTED", 1);
        test = MyApplication.getTestList().get(testSelected);
        image = (ImageView) findViewById(R.id.testImage);

        buttonBad = (Button) findViewById(R.id.button_bad);
        buttonGood = (Button) findViewById(R.id.button_good);
        buttonStop = (Button) findViewById(R.id.button_stop_test);

        result = new Result();
        result.setTestStartTime(System.currentTimeMillis());
        result.setTestId((test.getId().intValue()));


        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TestingActivity.this.finish();
            }
        });

        buttonGood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Answer answer = new Answer();
                answer.setAnswerValue(1);
                answer.setAnswerTime(System.currentTimeMillis());
                answer.setPrimeStimShowTime(primeShowTime);
                answer.setTestStimShowTime(testShowTime);
                answer.setAnswerNumber(imageIndex);
                result.addStimulusResult(answer);
                MyApplication.setResult(result);
                if (imageIndex < test.getSlideList().size()) {
                    activateSlides();
                } else {
                    Intent intent = new Intent(TestingActivity.this, ResultsAcitivity.class);
                    TestingActivity.this.startActivity(intent);
                    TestingActivity.this.finish();
                }
            }
        });

        buttonBad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Answer answer = new Answer();
                answer.setAnswerValue(0);
                answer.setAnswerTime(System.currentTimeMillis());
                answer.setPrimeStimShowTime(primeShowTime);
                answer.setTestStimShowTime(testShowTime);
                answer.setAnswerNumber(imageIndex);
                result.addStimulusResult(answer);
                MyApplication.setResult(result);
                if (imageIndex < test.getSlideList().size()) {
                    activateSlides();
                } else {
                    Intent intent = new Intent(TestingActivity.this, ResultsAcitivity.class);
                    TestingActivity.this.startActivity(intent);
                    TestingActivity.this.finish();
                }
            }
        });

        primeTimer = new Timer();
        testTimer = new Timer();

        primeImageHandler = new Handler();
        testImageHandler = new Handler();

        primeImageUpdateResults = new Runnable() {
            public void run() {
                showPrimeImage();
            }
        };

        testImageUpdateResults = new Runnable() {
            public void run() {
                showTestImage();
            }
        };

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                activateSlides();
            }
        }, 1);
    }

    private void activateSlides() {

        primeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                TestingActivity.this.primeImageHandler.post(TestingActivity.this.primeImageUpdateResults);
            }
        }, getResources().getInteger(R.integer.initial_delay));


        testTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                TestingActivity.this.testImageHandler.post(TestingActivity.this.testImageUpdateResults);
            }
        }, getResources().getInteger(R.integer.initial_delay) + test.getSlideList().get(imageIndex).getDelay());
    }


    private void showPrimeImage() {
        if (imageIndex < test.getSlideList().size()) {
            image.setImageBitmap(BitmapFactory.decodeStream(new ByteArrayInputStream(test.getSlideList().get(imageIndex).getPrimingImage())));
            primeShowTime=System.currentTimeMillis();
        }
    }


    private void showTestImage() {
        if (imageIndex < test.getSlideList().size()) {
            image.setImageBitmap(BitmapFactory.decodeStream(new ByteArrayInputStream(test.getSlideList().get(imageIndex).getTestImage())));
            testShowTime=System.currentTimeMillis();
        }
        imageIndex++;
//
//        Animation fade1 = new AlphaAnimation(0.0f, 1.0f);
//        fade1.setDuration(3000);
//        image.startAnimation(fade1);
    }
}
