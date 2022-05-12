import model.Point;
import model.Ship;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ShipCreatorTest {
    private final ShipCreator shipCreator = new ShipCreator();

    @Test
    void whenShipHasBeenCreated() {
        //GIVEN
        int length = 4;
        List<Ship> ships = List.of();

        //WHEN
        Ship createdShip = shipCreator.createShip(length, ships);

        //THEN
        assertNotNull(createdShip.getShipLength());
        assertNotNull(createdShip.getPoints());
    }

    @Test
    void whenCreatingShortShipWhileOtherShipsExists() {
        //GIVEN
        int length = 4;
        Ship existingShip1 = new Ship();
        existingShip1.setPoints(List.of(new Point(5, 9), new Point(5, 10), new Point(5, 8), new Point(5, 7), new Point(5, 6)));
        Ship existingShip2 = new Ship();
        existingShip2.setPoints(List.of(new Point(5, 5), new Point(6, 5), new Point(7, 5), new Point(8, 5), new Point(9, 5)));
        List<Ship> ships = List.of(existingShip1, existingShip2);

        //WHEN
        Ship createdShip = shipCreator.createShip(length, ships);

        //THEN
        List<Point> totalShipsPoints = new ArrayList<>(List.of());
        totalShipsPoints.addAll(existingShip1.getPoints());
        totalShipsPoints.addAll(existingShip2.getPoints());

        for(Point point : createdShip.getPoints()) {
            assertFalse(totalShipsPoints.contains(point));
        }
    }

    @Test
    void whenCreatingLongShipWhileOtherShipsExists() {
        //GIVEN
        int length = 5;
        Ship existingShip1 = new Ship();
        existingShip1.setPoints(List.of(new Point(5, 9), new Point(5, 10), new Point(5, 8), new Point(5, 7), new Point(5, 6)));
        Ship existingShip2 = new Ship();
        existingShip2.setPoints(List.of(new Point(5, 5), new Point(6, 5), new Point(7, 5), new Point(8, 5), new Point(9, 5)));
        List<Ship> ships = List.of(existingShip1, existingShip2);

        //WHEN
        Ship createdShip = shipCreator.createShip(length, ships);

        //THEN
        List<Point> totalShipsPoints = new ArrayList<>(List.of());
        totalShipsPoints.addAll(existingShip1.getPoints());
        totalShipsPoints.addAll(existingShip2.getPoints());

        for(Point point : createdShip.getPoints()) {
            assertFalse(totalShipsPoints.contains(point));
        }
    }

    @Test
    void CreateMultipleShipsOnEmptyMap() {
        //GIVEN
        Ship ship1 = new Ship();
        ship1.setShipLength(5);
        Ship ship2 = new Ship();
        ship2.setShipLength(4);
        Ship ship3 = new Ship();
        ship3.setShipLength(5);

        List<Ship> ships = new ArrayList<>(List.of());

        //WHEN
        ship1 = shipCreator.createShip(ship1.getShipLength(), ships);
        ships.add(ship1);
        ship2 = shipCreator.createShip(ship2.getShipLength(), ships);
        ships.add(ship2);
        ship3 = shipCreator.createShip(ship2.getShipLength(), ships);
        ships.add(ship3);


        //THEN
        for(Point point : ship1.getPoints()) {
            assertFalse(ship2.getPoints().contains(point));
        }
        for (Point point : ship2.getPoints()) {
            assertFalse(ship3.getPoints().contains(point));
        }

        for(Point point : ship3.getPoints()) {
            assertFalse(ship1.getPoints().contains(point));
        }
    }

    @Test
    void testIfReturnedListContainsAllPoints() {
        //GIVEN
        Ship ship1 = new Ship(3, List.of(new Point(1,5), new Point(1,6), new Point(1,7)));
        Ship ship2 = new Ship(2, List.of(new Point(2,5), new Point(2,6)));
        List<Point> totalPoints = new ArrayList<>(List.of());
        totalPoints.addAll(ship1.getPoints());
        totalPoints.addAll(ship2.getPoints());
        List<Ship> ships = List.of(ship1, ship2);

        //WHEN
        final List<Point> allShipsPoints = shipCreator.getAllShipsPoints(ships);

        //THEN
        assertEquals(allShipsPoints.size(), totalPoints.size());
        assertTrue(allShipsPoints.containsAll(totalPoints));
    }
}