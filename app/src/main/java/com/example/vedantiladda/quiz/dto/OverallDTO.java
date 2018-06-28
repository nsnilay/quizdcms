package com.example.vedantiladda.quiz.dto;

public class OverallDTO {
    private String userId;
    private int overAllPoints;

    private int rank;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getOverAllPoints() {
        return overAllPoints;
    }

    public void setOverAllPoints(int overAllPoints) {
        this.overAllPoints = overAllPoints;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "OverallDTO{" +
                "userId='" + userId + '\'' +
                ", overAllPoints=" + overAllPoints +
                ", rank=" + rank +
                '}';
    }
}
