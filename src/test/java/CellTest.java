import com.entities.Cell;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class CellTest {
    @Test
    public void verifyThatCellsWithSameCoordinatesAreEqual() {
        Cell firstCell = new Cell(0, 0);
        Cell secondCell = new Cell(0, 0);
        assertEquals(firstCell, secondCell);
    }

    @Test
    public void verifyThatCellsWithDifferentCoordinatesAreUnequal() {
        Cell firstCell = new Cell(0, 0);
        Cell secondCell = new Cell(1, 2);
        assertNotEquals(firstCell, secondCell);
    }

    @Test
    public void verifyCellIsSurroundedByExactEightNeighbours() {
        Cell cell = new Cell(1,1);
        int xCoordinate = cell.getXCoordinate(), yCoordinate = cell.getYCoordinate();
        Cell eighthNeighbourNeighbour = new Cell(xCoordinate - 1, yCoordinate - 1);
        Cell seventhNeighbour = new Cell(xCoordinate - 1, yCoordinate);
        Cell sixthNeighbour = new Cell(xCoordinate - 1, yCoordinate + 1);
        Cell fifthNeighbour = new Cell(xCoordinate, yCoordinate - 1);
        Cell fourthNeighbour = new Cell(xCoordinate, yCoordinate + 1);
        Cell thirdNeighbour = new Cell(xCoordinate + 1, yCoordinate - 1);
        Cell secondNeighbour = new Cell(xCoordinate + 1, yCoordinate);
        Cell firstNeighbour = new Cell(xCoordinate + 1, yCoordinate + 1);

        List<Cell> neighbouringCells = cell.getAllNeighbours().collect(Collectors.toList());

        assertTrue(neighbouringCells.size() == 8);
        assertTrue(neighbouringCells.contains(firstNeighbour));
        assertTrue(neighbouringCells.contains(secondNeighbour));
        assertTrue(neighbouringCells.contains(thirdNeighbour));
        assertTrue(neighbouringCells.contains(fourthNeighbour));
        assertTrue(neighbouringCells.contains(fifthNeighbour));
        assertTrue(neighbouringCells.contains(sixthNeighbour));
        assertTrue(neighbouringCells.contains(seventhNeighbour));
        assertTrue(neighbouringCells.contains(eighthNeighbourNeighbour));
    }
}
