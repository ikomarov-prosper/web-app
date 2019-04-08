package com.prosper.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.ejb.Stateful;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Igor  08/04/2019
 */
@Component
@Data
@Stateful
public class Table {

    private int rows = 3;
    private int columns = 3;
    private List<Cell> cell = new ArrayList<>();
    private List<Cell> usedCells = new ArrayList<>();

    public Table() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++){
               cell.add(new Cell(i,j));
            }
        }
    }

    public Cell getCell(int row, int col) {
        return cell.stream().filter(p->p.getRow() == row && p.getCol() == col)
                .findFirst().orElse(null);
    }

    public Cell getNextRandomCell() {
        List<Cell> unUsedCells = new ArrayList<>();
        unUsedCells.addAll(cell);
        unUsedCells.removeAll(usedCells);
        Collections.shuffle(unUsedCells);
        Cell nextRandomCell = null;
        if (!unUsedCells.isEmpty()) {
            nextRandomCell = unUsedCells.get(0);
            usedCells.add(nextRandomCell);
        }
        return nextRandomCell;
    }
}
