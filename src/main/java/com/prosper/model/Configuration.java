package com.prosper.model;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * Created by Igor  13/04/2019
 */
@Data
@Component
public class Configuration {

    private Integer sessionMaxInactiveIntervalInMilliseconds = 60000;
}
