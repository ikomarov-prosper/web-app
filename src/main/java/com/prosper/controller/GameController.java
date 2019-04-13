package com.prosper.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prosper.model.Application;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.function.Function;

/**
 * Created by Igor  08/04/2019
 */
@Controller
@Log4j2
public class GameController {

    @Autowired
    private Application application;


    @GetMapping(path = "/events/subscribe", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamFlux() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(new Function<Long, String>() {
                    @Override
                    public String apply(Long sequence) {

                        String  jsonData = null;
                        try {
                            jsonData = new ObjectMapper().writeValueAsString(application);
                            log.info("Users list sent to frontend: " + application.getUserList());
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }
                        return jsonData;
                    }
                });
    }
}
