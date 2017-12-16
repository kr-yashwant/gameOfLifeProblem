import com.entities.Cell;
import com.entities.CellState;
import com.entities.Grid;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class GridTest {

    @Test
    public void validateCellAliveAfterSowing() {
        Grid grid = new Grid(20, 20);
        Cell cell = mock(Cell.class);
        grid.sow(cell, 0, 0);
        verify(cell, times(1)).switchState();
    }

    @Test
    public void validateAliveCellStateChangeOutOfLonelinessUponGridTick() {
        Grid grid = new Grid(20, 20);

        Cell cell = mock(Cell.class); when(cell.isAlive()).thenReturn(true);

        grid.sow(cell, 0, 0); verify(cell, times(1)).switchState();

        grid.tick();

        verify(cell, times(2)).switchState();
    }

    @Test
    public void validateAliveCellStateChangeOutOfOvercrowdingUponGridTick() {
        Grid grid = new Grid(20, 20);

        Cell cell = mock(Cell.class); when(cell.isAlive()).thenReturn(true);
        Cell firstNeighbourCell = mock(Cell.class); when(firstNeighbourCell.isAlive()).thenReturn(true);
        Cell secondNeighbourCell = mock(Cell.class); when(secondNeighbourCell.isAlive()).thenReturn(true);
        Cell thirdNeighbourCell = mock(Cell.class); when(thirdNeighbourCell.isAlive()).thenReturn(true);
        Cell fourthNeighbourCell = mock(Cell.class); when(fourthNeighbourCell.isAlive()).thenReturn(true);

        grid.sow(cell, 1,1); verify(cell, times(1)).switchState();
        grid.sow(firstNeighbourCell, 0, 0); verify(firstNeighbourCell, times(1)).switchState();
        grid.sow(secondNeighbourCell, 0, 1); verify(secondNeighbourCell, times(1)).switchState();
        grid.sow(thirdNeighbourCell, 0, 2); verify(thirdNeighbourCell, times(1)).switchState();
        grid.sow(fourthNeighbourCell, 1, 2); verify(fourthNeighbourCell, times(1)).switchState();

        grid.tick();

        verify(cell, times(2)).switchState();
    }

    @Test
    public void validateLiveCellWithTwoNeighboursUndergoesNoStateChangeOnGridTick() {
        Grid grid = new Grid(3, 3);

        Cell cell = mock(Cell.class); when(cell.isAlive()).thenReturn(true);
        Cell firstNeighbourCell = mock(Cell.class); when(firstNeighbourCell.isAlive()).thenReturn(true);
        Cell secondNeighbourCell = mock(Cell.class); when(secondNeighbourCell.isAlive()).thenReturn(true);

        grid.sow(cell, 1,1); verify(cell, times(1)).switchState();
        grid.sow(firstNeighbourCell, 0, 0); verify(firstNeighbourCell, times(1)).switchState();
        grid.sow(secondNeighbourCell, 0, 1); verify(secondNeighbourCell, times(1)).switchState();

        grid.tick();

        verify(cell, times(1)).switchState();
    }

    @Test
    public void validateLiveCellWithThreeNeighboursUndergoesNoStateChangeOnGridTick() {
        Grid grid = new Grid(20, 20);

        Cell cell = mock(Cell.class); when(cell.isAlive()).thenReturn(true);
        Cell firstNeighbourCell = mock(Cell.class); when(firstNeighbourCell.isAlive()).thenReturn(true);
        Cell secondNeighbourCell = mock(Cell.class); when(secondNeighbourCell.isAlive()).thenReturn(true);
        Cell thirdNeighbourCell = mock(Cell.class); when(thirdNeighbourCell.isAlive()).thenReturn(true);

        grid.sow(cell, 1,1); verify(cell, times(1)).switchState();
        grid.sow(firstNeighbourCell, 0, 0); verify(firstNeighbourCell, times(1)).switchState();
        grid.sow(secondNeighbourCell, 0, 1); verify(secondNeighbourCell, times(1)).switchState();
        grid.sow(thirdNeighbourCell, 0, 2); verify(thirdNeighbourCell, times(1)).switchState();

        grid.tick();

        verify(cell, times(1)).switchState();
    }

    @Test
    public void validateDeadCellWithThreeAliveNeighboursStateChangeOnGridTick() {
        Grid grid = new Grid(20, 20);

        Cell cell = mock(Cell.class);

        Cell firstNeighbourCell = mock(Cell.class); when(firstNeighbourCell.isAlive()).thenReturn(true);
        Cell secondNeighbourCell = mock(Cell.class); when(secondNeighbourCell.isAlive()).thenReturn(true);
        Cell thirdNeighbourCell = mock(Cell.class); when(thirdNeighbourCell.isAlive()).thenReturn(true);

        grid.sow(cell, 1,1); verify(cell, times(1)).switchState();

        when(cell.isAlive()).thenReturn(false);

        grid.sow(firstNeighbourCell, 0, 0); verify(firstNeighbourCell, times(1)).switchState();
        grid.sow(secondNeighbourCell, 0, 1); verify(secondNeighbourCell, times(1)).switchState();
        grid.sow(thirdNeighbourCell, 0, 2); verify(thirdNeighbourCell, times(1)).switchState();

        grid.tick();

        verify(cell, times(2)).switchState();
    }
}
