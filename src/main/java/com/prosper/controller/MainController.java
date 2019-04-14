package com.prosper.controller;

import com.prosper.dto.Cell;
import com.prosper.dto.Cell.CellStatus;
import com.prosper.dto.User;
import com.prosper.model.ApplicationModel;
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
import java.util.Objects;

@Controller
@Log4j2
@ControllerAdvice
public class MainController {

    @Autowired
    private ApplicationModel applicationModel;

    @GetMapping(value = {"/"})
    public String main() {
        return "login";
    }

    @GetMapping(value = "/get")
    public String getData() {
        log.info("List of users : {}", applicationModel.getUserList());
        return "main";
    }

    @GetMapping(value = "/restart")
    public String restart() {
        log.info("Restarting game....");
        applicationModel.getTable().update();
        log.info("Restarting game. Users " + applicationModel.getUserList());
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

    private Cell getNextRandomCell() {
        Cell activeCell = applicationModel.getTable().getNextRandomCell();
        log.info("Next random cell : {}", activeCell);
        if (activeCell == null) {
            return null;
        }
        try {
            Thread.sleep(applicationModel.getApplicationConfiguration().getAnswerTimeInMilliseconds());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<User> userList = new ArrayList<>(applicationModel.getUserList());
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
            for (User user : applicationModel.getUserList()) {
                if (user != null) {
                    user.setAnswer(null);
                }
            }
        }
        return activeCell;
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String answerSubmit(@RequestParam("answer") String answer, HttpServletRequest request) {
        log.info("answerSubmit : {}", answer);

        HttpSession session = request.getSession(false);
        if (Objects.nonNull(session)) {
            Object sessionUserAttribute = session.getAttribute(User.class.getSimpleName());
            if (Objects.nonNull(sessionUserAttribute)) {
                User attribute = (User) sessionUserAttribute;
                attribute.setAnswer(answer);
            }
            else {
                log.warn("Session \"{}\" has no user attribute", session.getId());
            }
        }
        return "main";
    }
}
