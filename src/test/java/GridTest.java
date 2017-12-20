import com.entities.Cell;
import com.entities.Grid;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GridTest {
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
