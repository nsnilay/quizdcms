package com.example.vedantiladda.quiz.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.vedantiladda.quiz.R;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public class ContestNames extends AppCompatActivity {

    private Intent intent;
    private String category;
    private Retrofit retrofit;
    private OkHttpClient client;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contest_names);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.)
        Intent intent = getIntent();
        category = intent.getStringExtra("category");

        Toast.makeText(ContestNames.this,category,Toast.LENGTH_SHORT).show();

        displayAllContests(category);
    }

}
