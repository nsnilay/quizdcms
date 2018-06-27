package com.example.vedantiladda.quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.vedantiladda.quiz.dto.ContestRulesDTO;
import com.example.vedantiladda.quiz.dto.QuestionDTO;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PublishActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<QuestionDTO> questionDTOList = new ArrayList<>();
    private Boolean valid = false;
    OkHttpClient client = new OkHttpClient.Builder().build();
    final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.177.2.15:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build();
    ContestRulesDTO rules;
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
                if(valid){
                    //
                }
                else{
                    Toast.makeText(PublishActivity.this, "invalid contest", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(PublishActivity.this, QuestionBankActivity.class);
                    startActivity(i);
                }
            }
        });


    }

    public Boolean checkRules(List<QuestionDTO> questionList){

        IApiCall iApiCall = retrofit.create(IApiCall.class);
        final Call<ContestRulesDTO> getAllCall = iApiCall.getRules();

        getAllCall.enqueue(new Callback<ContestRulesDTO>() {
            @Override
            public void onResponse(Call<ContestRulesDTO> call, Response<ContestRulesDTO> response) {

                rules = response.body();
                Toast.makeText(PublishActivity.this, "success", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ContestRulesDTO> call, Throwable t) {

                Toast.makeText(PublishActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        int qcount=0;
        int mcount=0;
        int hcount=0;
        int ecount=0;
        int img=0;
        int audio=0;
        int video=0;
        int text=0;

        for(QuestionDTO question:questionDTOList){
            qcount++;
            if(question.getDifficulty().equals("easy")){
                ecount++;
            }
            if(question.getDifficulty().equals("medium")){
                mcount++;
            }
            if(question.getDifficulty().equals("hard")){
                hcount++;
            }
            if(question.getQuestionType().equals("text")){
                text++;
            }
            if(question.getQuestionType().equals("audio")){
                audio++;
            }
            if(question.getQuestionType().equals("video")){
                video++;
            }
            if(question.getQuestionType().equals("image")){
                img++;
            }
        }

        Boolean easyFlag = ecount == rules.getNumEasyQ();
        Boolean mediumFlag = mcount == rules.getNumMediumQ();
        Boolean hardFlag = hcount == rules.getNumHardQ();
        Boolean totalFlag = qcount == rules.getNumQuestions();
        Boolean audioFlag = audio == rules.getNumAudioQ();
        Boolean videoFlag = video == rules.getNumVideoQ();
        Boolean imageFlag = img == rules.getNumImageQ();
        Boolean textFlag =text == rules.getNumTextQ();

        if(easyFlag && mediumFlag && hardFlag && totalFlag && audioFlag && videoFlag && imageFlag && textFlag){
            return true;
        }
        return false;

    }
}
