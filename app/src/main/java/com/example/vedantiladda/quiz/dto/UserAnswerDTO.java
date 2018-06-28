package com.example.vedantiladda.quiz.dto;

import java.sql.Timestamp;

public class UserAnswerDTO {
    private String userAnswerId;
    private ContestQuestionDTO contestQuestionDTO;
    private String userId;
    private String answer;
    private Integer points;
    private Boolean skipped;
    private String timeOfAnswer;

    private Boolean answered;

    @Override
    public String toString() {
        return "UserAnswerDTO{" +
                "userAnswerId='" + userAnswerId + '\'' +
                ", contestQuestionDTO=" + contestQuestionDTO +
                ", userId='" + userId + '\'' +
                ", answer='" + answer + '\'' +
                ", points=" + points +
                ", skipped=" + skipped +
                ", timeOfAnswer=" + timeOfAnswer +
                ", answered=" + answered +
                '}';
    }

    public Boolean getSkipped() {
        return skipped;
    }

    public void setSkipped(Boolean skipped) {
        this.skipped = skipped;
    }

    public ContestQuestionDTO getContestQuestionDTO() {
        return contestQuestionDTO;
    }

    public void setContestQuestionDTO(ContestQuestionDTO contestQuestionDTO) {
        this.contestQuestionDTO = contestQuestionDTO;
    }
    public String getUserAnswerId() {
        return userAnswerId;
    }

    public void setUserAnswerId(String userAnswerId) {
        this.userAnswerId = userAnswerId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getTimeOfAnswer() {
        return timeOfAnswer;
    }

    public void setTimeOfAnswer(String timeOfAnswer) {
        this.timeOfAnswer = timeOfAnswer;
    }


    public Boolean getAnswered() {
        return answered;
    }

    public void setAnswered(Boolean answered) {
        this.answered = answered;
    }
}