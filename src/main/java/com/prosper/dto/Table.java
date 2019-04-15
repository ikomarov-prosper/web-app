package com.prosper.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prosper.enums.CellStatus;
import com.prosper.enums.CellType;
import com.prosper.enums.Complexity;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Igor  08/04/2019
 */

@Data
public class Table {

    private int rows;
    private int columns;
    private List<Cell> cell;
    private List<Cell> usedCells;
    private Cell activeCell;

    public Table(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.cell = new ArrayList<>();
        this.usedCells = new ArrayList<>();
    }

    public Table empty() {
        cell = new ArrayList<>();
        usedCells = new ArrayList<>();
        return  this;
    }

    public Table fill(List<CellType> cellTypes, Complexity complexity) {
        CellType randomCellType = cellTypes.get(new Random().nextInt(cellTypes.size()));
        activeCell = CellsFactory.get(randomCellType).setCol(-1).setRow(-1).setStatus(CellStatus.NOT_STARTED);//TODO
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                randomCellType = cellTypes.get(new Random().nextInt(cellTypes.size()));
                Cell cell = CellsFactory.get(randomCellType)
                        .setRow(i)
                        .setCol(j)
                        .setStatus(CellStatus.NOT_STARTED)
                        .setComplexity(complexity)
                        .fill();

                this.cell.add(cell);
            }
        }
        return this;
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
