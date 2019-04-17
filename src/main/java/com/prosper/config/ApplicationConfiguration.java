package com.prosper.config;

import com.prosper.enums.CellType;
import com.prosper.enums.Complexity;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Igor  13/04/2019
 */
@Data
@Component
public class ApplicationConfiguration {

    private Integer sessionMaxInactiveIntervalInMilliseconds = 60000;
    private Integer answerTimeInMilliseconds = 10000;
    private Integer rowSize = 3;
    private Integer colSize = 3;
    private List<CellType> expectedCellTypes = new ArrayList<>(Arrays.asList(CellType.ADDITION, CellType.SUBTRACTION));
    private Complexity complexity = Complexity.NORMAL;
}
