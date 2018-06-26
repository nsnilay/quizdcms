package com.example.vedantiladda.quiz.user;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.vedantiladda.quiz.R;
import com.example.vedantiladda.quiz.dto.ContestDTO;
import com.example.vedantiladda.quiz.dto.ContestQuestionDTO;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "GAMEACTIVITY";

    private TextView questionText, skipCountText, questionNumberText;
    private Button beginButton;
    private MediaSessionCompat mMediaSession;
    private PlaybackStateCompat.Builder mStateBuilder;
    private VideoView videoView;
    private ContestDTO contestDTO;
    private ContestQuestionDTO contestQuestionDTO;
    private List<ContestQuestionDTO> contestQuestionDTOList, copyContestQuestionDTOList;
    private ImageView imageView;
    private Uri videoUri;
    private MediaController mediaController;
    private MediaPlayer mediaPlayer;
    private FloatingActionButton nextQuestionFab, skipQuestionFab;

    private OkHttpClient client;
    private Retrofit retrofit;

    static final String MEDIA_LOG_TAG = "MediaLogTag";
    static final int MY_PERMISSIONS_REQUEST_READ_STORAGE = 1;

    static int skipCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setAllViews();
        requestPermissions();

    //    contestDTO = (ContestDTO) getIntent().getSerializableExtra("contestDTO");

        //  get All questions whenever the activity starts

        contestQuestionDTOList = new ArrayList<>();
        copyContestQuestionDTOList = new ArrayList<>();
        contestDTO = new ContestDTO();
        contestQuestionDTOList = getAllQuestionsByContest("bb23cb83-b906-43d5-aa93-bf8af9f54ed8");//contestDTO.getContestId());

        mMediaSession = new MediaSessionCompat(this, MEDIA_LOG_TAG);
        //Creating MediaController
        mediaController= new MediaController(this);
        mediaController.setAnchorView(videoView);

//        displayVideo();

        nextQuestionFab.setOnClickListener(this);
        skipQuestionFab.setOnClickListener(this);
        beginButton.setOnClickListener(this);
    }

    private List<ContestQuestionDTO> getAllQuestionsByContest(String contestId) {

        client = new OkHttpClient.Builder().build();

        retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.base_url_contest))
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();


        UserApiCall userApiCall = retrofit.create(UserApiCall.class);

        Call<ContestDTO> getAllQuestions = userApiCall.getQuestionsByContest(contestId);
        getAllQuestions.enqueue(new Callback<ContestDTO>() {
            @Override
            public void onResponse(Call<ContestDTO> call, Response<ContestDTO> response) {
                Log.d(TAG,call.request().url().toString());

                contestQuestionDTOList = response.body().getContestQuestionDTOList();
                Toast.makeText(GameActivity.this,"Retrieved questions Successfully" , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ContestDTO> call, Throwable t) {
                Toast.makeText(GameActivity.this,"API call failed" , Toast.LENGTH_SHORT).show();
                Log.e(TAG, t.getMessage());


            }
        });
//        Toast.makeText(GameActivity.this,"Retrieved questions Successfully" + contestDTO.getContestQuestionDTOList().toString() , Toast.LENGTH_SHORT).show();


        return contestDTO.getContestQuestionDTOList();

    }

    private void displayQuestion(ContestQuestionDTO contestQuestionDTO){

        //check the question type....accordingly display the user the video, audio and then the question
        // when the user presses the next button, make an API call

    }

    private void findEligibleQuestion(){
        // TODO : iterate the list of questions and find the eligible questions, then pass the question to displayQuestion
        /* for every question in question list
                if(isAnswered){ nextquestion}
                elseif (!isAnswered && skipcount!=2){ this is the question we have to return}
                if {skipcount ==2){
                        display you ran out of skips, change skipcount = 0 and display questions from beginning
         */
        
    }

    private void requestPermissions() {

        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(GameActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(GameActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                Toast.makeText(GameActivity.this," here", Toast.LENGTH_SHORT).show();

            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(GameActivity.this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        MY_PERMISSIONS_REQUEST_READ_STORAGE);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
            Toast.makeText(GameActivity.this,"permission already granted", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }

    void setAllViews(){
        questionText = findViewById(R.id.question_text);
        questionText.setMovementMethod(new ScrollingMovementMethod());
        skipCountText = findViewById(R.id.skip_count_text);
        questionNumberText = findViewById(R.id.question_number);

        videoView = findViewById(R.id.video);
        imageView = findViewById(R.id.image);

        nextQuestionFab = findViewById(R.id.next_question);
        skipQuestionFab = findViewById(R.id.skip_question_button);

        beginButton = findViewById(R.id.begin_button);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.next_question:
                /* TODO: Call API to save the answer state
                    and then
                 */


                break;

            case R.id.skip_question_button :
                break;

            case R.id.begin_button:
                beginButton.setVisibility(View.GONE);
                Toast.makeText(GameActivity.this,contestQuestionDTOList.toString(),Toast.LENGTH_SHORT).show();
                findEligibleQuestion();
//                displayNextQuestion(contestQuestionDTO);
                break;
        }
    }

    private void displayVideo(){
        //specify the location of media file
        videoUri=Uri.parse("http://techslides.com/demos/sample-videos/small.mp4");//Environment.getExternalStorageDirectory().getPath()+"/media/1.mp4");
        //Setting MediaController and URI, then starting the videoView
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(videoUri);
        videoView.requestFocus();
        videoView.start();
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                try {
                    playAudio();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void playAudio() throws IOException {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setDataSource("http://www.hubharp.com/web_sound/BachGavotteShort.mp3");
        mediaPlayer.prepare();
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                displayImage();
            }
        });


    }

    private void displayImage(){
        new DownLoadImageTask(imageView).execute("https://i.ytimg.com/vi/y0VODhYvq3s/maxresdefault.jpg");
    }

    private class DownLoadImageTask extends AsyncTask<String,Void,Bitmap> {
        ImageView imageView;

        public DownLoadImageTask(ImageView imageView){
            this.imageView = imageView;
        }

        /*
            doInBackground(Params... params)
                Override this method to perform a computation on a background thread.
         */
        protected Bitmap doInBackground(String...urls){
            String urlOfImage = urls[0];
            Bitmap logo = null;
            try{
                InputStream is = new URL(urlOfImage).openStream();
                /*
                    decodeStream(InputStream is)
                        Decode an input stream into a bitmap.
                 */
                logo = BitmapFactory.decodeStream(is);
            }catch(Exception e){ // Catch the download exception
                e.printStackTrace();
            }
            return logo;
        }

        /*
            onPostExecute(Result result)
                Runs on the UI thread after doInBackground(Params...).
         */
        protected void onPostExecute(Bitmap result){
            imageView.setImageBitmap(result);
        }
    }

}
