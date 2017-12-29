import com.logic.LifeDeathLogicUtil;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class LifeDeathLogicUtilTest {

    @Test
    public void verifyIfCellWithLessThanTwoNeighboursShouldStarve() {
        int countOfNeighbours = 1;
        assertTrue(LifeDeathLogicUtil.shouldStarve(countOfNeighbours));
    }

    @Test
    public void verifyIfCellWithMoreThanThreeNeighboursShouldSuffocate() {
        int countOfNeighbours = 4;
        assertTrue(LifeDeathLogicUtil.shouldSuffocate(countOfNeighbours));
    }

    @Test
    public void verifyIfCellWithThreeNeighboursShouldNotStarveOrSuffocate() {
        int countOfNeighbours = 3;
        assertFalse(LifeDeathLogicUtil.shouldSuffocate(countOfNeighbours));
        assertFalse(LifeDeathLogicUtil.shouldStarve(countOfNeighbours));
    }

    @Test
    public void verifyIfCellWithThreeNeighboursShouldBeMarkedToBeSown() {
        int countOfNeighbours = 3;
        assertTrue(LifeDeathLogicUtil.shouldCellBeSown(countOfNeighbours));
    }
}
