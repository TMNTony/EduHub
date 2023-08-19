package com.techelevator.controller;


import com.techelevator.dao.PageDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class PageController {
    private PageDao pageDao;

    public PageController(PageDao pageDao) {
        this.pageDao = pageDao;
    }

    //    Creates a page and return it with the new ID from the database.
    @RequestMapping(path = "/pages", method = RequestMethod.POST)
    public ResponseEntity<Page> createPage(@RequestBody Page page) {
        try {
            Page createdPage = pageDao.createPage(page);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPage);
        } catch (DaoException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //    Gets a list of pages by curriculum ID.
    @RequestMapping(path = "/pages/{curriculumId}", method = RequestMethod.GET)
    public ResponseEntity<List<Page>> getPagesByCurriculumId(@PathVariable int curriculumId) {
        try {
            List<Page> pages = pageDao.getPagesByCurriculumId(curriculumId);
            return ResponseEntity.ok(pages);
        } catch (DaoException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //    Updates a page and returns the updated data to the client.
    @RequestMapping(path = "/pages/{pageId}", method = RequestMethod.PUT)
    public ResponseEntity<Page> updatePage(@PathVariable int pageId, @RequestBody Page page) {
        try {
            Page updatedPage = pageDao.updatePage(page);
            if (updatedPage != null) {
                return ResponseEntity.ok(updatedPage);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (DaoException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //    Deletes a page.
    @RequestMapping(path = "/pages/{pageId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deletePage(@PathVariable int pageId) {
        try {
            boolean deleted = pageDao.deletePage(pageId);
            if (deleted) {
                return ResponseEntity.ok("Deleted");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
            }
        } catch (DaoException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something bad happened");
        }
    }
}
