import model.Player;
import model.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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

    @DisplayName("True when a ship got hit")
    @Test
    void whenTargetIsInRange() {
        //GIVEN
        target = new Point(4, 6);

        //WHEN
        Boolean value = pointsCalculator.checkHit(target, points);

        //THEN
        assertEquals(true, value);
    }

    @DisplayName("False when a ship was missed")
    @Test
    void whenTargetIsNotInRange() {
        //GIVEN
        target = new Point(1, 7);

        //WHEN
        Boolean value = pointsCalculator.checkHit(target, points);

        //THEN
        assertEquals(false, value);
    }

    @DisplayName("Add a point when a ship got hit")
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

    @DisplayName("Do not add a point when a ship was missed")
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