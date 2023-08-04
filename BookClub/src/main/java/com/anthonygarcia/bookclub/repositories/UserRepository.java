package com.anthonygarcia.bookclub.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.anthonygarcia.bookclub.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAll();
    Optional<User> findByEmail(String email);
    
}

//we do not need to make a repository for LoginUser as we only want to save instances of User to the database.