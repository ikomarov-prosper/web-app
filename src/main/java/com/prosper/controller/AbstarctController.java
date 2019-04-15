package com.prosper.controller;

import com.prosper.model.ApplicationModel;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@Log4j2
@ControllerAdvice
public class AbstarctController {

    @Autowired
    private ApplicationModel applicationModel;

    @ModelAttribute
    public void addAttributes(ModelMap model) {
        model.addAttribute(ApplicationModel.class.getSimpleName(), applicationModel);
    }
}
