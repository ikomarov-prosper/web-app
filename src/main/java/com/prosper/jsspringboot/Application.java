package com.prosper.jsspringboot;

import com.prosper.dto.AppData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Igor  29/03/2019
 */
@Controller
public class Application {

    @Autowired
    private AppData appData;

    @GetMapping({"/", "/hello"})
    public String hello(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
        model.addAttribute("name", name);
        return "hello";
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void updateData(@RequestBody AppData updatedAppData) {
        appData.setPyramidHeigh(updatedAppData.getPyramidHeigh());
        appData.setPyramidSymbol(updatedAppData.getPyramidSymbol());
        System.out.println("UPDATED with " + updatedAppData.getPyramidHeigh() + updatedAppData.getPyramidSymbol());
    }

    @RequestMapping(value = "/get")
    public AppData getData() {
        System.out.println("GET method : " + appData.getPyramidHeigh() + appData.getPyramidSymbol());
        return appData;
    }

}
