package com.example.vedantiladda.quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.vedantiladda.quiz.dto.ContestDTO;
//import com.example.vedantiladda.quiz.model.StaticContest;
import com.example.vedantiladda.quiz.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LeaderboardActivity extends AppCompatActivity implements LeaderboardAdapter.LeaderboardCommunicator,AdapterView.OnItemSelectedListener {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String url = "http://10.177.2.201:8080";
    private List<UserDTO> userDTOS = new ArrayList<>();
    private List<ContestDTO> contestDTOS = new ArrayList<>();
    private List<String> staticContestList = new ArrayList<>();
    String contest;
    OkHttpClient client = new OkHttpClient.Builder().build();
    final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        Spinner spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);

//        ContestDTO staticContest1 = new ContestDTO();
//        staticContest1.setContestName("ha ha ha ha");
//        ContestDTO staticContest2 = new ContestDTO();
//        staticContest2.setContestName("ha ha ha ha ha");
//        contestDTOS.add(staticContest1);
//        contestDTOS.add(staticContest2);

        GetAllContest();

//        for(ContestDTO contestDTO:contestDTOS)
//        {
//            staticContestList.add(contestDTO.getContestName());
//        }
        //staticContestList.add(contestDTOS.get(0).getContestName());
        Log.d("STATIC", staticContestList.toString());
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,staticContestList);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin.setAdapter(aa);

        //contest = getIntent().getExtras().getString("contestName");


        mRecyclerView = (RecyclerView) findViewById(R.id.leaderboardRecycler);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        mAdapter = new LeaderboardAdapter(userDTOS, LeaderboardActivity.this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

//        UserDTO userDTO1 = new UserDTO();
//        UserDTO userDTO2 = new UserDTO();
//        userDTO1.setUserName("username1");
//        userDTO2.setUserName("username2");
//        userDTOS.add(userDTO1);
//        userDTOS.add(userDTO2);





        final ImageView Back = (ImageView) findViewById(R.id.back);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LeaderboardActivity.this,Navigation_Activity.class);
                startActivity(i);
                finish();

            }
        });


    }

    @Override
    public void onClickTextView(String id) {

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getApplicationContext(),contestDTOS.get(i).getContestName() , Toast.LENGTH_LONG).show();

        GetLeaderboard(contestDTOS.get(i).getContestName());
        /* IApiCall iApiCall = retrofit.create(IApiCall.class);
        final Call<List<UserDTO>> getAllCall = iApiCall.getAllOverall(contestName);
        getAllCall.enqueue(new Callback<List<UserDTO>>() {
            @Override
            public void onResponse(Call<List<UserDTO>> call, Response<List<UserDTO>> response) {
                userDTOS.clear();
                userDTOS.addAll(response.body());
                mAdapter.notifyDataSetChanged();
                Toast.makeText(LeaderboardActivity.this, "received", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<UserDTO>> call, Throwable t) {
                Toast.makeText(LeaderboardActivity.this, "failed", Toast.LENGTH_LONG).show();

            }

        });*/






    }

    public void GetLeaderboard(String contestName){
        /*if(contestName == contestDTOS.get(0).getContestName()) {
            userDTOS.clear();
            UserDTO userDTO1 = new UserDTO();
            UserDTO userDTO2 = new UserDTO();
            UserDTO userDTO5 = new UserDTO();
            UserDTO userDTO6 = new UserDTO();
            userDTO1.setUserName("username1");
            userDTO2.setUserName("username2");
            userDTO5.setUserName("username5");
            userDTO6.setUserName("username6");
            userDTOS.add(userDTO1);
            userDTOS.add(userDTO2);
            userDTOS.add(userDTO5);
            userDTOS.add(userDTO6);
            mAdapter.notifyDataSetChanged();
        }

        else if(contestName == contestDTOS.get(1).getContestName()) {

            userDTOS.clear();
            UserDTO userDTO3 = new UserDTO();
            UserDTO userDTO4 = new UserDTO();
            userDTO3.setUserName("username3");
            userDTO4.setUserName("username4");
            userDTOS.add(userDTO3);
            userDTOS.add(userDTO4);
            mAdapter.notifyDataSetChanged();
        }*/

        /* IApiCall iApiCall = retrofit.create(IApiCall.class);
        final Call<List<UserDTO>> getAllCall = iApiCall.getAllOverall(contestName);
        getAllCall.enqueue(new Callback<List<UserDTO>>() {
            @Override
            public void onResponse(Call<List<UserDTO>> call, Response<List<UserDTO>> response) {
                userDTOS.clear();
                userDTOS.addAll(response.body());
                mAdapter.notifyDataSetChanged();
                Toast.makeText(LeaderboardActivity.this, "received", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<UserDTO>> call, Throwable t) {
                Toast.makeText(LeaderboardActivity.this, "failed", Toast.LENGTH_LONG).show();

            }

        });*/
    }

    public void GetAllContest(){
        final IApiCall iApiCall = retrofit.create(IApiCall.class);
        final Call<List<ContestDTO>> getAllCall = iApiCall.getAllOverall();
        getAllCall.enqueue(new Callback<List<ContestDTO>>() {
            @Override
            public void onResponse(Call<List<ContestDTO>> call, Response<List<ContestDTO>> response) {
                contestDTOS.clear();
                contestDTOS.addAll(response.body());
                for(ContestDTO contestDTO:contestDTOS)
                {
                    staticContestList.add(contestDTO.getContestName());
                }

                Toast.makeText(LeaderboardActivity.this, "received", Toast.LENGTH_LONG).show();


            }

            @Override
            public void onFailure(Call<List<ContestDTO>> call, Throwable t) {
                Log.d("API",t.getMessage());
                Toast.makeText(LeaderboardActivity.this, "failed", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
