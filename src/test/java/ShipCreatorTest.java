import model.Point;
import model.Ship;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

class ShipCreatorTest {
    private final ShipCreator shipCreator = new ShipCreator();


    @Test
    void returnTrueWhenPositionsAreNotOccupiedByOtherShip() {
        //GIVEN
        List<Point> shipToCreate = new java.util.ArrayList<>(Collections.emptyList());
        Point pointToCreate = new Point(5, 9);
        shipToCreate.add(pointToCreate);

        List<Ship> ships = List.of();

        //WHEN
        Boolean value = shipCreator.checkIfShipLocationIsOccupiedByOther(ships, shipToCreate);

        //THEN
        Assertions.assertTrue(value);
    }

    @Test
    void returnFalseWhenPositionsAreOccupiedByOtherShip() {
        //GIVEN
        List<Point> shipToCreate = new java.util.ArrayList<>(Collections.emptyList());
        Point pointToCreate = new Point(5, 9);
        shipToCreate.add(pointToCreate);

        List<Ship> ships = List.of();

        //WHEN
        Boolean value = shipCreator.checkIfShipLocationIsOccupiedByOther(ships, shipToCreate);

        //THEN
        Assertions.assertFalse(value);
    }

    @Test
    void whenShipHasBeenCreatedWithGivenArguments() {
        int length = 4;
        String position = "horizontal";
        String direction = "left";

        Ship createdShip = shipCreator.createShip(length, position, direction);

        Assertions.assertNotNull(createdShip.getShipLength());
        Assertions.assertNotNull(createdShip.getName());
        Assertions.assertNotNull(createdShip.getPoints());
    }
}