package com.App.Proyect_Developper.Services;

import com.App.Proyect_Developper.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServices {

    @Autowired
    public UserRepository userRepository;

}