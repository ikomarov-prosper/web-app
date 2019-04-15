package com.prosper.dto;

import com.prosper.config.ApplicationConfiguration;
import com.prosper.enums.CellStatus;

public interface Cell {

    int getRow();
    int getCol();
    Cell setRow(int row);
    Cell setCol(int col);
    Cell setStatus(CellStatus cellStatus);
    String getAnswer();
    String getQuestion();
}
