package com.prosper.dto;

import com.prosper.enums.CellStatus;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Random;

@Data
@Accessors(chain = true)
public class SubtractionCell implements Cell {

    private int col;
    private int row;
    private CellStatus status;
    private String question;
    private String answer;

    private final int a = new Random().nextInt(10);
    private final int b = new Random().nextInt(10);

    public SubtractionCell() {
    }

    public SubtractionCell(int row, int col, CellStatus status) {
        this.row = row;
        this.col = col;
        this.status = status;
    }

    public  String getAnswer() {
        return answer = String.valueOf(a - b);
    }

    public String getQuestion() {
        return question = "" + a + " - " +  b  + "  = ?";
    }

    @Override
    public String toString() {
        return String.format("[%d, %d]", row, col);
    }
}
