package com.example.vedantiladda.quiz;


import com.example.vedantiladda.quiz.dto.QuestionDTO;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IApiCall {
    @GET("question/getByDifficulty/{difficulty}/{pageNumber}")
    Call<List<QuestionDTO>>getAllQuestions(@Path("difficulty") String difficulty, @Path("pageNumber") Integer pageNumber);

    @GET("question/getAll")
    Call<List<QuestionDTO>>getAll();

    @GET("question/getAllByStatus/{status}/{pageNumber}")
    Call<List<QuestionDTO>>getAllPending(@Path("status") String status, @Path("pageNumber") Integer pageNumber);

    @POST("question/saveAll/{List<QuestionDTO>}")
    Call<Boolean>saveAll(@Body List<QuestionDTO> questionList);
}
