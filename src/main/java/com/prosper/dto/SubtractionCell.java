package com.prosper.dto;

import com.prosper.enums.CellStatus;
import com.prosper.enums.Complexity;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Objects;
import java.util.Random;

@Data
@Accessors(chain = true)
public class SubtractionCell implements Cell {

    private int col;
    private int row;
    private CellStatus status;
    private Complexity complexity;
    private int a;
    private int b;

    public SubtractionCell() {
    }

    public SubtractionCell(int row, int col, CellStatus status, Complexity complexity) {
        this.row = row;
        this.col = col;
        this.status = status;
        this.complexity = complexity;
    }

    public  String getAnswer() {
        return String.valueOf(a - b);
    }

    public String getQuestion() {
        return "" + a + " - " +  b  + "  = ?";
    }

    @Override
    public Cell fill() {
        assert Objects.nonNull(complexity);
        a = new Random().nextInt(complexity.getUpperBound()) + complexity.getLowerBound();
        b = new Random().nextInt(complexity.getUpperBound()) + complexity.getLowerBound();
        return this;
    }

    @Override
    public String toString() {
        return String.format("[%d, %d]", row, col);
    }
}
