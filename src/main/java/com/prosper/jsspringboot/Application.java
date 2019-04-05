package com.prosper.jsspringboot;

import com.prosper.dto.AppData;
import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Igor  29/03/2019
 */
@RestController
@Log4j2
public class Application {

    @Autowired
    private AppData appData;

    /*
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void updateData(@RequestBody AppData updatedAppData) {
        appData.setTableSize(updatedAppData.getTableSize());
        appData.setAnswer(updatedAppData.getAnswer());
        log.info("updateData : {}", appData.toString());
    }
*/
//    @RequestMapping(value = "/update", method = RequestMethod.PUT)
//    public void answerSubmit(@ModelAttribute AppData userData) {
//        appData.setTableSize(userData.getTableSize());
//        appData.setAnswer(userData.getAnswer());
//        log.info("answerSubmit : {}", appData.toString());
//    }

//    @RequestMapping(value = "/get")
//    public AppData getData(Model model) {
//        model.addAttribute("appData", new AppData());
//        log.info("getData : {}", appData.toString());
//        return appData;
//    }
}
