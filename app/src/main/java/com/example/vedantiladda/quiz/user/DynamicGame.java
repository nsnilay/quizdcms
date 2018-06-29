package com.example.vedantiladda.quiz.user;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.vedantiladda.quiz.R;
import com.example.vedantiladda.quiz.dto.CategoryDTO;
import com.example.vedantiladda.quiz.dto.ContestDTO;
import com.example.vedantiladda.quiz.dto.ContestQuestionDTO;
import com.example.vedantiladda.quiz.dto.FCMQuestion;
import com.example.vedantiladda.quiz.dto.QuestionDTO;
import com.example.vedantiladda.quiz.dto.UserAnswerDTO;

import java.io.IOException;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DynamicGame extends AppCompatActivity implements View.OnClickListener {

    final String TAG = "FCMGAME";

    private ImageView imageView;
    private VideoView videoView;
    private TextView questionText, pleaseWait;
    private CheckBox optionOne, optionTwo, optionThree, optionFour;
    private Button submitButton;
    private LinearLayout linearLayout;

    private Retrofit retrofit;
    private OkHttpClient client;

    private FCMQuestion fcmQuestion;
    private ContestDTO contestDTO;

    private String message;
    private String answerType;


    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            fcmQuestion =(FCMQuestion) intent.getSerializableExtra("message");
            Log.d("receiver", "Got message: " + fcmQuestion.toString());
            if(fcmQuestion.getStatus().equals("end")){
                Toast.makeText(DynamicGame.this,"Game Over",Toast.LENGTH_SHORT).show();
            }else {
                displayQuestion(fcmQuestion);
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_game);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setAllViews();

        submitButton.setOnClickListener(this);

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("custom-event-name"));

        contestDTO = (ContestDTO)getIntent().getSerializableExtra("contestDTO");

        // TODO ;
        client = new OkHttpClient.Builder().build();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.177.1.245:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        submitButton.setVisibility(View.INVISIBLE);




    }

    private void showQuestionViews(){
        questionText.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.VISIBLE);
        submitButton.setVisibility(View.VISIBLE);
        pleaseWait.setVisibility(View.INVISIBLE);
    }

    private void displayQuestion(FCMQuestion fcmQuestion){

        if(fcmQuestion == null){
            //      Log.d(TAG, "NO MORE QUESTIONS"  + contestQuestionDTO.getQuestionDTO().toString());
            Toast.makeText(DynamicGame.this,"No more questions", Toast.LENGTH_SHORT).show();
            linearLayout.setVisibility(View.GONE);
//            skipQuestionFab.setVisibility(View.INVISIBLE);
            return;
        }
        Log.d(TAG, fcmQuestion.toString());

        switch (fcmQuestion.getQuestionType()){
            case "text":
                showQuestionViews();
                answerType = fcmQuestion.getAnswerType();
                questionText.setText(fcmQuestion.getQuestionText());
                optionOne.setText("A: " + fcmQuestion.getOptionOne());
                optionTwo.setText("B: " + fcmQuestion.getOptionTwo());
                optionThree.setText("C: " +   fcmQuestion.getOptionThree());
                optionFour.setText("D: " + fcmQuestion.getOptionFour());

                break;
            case "audio":
//                try {
//                    playAudio(questionDTO);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                break;
            case "video":
//                displayVideo(questionDTO);
                break;
            case "image":
//                displayImage(questionDTO);
                break;
        }

        //check the question type....accordingly display the user the video, audio and then the question
        // when the user presses the next button, make an API call
        // return question is null then, endgame

    }

    private String getSuitableAnswer(){
        StringBuilder answerBuilder = new StringBuilder();
        if(answerType.equals("sequence")){
            return " ";
        }
        if(optionOne.isChecked())
            answerBuilder.append("A");
        if(optionTwo.isChecked())
            answerBuilder.append("B");
        if(optionThree.isChecked())
            answerBuilder.append("C");
        if(optionFour.isChecked())
            answerBuilder.append("D");
        return answerBuilder.toString();
    }

    private void setAllViews(){
        imageView = findViewById(R.id.dynamic_image);
        videoView = findViewById(R.id.dynamic_video);
        questionText = findViewById(R.id.dynamic_question_text);
        optionOne = findViewById(R.id.dynamic_option_A);
        optionTwo = findViewById(R.id.dynamic_option_B);
        optionThree = findViewById(R.id.dynamic_option_C);
        optionFour = findViewById(R.id.dynamic_option_D);
        submitButton = findViewById(R.id.submit_button);

        linearLayout = findViewById(R.id.dynamic_linear);
        pleaseWait = findViewById(R.id.please_wait);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.submit_button:
                // TODO:  MAKE AN API CALL TO SUBMIT THE ANSWER

                UserAnswerDTO userAnswerDTO = new UserAnswerDTO();
                userAnswerDTO.setAnswered(true);
                userAnswerDTO.setSkipped(false);
                userAnswerDTO.setPoints(0);

                SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
                String userId = sharedPreferences.getString("userId", " ");

                userAnswerDTO.setUserId(userId);
                userAnswerDTO.setAnswer(getSuitableAnswer());
                userAnswerDTO.setTimeOfAnswer(null);

                ContestQuestionDTO contestQuestionDTO = new ContestQuestionDTO();
                contestQuestionDTO.setQuestionId(fcmQuestion.getQuestionId());
                contestQuestionDTO.setContestQuestionId(fcmQuestion.getContestQuestionId());
                contestQuestionDTO.setContestDTO(contestDTO);

                userAnswerDTO.setContestQuestionDTO(contestQuestionDTO);
//                userAnswerDTO.getContestQuestionDTO().setContestDTO(contestDTO);

                UserApiCall userApiCall = retrofit.create(UserApiCall.class);
                Call<Boolean> saveAnswer = userApiCall.saveAnswer(userAnswerDTO);
                saveAnswer.enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        try {
                            Log.d(TAG, response.body().toString());

                            Toast.makeText(DynamicGame.this, "Submitted", Toast.LENGTH_SHORT).show();
                            submitButton.setVisibility(View.INVISIBLE);
                            linearLayout.setVisibility(View.INVISIBLE);
                            questionText.setVisibility(View.INVISIBLE);
                            optionOne.setChecked(false);
                            optionTwo.setChecked(false);
                            optionThree.setChecked(false);
                            optionFour.setChecked(false);
                            pleaseWait.setVisibility(View.VISIBLE);
                        }catch (Exception e){
                            Log.e(TAG+"response error",e.getMessage());
                        }
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                        Log.e(TAG, t.getMessage());


                    }
                });



                break;

        }
    }

}
