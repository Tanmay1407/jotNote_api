package com.patel.tanmay.jotNote.service;

import com.patel.tanmay.jotNote.entity.User;
import com.patel.tanmay.jotNote.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User addUser(User newUser){
        userRepository.save(newUser);
        return  newUser;
    }

    public User saveEntry(User user){
        User userInDB = userRepository.findByuserName(user.getUsername());
        if(userInDB != null){
            userInDB.setUserNotes(user.getUserNotes());
            userInDB.setPassword(user.getPassword());
            userRepository.save(userInDB);
        }else addUser(user);
        return user;
    }

    public List<User> getAllUsers(){
        return  userRepository.findAll();

    }

    public User getUserById(ObjectId userId){
        return userRepository.findById(userId).orElse(null);
    }

    public ObjectId deleteUserById(ObjectId userId){
        userRepository.deleteById(userId);
        return userId;
    }

    public User findByUserName(String username){
        return userRepository.findByuserName(username);
    }
}
