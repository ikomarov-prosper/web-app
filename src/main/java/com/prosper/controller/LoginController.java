package com.prosper.controller;

import com.prosper.model.Application;
import com.prosper.model.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Log4j2
@ControllerAdvice
public class LoginController {

    @Autowired
    private Application application;

    @Autowired
    private User user;

    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    public String login(@ModelAttribute("user") User user, ModelMap model, HttpSession session, HttpServletRequest request) throws ServletException {
        request.getSession();
        session.setMaxInactiveInterval(10);
        this.user.setName(user.getName());
        session.setAttribute(User.class.getSimpleName(), new User(this.user));
        return "main";
    }
}
