package com.techelevator.model;

public class Page {

    private int pageId;

    private int curriculumId;
    private int pageNum;
    private String title;

    private String content;

    public Page() {
    }

    public Page(int pageId, int curriculumId, int pageNum, String title, String content) {
        this.pageId = pageId;
        this.curriculumId = curriculumId;
        this.pageNum = pageNum;
        this.title = title;
        this.content = content;
    }

    public int getId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    public int getCurriculumId() {
        return curriculumId;
    }

    public void setCurriculumId(int curriculumId) {
        this.curriculumId = curriculumId;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
