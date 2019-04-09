package com.prosper.controller;

import com.prosper.model.Application;
import com.prosper.model.Cell;
import com.prosper.model.Cell.CellStatus;
import com.prosper.model.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

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
    public String restart() {
        log.info("Restarting game....");
        application.getTable().update();
        return "main";
    }

    @GetMapping(value = "/getNextCell")
    public String getNextRandomCell() {

        Boolean flag = null;
        do {
            Cell activeCell = application.getTable().getNextRandomCell();
            log.info("Next random cell : {}", activeCell);

            if(activeCell!=null) {
                flag = true;
            }
            else {
                flag = false;
                return "main";
            }

            Thread thread = new Thread(){
                public void run(){
                    try {
                        Thread.sleep(6000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    try {
                        for (User user : application.getUserList()) {
                            if (null == user.getAnswer()) {
                                activeCell.setStatus(CellStatus.FAILED);
                                return;
                            }
                            if (!user.getAnswer().equalsIgnoreCase(activeCell.getAnswer())) {
                                activeCell.setStatus(CellStatus.FAILED);
                                return;
                            }
                        }
                        activeCell.setStatus(CellStatus.RESOLVED);
                    }
                    finally {
                        for (User user : application.getUserList()) {
                            user.setAnswer(null);
                        }
                    }
                }
            };

            thread.start();

            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        while (flag);
        return "main";
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String answerSubmit(@ModelAttribute("User") User user, Model model, HttpSession session) {
        this.user.setAnswer(user.getAnswer());
        session.setAttribute(User.class.getSimpleName(), new User(this.user));
        log.info("answerSubmit : {}", user.getAnswer());
        return "main";
    }
}
