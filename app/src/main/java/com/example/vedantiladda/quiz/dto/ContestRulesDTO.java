package com.example.vedantiladda.quiz.dto;

public class ContestRulesDTO {
    private String ruleId;
    private Integer numQuestions ;
    private Integer numEasyQ;
    private Integer numMediumQ;
    private Integer numHardQ;
    private Integer numTextQ;
    private Integer numImageQ;
    private Integer numAudioQ;
    private Integer  numVideoQ;

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public Integer getNumQuestions() {
        return numQuestions;
    }

    public void setNumQuestions(Integer numQuestions) {
        this.numQuestions = numQuestions;
    }

    public Integer getNumEasyQ() {
        return numEasyQ;
    }

    public void setNumEasyQ(Integer numEasyQ) {
        this.numEasyQ = numEasyQ;
    }

    public Integer getNumMediumQ() {
        return numMediumQ;
    }

    public void setNumMediumQ(Integer numMediumQ) {
        this.numMediumQ = numMediumQ;
    }

    public Integer getNumHardQ() {
        return numHardQ;
    }

    public void setNumHardQ(Integer numHardQ) {
        this.numHardQ = numHardQ;
    }

    public Integer getNumTextQ() {
        return numTextQ;
    }

    public void setNumTextQ(Integer numTextQ) {
        this.numTextQ = numTextQ;
    }

    public Integer getNumImageQ() {
        return numImageQ;
    }

    public void setNumImageQ(Integer numImageQ) {
        this.numImageQ = numImageQ;
    }

    public Integer getNumAudioQ() {
        return numAudioQ;
    }

    public void setNumAudioQ(Integer numAudioQ) {
        this.numAudioQ = numAudioQ;
    }

    public Integer getNumVideoQ() {
        return numVideoQ;
    }

    public void setNumVideoQ(Integer numVideoQ) {
        this.numVideoQ = numVideoQ;
    }
}
