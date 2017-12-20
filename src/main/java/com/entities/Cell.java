package com.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Cell {
    private int xCoordinate;
    private int yCoordinate;
    private List<Cell> neighbouringCells;

    public Cell(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.neighbouringCells = new ArrayList<>();
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public Stream<Cell> getAllNeighbours() {
        if (this.neighbouringCells.isEmpty()) addNeighbours();
        return this.neighbouringCells.stream();
    }

    private void addNeighbours() {
        this.neighbouringCells.add(new Cell(this.xCoordinate - 1, this.yCoordinate - 1));
        this.neighbouringCells.add(new Cell(this.xCoordinate - 1, this.yCoordinate));
        this.neighbouringCells.add(new Cell(this.xCoordinate - 1, this.yCoordinate + 1));
        this.neighbouringCells.add(new Cell(this.xCoordinate, this.yCoordinate - 1));
        this.neighbouringCells.add(new Cell(this.xCoordinate, this.yCoordinate + 1));
        this.neighbouringCells.add(new Cell(this.xCoordinate + 1, this.yCoordinate - 1));
        this.neighbouringCells.add(new Cell(this.xCoordinate + 1, this.yCoordinate));
        this.neighbouringCells.add(new Cell(this.xCoordinate + 1, this.yCoordinate + 1));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return xCoordinate == cell.xCoordinate &&
                yCoordinate == cell.yCoordinate;
    }

    @Override
    public int hashCode() {

        return Objects.hash(xCoordinate, yCoordinate);
    }
}
