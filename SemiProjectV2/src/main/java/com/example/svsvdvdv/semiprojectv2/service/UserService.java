package com.example.svsvdvdv.semiprojectv2.service;


import org.apache.catalina.User;

public interface UserService {

    User newUser(User user);

    User loginUser(User user);

}
