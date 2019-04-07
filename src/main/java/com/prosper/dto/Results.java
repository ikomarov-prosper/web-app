package com.prosper.dto;

import org.springframework.stereotype.Component;

import javax.ejb.Stateful;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Igor  07/04/2019
 */
@Component
@Stateful
public class Results {


    private List<User> userList = new ArrayList<>();


    public void add(User user) {
        userList.add(user);
    }

    public List<User> get() {
        return userList;
    }
}
