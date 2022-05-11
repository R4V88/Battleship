import model.Point;
import model.Ship;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShipCreator {

    Ship createShip(int length, String position, String direction, Point startingPoint) {

        return new Ship();
    }

    Boolean checkIfShipLocationIsOccupiedByOther(List<Ship> ships, List<Point> shipToCreate) {
        List<Point> points = getAllShipsPoints(ships);

        for (Point pointToCreate : shipToCreate) {
            if (points.contains(pointToCreate)) {
                return true;
            }
        }
        return false;
    }

    private List<Point> getAllShipsPoints(List<Ship> ships) {
        List<Point> allShips = new ArrayList<>(Collections.emptyList());
        for (Ship ship : ships) {
            List<Point> shipPoints = ship.getPoints();
            allShips.addAll(shipPoints);
        }
        return allShips;
    }
}
