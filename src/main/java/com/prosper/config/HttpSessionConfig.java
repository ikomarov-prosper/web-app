package com.prosper.config;


import lombok.extern.log4j.Log4j2;
import org.apache.catalina.session.StandardSession;
import org.apache.catalina.session.StandardSessionFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@Log4j2
public class HttpSessionConfig {

    private static final Map<String, HttpSession> sessions;
    static {
        sessions = new HashMap<>();
    }

    public static Map<String, HttpSession> getSessions() {
        return sessions;
    }

    public static void addSession(HttpSession session) {
        sessions.put(session.getId(), session);
    }

    public List<HttpSession> getActiveSessions() {
        return new ArrayList<>(sessions.values());
    }

    @Bean
    public HttpSessionListener httpSessionListener() {
        return new HttpSessionListener() {
            @Override
            public void sessionCreated(HttpSessionEvent hse) {
                log.info("New session has been created: {}", hse.getSession().getId());
                sessions.put(hse.getSession().getId(), hse.getSession());
            }

            @Override
            public void sessionDestroyed(HttpSessionEvent hse) {
                log.info("Session has been destroyed: {}", hse.getSession().getId());
                //hse.getSession().invalidate();
                sessions.remove(hse.getSession().getId());
            }
        };
    }
}
