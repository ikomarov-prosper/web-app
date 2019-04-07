package com.prosper.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * Created by Igor  29/03/2019
 */
@Component
@Data
//component will be created for every session
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AppData {

    @JsonProperty("tableSize")
    private Integer tableSize = 1;

    @JsonProperty("answer")
    private String answer;
}
