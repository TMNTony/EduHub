package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Course;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCourseDao implements CourseDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcCourseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Course> getCourses() {
        List<Course> course = new ArrayList<>();
        String sql = "SELECT * FROM course";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);

        while (rowSet.next()) {
            Course courses = mapRowToCourse(rowSet);
            course.add(courses);
        }
        return course;
    }

    @Override
    public Course getCourseById(int id) {
        Course course = null;
        String sql = "SELECT * FROM course WHERE course_id = ?";

        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, id);
            if (rowSet.next()) {
                course = mapRowToCourse(rowSet);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return course;
    }


    @Override
    public Course getCourseByName(String name) {
        Course course = null;
        String sql = "SELECT * FROM course WHERE course_name = ?";

        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, name);
            if (rowSet.next()) {
                course = mapRowToCourse(rowSet);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return course;
    }

    @Override
    public Course createCourse(Course course) {
        Course newCourse = null;
        String sql = "INSERT INTO course (course_name, description, difficulty, cost)" +
                "VALUES (?, ?, ?, ?) RETURNING course_id";
        try {
            int newCourseId = jdbcTemplate.queryForObject(sql, int.class, course.getName(), course.getDescription(), course.getDifficulty(), course.getCost());
            newCourse = getCourseById(newCourseId);

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newCourse;
    }

    @Override
    public Course updateCourse(Course course) {
        String sql = "UPDATE course SET course_name = ?, description = ?, difficulty = ?, cost = ? WHERE course_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, course.getName(), course.getDescription(), course.getDifficulty(), course.getCost(), course.getId());
        if (rowsAffected > 0) {
            return course;
        } else {
            return null;
        }


    }

    @Override
    public boolean deleteCourse(int courseId) {
        String sql = "DELETE FROM course WHERE course_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, courseId);
        return rowsAffected > 0;
    }

    private Course mapRowToCourse(SqlRowSet rs) {
        Course course = new Course();
        course.setId(rs.getInt("course_id"));
        course.setName(rs.getString("course_name"));
        course.setDescription(rs.getString("description"));
        course.setDifficulty(rs.getString("difficulty"));
        course.setCost(rs.getBigDecimal("cost"));
        return course;
    }
}
