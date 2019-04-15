package com.prosper.dto;

import com.prosper.config.ApplicationConfiguration;
import com.prosper.enums.CellStatus;
import com.prosper.enums.Complexity;

public interface Cell {

    int getRow();
    int getCol();
    Cell setRow(int row);
    Cell setCol(int col);
    Cell setStatus(CellStatus cellStatus);
    Cell setComplexity(Complexity complexity);
    String getAnswer();
    String getQuestion();
    Cell fill();
}
