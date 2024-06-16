package com.dictionaryapp.service;

import com.dictionaryapp.model.dto.RegisterUserDTO;
import com.dictionaryapp.repo.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public final ModelMapper modelMapper;
    public final UserRepository userRepository;

    public UserService(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    public boolean registerUser(RegisterUserDTO registerUserDTO) {
        return true;
    }
}
