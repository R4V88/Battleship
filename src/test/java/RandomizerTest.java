import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import model.Direction;
import model.Point;
import model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static model.Direction.DOWN;
import static model.Direction.LEFT;
import static model.Direction.RIGHT;
import static model.Direction.UP;
import static model.Position.HORIZONTAL;
import static model.Position.VERTICAL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@FieldDefaults(level = AccessLevel.PRIVATE)
class RandomizerTest {
    private final Integer shipLong = 5;
    private final Randomizer randomizer = new Randomizer();
    private List<Position> positions;
    private List<Direction> horizontalDirections;
    private List<Direction> verticalDirections;

    @BeforeEach
    void setUp() {
        //GIVEN
        positions = List.of(HORIZONTAL, VERTICAL);
        verticalDirections = List.of(UP, DOWN);
        horizontalDirections = List.of(LEFT, RIGHT);
    }

    @Test
    void randomPositionShouldBeAsGiven() {
        //WHEN
        Position position = randomizer.randomPosition();

        //THEN
        boolean contains = positions.contains(position);
        assertTrue(contains);
    }

    @Test
    void randomPositionShouldBeHorizontalOrVertical() {
        //WHEN
        Position position = randomizer.randomPosition();

        //THEN
        for (Position pos : positions) {
            if (pos.equals(position)) {
                assertEquals(pos, position);
            }
        }
    }

    @Test
    void randomDirectionShouldBeLeftOrRight() {
        //WHEN
        Direction dicrection = randomizer.randomDirection(HORIZONTAL);

        //THEN
        for (Direction dir : horizontalDirections) {
            if (dir.equals(dicrection)) {
                assertEquals(dir, dicrection);
            }
        }

    }

    @Test
    void randomDirectionShouldBeUpOrDown() {
        //WHEN
        Direction dicrection = randomizer.randomDirection(VERTICAL);

        //THEN
        for (Direction dir : verticalDirections) {
            if (dir.equals(dicrection)) {
                assertEquals(dir, dicrection);
            }
        }

    }

    @Test
    void returnedPointForVerticalUpLongAttributesShouldHaveCooridinatesInGivenRange() {
        //GIVEN
        List<Integer> givenX = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> givenY = List.of(1, 2, 3, 4, 5, 6);

        //WHEN
        Point point = randomizer.randomShipStartPoint(VERTICAL, UP, shipLong);
        int pointX = point.getX();
        int pointY = point.getY();

        //THEN
        for (Integer value : givenX) {
            if (pointX == value) {
                assertEquals(value, pointX);
            }
        }
        for (Integer value : givenY) {
            if (pointY == value) {
                assertEquals(value, pointY);
            }
        }

    }

    @Test
    void returnedPointForVerticalDownLongAttributesShouldHaveCooridinatesInGivenRange() {
        //GIVEN
        List<Integer> givenX = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> givenY = List.of(5, 6, 7, 8, 9, 10);

        //WHEN
        Point point = randomizer.randomShipStartPoint(VERTICAL, UP, shipLong);
        int pointX = point.getX();
        int pointY = point.getY();

        //THEN
        for (Integer value : givenX) {
            if (pointX == value) {
                assertEquals(value, pointX);
            }
        }
        for (Integer value : givenY) {
            if (pointY == value) {
                assertEquals(value, pointY);
            }
        }

    }

    @Test
    void returnedPointForHorizontalLeftLongAttributesShouldHaveCooridinatesInGivenRange() {
        //GIVEN
        List<Integer> givenX = List.of(5, 6, 7, 8, 9, 10);
        List<Integer> givenY = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        //WHEN
        Point point = randomizer.randomShipStartPoint(HORIZONTAL, LEFT, shipLong);
        int pointX = point.getX();
        int pointY = point.getY();

        //THEN
        for (Integer value : givenX) {
            if (pointX == value) {
                assertEquals(value, pointX);
            }
        }
        for (Integer value : givenY) {
            if (pointY == value) {
                assertEquals(value, pointY);
            }
        }

    }

    @Test
    void returnedPointForHorizontalRightLongAttributesShouldHaveCooridinatesInGivenRange() {
        //GIVEN
        List<Integer> givenX = List.of(1, 2, 3, 4, 5, 6);
        List<Integer> givenY = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        //WHEN
        Point point = randomizer.randomShipStartPoint(VERTICAL, RIGHT, shipLong);
        int pointX = point.getX();
        int pointY = point.getY();

        //THEN
        for (Integer value : givenX) {
            if (pointX == value) {
                assertEquals(value, pointX);
            }
        }
        for (Integer value : givenY) {
            if (pointY == value) {
                assertEquals(value, pointY);
            }
        }
    }

    @Test
    void generatedRandomPointShouldbeInGivenRange() {
        //GIVEN
        List<Integer> range = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Point> hits = List.of(
                new Point(1, 2),
                new Point(2, 3),
                new Point(3, 4),
                new Point(3, 6),
                new Point(10, 10),
                new Point(1, 1),
                new Point(8, 1),
                new Point(6, 5),
                new Point(5, 6));

        //WHEN
        Point point = randomizer.getRandomPoint(hits);

        assertTrue(range.contains(point.getX()));
        assertTrue(range.contains(point.getY()));
    }
}