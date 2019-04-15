package com.prosper.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prosper.config.ApplicationConfiguration;
import com.prosper.config.HttpSessionConfig;
import com.prosper.dto.Table;
import com.prosper.dto.User;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

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
public class ApplicationModel {

    @Autowired
    private ApplicationConfiguration applicationConfiguration;

    @Autowired
    private Table table;

    public String getUsersInJson() {
        try {
           return new ObjectMapper().writeValueAsString(getUserList()).replaceAll("[\"]","\\\\\"");
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public List<User> getUserList() {
        List<User> users = new ArrayList<>();
        for (HttpSession session : HttpSessionConfig.getSessions().values()) {
            Object sessionAttribute = session.getAttribute(User.class.getSimpleName());
            if(sessionAttribute != null) {
                users.add((User) sessionAttribute);
            }
        }
        log.info("User list: {}", users);
        return users;
    }
}
