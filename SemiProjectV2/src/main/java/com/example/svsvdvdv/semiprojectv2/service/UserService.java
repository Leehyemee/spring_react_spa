package com.example.svsvdvdv.semiprojectv2.service;


import com.example.svsvdvdv.semiprojectv2.domain.User;
import org.apache.naming.factory.SendMailFactory;

public interface UserService {

    User newUser(User user);

    User loginUser(User user);

    boolean verifyEmail(String userid, String email, String code);

}
