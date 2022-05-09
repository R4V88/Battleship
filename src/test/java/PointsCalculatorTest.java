import model.Player;
import model.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PointsCalculatorTest {
    private final PointsCalculator pointsCalculator = new PointsCalculator();
    List<Point> points;
    Point target;
    Player player;
    boolean value;

    @BeforeEach
    void setUp() {
        points = List.of(new Point(4, 6), new Point(3, 5), new Point(1, 9));
    }

    @Test
    void whenTargetIsInRange() {
        //GIVEN
        target = new Point(4, 6);

        //WHEN
        Boolean value = pointsCalculator.checkHit(target, points);

        //THEN
        assertEquals(true, value);
    }

    @Test
    void whenTargetIsNotInRange() {
        //GIVEN
        target = new Point(1, 7);

        //WHEN
        Boolean value = pointsCalculator.checkHit(target, points);

        //THEN
        assertEquals(false, value);
    }

    @Test
    void whenPointShouldAdd() {
        //GIVEN
        player = new Player();
        value = true;

        //WHEN
        pointsCalculator.recalculatePoints(player, value);

        //THEN
        assertEquals(1, player.getScore());
    }

    @Test
    void whenPointShouldNotAdd() {
        //GIVEN
        player = new Player();
        value = false;

        //WHEN
        pointsCalculator.recalculatePoints(player, value);

        //THEN
        assertEquals(0, player.getScore());
    }
}