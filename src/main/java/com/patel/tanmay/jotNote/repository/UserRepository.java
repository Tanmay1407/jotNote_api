package com.patel.tanmay.jotNote.repository;

import com.patel.tanmay.jotNote.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByuserName(String username);
}
