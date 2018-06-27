package com.example.vedantiladda.quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.vedantiladda.quiz.dto.QuestionDTO;

import java.util.ArrayList;
import java.util.List;

public class PublishActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<QuestionDTO> questionDTOList = new ArrayList<>();
    private Boolean valid = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        mRecyclerView = (RecyclerView) findViewById(R.id.publishRecycler);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        Intent publish = getIntent();
        questionDTOList = (List<QuestionDTO>) publish.getSerializableExtra("questions");
        Log.d("check", questionDTOList.toString());
        mAdapter = new PublishAdapter(questionDTOList);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        Button publishButton = findViewById(R.id.publishButton);
        publishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valid = checkRules(questionDTOList);
            }
        });


    }

    public Boolean checkRules(List<QuestionDTO> questionList){
        return true;
    }
}
