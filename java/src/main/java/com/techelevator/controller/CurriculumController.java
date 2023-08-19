package com.techelevator.controller;


import com.techelevator.dao.CurriculumDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Curriculum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class CurriculumController {
    private CurriculumDao curriculumDao;

    public CurriculumController(CurriculumDao curriculumDao) {
        this.curriculumDao = curriculumDao;
    }

    @RequestMapping(path = "/curriculum", method = RequestMethod.GET)
    public List<Curriculum> getCurriculum() {
        List<Curriculum> listOfCurriculum = curriculumDao.getCurriculum();

        return listOfCurriculum;
    }

    @RequestMapping(path = "curriculum/id/{id}", method = RequestMethod.GET)
    public ResponseEntity<Curriculum> getCurriculumById(@PathVariable int id) {
        try {
            Curriculum curriculum = curriculumDao.getCurriculumById(id);
            return ResponseEntity.ok(curriculum);
        } catch (DaoException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @RequestMapping(path = "curriculum/bycourse/{courseId}", method = RequestMethod.GET)
    public List<Curriculum> getCurriculumByCourseId(@PathVariable int courseId) {
        try {
           List<Curriculum> curriculum = curriculumDao.getCurriculumByCourseId(courseId);
            return curriculum;
        } catch (DaoException e) {
            return null;
        }

    }



    @RequestMapping(path = "/curriculum/title/{title}", method = RequestMethod.GET)
    public ResponseEntity<Curriculum> getCurriculumByName(@PathVariable String title) {
        try {
            Curriculum curriculum = curriculumDao.getCurriculumByName(title);
            return ResponseEntity.ok(curriculum);
        } catch (DaoException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @RequestMapping(path = "/curriculum", method = RequestMethod.POST)
    public ResponseEntity<Curriculum> createCurriculum(@RequestBody Curriculum curriculum) {
        try {
            Curriculum createdCurriculum = curriculumDao.createCurriculum(curriculum);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCurriculum);
        } catch (DaoException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @RequestMapping(path = "/curriculum/{curriculumId}", method = RequestMethod.PUT)
    public ResponseEntity<Curriculum> updateCurriculum(@PathVariable int curriculumId, @RequestBody Curriculum curriculum) {
        try {
            curriculum.setCurriculumId(curriculumId);
            Curriculum updatedCurriculum = curriculumDao.updateCurriculum(curriculum);

            if (updatedCurriculum != null) {
                return ResponseEntity.ok(updatedCurriculum);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (DaoException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }


    }

    @RequestMapping(path = "/curriculum/{curriculumId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteCurriculum(@PathVariable int curriculumId) {
        try {
            boolean deleted = curriculumDao.deleteCurriculum(curriculumId);
            if (deleted) {
                return ResponseEntity.status(HttpStatus.OK).body("Deleted");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
            }
        } catch (DaoException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something bad happened");
        }
    }


}
