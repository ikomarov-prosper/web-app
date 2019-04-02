package com.prosper.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * Created by Igor  29/03/2019
 */
@Component
@Data
public class AppData {

    @JsonProperty("pyramidHeigh")
    private Integer pyramidHeigh = 5;

    @JsonProperty("pyramidSymbol")
    private String  pyramidSymbol = "$";
}
