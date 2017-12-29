package com.logic;

public class LifeDeathLogicUtil {
    public static boolean shouldStarve(long countOfAliveNeighbours) {
        return countOfAliveNeighbours < 2;
    }

    public static boolean shouldSuffocate(long countOfAliveNeighbours) {
        return countOfAliveNeighbours > 3;
    }

    public static boolean shouldCellBeSown(long countOfAliveNeighbours) {
        return countOfAliveNeighbours == 3;
    }
}
