package com.example.vedantiladda.quiz.login;

//import com.example.vedantiladda.ecommerce.model.UserEntity;
import com.example.vedantiladda.quiz.dto.UserDTO;
import com.example.vedantiladda.quiz.dto.UserLogin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PostAll {


    @POST("user/login")
    Call<String> loginDetails(@Body UserLogin userLogin);




}
