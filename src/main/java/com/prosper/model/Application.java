package com.prosper.model;

import com.prosper.HttpSessionConfig;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ejb.Stateful;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Igor  07/04/2019
 */
@Component
@Stateful
@Data
public class Application {

    private int rows = 3;
    private int columns = 3;
    private  int [][] cells = new int[rows][columns];

    public List<User> getUserList() {
        List<User> users = new ArrayList<>();
        for (HttpSession session : HttpSessionConfig.getSessions().values()) {
            users.add((User) session.getAttribute(User.class.getSimpleName()));
        }
        return users;
    }
}
