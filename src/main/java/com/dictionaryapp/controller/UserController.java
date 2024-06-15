package com.dictionaryapp.controller;

import com.dictionaryapp.model.dto.RegisterUserDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @GetMapping("/register")
    public String viewRegister() {
        return "register";
    }
    @GetMapping("/login")
    public String doLogin(){
        return "login";
    }

    @ PostMapping("/register")
    public String doRegister(@Valid RegisterUserDTO data, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "redirect:/register";
        }

        return "redirect:/login";
    }
}
