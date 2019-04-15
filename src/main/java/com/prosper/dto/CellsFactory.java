package com.prosper.dto;

import com.prosper.enums.CellType;

public class CellsFactory {

    public static Cell get(CellType cellType) {
        switch (cellType) {
            case ADDITION:
                return new AdditionCell();
            case SUBTRACTION:
                return new SubtractionCell();
            default:
                return new AdditionCell();
        }
    }
}
