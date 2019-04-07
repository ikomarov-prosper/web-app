package com.prosper.dto;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * Created by Igor  06/04/2019
 */
@Data
@Component
//component will be created for every session
@Scope(value = "session",proxyMode = ScopedProxyMode.TARGET_CLASS)
public class User {
    private String name;
}
