package com.example.vedantiladda.quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.vedantiladda.quiz.user.GameActivity;
import com.example.vedantiladda.quiz.user.UserMain;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        startActivity(new Intent(LoginActivity.this, GameActivity.class));

    }
}
