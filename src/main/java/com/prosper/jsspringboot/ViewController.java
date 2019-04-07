package com.prosper.jsspringboot;

import com.prosper.dto.AppData;
import com.prosper.dto.Results;
import com.prosper.dto.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.session.SessionRegistry;
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
//@Scope("session")
public class ViewController {

    @Autowired
    @Qualifier("sessionRegistry")
    private SessionRegistry sessionRegistry;

    @Autowired
    private Results results;

    @Autowired
    private AppData appData;

    @Autowired
    private User user;

    @GetMapping(value = {"/"})
    public String main(ModelMap model) {
        model.addAttribute("user", user);
        model.addAttribute("appData", appData);
        return "login";
    }
    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    public String main(@ModelAttribute("user") User user, ModelMap model, HttpSession session) {
        model.addAttribute("user", user);
        model.addAttribute("appData", appData);
        session.setAttribute("user", user);
        this.user.setName(user.getName());
        results.add(user);
        return "main";
    }

    @GetMapping(value = "/get")
    public String getData(Model model) {
        log.info("getData : {}", appData.toString());
        log.info("model : {}", user.toString());
        model.addAttribute("appData", appData);

        List<Object> principals = sessionRegistry.getAllPrincipals();

        List<String> usersNamesList = new ArrayList<String>();

        for (Object principal: principals) {
            if (principal instanceof User) {
                usersNamesList.add(((User) principal).getName());
            }
        }
        log.info("List of all principals : {}", principals);
        log.info("List of users : {}", usersNamesList);
        log.info("List of users : {}", results.get());


        return "main";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String answerSubmit(@ModelAttribute("appData") AppData appData, Model model) {
        this.appData.setTableSize(appData.getTableSize());
        this.appData.setAnswer(appData.getAnswer());
        log.info("answerSubmit : {}", appData.toString());
        return "main";
    }
}
