package com.dictionaryapp.controller;

import com.dictionaryapp.model.dto.RegisterUserDTO;
import com.dictionaryapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("registerData")
    public RegisterUserDTO emptyUserDTO(){
        return new RegisterUserDTO();
    }

    @GetMapping("/register")
    public String viewRegister() {

        return "register";
    }
    @GetMapping("/login")
    public String doLogin(){
        return "login";
    }

    @ PostMapping("/register")
    public String doRegister(@Valid RegisterUserDTO data, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("registerData", data);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerData", bindingResult);
            return "redirect:/register";
        }

        return "redirect:/login";
    }
}
