package com.techelevator.controller;

import com.techelevator.dao.UserDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Course;
import com.techelevator.model.RegisterUserDto;
import com.techelevator.model.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@CrossOrigin
public class UserController {

    private UserDao UserDao;

    public UserController(UserDao UserDao){
        this.UserDao = UserDao;
    }
    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public List<User> getUsers() {
        List<User> listOfUsers = UserDao.getUsers();
        return listOfUsers;
    }

    @RequestMapping(path = "/user/id/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        try{
            User user = UserDao.getUserById(id);
            if(user != null){
                return ResponseEntity.ok(user);
            }else{
                return ResponseEntity.notFound().build();
            }
        }catch (DaoException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    @RequestMapping(path = "/user/username/{username}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserByUserName(@PathVariable String username) {
        try{
            User user = UserDao.getUserByUsername(username);
            if(user != null){
                return ResponseEntity.ok(user);
            }else return ResponseEntity.notFound().build();
        }catch (DaoException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    @RequestMapping(path = "/user", method = RequestMethod.POST)
    public User createUser(@RequestBody RegisterUserDto user) {
        return UserDao.createUser(user);
    }
    @RequestMapping(path = "/addAdmin/{username}", method = RequestMethod.PUT)
    public ResponseEntity<User> addAdminRole(@PathVariable String username){
        try{
            User updatedUser = UserDao.addAdminRole(username);
            return ResponseEntity.ok(updatedUser);
        }catch (DaoException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
