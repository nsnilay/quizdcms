package com.example.vedantiladda.quiz;

import com.example.vedantiladda.quiz.dto.Category;
import com.example.vedantiladda.quiz.dto.Contest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IApiCall {
    @POST("createContest")
    Call<Boolean> addContest(@Body Contest contest);

    @GET("category/getAll")
    Call<List<Category>> getCategories();
}
