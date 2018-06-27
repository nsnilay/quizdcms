package com.example.vedantiladda.quiz;

import com.example.vedantiladda.quiz.dto.Category;
import com.example.vedantiladda.quiz.dto.Contest;
import com.example.vedantiladda.quiz.dto.ContestRulesDTO;
import com.example.vedantiladda.quiz.dto.QuestionDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IApiCall {
    @POST("createContest")
    Call<Boolean> addContest(@Body Contest contest);

    @GET("category/getAll")
    Call<List<Category>> getCategories();
    @GET("question/getByDifficulty/{difficulty}/{pageNumber}")
    Call<List<QuestionDTO>>getAllQuestions(@Path("difficulty") String difficulty, @Path("pageNumber") Integer pageNumber);

    @GET("question/getAll")
    Call<List<QuestionDTO>>getAll();

    @GET("question/getAllByStatus/{status}/{pageNumber}")
    Call<List<QuestionDTO>>getAllPending(@Path("status") String status, @Path("pageNumber") Integer pageNumber);

    @POST("question/saveAll/{List<QuestionDTO>}")
    Call<Boolean>saveAll(@Body List<QuestionDTO> questionList);

    @GET("question/getByCategoryIdAndDifficulty/{categoryId}/{difficulty}/{pageNumber}")
    Call<List<QuestionDTO>>getAllStatic(@Path("difficulty") String difficulty,@Path("categoryId") String categoryId,
                                        @Path("pageNumber") Integer pageNumber);

    @GET("contest/getRules")
    Call<ContestRulesDTO>getRules();
}
