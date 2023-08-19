package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Homework;
import com.techelevator.model.Page;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class JDBCHomeworkDao implements HomeWorkDao {
    private final JdbcTemplate jdbcTemplate;

    public JDBCHomeworkDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Homework> getHomeWorkByParentId(int parentId) {
        List<Homework> homework = new ArrayList<>();
        String sql = "SELECT * FROM homework WHERE parent_id = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, parentId);

        while (rowSet.next()) {
            Homework h = mapRowToHomework(rowSet);
            homework.add(h);
        }

        return homework;
    }

    @Override
    public Homework getHomeworkById(int homeworkId) {
        Homework homework = null;
        String sql = "SELECT * FROM homework WHERE homework_id = ?";
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, homeworkId);
            if (rowSet.next()) {
                homework = mapRowToHomework(rowSet);
                return homework;
            }
        } catch (DaoException e) {
            throw new DaoException("Not found");
        }
        return null;
    }

    @Override
    public Homework getHomeworkByUserId(int userId) {
        Homework homework = null;
        String sql = "SELECT * FROM homework WHERE user_id = ?";
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId);
            if (rowSet.next()) {
                homework = mapRowToHomework(rowSet);
                return homework;
            }
        } catch (DaoException e) {
            throw new DaoException("Not found");
        }
        return null;
    }




    @Override
    public Homework createHomework(Homework homework) {
        Homework newHomework = null;
        String sql = "INSERT INTO homework (parent_id, link, score) VALUES (?, ?, ?) RETURNING homework_id";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        try {
            int newHomeworkId = jdbcTemplate.queryForObject(sql, int.class, homework.getParentId(), homework.getLink(), homework.getScore());
            newHomework = getHomeworkById(newHomeworkId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return newHomework;
    }

    @Override
    public Homework updateHomework(Homework homework) {
        String sql = "UPDATE homework SET link = ?, score = ? WHERE homework_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, homework.getLink(), homework.getScore(), homework.getId());
        if (rowsAffected > 0) {
            return homework;
        } else {
            return null;
        }
    }
//Deletes homework by the ID.
    @Override
    public boolean deleteHomeworkById(int homeworkId) {
        String sql = "DELETE FROM homework WHERE homework_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, homeworkId);
        return rowsAffected > 0;
    }

    @Override
    public boolean deleteAllHomework() {
        String sql = "DELETE FROM homework";
        int rowsAffected = jdbcTemplate.update(sql);
        return rowsAffected > 0;
    }



    private Homework mapRowToHomework(SqlRowSet rs) {
        Homework homework = new Homework();
        homework.setId(rs.getInt("homework_id"));
        homework.setParentId(rs.getInt("parent_id"));
        homework.setUserId(rs.getInt("user_id"));
        homework.setLink(rs.getString("link"));
        homework.setScore(rs.getInt("score"));
        return homework;
    }
}
