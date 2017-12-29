package com.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Cell {
    private int xCoordinate;
    private int yCoordinate;
    private List<Cell> neighbouringCells;
    private final int[] range = {-1, 0, 1};

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
        Arrays.stream(range).forEach((rowAddendum) -> Arrays.stream(range).forEach((colAddendum) -> {
            if (rowAddendum != 0 || colAddendum != 0) {
                this.neighbouringCells.add(new Cell(this.xCoordinate + rowAddendum, this.yCoordinate + colAddendum));
            }
        }));
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
