package com.example.vedantiladda.quiz.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.vedantiladda.quiz.Navigation_Activity;
import com.example.vedantiladda.quiz.R;
import com.example.vedantiladda.quiz.dto.UserDTO;
import com.example.vedantiladda.quiz.dto.UserLogin;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private String url = "http://10.177.2.200:8080/";
    private String username;

    public boolean isValidEmailAddress2(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        final EditText editText = findViewById(R.id.editText);
        final EditText editText9 = findViewById(R.id.editText9);




        OkHttpClient client = new OkHttpClient.Builder().build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        final     SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        if(sharedPreferences.contains("userName")){
            Intent intent = new Intent(this,Navigation_Activity.class);
            startActivity(intent);
        }

        Button login_button = findViewById(R.id.login_button);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String editTextValue = editText.getText().toString();
                String editTextValue9 = editText9.getText().toString();


                if(!isValidEmailAddress2(editText.getText().toString().trim())){
                    editText.setError("Enter a valid email id!");
                    return;
                }

                if (editText9.getText().toString().isEmpty()) {
                    editText9.setError("Required field!");
                    return;
                }
                final UserLogin userLogin = new UserLogin();
                final UserDTO userDTO = new UserDTO();


                userLogin.setUserEmail(editText.getText().toString());
                userLogin.setPassword(editText9.getText().toString());

                final PostAll login = retrofit.create(PostAll.class);
                Call<String> loginCall = login.loginDetails(userLogin);

                loginCall.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {

                        if(response.body() == null){
                            Toast.makeText(getApplicationContext(), "please enter valid credentials!", Toast.LENGTH_SHORT).show();

                        }
                        else {
                            username = response.body();
                            SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("userName", username);
                            editor.putString("Email",editTextValue );
                            editor.apply();

                            Toast.makeText(getApplicationContext(), "successfully logged in!", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(LoginActivity.this, Navigation_Activity.class);
                            startActivity(i);
                            finish();
                        }

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.d("hey",call.request().body().toString());

                        Log.d("hey",t.getMessage());
                        System.out.println(t);
                        Toast.makeText(getApplicationContext(), "login failed!", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });
    }
}

