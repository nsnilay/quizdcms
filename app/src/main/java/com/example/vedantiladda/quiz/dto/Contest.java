package com.example.vedantiladda.quiz.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class Contest implements Serializable {

    private String contestId;
    private String contestName;
    private String contestType;
    private Timestamp startDate;
    private Timestamp endDate;
    private String categoryId;
    private String adminId;
    private Integer bonus;
    private String categoryName;
    private String email;
    private Integer numberOfQuestions;
    private List<ContestQuestionDTO> contestQuestionDTOList;

    public List<ContestQuestionDTO> getContestQuestionDTOList() {
        return contestQuestionDTOList;
    }

    public void setContestQuestionDTOList(List<ContestQuestionDTO> contestQuestionDTOList) {
        this.contestQuestionDTOList = contestQuestionDTOList;
    }

    public Integer getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(Integer numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    private Integer questionVisibilityDuration;

    @Override
    public String toString() {
        return "ContestDTO{" +
                "contestId='" + contestId + '\'' +
                ", contestName='" + contestName + '\'' +
                ", contestType='" + contestType + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", categoryId='" + categoryId + '\'' +
                ", adminId='" + adminId + '\'' +
                ", bonus=" + bonus +
                ", categoryName='" + categoryName + '\'' +
                ", email='" + email + '\'' +
                '}';
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

    public Integer getQuestionVisibilityDuration() {
        return questionVisibilityDuration;
    }

    public void setQuestionVisibilityDuration(Integer questionVisibilityDuration) {
        this.questionVisibilityDuration = questionVisibilityDuration;
    }

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

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
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

    public Integer getBonus() {
        return bonus;
    }

    public void setBonus(Integer bonus) {
        this.bonus = bonus;
    }
}
