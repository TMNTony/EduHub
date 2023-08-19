package com.techelevator.dao;

import java.util.List;


import com.techelevator.model.Course;

public interface CourseDao {

    List<Course> getCourses();

    Course getCourseById(int id);


    Course getCourseByName(String name);

    Course createCourse(Course course);

    Course updateCourse(Course course);
    boolean deleteCourse(int courseId);
}
