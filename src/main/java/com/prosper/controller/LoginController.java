package com.prosper.controller;

import com.prosper.config.HttpSessionConfig;
import com.prosper.model.ApplicationModel;
import com.prosper.dto.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Log4j2
@ControllerAdvice
public class LoginController {

    @Autowired
    private ApplicationModel applicationModel;

    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    public String login(@RequestParam("userName") String userName, HttpServletRequest request) throws ServletException {
        HttpSession session = request.getSession(false);
        log.info("Session from login : " + session.getId());
        session.setMaxInactiveInterval(applicationModel.getApplicationConfiguration().getSessionMaxInactiveIntervalInMilliseconds());
        User attrUser = (User) session.getAttribute(User.class.getSimpleName());

        if(attrUser != null) {
            attrUser.setName(userName);
        }
        else {
            session.setAttribute(User.class.getSimpleName(), new User(userName, null));
        }

        return "main";
    }
}
