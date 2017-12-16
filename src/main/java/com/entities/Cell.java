package com.entities;

public class Cell {
    private CellState state;

    public Cell() {
        this.state = CellState.DEAD;
    }

    public void switchState() {
        this.state = CellState.DEAD.equals(this.state)?CellState.ALIVE:CellState.DEAD;
    }

    public boolean isAlive() {
        return this.state.equals(CellState.ALIVE);
    }
}
