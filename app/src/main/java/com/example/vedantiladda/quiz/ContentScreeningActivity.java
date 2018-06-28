package com.example.vedantiladda.quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.vedantiladda.quiz.dto.QuestionDTO;
import com.example.vedantiladda.quiz.utils.EndlessRecyclerViewScrollListener;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContentScreeningActivity extends AppCompatActivity implements ContentAdapter.Communicator{

    List<QuestionDTO> questionList= new ArrayList<>();
    List<QuestionDTO> selected = new ArrayList<>();
    ContentAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    RecyclerView rv;
    OkHttpClient client = new OkHttpClient.Builder().build();
    final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.177.2.15:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build();
    int i = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_screening);
        rv = findViewById(R.id.contentRecycler);
        adapter = new ContentAdapter(questionList, this);
        linearLayoutManager = new LinearLayoutManager(ContentScreeningActivity.this);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(adapter);
        loadFirstPage();
        rv.addOnScrollListener(new EndlessRecyclerViewScrollListener() {
            @Override
            public void onLoadMore() {
                loadNextPage();
            }
        });

        Button submit = findViewById(R.id.send);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveAll();
            }
        });


    }

    public void saveAll(){
        IApiCall iApiCall = retrofit.create(IApiCall.class);
        final Call<Boolean> saveAllCall = iApiCall.saveAll(selected);


        saveAllCall.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {

                Toast.makeText(ContentScreeningActivity.this, "success", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

                Toast.makeText(ContentScreeningActivity.this, "failure", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void loadFirstPage(){
        IApiCall iApiCall = retrofit.create(IApiCall.class);
        final Call<List<QuestionDTO>> getAllCall = iApiCall.getAllPending("pending", 1);

        getAllCall.enqueue(new Callback<List<QuestionDTO>>() {
            @Override
            public void onResponse(Call<List<QuestionDTO>> call, Response<List<QuestionDTO>> response) {
                // Got data. Send it to adapter



                questionList.addAll(response.body());
                Log.e("ContentScreening", questionList.toString());
                adapter.notifyDataSetChanged();

                Toast.makeText(ContentScreeningActivity.this, "success", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<QuestionDTO>> call, Throwable t) {
                Log.e("ContentScreening", call.request().url().toString());

                Log.e("ContentScreening", t.getMessage());
                Toast.makeText(ContentScreeningActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    public void loadNextPage(){
        IApiCall iApiCall = retrofit.create(IApiCall.class);
        final Call<List<QuestionDTO>> getAllCall = iApiCall.getAllPending("pending", i);

        getAllCall.enqueue(new Callback<List<QuestionDTO>>() {
            @Override
            public void onResponse(Call<List<QuestionDTO>> call, Response<List<QuestionDTO>> response) {
                // Got data. Send it to adapter



                questionList.addAll(response.body());
                Log.e("ContentScreening", questionList.get(0).toString());
                adapter.notifyDataSetChanged();
                i++;
                Toast.makeText(ContentScreeningActivity.this, "success", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<QuestionDTO>> call, Throwable t) {
                Log.e("ContentScreening", call.request().url().toString());

                Log.e("ContentScreening", t.getMessage());
                Toast.makeText(ContentScreeningActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onSetSwitch(String id) {
        for(QuestionDTO question: questionList){
            if(question.getQuestionId() == id){
                selected.add(question);
            }
        }

    }

    @Override
    public void onReleaseSwitch(String id) {
        for(QuestionDTO question: questionList){
            if(question.getQuestionId() == id){
                selected.remove(question);
            }
        }
    }
}
