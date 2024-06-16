package com.dictionaryapp.service;

import com.dictionaryapp.config.UserSession;
import com.dictionaryapp.model.dto.LoginUserDTO;
import com.dictionaryapp.model.dto.RegisterUserDTO;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.repo.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserSession userSession;

    public UserService(ModelMapper modelMapper,
                       UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       UserSession userSession) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userSession = userSession;
    }

    public boolean registerUser(RegisterUserDTO registerUserDTO) {
        if (!registerUserDTO.getPassword().equals(registerUserDTO.getConfirmPassword())) {
            return false;
        }
        boolean isUsernameOrEmailTaken = userRepository.existsByUsernameOrEmail(registerUserDTO.getUsername(), registerUserDTO.getEmail());
        if (isUsernameOrEmailTaken) {
            return false;
        }
        User mapped = this.modelMapper.map(registerUserDTO, User.class);
        mapped.setPassword(this.passwordEncoder.encode(mapped.getPassword()));
        this.userRepository.save(mapped);
        return true;
    }

    public boolean login(LoginUserDTO loginData) {
        Optional<User> byUsername = userRepository.findByUsername(loginData.getUsername());

        if (byUsername.isEmpty()) {
            return false;
        }

        User user = byUsername.get();
        if (!passwordEncoder.matches(loginData.getPassword(), user.getPassword())) {
            return false;
        }
        userSession.login(user);
        return true;
    }

    public void logout() {
        userSession.logout();
    }
}
