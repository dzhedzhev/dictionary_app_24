package com.dictionaryapp.controller;

import com.dictionaryapp.model.dto.RegisterUserDTO;
import com.dictionaryapp.model.dto.LoginUserDTO;
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
    public RegisterUserDTO emptyRegisterDTO(){
        return new RegisterUserDTO();
    }
    @ModelAttribute("loginData")
    public LoginUserDTO emptyLoginDTO(){
        return new LoginUserDTO();
    }

    @GetMapping("/register")
    public String viewRegister() {

        return "register";
    }
    @GetMapping("/login")
    public String viewLogin(){
        return "login";
    }

    @ PostMapping("/register")
    public String doRegister(@Valid RegisterUserDTO data, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors() || !userService.registerUser(data)){
            redirectAttributes.addFlashAttribute("registerData", data);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerData", bindingResult);
            return "redirect:/register";
        }
        return "redirect:/login";
    }
    @PostMapping("/login")
    public String doLogin(@Valid LoginUserDTO loginData, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("loginData", loginData);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginData", bindingResult);

            return "redirect:/login";
        }
        boolean success = userService.login(loginData);
        if (!success){
            redirectAttributes.addFlashAttribute("loginData", loginData);
            redirectAttributes.addFlashAttribute("userPassMismatch", true);

            return "redirect:/login";
        }
        return "redirect:/home";
    }
    @PostMapping("/logout")
    public String doLogout() {
        userService.logout();
        return "redirect:/";
    }
}
