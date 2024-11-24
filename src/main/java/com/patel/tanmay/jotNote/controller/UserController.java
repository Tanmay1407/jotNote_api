package com.patel.tanmay.jotNote.controller;

import com.patel.tanmay.jotNote.entity.User;
import com.patel.tanmay.jotNote.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody User newUser){
        try{
            User createdUser = userService.addUser(newUser);
            return new ResponseEntity<>(createdUser,HttpStatus.CREATED);
        }catch (Exception e){
            System.out.println("Error occurred in createUser controller: "+e.getMessage());
            return new ResponseEntity<>("Unable to create user",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("id/{userId}")
    public ResponseEntity<?> getUser(@PathVariable ObjectId userId){
        try{
            User user = userService.getUserById(userId);
            if(user != null){
                return  new ResponseEntity<>(user,HttpStatus.OK);
            }
            return new ResponseEntity<>("User Not Found",HttpStatus.NOT_FOUND);
        }catch (Exception e){
            System.out.println("Error occurred in getUser controller: "+e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping()
    public ResponseEntity<?> getAllUser(){
        try{
            List<User> users = userService.getAllUsers();
            if(users != null && !users.isEmpty()){
                return new ResponseEntity<>(users,HttpStatus.OK);
            }
            return new ResponseEntity<>("No users found",HttpStatus.NOT_FOUND);
        }catch (Exception e){
            System.out.println("Error occurred in getAllUser controller: "+e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("id/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable ObjectId userId){
        try{
            userService.deleteUserById(userId);
            return new ResponseEntity<>("User removed",HttpStatus.NO_CONTENT);
        }catch (Exception e){
            System.out.println("Error occurred in deleteUser controller: "+e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
