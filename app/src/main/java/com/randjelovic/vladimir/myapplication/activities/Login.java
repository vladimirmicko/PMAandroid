package com.randjelovic.vladimir.myapplication.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.randjelovic.vladimir.myapplication.R;
import com.randjelovic.vladimir.myapplication.services.LoginService;

public class Login extends AppCompatActivity {

    Button btLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btLogin = (Button) findViewById(R.id.btLogin);

         btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new LoginService().execute("vlada","vlada");
            }
        });
    }
}
