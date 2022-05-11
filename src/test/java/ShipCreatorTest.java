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
        int length = 4;
        String position = "horizontal";
        String direction = "left";
        Point startingPoint = new Point(3, 4);

        Ship createdShip = shipCreator.createShip(length, position, direction, startingPoint);

        Assertions.assertNotNull(createdShip.getShipLength());
        Assertions.assertNotNull(createdShip.getPoints());
    }
}