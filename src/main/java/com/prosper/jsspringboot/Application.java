package com.prosper.jsspringboot;

import com.prosper.dto.AppData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
        appData.setTableSize(updatedAppData.getTableSize());
        System.out.println("UPDATED with " + appData.toString());
    }

    @RequestMapping(value = "/get")
    public AppData getData() {
        System.out.println("GET method : " + appData.toString());
        return appData;
    }

}
