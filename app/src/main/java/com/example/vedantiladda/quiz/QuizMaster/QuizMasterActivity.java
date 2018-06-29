package com.example.vedantiladda.quiz.QuizMaster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.vedantiladda.quiz.QuizMaster.QuizMasterPojo.ContestDTO;
import com.example.vedantiladda.quiz.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class QuizMasterActivity extends AppCompatActivity implements QuizMaster_recycler_view_adapter.IPostsAdapterCommunicatorDynamicContests {
    private RecyclerView qmRecyclerView;
    private QuizMaster_recycler_view_adapter qmadapter;
    private Retrofit qmretrofit;
    private OkHttpClient qmclient;
    private List<ContestDTO> contestDTOList = new ArrayList<>();
    // private String dynamic;
    // private String static;
    // private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_master);
        // FirebaseApp.initializeApp(getApplicationContext());
        FirebaseApp.initializeApp(this);


        qmclient = new OkHttpClient.Builder().build();
        qmretrofit = new Retrofit.Builder().baseUrl("http://10.177.2.201:8080/contest/")
                .addConverterFactory(GsonConverterFactory.create()).client(qmclient).build();
        qmRecyclerView = (RecyclerView) findViewById(R.id.dynamic_contests_recyclerView_id);
        qmadapter = new QuizMaster_recycler_view_adapter(contestDTOList, getApplication(), QuizMasterActivity.this);
        qmRecyclerView.setLayoutManager(new LinearLayoutManager(QuizMasterActivity.this));
        qmRecyclerView.setAdapter(qmadapter);


        IApiCall iApiCall = qmretrofit.create(IApiCall.class);
        Call<List<ContestDTO>> getAllCall = iApiCall.getDynamicContest();
        getAllCall.enqueue(new Callback<List<ContestDTO>>() {
            @Override
            public void onResponse(Call<List<ContestDTO>> call, Response<List<ContestDTO>> response) {
                try {
                    //contestDTOList.clear();
                    contestDTOList.addAll(response.body());
                    qmadapter.notifyDataSetChanged();
                } catch (Exception e) {
                    Log.e("API", e.getMessage().toString());
                }
                Toast.makeText(QuizMasterActivity.this, "Got list of Contests Successfully", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<ContestDTO>> call, Throwable t) {
                Log.i("API", t.getMessage().toString());
                //Toast.makeText(QuizMasterActivity.this, "On Failure 1" , Toast.LENGTH_LONG).show();
            }
        });

    }


    @Override
    public void itemClick(String contestId) {
        //TODO: Change Topic to quizMaster
        FirebaseMessaging.getInstance().subscribeToTopic("quizMaster");
        Intent intent = new Intent(this, QuizMaterContestActivity.class);
        intent.putExtra("contestId", contestId);
        startActivity(intent);
    }
}
