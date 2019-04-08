package com.prosper.model;

import lombok.Data;

/**
 * Created by Igor  08/04/2019
 */
@Data
public class Cell {

    private int col;
    private int row;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public String toString() {
        return String.format("[%d, %d]", row, col);
    }
}
