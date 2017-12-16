import com.entities.Cell;
import static org.junit.Assert.*;

import org.junit.Test;

public class CellTest {

    @Test
    public void verifyCellDeadAtCreation() {
        Cell cell = new Cell();
        assertFalse(cell.isAlive());
    }

    @Test
    public void verifyCellIsAliveAfterInitialStateSwitch() {
        Cell cell = new Cell();
        cell.switchState();
        assertTrue(cell.isAlive());
    }

    @Test
    public void verifyCellDiesAfterStateSwitchFromAlive() {
        Cell cell = new Cell();
        cell.switchState();
        assertTrue(cell.isAlive());
        cell.switchState();
        assertFalse(cell.isAlive());
    }
}
