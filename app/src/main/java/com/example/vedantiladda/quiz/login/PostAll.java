package com.example.vedantiladda.quiz.login;

//import com.example.vedantiladda.ecommerce.model.UserEntity;
import com.example.vedantiladda.quiz.dto.UserDTO;
import com.example.vedantiladda.quiz.dto.UserLogin;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PostAll {


    @POST("user/login")
    Call<List<String>> loginDetails(@Body UserLogin userLogin);




}
