import model.Point;
import model.Ship;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShipCreator {
    Randomizer randomizer = new Randomizer();

    Ship createShip(int length,  List<Ship> ships) {
        Ship createdShip = new Ship();
        createdShip.setShipLength(length);
        boolean loop = true;
        do {
            String position = randomizer.randomPosition();
            String direction = randomizer.randomDirection(position);
            Point startPoint = randomizer.randomShipStartPoint(position, direction, createdShip.getShipLength());
            List<Point> points = new ArrayList<>(List.of(startPoint));
            int startX = startPoint.getX();
            int startY = startPoint.getY();
            int newX;
            int newY;
            switch (position) {
                case Randomizer.POSITION_HORIZONTAL -> {
                    switch (direction) {
                        case (Randomizer.DIRECTION_LEFT) -> {
                            for (int i = 0; i < length - 1; i++) {
                                newX = --startX;
                                points.add(new Point(newX, startY));
                            }
                            if (!checkIfShipLocationIsOccupiedByOther(ships, points)) {
                                createdShip = new Ship(length, points);
                                loop = false;
                            }
                        }
                        case (Randomizer.DIRECTION_RIGHT) -> {
                            for (int i = 0; i < length - 1; i++) {
                                newX = ++startX;
                                points.add(new Point(newX, startY));
                            }
                            if (!checkIfShipLocationIsOccupiedByOther(ships, points)) {
                                createdShip = new Ship(length, points);
                                loop = false;
                            }
                        }
                    }
                }
                case Randomizer.POSITION_VERTICAL -> {
                    switch (direction) {
                        case (Randomizer.DIRECTION_UP) -> {
                            for (int i = 0; i < length - 1; i++) {
                                newY = ++startY;
                                points.add(new Point(startX, newY));
                            }
                            if (!checkIfShipLocationIsOccupiedByOther(ships, points)) {
                                createdShip = new Ship(length, points);
                                loop = false;
                            }
                        }
                        case (Randomizer.DIRECTION_DOWN) -> {
                            for (int i = 0; i < length - 1; i++) {
                                newY = --startY;
                                points.add(new Point(startX, newY));
                            }
                            if (!checkIfShipLocationIsOccupiedByOther(ships, points)) {
                                createdShip = new Ship(length, points);
                                loop = false;
                            }
                        }
                    }
                }
            }
        } while (loop);
        return createdShip;
    }

    private Boolean checkIfShipLocationIsOccupiedByOther(List<Ship> ships, List<Point> shipToCreate) {
        List<Point> points = getAllShipsPoints(ships);

        for (Point point : points) {
            if (shipToCreate.contains(point)) {
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

