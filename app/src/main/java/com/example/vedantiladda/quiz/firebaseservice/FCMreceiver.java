package com.example.vedantiladda.quiz.firebaseservice;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.example.vedantiladda.quiz.dto.FCMQuestion;
import com.example.vedantiladda.quiz.user.DynamicGame;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.Serializable;
import java.util.Map;

public class FCMreceiver extends FirebaseMessagingService {

    private String questionId = "questionId";
    private String contestQuestionId = "contestQuestionId";
    private String categoryId = "categoryId";
    private String difficulty = "difficulty";
    private String questionType = "questionType";
    private String questionText = "questionText";
    private String questionUrl = "questionUrl";
    private String optionOne = "optionOne";
    private String optionTwo = "optionTwo";
    private String optionThree = "optionThree";
    private String optionFour = "optionFour";
    private String answerType = "answerType";
    private String points = "points";
    private String status = "status";

    public FCMreceiver() {
    }

    @Override
    public void onMessageReceived(final RemoteMessage remoteMessage) {
        // ...

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d("Message", "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d("Message", "Message data payload: " + remoteMessage.getData());
            Handler mainHandler = new Handler(getMainLooper());

            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    FCMQuestion fcmQuestion = new FCMQuestion();
                    fcmQuestion.setQuestionId(remoteMessage.getData().get(questionId));
                    fcmQuestion.setContestQuestionId(remoteMessage.getData().get(contestQuestionId));
                    fcmQuestion.setCategoryId(remoteMessage.getData().get(categoryId));
                    fcmQuestion.setDifficulty(remoteMessage.getData().get(difficulty));
                    fcmQuestion.setQuestionType(remoteMessage.getData().get(questionType));
                    fcmQuestion.setQuestionUrl(remoteMessage.getData().get(questionUrl));
                    fcmQuestion.setQuestionText(remoteMessage.getData().get(questionText));
                    fcmQuestion.setOptionOne(remoteMessage.getData().get(optionOne));
                    fcmQuestion.setOptionTwo(remoteMessage.getData().get(optionTwo));
                    fcmQuestion.setOptionThree(remoteMessage.getData().get(optionThree));
                    fcmQuestion.setOptionFour(remoteMessage.getData().get(optionFour));
                    fcmQuestion.setAnswerType(remoteMessage.getData().get(answerType));
                    fcmQuestion.setStatus(remoteMessage.getData().get(status));

                    sendMessage(fcmQuestion);
                }
            });

            if (/* Check if data needs to be processed by long running job */ true) {
                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
                //scheduleJob();
            } else {
                // Handle message within 10 seconds
                //handleNow();
            }

        }



        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d("Message", "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }
        //  TODO : change it accordingly - used to send msgs to dynamic game
    private void sendMessage(FCMQuestion fcmQuestion) {
        Log.d("sender", "Broadcasting message");
        Intent intent = new Intent("custom-event-name");
        // You can also include some extra data.
        intent.putExtra("message",(Serializable) fcmQuestion);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}
