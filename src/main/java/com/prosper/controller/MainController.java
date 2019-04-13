package com.prosper.controller;

import com.prosper.model.Application;
import com.prosper.model.Cell;
import com.prosper.model.Cell.CellStatus;
import com.prosper.model.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@Log4j2
@ControllerAdvice
public class MainController {

    @Autowired
    private Application application;

    @GetMapping(value = {"/"})
    public String main() {
        return "login";
    }

    @GetMapping(value = "/get")
    public String getData() {
        log.info("List of users : {}", application.getUserList());
        return "main";
    }

    @GetMapping(value = "/restart")
    public String restart() {
        log.info("Restarting game....");
        application.getTable().update();
        log.info("Restarting game. Users " + application.getUserList());
        return "main";
    }

    @GetMapping(value = "/start")
    public String start(HttpServletRequest request) {
        log.info("Starting game....");
        new Thread(() -> {
            while (getNextRandomCell() != null) {
            }
        }).start();
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

        List<User> userList = new ArrayList<>(application.getUserList());
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
        } finally {
            for (User user : application.getUserList()) {
                if (user != null) {
                    user.setAnswer(null);
                }
            }
        }
        return activeCell;
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String answerSubmit(@RequestParam("answer") String answer, HttpSession session) {
        log.info("answerSubmit : {}", answer);

        //TODO: check session is exist
        User attribute = (User) session.getAttribute(User.class.getSimpleName());
        attribute.setAnswer(answer);
        return "main";
    }
}
