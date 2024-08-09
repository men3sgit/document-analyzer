package com.edu.nlu.document.service;

import com.edu.nlu.document.model.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findByUsername(String username);

    User findById(Long id);
}
