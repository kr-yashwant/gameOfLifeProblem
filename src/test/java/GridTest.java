import com.entities.Cell;
import com.entities.Grid;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GridTest {

    @Test
    public void verifyCellsAreAliveAfterSowing() {
        Cell firstCell = new Cell(1,1);
        Cell secondCell = new Cell(2,3);
        Cell thirdCell = new Cell(4,5);
        Cell fourthCell = new Cell(6,7);
        Cell fifthCell = new Cell(8,9);
        Set<Cell> seed = new HashSet<>();
        seed.add(firstCell);
        seed.add(secondCell);
        seed.add(thirdCell);
        seed.add(fourthCell);
        seed.add(fifthCell);

        Grid grid = new Grid(seed);

        assertTrue(grid.containsAliveCellAt(firstCell.getXCoordinate(),firstCell.getYCoordinate()));
        assertTrue(grid.containsAliveCellAt(secondCell.getXCoordinate(),secondCell.getYCoordinate()));
        assertTrue(grid.containsAliveCellAt(thirdCell.getXCoordinate(),thirdCell.getYCoordinate()));
        assertTrue(grid.containsAliveCellAt(fourthCell.getXCoordinate(),fourthCell.getYCoordinate()));
        assertTrue(grid.containsAliveCellAt(fifthCell.getXCoordinate(),fifthCell.getYCoordinate()));
    }

    @Test
    public void verifyCellsThatAreNotSownAreDead() {
        Cell firstCell = new Cell(1,1);
        Cell secondCell = new Cell(2,3);
        Cell thirdCell = new Cell(4,5);
        Cell fourthCell = new Cell(6,7);
        Cell fifthCell = new Cell(8,9);
        Set<Cell> seed = new HashSet<>();

        Grid grid = new Grid(seed);

        assertFalse(grid.containsAliveCellAt(firstCell.getXCoordinate(),firstCell.getYCoordinate()));
        assertFalse(grid.containsAliveCellAt(secondCell.getXCoordinate(),secondCell.getYCoordinate()));
        assertFalse(grid.containsAliveCellAt(thirdCell.getXCoordinate(),thirdCell.getYCoordinate()));
        assertFalse(grid.containsAliveCellAt(fourthCell.getXCoordinate(),fourthCell.getYCoordinate()));
        assertFalse(grid.containsAliveCellAt(fifthCell.getXCoordinate(),fifthCell.getYCoordinate()));
    }

    @Test
    public void verifyThatAliveCellWithLessThanTwoNeighboursDiesAfterNextStep() {
        Set<Cell> seed = new HashSet<>();
        seed.add(new Cell(1, 1));
        seed.add(new Cell(5,5));
        seed.add(new Cell(4,5));
        Grid grid = new Grid(seed);

        grid.performNextStep();

        assertFalse(grid.containsAliveCellAt(1,1));
        assertFalse(grid.containsAliveCellAt(5,5));
    }

    @Test
    public void verifyThatAliveCellWithMoreThanThreeNeighboursDiesAfterNextStep() {
        Set<Cell> seed = new HashSet<>();
        seed.add(new Cell(1, 1));
        seed.add(new Cell(1,2));
        seed.add(new Cell(1,0));
        seed.add(new Cell(0,1));
        seed.add(new Cell(2,1));
        Grid grid = new Grid(seed);

        grid.performNextStep();

        assertFalse(grid.containsAliveCellAt(1,1));
    }

    @Test
    public void verifyThatAliveCellWithExactThreeNeighboursStaysAliveAfterNextStep() {
        Set<Cell> seed = new HashSet<>();
        seed.add(new Cell(1, 1));
        seed.add(new Cell(1,2));
        seed.add(new Cell(0,1));
        seed.add(new Cell(2,1));
        Grid grid = new Grid(seed);

        grid.performNextStep();

        assertTrue(grid.containsAliveCellAt(1,1));
    }

    @Test
    public void verifyThatDeadCellWithExactThreeAliveNeighboursComesToLifeInNextStep() {
        Set<Cell> seed = new HashSet<>();
        seed.add(new Cell(1,2));
        seed.add(new Cell(0,1));
        seed.add(new Cell(2,1));
        Grid grid = new Grid(seed);

        grid.performNextStep();

        assertTrue(grid.containsAliveCellAt(1,1));
    }
}
