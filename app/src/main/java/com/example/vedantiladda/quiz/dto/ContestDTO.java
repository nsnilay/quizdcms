package com.example.vedantiladda.quiz.dto;

import java.util.List;

public class ContestDTO {

    private String contestId;
    private String contestName;
    private String contestType;
    private String startDate;
    private String endDate;
    private String categoryId;
    private String adminId;
    private Integer questionVisibilityDuration;
    private Integer bonus;
    private String categoryName;
    private String email;
    private Integer numberOfQuestions;
    private Integer numberOfQUestions;
    private List<ContestQuestionDTO> contestQuestionDTOList;



    public String getContestId() {
        return contestId;
    }

    public void setContestId(String contestId) {
        this.contestId = contestId;
    }

    public String getContestName() {
        return contestName;
    }

    public void setContestName(String contestName) {
        this.contestName = contestName;
    }

    public String getContestType() {
        return contestType;
    }

    public void setContestType(String contestType) {
        this.contestType = contestType;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public Integer getQuestionVisibilityDuration() {
        return questionVisibilityDuration;
    }

    public void setQuestionVisibilityDuration(Integer questionVisibilityDuration) {
        this.questionVisibilityDuration = questionVisibilityDuration;
    }

    public Integer getBonus() {
        return bonus;
    }

    public void setBonus(Integer bonus) {
        this.bonus = bonus;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(Integer numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public void setQuestionVisibilityDuration(int questionVisibilityDuration) {
        this.questionVisibilityDuration = questionVisibilityDuration;
    }


    public Integer getNumberOfQUestions() {
        return numberOfQUestions;
    }

    public void setNumberOfQUestions(Integer numberOfQUestions) {
        this.numberOfQUestions = numberOfQUestions;
    }

    public List<ContestQuestionDTO> getContestQuestionDTOList() {
        return contestQuestionDTOList;
    }

    public void setContestQuestionDTOList(List<ContestQuestionDTO> contestQuestionDTOList) {
        this.contestQuestionDTOList = contestQuestionDTOList;
    }

    @Override
    public String toString() {
        return "ContestDTO{" +
                "contestId='" + contestId + '\'' +
                ", contestName='" + contestName + '\'' +
                ", contestType='" + contestType + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", adminId='" + adminId + '\'' +
                ", questionVisibilityDuration=" + questionVisibilityDuration +
                ", bonus=" + bonus +
                ", categoryName='" + categoryName + '\'' +
                ", email='" + email + '\'' +
                ", numberOfQuestions=" + numberOfQuestions +
                ", numberOfQUestions=" + numberOfQUestions +
                ", contestQuestionDTOList=" + contestQuestionDTOList +
                '}';
    }
}
