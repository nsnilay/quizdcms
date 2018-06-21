package com.example.vedantiladda.quiz.user;

import com.example.vedantiladda.quiz.dto.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserApiCall {

    @GET("category/getAll")
    Call<List<Category>> getCategoryForContest();
}
