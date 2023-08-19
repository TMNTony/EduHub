package com.techelevator.dao;

import com.techelevator.exception.DaoException;

import com.techelevator.model.Curriculum;
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
public class JdbcCurriculumDao implements CurriculumDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcCurriculumDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Curriculum> getCurriculum() {
        List<Curriculum> curriculumList = new ArrayList<>();
        String sql = "SELECT * FROM curriculum";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);

        while (rowSet.next()) {
            Curriculum curriculum = mapRowToCurriculum(rowSet);
            curriculumList.add(curriculum);

        }
        return curriculumList;
    }

    public Curriculum getCurriculumById(int curriculumId) {
        Curriculum curriculum = null;
        String sql = "SELECT * FROM curriculum WHERE curriculum_id = ?";
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, curriculumId);
            if (rowSet.next()) {
                curriculum = mapRowToCurriculum(rowSet);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return curriculum;
    }

    public List<Curriculum> getCurriculumByCourseId(int courseId) {
        List<Curriculum> curriculumList = new ArrayList<>();
        String sql = "SELECT * FROM curriculum WHERE course_id = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, courseId);

        while (rowSet.next()) {
            Curriculum curriculum = mapRowToCurriculum(rowSet);
            curriculumList.add(curriculum);

        }
        return curriculumList;
    }

    public Curriculum getCurriculumByName(String title) {
        Curriculum curriculum = null;
        String sql = "SELECT * FROM curriculum WHERE title = ?";
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, title);
            if (rowSet.next()) {
                curriculum = mapRowToCurriculum(rowSet);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return curriculum;
    }

    @Override
    public Curriculum createCurriculum(Curriculum curriculum) {
        Curriculum newCurriculum = null;
        String sql = "INSERT INTO curriculum (course_id, title, content) VALUES (?, ?, ?) RETURNING curriculum_id";

        try {
            int newCurriculumId = jdbcTemplate.queryForObject(sql, int.class, curriculum.getCourseId(), curriculum.getTitle(), curriculum.getContent());
            newCurriculum = getCurriculumById(newCurriculumId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newCurriculum;
    }


    @Override
    public Curriculum updateCurriculum(Curriculum curriculum) {

        String sql = "UPDATE curriculum SET title = ?, content = ? WHERE curriculum_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, curriculum.getTitle(), curriculum.getContent(), curriculum.getCurriculumId());
        if (rowsAffected > 0) {
            return curriculum;
        } else {
            return null;
        }


    }

    public boolean deleteCurriculum(int curriculumId) {
        String sql = "DELETE FROM curriculum WHERE curriculum_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, curriculumId);

        return rowsAffected > 0;
    }

    private Curriculum mapRowToCurriculum(SqlRowSet rs) {
        Curriculum curriculum = new Curriculum();
        curriculum.setCurriculumId(rs.getInt("curriculum_id"));
        curriculum.setCourseId(rs.getInt("course_id"));
        curriculum.setTitle(rs.getString("title"));
        curriculum.setContent(rs.getString("content"));
        return curriculum;

    }


}
