package com.prosper.jsspringboot;

import com.prosper.dto.AppData;
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
public class Application {

    @Autowired
    private AppData appData;

    private static Boolean trigger = false;

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void updateData(@RequestBody AppData updatedAppData) {
        if(appData.getPyramidSymbol() != updatedAppData.getPyramidSymbol() || appData.getPyramidHeigh() != updatedAppData.getPyramidHeigh()) {
            trigger = trigger ? false : true;
        }


        System.out.println("Trigger : " + trigger);
        appData.setPyramidHeigh(updatedAppData.getPyramidHeigh());
        appData.setPyramidSymbol(updatedAppData.getPyramidSymbol());
        System.out.println("UPDATED with " + updatedAppData.getPyramidHeigh() + updatedAppData.getPyramidSymbol());

    }

    @RequestMapping(value = "/get")
    public AppData getData() {
        System.out.println("GET method : " + appData.getPyramidHeigh() + appData.getPyramidSymbol());
        return appData;
    }

    @GetMapping(path = "/events/subscribe", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamFlux() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(new Function<Long, String>() {
                    @Override
                    public String apply(Long sequence) {
                        return "Flux - " + LocalTime.now().toString() + " : " + trigger;
                    }
                });
    }

}
