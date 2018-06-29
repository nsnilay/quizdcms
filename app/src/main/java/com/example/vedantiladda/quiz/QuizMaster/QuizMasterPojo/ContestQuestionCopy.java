package com.example.vedantiladda.quiz.QuizMaster.QuizMasterPojo;

public class ContestQuestionCopy {
    private String contestQuestionId;
    private Boolean visible;

    @Override
    public String toString() {
        return "ContestQuestionCopy{" +
                "contestQuestionId='" + contestQuestionId + '\'' +
                ", visible=" + visible +
                '}';
    }

    public String getContestQuestionId() {
        return contestQuestionId;
    }

    public void setContestQuestionId(String contestQuestionId) {
        this.contestQuestionId = contestQuestionId;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
}
