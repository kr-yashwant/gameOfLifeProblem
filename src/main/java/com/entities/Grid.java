package com.entities;

import java.util.*;

public class Grid {
    private Set<Cell> cells;
    private Queue<Cell> cellsToSow;
    private Queue<Cell> cellsToKill;
    private Map<Cell, Integer> neighbourCountTable;

    public Grid(Set<Cell> cells) {
        this.cells = cells;
        this.cellsToSow = new LinkedList<>();
        this.cellsToKill = new LinkedList<>();
        this.neighbourCountTable = new HashMap<>();
        this.cells.forEach(this::countDeadNeighbours);
    }

    public void performNextStep() {
        markCellsToBeSownInNextStep();
        markCellsToDieInNextStep();
        this.cellsToKill.forEach(this.cells::remove);
        this.cells.addAll(this.cellsToSow);
    }

    public boolean containsAliveCellAt(int xCoordinate, int yCoordinate) {
        Cell cell = new Cell(xCoordinate, yCoordinate);
        return this.cells.contains(cell);
    }

    private void markCellsToDieInNextStep() {
        this.cells.stream()
                .filter(it -> {
                    long countNeighbours = countNeighbours(it);
                    return countNeighbours < 2 || countNeighbours > 3;
                })
                .forEach(this.cellsToKill::offer);
    }

    private void markCellsToBeSownInNextStep() {
        this.neighbourCountTable.keySet().stream()
                .filter(it -> neighbourCountTable.get(it) == 3)
                .forEach(this.cellsToSow::offer);
    }

    private long countNeighbours(Cell cell) {
        return cell.getAllNeighbours().filter((it) ->
                this.containsAliveCellAt(it.getXCoordinate(), it.getYCoordinate())
        ).count();
    }

    private void countDeadNeighbours(Cell cell) {
        cell.getAllNeighbours().forEach((it) -> {
            if (!this.cells.contains(it)) {
                if (this.neighbourCountTable.containsKey(it)) {
                    Integer presentCount = this.neighbourCountTable.get(it);
                    this.neighbourCountTable.put(it, presentCount + 1);
                } else {
                    this.neighbourCountTable.put(it, 1);
                }
            }
        });
    }
}
