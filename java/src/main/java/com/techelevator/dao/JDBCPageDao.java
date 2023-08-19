package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Page;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

@Component
public class JDBCPageDao implements PageDao {

    private final JdbcTemplate jdbcTemplate;

    public JDBCPageDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
//    Allows for getting all pages within the curriculum module.
    public List<Page> getPagesByCurriculumId(int curriculumId) {
        List<Page> pages = new ArrayList<>();
        String sql = "SELECT * FROM pages WHERE curriculum_id = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, curriculumId);

        while (rowSet.next()) {
            Page page = mapRowToPage(rowSet);
            pages.add(page);
        }
        return pages;

    }

    @Override
//    Retrieve a specific page by the unique page ID.
    public Page getPageById(int pageId) {
        Page page = null;
        String sql = "SELECT * FROM pages WHERE page_id = ?";
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, pageId);
            if (rowSet.next()) {
                page = mapRowToPage(rowSet);
                return page;
            }
        } catch (DaoException e) {
            throw new DaoException("Not found");
        }
        return null;
    }


    @Override
//    Creates a page within the pages table in the database and gets the created pageId which is passed to the controller
//    and sent to the front end.
    public Page createPage(Page page) {
        Page newPage = null;
        String sql = "INSERT INTO pages (curriculum_id, page_number, title, content) VALUES (?, ?, ?, ?) RETURNING page_id";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        try {
            int newPageId = jdbcTemplate.queryForObject(sql, int.class, page.getCurriculumId(), page.getPageNum(), page.getTitle(), page.getContent());
            newPage = getPageById(newPageId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return newPage;
    }

    @Override
//    Updates the pages table with data sent from the front end VIA JSON. Written with all parameters and getters to allow for max flexibility.
    public Page updatePage(Page page) {
        String sql = "UPDATE pages SET curriculum_id = ?, page_number = ?, title = ?, content = ? WHERE page_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, page.getCurriculumId(), page.getPageNum(), page.getTitle(), page.getContent(), page.getId());
        if (rowsAffected > 0) {
            return page;
        } else {
            return null;
        }
    }

    @Override
//    Deletes a single page from the database by the unique page_id.
    public boolean deletePage(int pageId) {
        String sql = "DELETE FROM pages WHERE page_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, pageId);
        return rowsAffected > 0;
    }

    //    This method maps the rows from an SQL rowset after query to instantiate an object.
    private Page mapRowToPage(SqlRowSet rs) {
        Page page = new Page();
        page.setPageId(rs.getInt("page_id"));
        page.setCurriculumId(rs.getInt("curriculum_id"));
        page.setPageNum(rs.getInt("page_number"));
        page.setTitle(rs.getString("title"));
        page.setContent(rs.getString("content"));
        return page;
    }
}
