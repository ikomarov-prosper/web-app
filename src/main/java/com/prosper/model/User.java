package com.prosper.model;

import lombok.Data;

/**
 * Created by Igor  06/04/2019
 */
@Data
public class User {
    private String name;
    private String answer;

    public User() {
    }

    public User(String name, String answer) {
        this.name = name;
        this.answer = answer;
    }

    public User(User user) {
        name = user.getName();
        answer = user.getAnswer();
    }
}
