package com.example.vedantiladda.quiz.user;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.vedantiladda.quiz.R;

public class DynamicGame extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageView;
    private VideoView videoView;
    private TextView questionText;
    private CheckBox optionOne, optionTwo, optionThree, optionFour;
    private EditText sequenceText;
    private Button submitButton;

    private String answerType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_game);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setAllViews();

        submitButton.setOnClickListener(this);


    }

    private String getSuitableAnswer(){
        StringBuilder answerBuilder = new StringBuilder();
        if(answerType.equals("sequence")){
            return sequenceText.getText().toString();
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
        optionTwo = findViewById(R.id.option_B);
        optionThree = findViewById(R.id.option_C);
        optionFour = findViewById(R.id.option_D);
        submitButton = findViewById(R.id.submit_button);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.submit_button:
                // TODO:  MAKE AN API CALL TO SUBMIT THE ANSWER

                break;

        }
    }

}
