package com.prosper.controller;

import com.prosper.model.Application;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@Log4j2
@ControllerAdvice
public class AbstarctController {

    @Autowired
    private Application application;

//    @Autowired
//    private User user;

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute(Application.class.getSimpleName(), application);
    }
}
