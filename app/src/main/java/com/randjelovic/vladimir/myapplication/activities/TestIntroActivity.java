package com.randjelovic.vladimir.myapplication.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.randjelovic.vladimir.myapplication.R;
import com.randjelovic.vladimir.myapplication.common.MyApplication;

public class TestIntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_intro);
        final int testSelected = getIntent().getIntExtra("TEST_SELECTED", 1);

        Button startButton = (Button) findViewById(R.id.button_start_test);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyApplication.getTestScore().getScoreList().clear();
                Intent intent = new Intent(TestIntroActivity.this, TestingActivity.class);
                intent.putExtra("TEST_SELECTED", testSelected);
                TestIntroActivity.this.startActivity(intent);
                TestIntroActivity.this.finish();
            }
        });
    }
}
