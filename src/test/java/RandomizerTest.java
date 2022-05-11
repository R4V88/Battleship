import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import model.Point;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
class RandomizerTest {
    final String positionHorizontal = "horizontal";
    final String positionVertical = "vertical";
    final String directionUp = "up";
    final String directionDown = "down";
    final String directionLeft = "left";
    final String directionRight = "right";
    final Integer shipLong = 5;
    final Integer shipShort = 4;
    final Randomizer randomizer = new Randomizer();
    List<String> positions;
    List<String> directions;
    List<String> horizontalDirections;
    List<String> verticalDirections;

    @BeforeEach
    void setUp() {
        //GIVEN
        positions = List.of(positionHorizontal, positionVertical);
        directions = List.of(directionUp, directionDown, directionLeft, directionRight);
        verticalDirections = List.of(directionUp, directionDown);
        horizontalDirections = List.of(directionLeft, directionRight);
    }

    @Test
    void randomPositionShouldBeAsGiven() {
        //WHEN
        String position = randomizer.randomPosition();

        //THEN
        boolean contains = positions.contains(position);
        Assertions.assertTrue(contains);
    }

    @Test
    void randomPositionShouldBeHorizontalOrVertical() {
        //WHEN
        String position = randomizer.randomPosition();

        //THEN
        for (String pos : positions) {
            if (pos.equals(position)) {
                Assertions.assertEquals(pos, position);
            }
        }
    }

    @Test
    void randomDirectionShouldBeLeftOrRight() {
        //WHEN
        String dicrection = randomizer.randomDirection(positionHorizontal);

        //THEN
        for (String dir : horizontalDirections) {
            if (dir.equals(dicrection)) {
                Assertions.assertEquals(dir, dicrection);
            }
        }

    }

    @Test
    void randomDirectionShouldBeUpOrDown() {
        //WHEN
        String dicrection = randomizer.randomDirection(positionVertical);

        //THEN
        for (String dir : verticalDirections) {
            if (dir.equals(dicrection)) {
                Assertions.assertEquals(dir, dicrection);
            }
        }

    }

    @Test
    void returnedPointForVerticalUpLongAttributesShouldHaveCooridinatesInGivenRange() {
        //GIVEN
        List<Integer> givenX = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> givenY = List.of(1, 2, 3, 4, 5, 6);

        //WHEN
        Point point = randomizer.randomShipStartPoint(positionVertical, directionUp, shipLong);
        int pointX = point.getX();
        int pointY = point.getY();

        //THEN
        for (Integer value : givenX) {
            if (pointX == value) {
                Assertions.assertEquals(value, pointX);
            }
        }
        for (Integer value : givenY) {
            if (pointY == value) {
                Assertions.assertEquals(value, pointY);
            }
        }

    }

    @Test
    void returnedPointForVerticalDownLongAttributesShouldHaveCooridinatesInGivenRange() {
        //GIVEN
        List<Integer> givenX = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> givenY = List.of(5, 6, 7, 8, 9, 10);

        //WHEN
        Point point = randomizer.randomShipStartPoint(positionVertical, directionUp, shipLong);
        int pointX = point.getX();
        int pointY = point.getY();

        //THEN
        for (Integer value : givenX) {
            if (pointX == value) {
                Assertions.assertEquals(value, pointX);
            }
        }
        for (Integer value : givenY) {
            if (pointY == value) {
                Assertions.assertEquals(value, pointY);
            }
        }

    }

    @Test
    void returnedPointForHorizontalLeftLongAttributesShouldHaveCooridinatesInGivenRange() {
        //GIVEN
        List<Integer> givenX = List.of(5, 6, 7, 8, 9, 10);
        List<Integer> givenY = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        //WHEN
        Point point = randomizer.randomShipStartPoint(positionHorizontal, directionLeft, shipLong);
        int pointX = point.getX();
        int pointY = point.getY();

        //THEN
        for (Integer value : givenX) {
            if (pointX == value) {
                Assertions.assertEquals(value, pointX);
            }
        }
        for (Integer value : givenY) {
            if (pointY == value) {
                Assertions.assertEquals(value, pointY);
            }
        }

    }

    @Test
    void returnedPointForHorizontalRightLongAttributesShouldHaveCooridinatesInGivenRange() {
        //GIVEN
        List<Integer> givenX = List.of(1, 2, 3, 4, 5, 6);
        List<Integer> givenY = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        //WHEN
        Point point = randomizer.randomShipStartPoint(positionHorizontal, directionRight, shipLong);
        int pointX = point.getX();
        int pointY = point.getY();

        //THEN
        for (Integer value : givenX) {
            if (pointX == value) {
                Assertions.assertEquals(value, pointX);
            }
        }
        for (Integer value : givenY) {
            if (pointY == value) {
                Assertions.assertEquals(value, pointY);
            }
        }
    }
}