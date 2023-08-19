package com.techelevator.model;

public class Curriculum {

        private int curriculumId;
        private int courseId;
        private String title;
        private String content;


      public Curriculum(){}

    public Curriculum( int curriculumId, String title, String content) {

        this.curriculumId = curriculumId;
        this.title = title;
        this.content = content;
    }


    public void setCurriculumId(int curriculumId) {
        this.curriculumId = curriculumId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }



    public int getCurriculumId() {
        return curriculumId;
    }

    public String getTitle() {
        return title;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getContent() {
        return content;
    }
}
