package com.randjelovic.vladimir.myapplication.activities;

import android.content.Intent;
import android.media.Image;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.randjelovic.vladimir.myapplication.R;

public class IntroActivity extends AppCompatActivity {

    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        image = (ImageView) findViewById(R.id.splashscreen);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent intent = new Intent(IntroActivity.this, LoginActivity.class);
                IntroActivity.this.startActivity(intent);
                IntroActivity.this.finish(); }
        }, getResources().getInteger(R.integer.splash_time));
    }
}
