package com.randjelovic.vladimir.myapplication.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.randjelovic.vladimir.myapplication.R;
import com.randjelovic.vladimir.myapplication.services.LoginService;

public class Login extends AppCompatActivity {

    Button btLogin;
    EditText etUsername;
    EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btLogin = (Button) findViewById(R.id.btLogin);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);

         btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new LoginService().execute(etUsername.getText().toString(),etPassword.getText().toString());
            }
        });
    }
}
