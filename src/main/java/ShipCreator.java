import model.Ship;

import java.util.List;

public class ShipCreator {

    Ship createShip(int length, String position, String direction) {
        return new Ship();
    }

    Boolean checkIfShipLocationIsOccupiedByOther(List<Ship> ships) {
        return false;
    }
}
