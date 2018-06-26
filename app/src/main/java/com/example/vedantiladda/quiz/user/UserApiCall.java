package com.example.vedantiladda.quiz.user;

import com.example.vedantiladda.quiz.dto.CategoryDTO;
import com.example.vedantiladda.quiz.dto.ContestDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserApiCall {

    @GET("categoryDTO/getAll")
    Call<List<CategoryDTO>> getCategoryForContest();

    @GET("getContestsByCategory/{categoryId}")
    Call<List<ContestDTO>> getContestsByCategory(@Path("categoryId")String categoryId);

    @GET("getContestQuestions/{contestId}")
    Call<ContestDTO> getQuestionsByContest(@Path("contestId")String contestId);
}
