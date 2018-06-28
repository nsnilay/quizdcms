package com.example.vedantiladda.quiz.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vedantiladda.quiz.R;
import com.example.vedantiladda.quiz.adapter.RVContestsAdapter;
import com.example.vedantiladda.quiz.dto.CategoryDTO;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class UserContests extends Fragment implements RVContestsAdapter.CategoryAdapterCommunicator {

    private static final String TAG = "USERCATEGORY";

    private Retrofit retrofit;
    private OkHttpClient client;
    private RecyclerView recyclerView;
    private RVContestsAdapter rvContestsAdapter;

    private List<CategoryDTO> categories = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        View view = inflater.inflate(R.layout.fragment_user_contests, container, false);
        recyclerView = view.findViewById(R.id.rv_category);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        rvContestsAdapter = new RVContestsAdapter(categories, this);
        recyclerView.setAdapter(rvContestsAdapter);

        getAllCategory();

        // Hardcoded categories
//        CategoryDTO categoryDTO = new CategoryDTO();
//        categoryDTO.setCategoryId("1234");
//        categoryDTO.setCategoryName("Rohit");
//        categories.add(categoryDTO);
//
//        CategoryDTO category1 = new CategoryDTO();
//        category1.setCategoryName("Nilay");
//        category1.setCategoryId("2121");
//        categories.add(category1);
//
//        rvContestsAdapter.notifyDataSetChanged();


        return view;

    }

    @Override
    public void itemClick(CategoryDTO categoryDTO) {
        Toast.makeText(getActivity(),"YO",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), ContestNames.class);
        intent.putExtra("categoryDTO", categoryDTO);
        startActivity(intent);
    }

    void getAllCategory(){

        client = new OkHttpClient.Builder().build();

        retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.base_url_category))
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        UserApiCall userApiCall = retrofit.create(UserApiCall.class);
        Call<List<CategoryDTO>> getAllCategories = userApiCall.getCategoryForContest();
        getAllCategories.enqueue(new Callback<List<CategoryDTO>>() {
            @Override
            public void onResponse(Call<List<CategoryDTO>> call, Response<List<CategoryDTO>> response) {
                categories.clear();
                categories.addAll(response.body());
                rvContestsAdapter.notifyDataSetChanged();
                Toast.makeText(getActivity(),"Retrieved categories Successfully" , Toast.LENGTH_SHORT).show();
                Log.i(TAG, "API CALL SUCCESS");


            }

            @Override
            public void onFailure(Call<List<CategoryDTO>> call, Throwable t) {
                Toast.makeText(getActivity(),"API call failed" , Toast.LENGTH_SHORT).show();
                Log.i(TAG, "API CALL FAILED");


            }
        });

    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(getString(R.string.title_user_contests));
    }


}
