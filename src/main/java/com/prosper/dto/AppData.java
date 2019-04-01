package com.prosper.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

/**
 * Created by Igor  29/03/2019
 */
@Component
public class AppData {


    @JsonProperty("pyramidHeigh")
    private Integer pyramidHeigh;

    @JsonProperty("pyramidSymbol")
    private String  pyramidSymbol;

    public Integer getPyramidHeigh() {
        return pyramidHeigh;
    }

    public void setPyramidHeigh(Integer pyramidHeigh) {
        this.pyramidHeigh = pyramidHeigh;
    }

    public String getPyramidSymbol() {
        return pyramidSymbol;
    }

    public void setPyramidSymbol(String pyramidSymbol) {
        this.pyramidSymbol = pyramidSymbol;
    }
}
