package com.dictionaryapp.config;

import com.dictionaryapp.model.entity.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

@Configuration
@SessionScope
public class UserSession {
    private long id;
    private String username;
    public void login(User user){
        this.id = user.getId();
        this.username = user.getUsername();
    }
    public boolean isUserLoggedIn() {
        return id != 0;
    }

    public void logout(){
        this.id = 0;
        this.username = "";
    }

    public String username(){
        return username;
    }
}
