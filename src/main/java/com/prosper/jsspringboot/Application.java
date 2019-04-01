package com.prosper.jsspringboot;

import com.prosper.dto.AppData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Igor  29/03/2019
 */
@RestController
public class Application {

    @Autowired
    private AppData appData;

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void updateData(@RequestBody AppData updatedAppData) {
        appData.setPyramidHeigh(updatedAppData.getPyramidHeigh());
        appData.setPyramidSymbol(updatedAppData.getPyramidSymbol());
        System.out.println("UPDATED with " + updatedAppData.getPyramidHeigh() + updatedAppData.getPyramidSymbol());
    }

    @RequestMapping(value = "/get")
    @ResponseBody
    public AppData getData() {
        return appData;
    }

}
