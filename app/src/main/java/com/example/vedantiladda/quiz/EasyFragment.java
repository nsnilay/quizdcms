package com.example.vedantiladda.quiz;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class EasyFragment extends Fragment implements PaginationAdapter.Communicator {

    List<QuestionDTO> selectedQuestionDTOS = new ArrayList<>();
    PaginationAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    RecyclerView rv;
    private List<QuestionDTO> questionDTOList = new ArrayList<>();
    OkHttpClient client = new OkHttpClient.Builder().build();
    final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.177.2.15:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build();
    int i=2;

    public interface EasyQuestions {
        void onEasyDataPass(List<QuestionDTO> selectedQuestionDTOS);
    }

    EasyQuestions easyQuestions;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        easyQuestions = (EasyQuestions) context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Return the layout file after inflating
        View view =  inflater.inflate(R.layout.easy_tab, container, false);
        rv = view.findViewById(R.id.easyRecycler);

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

        for(QuestionDTO questionDTO : questionDTOList) {
            if(questionDTO.getQuestionId() == id)
            selectedQuestionDTOS.add(questionDTO);
        }

        easyQuestions.onEasyDataPass(selectedQuestionDTOS);

    }

    private void loadFirstPage() {
//        Log.d("TAG", "loadFirstPage: ");
//        QuestionDTO questionDTO1 = new QuestionDTO();
//        QuestionDTO questionDTO2 = new QuestionDTO();
//        QuestionDTO questionDTO3 = new QuestionDTO();
//        QuestionDTO questionDTO4 = new QuestionDTO();
//        QuestionDTO questionDTO5 = new QuestionDTO();
//        QuestionDTO questionDTO6 = new QuestionDTO();
//        QuestionDTO questionDTO7 = new QuestionDTO();
//        QuestionDTO questionDTO8 = new QuestionDTO();
//        QuestionDTO questionDTO9 = new QuestionDTO();
//        QuestionDTO questionDTO10 = new QuestionDTO();
//
//        questionDTO1.setQuestionContent("first");
//        questionDTO1.setQuestionType("first");
//        questionDTO2.setQuestionContent("second");
//        questionDTO2.setQuestionType("second");
//        questionDTO3.setQuestionContent("third");
//        questionDTO3.setQuestionType("third");
//        questionDTO4.setQuestionContent("fourth");
//        questionDTO4.setQuestionType("fourth");
//        questionDTO5.setQuestionContent("fifth");
//        questionDTO5.setQuestionType("fifth");
//        questionDTO6.setQuestionContent("sixth");
//        questionDTO6.setQuestionType("sixth");
//        questionDTO7.setQuestionContent("seventh");
//        questionDTO7.setQuestionType("seventh");
//        questionDTO8.setQuestionContent("eighth");
//        questionDTO8.setQuestionType("eighth");
//        questionDTO9.setQuestionContent("ninth");
//        questionDTO9.setQuestionType("ninth");
//        questionDTO10.setQuestionContent("tenth");
//        questionDTO10.setQuestionType("tenth");
//        questionDTO1.setQuestionId("1");
//        questionDTO2.setQuestionId("2");
//        questionDTO3.setQuestionId("3");
//        questionDTO4.setQuestionId("4");
//        questionDTO5.setQuestionId("5");
//        questionDTO6.setQuestionId("6");
//        questionDTO7.setQuestionId("7");
//        questionDTO8.setQuestionId("8");
//        questionDTO9.setQuestionId("9");
//        questionDTO10.setQuestionId("10");
//        questionDTOList.add(questionDTO1);
//        questionDTOList.add(questionDTO2);
//        questionDTOList.add(questionDTO3);
//        questionDTOList.add(questionDTO4);
//        questionDTOList.add(questionDTO5);
//        questionDTOList.add(questionDTO6);
//        questionDTOList.add(questionDTO7);
//        questionDTOList.add(questionDTO8);
//        questionDTOList.add(questionDTO9);
//        questionDTOList.add(questionDTO10);
//        questionDTOList.add(questionDTO1);
//        questionDTOList.add(questionDTO2);
//        questionDTOList.add(questionDTO3);
//        questionDTOList.add(questionDTO4);
//        adapter.notifyDataSetChanged();

        IApiCall iApiCall = retrofit.create(IApiCall.class);
        final Call<List<QuestionDTO>> getAllCall = iApiCall.getAllQuestions("easy", 1);

        getAllCall.enqueue(new Callback<List<QuestionDTO>>() {
            @Override
            public void onResponse(Call<List<QuestionDTO>> call, Response<List<QuestionDTO>> response) {
                // Got data. Send it to adapter



                questionDTOList.addAll(response.body());
                Log.e("EasyFragment", questionDTOList.get(0).toString());
                adapter.notifyDataSetChanged();
                Toast.makeText(getActivity(), "success", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<QuestionDTO>> call, Throwable t) {
                Log.e("EasyFragment", call.request().url().toString());

                Log.e("EasyFragment", t.getMessage());
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }



    private void loadNextPage() {
//        Log.d("TAG","loadNextPage: " + currentPage);
//
//        QuestionDTO questionDTO1 = new QuestionDTO();
//        QuestionDTO questionDTO2 = new QuestionDTO();
//        QuestionDTO questionDTO3 = new QuestionDTO();
//        QuestionDTO questionDTO4 = new QuestionDTO();
//        QuestionDTO questionDTO5 = new QuestionDTO();
//
//        questionDTO1.setQuestionContent("sixth");
//        questionDTO1.setQuestionType("sixth");
//        questionDTO2.setQuestionContent("seventh");
//        questionDTO2.setQuestionType("seventh");
//        questionDTO3.setQuestionContent("eighth");
//        questionDTO3.setQuestionType("eighth");
//        questionDTO4.setQuestionContent("ninth");
//        questionDTO4.setQuestionType("ninth");
//        questionDTO5.setQuestionContent("tenth");
//        questionDTO5.setQuestionType("tenth");
//        questionDTO1.setQuestionId("1");
//        questionDTO2.setQuestionId("2");
//        questionDTO3.setQuestionId("3");
//        questionDTO4.setQuestionId("4");
//        questionDTO5.setQuestionId("5");
//        questionDTOList.add(questionDTO1);
//        questionDTOList.add(questionDTO2);
//        questionDTOList.add(questionDTO3);
//        questionDTOList.add(questionDTO4);
//        questionDTOList.add(questionDTO5);
//        adapter.notifyDataSetChanged();


        IApiCall iApiCall = retrofit.create(IApiCall.class);
        final Call<List<QuestionDTO>> getAllCall = iApiCall.getAllQuestions("easy", i);

        getAllCall.enqueue(new Callback<List<QuestionDTO>>() {
            @Override
            public void onResponse(Call<List<QuestionDTO>> call, Response<List<QuestionDTO>> response) {
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
