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

    @JsonProperty("tableSize")
    private Integer tableSize = 1;
}
