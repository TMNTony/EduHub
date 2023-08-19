package com.techelevator.controller;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Course;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.techelevator.dao.CourseDao;

import javax.naming.spi.ResolveResult;
import java.util.List;


@RestController
@CrossOrigin
public class CourseController {

    private CourseDao CourseDao;


    public CourseController(CourseDao CourseDao) {
        this.CourseDao = CourseDao;
    }


    @RequestMapping(path = "/course", method = RequestMethod.GET)

    public List<Course> getCourses() {
        List<Course> listOfCourses = CourseDao.getCourses();
        return listOfCourses;
    }


    @RequestMapping(path = "/course/id/{id}", method = RequestMethod.GET)

    public ResponseEntity<Course> getCourseById(@PathVariable int id) {
        try {
            Course course = CourseDao.getCourseById(id);
            return ResponseEntity.ok(course);
        } catch (DaoException e) {
            return ResponseEntity.notFound().build();
        }


    }

    @RequestMapping(path = "/course/name/{name}", method = RequestMethod.GET)

    public ResponseEntity<Course> getCourseByName(@PathVariable String name) {
        try {
            Course course = CourseDao.getCourseByName(name);
            return ResponseEntity.ok(course);
        } catch (DaoException e) {
            return ResponseEntity.notFound().build();
        }

    }


    @RequestMapping(path = "/course", method = RequestMethod.POST)
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        try {
            Course createdCourse = CourseDao.createCourse(course);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCourse);
        } catch (DaoException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    @RequestMapping(path = "/course/{courseId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteCourse(@PathVariable int courseId) {
        try {
            boolean deleted = CourseDao.deleteCourse(courseId);
            if (deleted) {
                return ResponseEntity.ok("Deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
            }
        } catch (DaoException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something bad happened");
        }
    }

    @RequestMapping(path = "/course/{courseId}", method = RequestMethod.PUT)
    public ResponseEntity<Course> updateCourse(@PathVariable int courseId, @RequestBody Course course) {
        try {
            Course updatedCourse = CourseDao.updateCourse(course);
            if (updatedCourse != null) {
                return ResponseEntity.ok(updatedCourse);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (DaoException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


}
