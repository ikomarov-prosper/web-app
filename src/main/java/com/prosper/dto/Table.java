package com.prosper.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prosper.dto.Cell.CellStatus;
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
    private List<Cell> cell;
    private List<Cell> usedCells;
    private Cell activeCell;

    public Table() {
        update();
    }

    public void update() {
        activeCell = new Cell(-1,-1, CellStatus.NOT_STARTED);//TODO
        cell = new ArrayList<>();
        usedCells = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++){
               cell.add(new Cell(i,j, CellStatus.NOT_STARTED));
            }
        }
    }

    @JsonIgnore
    public Cell getCell(int row, int col) {
        return cell.stream().filter(p->p.getRow() == row && p.getCol() == col)
                .findFirst().orElse(null);
    }

    @JsonIgnore
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
        activeCell = nextRandomCell;
        if(null != activeCell) {
            activeCell.setStatus(CellStatus.IN_PROGRESS);
        }
        return nextRandomCell;
    }
}
