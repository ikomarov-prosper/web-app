package com.prosper.enums;

public enum Complexity {
    EASY(1, 10),
    NORMAL(10, 20),
    HARD(20, 50),
    VERY_HARD(100, 200);

    private int lowerBound;
    private int upperBound;

    Complexity(int lowerBound, int upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    public int getLowerBound() {
        return lowerBound;
    }

    public int getUpperBound() {
        return upperBound;
    }
}
