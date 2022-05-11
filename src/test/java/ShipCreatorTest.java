import model.Point;
import model.Ship;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class ShipCreatorTest {
    private final ShipCreator shipCreator = new ShipCreator();


    @Test
    void returnTrueWhenPositionsAreNotOccupiedByOtherShip() {
        //GIVEN
        List<Point> shipToCreate = new ArrayList<>(Collections.emptyList());
        Point shipPoint1 = new Point(5, 9);
        Point shipPoint2 = new Point(5, 10);
        Point shipPoint3 = new Point(5, 8);
        Point shipPoint4 = new Point(5, 7);
        shipToCreate.add(shipPoint1);
        shipToCreate.add(shipPoint2);
        shipToCreate.add(shipPoint3);
        shipToCreate.add(shipPoint4);

        Ship existingShip1 = new Ship();
        existingShip1.setPoints(List.of(new Point(5, 1), new Point(5, 2), new Point(5, 3), new Point(5, 4)));
        List<Ship> ships = List.of(existingShip1);

        //WHEN
        Boolean value = shipCreator.checkIfShipLocationIsOccupiedByOther(ships, shipToCreate);

        //THEN
        Assertions.assertFalse(value);
    }

    @Test
    void returnFalseWhenPositionsAreOccupiedByOtherShip() {
        //GIVEN
        List<Point> shipToCreate = new ArrayList<>(List.of());
        Point shipPoint1 = new Point(5, 9);
        Point shipPoint2 = new Point(5, 10);
        Point shipPoint3 = new Point(5, 8);
        Point shipPoint4 = new Point(5, 7);
        shipToCreate.add(shipPoint1);
        shipToCreate.add(shipPoint2);
        shipToCreate.add(shipPoint3);
        shipToCreate.add(shipPoint4);

        List<Ship> ships = new ArrayList<>(List.of());
        Ship existingShip1 = new Ship();
        existingShip1.setPoints(List.of(new Point(5, 7), new Point(5, 8), new Point(5, 9), new Point(5, 10)));
        ships.add(existingShip1);

        //WHEN
        Boolean value = shipCreator.checkIfShipLocationIsOccupiedByOther(ships, shipToCreate);

        //THEN
        Assertions.assertTrue(value);
    }

    @Test
    void whenShipHasBeenCreatedWithGivenArguments() {
        //GIVEN
        int length = 4;
        String position = "horizontal";
        String direction = "left";
        Point startingPoint = new Point(4, 4);
        List<Ship> ships = List.of();

        //WHEN
        Ship createdShip = shipCreator.createShip(length, position, direction, startingPoint, ships);

        //THEN
        Assertions.assertNotNull(createdShip.getShipLength());
        Assertions.assertNotNull(createdShip.getPoints());
    }

    @Test
    void whenShipHasBeenCreatedWithHorizontalRightShortArguments() {
        //GIVEN
        int length = 4;
        String position = "horizontal";
        String direction = "right";
        Point startingPoint = new Point(7, 4);
        List<Point> pointsThatShouldBeCreated = List.of(new Point(7, 4), new Point(8, 4), new Point(9, 4), new Point(10, 4));
        List<Ship> ships = List.of();

        //WHEN
        Ship createdShip = shipCreator.createShip(length, position, direction, startingPoint, ships);

        //THEN
        Assertions.assertEquals(pointsThatShouldBeCreated, createdShip.getPoints());
    }

    @Test
    void whenCreatingShipOnOtherShipsPlace() {
        //GIVEN
        int length = 4;
        String position = "horizontal";
        String direction = "right";
        Point startingPoint = new Point(4, 9);

        Ship existingShip1 = new Ship();
        existingShip1.setPoints(List.of(new Point(5, 9), new Point(5, 10), new Point(5, 8), new Point(5, 7), new Point(5, 6)));
        Ship existingShip2 = new Ship();
        existingShip2.setPoints(List.of(new Point(5, 5), new Point(6, 5), new Point(7, 5), new Point(8, 5), new Point(9, 5)));
        List<Ship> ships = List.of(existingShip1, existingShip2);

        //WHEN
        Ship createdShip = shipCreator.createShip(length, position, direction, startingPoint, ships);

        //THEN
        List<Point> totalShipsPoints = new ArrayList<>(List.of());
        totalShipsPoints.addAll(existingShip1.getPoints());
        totalShipsPoints.addAll(existingShip2.getPoints());
        System.out.println(totalShipsPoints);
        System.out.println(createdShip.getPoints());

        for(Point point : createdShip.getPoints()) {
            Assertions.assertFalse(totalShipsPoints.contains(point));
        }

    }
}