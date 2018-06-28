package com.example.vedantiladda.quiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.vedantiladda.quiz.IApiCall;
import com.example.vedantiladda.quiz.PaginationAdapter;
import com.example.vedantiladda.quiz.R;
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

public class HardFragment extends Fragment implements PaginationAdapter.Communicator {

    List<QuestionDTO> selectedQuestionDTOS = new ArrayList<>();
    PaginationAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    RecyclerView rv;
    private EndlessRecyclerViewScrollListener scrollListener;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private List<QuestionDTO> questionDTOList = new ArrayList<>();
        OkHttpClient client = new OkHttpClient.Builder().build();
    final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.177.2.15:8080/ ")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build();
    int i=2;
    String category;
    String contestType;
    public interface HardQuestions {
        void onHardDataPass(List<QuestionDTO> selectedQuestionDTOS);
    }

    HardQuestions hardQuestions;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        hardQuestions = (HardQuestions) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        selectedQuestionDTOS.clear();
        Intent contest = getActivity().getIntent();
        contestType = contest.getStringExtra("ContestType");
        category = contest.getStringExtra("Contest_CategoryId");
        View view =  inflater.inflate(R.layout.hard_tab, container, false);
        rv = view.findViewById(R.id.hardRecycler);
        adapter = new PaginationAdapter(questionDTOList, this);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(adapter);
        loadFirstPage();
        rv.addOnScrollListener(new EndlessRecyclerViewScrollListener() {
            @Override
            public void onLoadMore() {
                loadNextPage();
            }
        });


        return view;

    }
    @Override
    public void onClickCheckBox(String id) {
        Boolean flag = true;
        for(QuestionDTO question: selectedQuestionDTOS){
            if(question.getQuestionId().equals(id)){
                flag = false;
            }
        }

        for(QuestionDTO questionDTO : questionDTOList) {
            if(questionDTO.getQuestionId().equals(id) && questionDTO.getChecked()&&flag)
                selectedQuestionDTOS.add(questionDTO);
            if(questionDTO.getQuestionId().equals(id) && !questionDTO.getChecked()) {
                selectedQuestionDTOS.remove(questionDTO);
                Log.d("REMOVE", selectedQuestionDTOS.toString());
            }

        }

        hardQuestions.onHardDataPass(selectedQuestionDTOS);
    }

    private void loadFirstPage() {


        IApiCall iApiCall = retrofit.create(IApiCall.class);
        Call<List<QuestionDTO>> getAllCall = iApiCall.getAllQuestions("hard", 1);
        if(contestType.equals("static")){
            getAllCall = iApiCall.getAllStatic("hard", category, 1);
        }

        getAllCall.enqueue(new Callback<List<QuestionDTO>>() {
            @Override
            public void onResponse(Call<List<QuestionDTO>> call, Response<List<QuestionDTO>> response) {


                questionDTOList.addAll(response.body());
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<QuestionDTO>> call, Throwable t) {
                Toast.makeText(getActivity(), "failed", Toast.LENGTH_LONG).show();
            }
        });
    }



    private void loadNextPage() {


        IApiCall iApiCall = retrofit.create(IApiCall.class);
        Call<List<QuestionDTO>> getAllCall = iApiCall.getAllQuestions("hard", i);
        if(contestType.equals("static")){
            getAllCall = iApiCall.getAllStatic("hard", category, i);
        }

        getAllCall.enqueue(new Callback<List<QuestionDTO>>() {
            @Override
            public void onResponse(Call<List<QuestionDTO>> call, Response<List<QuestionDTO>> response) {

                isLoading = false;

                questionDTOList.addAll(response.body());
                adapter.notifyDataSetChanged();
                i++;

            }

            @Override
            public void onFailure(Call<List<QuestionDTO>> call, Throwable t) {
                Toast.makeText(getActivity(), "failed", Toast.LENGTH_LONG).show();

            }
        });
    }

}
