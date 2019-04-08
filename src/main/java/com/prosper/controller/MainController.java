package com.prosper.controller;

import com.prosper.model.Application;
import com.prosper.model.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@Log4j2
public class MainController {

    @Autowired
    private Application application;

    @Autowired
    private User user;

    @GetMapping(value = {"/"})
    public String main(ModelMap model) {
        model.addAttribute("user", user);
        model.addAttribute("application", application);
        return "login";
    }


    @GetMapping(value = "/get")
    public String getData(Model model) {
        model.addAttribute("application", application);
        model.addAttribute("user", user);
        log.info("List of users : {}", application.getUserList());
        return "main";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String answerSubmit(@ModelAttribute("user") User user, Model model, HttpSession session) {
        this.user.setAnswer(user.getAnswer());
        model.addAttribute("application", application);
        session.setAttribute("user", new User(this.user));
        log.info("answerSubmit : {}", user.getAnswer());
        return "main";
    }
}
