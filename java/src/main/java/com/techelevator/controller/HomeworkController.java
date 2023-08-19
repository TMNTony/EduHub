package com.techelevator.controller;

import com.techelevator.dao.HomeWorkDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Curriculum;
import com.techelevator.model.Homework;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class HomeworkController {

    private HomeWorkDao homeWorkDao;

    public HomeworkController(HomeWorkDao homeWorkDao) {
        this.homeWorkDao = homeWorkDao;
    }

    //Get a list of homework assignment related to the parent course/curriculum id.
    @RequestMapping(path = "/homework/id/parentId/{parentId}", method = RequestMethod.GET)
    public List<Homework> getHomeworkByParentId(@PathVariable int parentId) {
        List<Homework> listOfHomeworkByParentId = homeWorkDao.getHomeWorkByParentId(parentId);
        return listOfHomeworkByParentId;
    }

    //Get a homework by the homeworks user id (id of the student).
    @RequestMapping(path = "/homework/id/{userId}", method = RequestMethod.GET)
    public ResponseEntity<Homework> getHomeworkByUserId(@PathVariable int userId) {
        try {
            Homework homework = homeWorkDao.getHomeworkByUserId(userId);
            return ResponseEntity.ok(homework);
        } catch (DaoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //Gets homework by the unique id of the that homework item.
    @RequestMapping(path = "/homework/id/homeworkId{homeworkId}", method = RequestMethod.GET)
    public ResponseEntity<Homework> getHomeworkById(@PathVariable int homeworkId) {
        try {
            Homework homework = homeWorkDao.getHomeworkById(homeworkId);
            return ResponseEntity.ok(homework);
        } catch (DaoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //creates a homework object in the database.
    @RequestMapping(path = "/homework", method = RequestMethod.POST)
    public ResponseEntity<Homework> createHomework(@RequestBody Homework homework) {
        try {
            Homework createdHomework = homeWorkDao.createHomework(homework);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdHomework);
        } catch (DaoException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //Allows for updating of homework items.
    @RequestMapping(path = "/homework/{homeworkId}", method = RequestMethod.PUT)
    public ResponseEntity<Homework> updateHomework(@PathVariable int homeworkId, @RequestBody Homework homework) {
        try {
            homework.setId(homeworkId);
            Homework updatedHomework = homeWorkDao.updateHomework(homework);

            if (updatedHomework != null) {
                return ResponseEntity.ok(updatedHomework);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (DaoException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //Allows for deletion of a homework item.
    @RequestMapping(path = "/homework/{homeworkId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteHomeworkById(@PathVariable int homeworkId) {
        try {
            boolean deleted = homeWorkDao.deleteHomeworkById(homeworkId);
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
