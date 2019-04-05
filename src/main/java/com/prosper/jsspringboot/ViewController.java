package com.prosper.jsspringboot;

import com.prosper.dto.AppData;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Log4j2
public class ViewController {

    @Autowired
    private AppData appData;

    @GetMapping({"/", "/main"})
    public String main(Model model) {
        model.addAttribute("appData", appData);
        return "main";
    }

    @RequestMapping(value = "/get")
    public AppData getData(Model model) {
        log.info("getData : {}", appData.toString());
        return appData;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String answerSubmit(@ModelAttribute("appData") AppData appData, Model model) {
        this.appData.setTableSize(appData.getTableSize());
        this.appData.setAnswer(appData.getAnswer());
        model.addAttribute("answer", appData.getAnswer());
        model.addAttribute("tableSize", appData.getTableSize());
        log.info("answerSubmit : {}", appData.toString());
        return "main";
    }
}
