package com.prosper.model;

import lombok.Data;

import java.util.Random;

/**
 * Created by Igor  08/04/2019
 */
@Data
public class Cell {

    private int col;
    private int row;
    private CellStatus status;
    private String question;
    private String answer;

    private final int a = new Random().nextInt(10);
    private final int b = new Random().nextInt(10);

    public enum CellStatus {
        RESOLVED,
        FAILED,
        IN_PROGRESS,
        NOT_STARTED;
    }

    public Cell(int row, int col, CellStatus status) {
        this.row = row;
        this.col = col;
        this.status = status;
    }

    public  String getAnswer() {
        return answer = String.valueOf(a + b);
    }

    public String getQuestion() {
        return question = "" + a + " + " +  b  + "  = ?";
    }

    @Override
    public String toString() {
        return String.format("[%d, %d]", row, col);
    }

}
