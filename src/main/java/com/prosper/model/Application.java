package com.prosper.model;

import com.prosper.HttpSessionConfig;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ejb.Stateful;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Igor  07/04/2019
 */
@Component
@Stateful
@Data
@Log4j2
public class Application {

    @Autowired
    private Table table;

    public List<User> getUserList() {
        List<User> users = new ArrayList<>();
        for (HttpSession session : HttpSessionConfig.getSessions().values()) {
            Object sessionAttribute = session.getAttribute(User.class.getSimpleName());
            if(sessionAttribute != null) {
                users.add((User) sessionAttribute);
            }
        }
        log.info("User list: " + users);
        return users;
    }
}
