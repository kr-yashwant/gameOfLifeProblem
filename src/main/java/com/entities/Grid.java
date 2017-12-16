package com.entities;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;

public class Grid {
    private int width, height;
    private Cell[][] cells;
    private Queue<Cell> getCellsIdentifiedChangingStateInNextTick;

    public Grid(int width, int height) {
        this.width = width; this.height = height;
        cells = new Cell[width][height];
        for (int xCoordinate = 0; xCoordinate<this.width; xCoordinate++) {
            for (int yCoordinate = 0; yCoordinate<this.height; yCoordinate++) {
                this.cells[xCoordinate][yCoordinate] = new Cell();
            }
        }
        this.getCellsIdentifiedChangingStateInNextTick = new LinkedList<>();
    }

    public void tick() {
        this.identifyCellsForChangingStateInNextTick();
        this.changeStatesForIdentifiedCells();
    }

    private void changeStatesForIdentifiedCells() {
        this.getCellsIdentifiedChangingStateInNextTick.forEach(Cell::switchState);
        this.getCellsIdentifiedChangingStateInNextTick.clear();
    }

    private void identifyCellsForChangingStateInNextTick() {
        for(int xCoordinate = 0; xCoordinate<this.width; xCoordinate++) {
            for(int yCoordinate = 0; yCoordinate<this.height; yCoordinate++) {
                Cell it = this.cells[xCoordinate][yCoordinate];
                int countNeighbours = countNeighbours(xCoordinate, yCoordinate);

                if( it.isAlive()  && countNeighbours < 2 ) this.getCellsIdentifiedChangingStateInNextTick.add(it);
                if( it.isAlive()  && countNeighbours > 3 ) this.getCellsIdentifiedChangingStateInNextTick.add(it);
                if(!it.isAlive()  && countNeighbours ==3 ) this.getCellsIdentifiedChangingStateInNextTick.add(it);

            }
        }
    }

    public void sow(Cell cell, int xCoordinate, int yCoordinate) {
        this.cells[xCoordinate][yCoordinate] = cell;
        cell.switchState();
    }

    private int countNeighbours(int xCoordinate, int yCoordinate) {
        int countOfAliveNeighbours = 0;

        for(int neighbourXCoordinate = xCoordinate-1; neighbourXCoordinate<=xCoordinate+1; neighbourXCoordinate++) {
            for(int neighbourYCoordinate = yCoordinate-1; neighbourYCoordinate<=yCoordinate+1; neighbourYCoordinate++) {
                if(neighbourXCoordinate > -1 && neighbourYCoordinate > -1
                        && !(neighbourXCoordinate == xCoordinate && neighbourYCoordinate == yCoordinate)
                        && neighbourXCoordinate < this.width && neighbourYCoordinate < height
                        && this.cells[neighbourXCoordinate][neighbourYCoordinate].isAlive()) {
                    countOfAliveNeighbours++;
                }
            }
        }
        return countOfAliveNeighbours;
    }
}
