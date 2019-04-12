package com.prosper.controller;

import com.prosper.model.Application;
import com.prosper.model.Cell;
import com.prosper.model.Cell.CellStatus;
import com.prosper.model.User;
import lombok.extern.log4j.Log4j2;
import org.apache.catalina.session.StandardSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@Log4j2
@ControllerAdvice
public class MainController {

    @Autowired
    private Application application;

    @Autowired
    private User user;

    @GetMapping(value = {"/"})
    public String main(ModelMap model) {
        return "login";
    }

    @GetMapping(value = "/get")
    public String getData(Model model) {
        log.info("List of users : {}", application.getUserList());
        return "main";
    }

    @GetMapping(value = "/restart")
    public String restart(HttpSession session) {
        log.info("Restarting game....");
        application.getTable().update();
        session.setAttribute(User.class.getSimpleName(), new User(this.user));
        log.info("Restarting game. Users " + application.getUserList());
        return "main";
    }

    @GetMapping(value = "/start")
    public String start(HttpServletRequest request) {
        log.info("Starting game....");
        request.getSession().setAttribute(User.class.getSimpleName(), new User(this.user));

        new Thread() {
            public void run() {

                while (getNextRandomCell() != null)

                {
//            if (request.getRequestedSessionId() != null
//                    && !request.isRequestedSessionIdValid()) {
//                log.info("Session is expired");
//                return "login";
//            }

                }
            }
        }.start();

        return "main";
    }

public Cell getNextRandomCell() {


    Cell activeCell = application.getTable().getNextRandomCell();
    log.info("Next random cell : {}", activeCell);
    if (activeCell == null) {
        return null;
    }
    try {
        Thread.sleep(6000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }


    application.getUserList().removeAll(Collections.singleton(null));//TODO: Why doe we have null users?
    List<User> userList = new ArrayList<>(application.getUserList());
    userList.removeAll(Collections.singleton(null));//TODO: Why doe we have null users?

    try {

        if (userList.isEmpty()) {
            activeCell.setStatus(CellStatus.FAILED);
            return activeCell;
        }

        for (User user : userList) {
                if (null == user.getAnswer()) {
                    activeCell.setStatus(CellStatus.FAILED);
                    return activeCell;
                }
                if (!user.getAnswer().equalsIgnoreCase(activeCell.getAnswer())) {
                    activeCell.setStatus(CellStatus.FAILED);
                    return activeCell;
                }
        }
        activeCell.setStatus(CellStatus.RESOLVED);


    }
    finally {
            for (User user : application.getUserList()) {
                if(user !=null) {
                    user.setAnswer(null);
                }
            }
    }


    return activeCell;
}


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String answerSubmit(@ModelAttribute("User") User user, Model model, HttpSession session) {
        log.info("answerSubmit : {}", user.getAnswer());
        this.user.setAnswer(user.getAnswer());
        session.setAttribute(User.class.getSimpleName(), new User(this.user));
        log.info("answerSubmit : {}", user.getAnswer());
        return "main";
    }
}
