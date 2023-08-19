package com.techelevator.model;

public class Homework {
    private int id;

    private int parentId;
    private int userId;

    private String link;

    public Homework(int id, int parentId, int userId, String link, int score) {
        this.id = id;
        this.parentId = parentId;
        this.userId = userId;
        this.link = link;
        this.score = score;
    }

    private int score;

    public Homework(){}

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
