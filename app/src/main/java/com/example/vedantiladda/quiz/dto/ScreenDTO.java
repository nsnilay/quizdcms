package com.example.vedantiladda.quiz.dto;

public class ScreenDTO {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "ScreenDTO{" +
                "id='" + id + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String status;
}
