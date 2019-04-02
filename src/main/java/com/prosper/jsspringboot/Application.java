package com.prosper.jsspringboot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prosper.dto.AppData;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalTime;
import java.util.function.Function;

/**
 * Created by Igor  29/03/2019
 */
@RestController
@Log4j2
public class Application {

    @Autowired
    private AppData appData;

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void updateData(@RequestBody AppData updatedAppData) {
        if(appData.getPyramidSymbol() != updatedAppData.getPyramidSymbol() || appData.getPyramidHeigh() != updatedAppData.getPyramidHeigh()) {
            appData.setTrigger(appData.getTrigger() ? false : true);
        }

        log.info("Trigger : {}", appData.getTrigger());
        appData.setPyramidHeigh(updatedAppData.getPyramidHeigh());
        appData.setPyramidSymbol(updatedAppData.getPyramidSymbol());
        log.info("updateData : {}, {}", updatedAppData.getPyramidHeigh(), updatedAppData.getPyramidSymbol());

    }

    @RequestMapping(value = "/get")
    public AppData getData() {
        log.info("getData : {}, {}", appData.getPyramidHeigh(), appData.getPyramidSymbol());
        return appData;
    }

    @GetMapping(path = "/events/subscribe", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamFlux() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(new Function<Long, String>() {
                    @Override
                    public String apply(Long sequence) {

                        String  jsonData = null;
                        try {
                            jsonData = new ObjectMapper().writeValueAsString(appData);
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }
                        return jsonData;
                    }
                });
    }

}
