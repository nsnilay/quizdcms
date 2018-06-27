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
    private List<QuestionDTO> copy = new ArrayList<>();
    private Boolean valid = false;
    OkHttpClient client = new OkHttpClient.Builder().build();
    final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.177.1.85:8090/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build();
    ContestRulesDTO rules = new ContestRulesDTO();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        mRecyclerView = findViewById(R.id.publishRecycler);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        Intent publish = getIntent();
        questionDTOList = (List<QuestionDTO>) publish.getSerializableExtra("questions");
        copy = (List<QuestionDTO>) publish.getSerializableExtra("questions");
//        int n = questionDTOList.size();
//        int i;
//        for(i=0;i<n-1;i++){
//            int j = i+1;
//            for(;j<n;j++){
//                if(questionDTOList.get(i).getQuestionId() == questionDTOList.get(j).getQuestionId())
//                    copy.remove(i);
//            }
//        }
        Log.d("check", questionDTOList.toString());
        mAdapter = new PublishAdapter(copy);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        IApiCall iApiCall = retrofit.create(IApiCall.class);
        final Call<ContestRulesDTO> getAllCall = iApiCall.getRules();

        getAllCall.enqueue(new Callback<ContestRulesDTO>() {
            @Override
            public void onResponse(Call<ContestRulesDTO> call, Response<ContestRulesDTO> response) {
                Log.d("PUBLISH", call.request().url().toString());
                rules = response.body();
                valid = checkRules(copy);
                Toast.makeText(PublishActivity.this, "success", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ContestRulesDTO> call, Throwable t) {

                Toast.makeText(PublishActivity.this,"failure", Toast.LENGTH_LONG).show();

            }
        });
        Button publishButton = findViewById(R.id.publishButton);
        publishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(valid){
                    Toast.makeText(PublishActivity.this, "yay!", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(PublishActivity.this, "invalid contest", Toast.LENGTH_LONG).show();
                    //Intent i = new Intent(PublishActivity.this, QuestionBankActivity.class);
                    //startActivity(i);
                }
            }
        });


    }

    public Boolean checkRules(List<QuestionDTO> questionList){



        int qcount=0;
        int mcount=0;
        int hcount=0;
        int ecount=0;
        int img=0;
        int audio=0;
        int video=0;
        int text=0;

        for(QuestionDTO question:questionList){
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
        Log.i("Easy", rules.toString());
        Boolean easyFlag = false;
        Boolean mediumFlag = false;
        Boolean hardFlag = false;
        Boolean textFlag =false;
        Boolean imageFlag =false;
        Boolean videoFlag =false;
        Boolean audioFlag =false;
        Boolean totalFlag =false;
        if(ecount == rules.getNumEasyQ())
            easyFlag  = true ;
        if(mcount == rules.getNumMediumQ())
            mediumFlag =true;
        if(hcount == rules.getNumHardQ())
            hardFlag = true;

        if(qcount == rules.getNumQuestions())
            totalFlag =true;

        if(audio == rules.getNumAudioQ())
            audioFlag = true;

        if(video == rules.getNumVideoQ())
            videoFlag = true;

        if(img == rules.getNumImageQ())
            imageFlag = true ;

        if(text == rules.getNumTextQ())
            textFlag =true;

        if(easyFlag && mediumFlag && hardFlag && totalFlag && audioFlag && videoFlag && imageFlag && textFlag){
            return true;
        }
        return false;

    }
}
