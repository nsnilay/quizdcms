package com.example.vedantiladda.quiz;

import com.example.vedantiladda.quiz.dto.ContestDTO;
//import com.example.vedantiladda.quiz.model.StaticContest;
import com.example.vedantiladda.quiz.dto.ContestwiseDTO;
import com.example.vedantiladda.quiz.dto.OverallDTO;
import com.example.vedantiladda.quiz.dto.UserDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IApiCall {
    @GET("/contest/getContestsByType/{contestType}")
    Call<List<ContestDTO>> getAllStaticContests(@Path("contestType") String contestType);

    @GET("/contest/getAll")
    Call<List<ContestDTO>> getAllOverall();

    @GET("/contest/getContestsByType/{contestType}")
    Call<List<ContestDTO>> getAllDynamicContests(@Path("contestType") String contestType);

    @GET("/contest/getOverAllLeaderBoard")
    Call<List<OverallDTO>> getOverAll();

    @GET("/contest/getContestWiseLeaderBoard/{contestId}")
    Call<List<ContestwiseDTO>> getContestwisePoints(@Path("contestId") String contestId);
}
