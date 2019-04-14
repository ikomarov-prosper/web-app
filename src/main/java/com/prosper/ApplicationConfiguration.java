package com.prosper;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * Created by Igor  13/04/2019
 */
@Data
@Component
public class ApplicationConfiguration {

    private Integer sessionMaxInactiveIntervalInMilliseconds = 60000;
    private Integer answerTimeInMilliseconds = 10000;
}
