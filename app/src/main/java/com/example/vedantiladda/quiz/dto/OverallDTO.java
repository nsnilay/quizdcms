package com.example.vedantiladda.quiz.dto;

import java.math.BigInteger;

public class OverallDTO {
    private String userId;
    private BigInteger overAllPoints;

    private BigInteger rank;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigInteger getOverAllPoints() {
        return overAllPoints;
    }

    public void setOverAllPoints(BigInteger overAllPoints) {
        this.overAllPoints = overAllPoints;
    }

    public BigInteger getRank() {
        return rank;
    }

    public void setRank(BigInteger rank) {
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
