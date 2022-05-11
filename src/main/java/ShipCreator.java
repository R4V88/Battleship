import model.Point;
import model.Ship;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShipCreator {
    Randomizer randomizer = new Randomizer();

    Ship createShip(int length, String position, String direction, Point startingPoint, List<Ship> ships) {
        int startX = startingPoint.getX();
        int startY = startingPoint.getY();
        int newX;
        int newY;
        List<Point> points = new ArrayList<>(List.of(startingPoint));
        Ship createdShip = new Ship();
        boolean loop = true;

        if (ships.isEmpty()) {
            switch (position) {
                case Randomizer.POSITION_HORIZONTAL -> {
                    switch (direction) {
                        case (Randomizer.DIRECTION_LEFT) -> {
                            for (int i = 1; i < length; i++) {
                                newX = --startX;
                                points.add(new Point(newX, startY));
                            }
                            createdShip = new Ship(length, points);
                        }
                        case (Randomizer.DIRECTION_RIGHT) -> {
                            for (int i = 0; i < length - 1; i++) {
                                newX = ++startX;
                                points.add(new Point(newX, startY));
                            }
                            createdShip = new Ship(length, points);
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
                            createdShip = new Ship(length, points);
                        }
                        case (Randomizer.DIRECTION_DOWN) -> {
                            for (int i = 0; i < length - 1; i++) {
                                newY = --startY;
                                points.add(new Point(startX, newY));
                            }
                            createdShip = new Ship(length, points);
                        }
                    }
                }
            }

        } else {
            switch (position) {
                case Randomizer.POSITION_HORIZONTAL -> {
                    switch (direction) {
                        case (Randomizer.DIRECTION_LEFT) -> {
                            do {
                                for (int i = 0; i < length - 1; i++) {
                                    newX = --startX;
                                    points.add(new Point(newX, startY));
                                }
                                createdShip = getShip(length, position, direction, ships, points);
                            } while (createdShip.getPoints().size() != length);
                        }
                        case (Randomizer.DIRECTION_RIGHT) -> {
                            do {
                                for (int i = 0; i < length - 1; i++) {
                                    newX = ++startX;
                                    points.add(new Point(newX, startY));
                                }
                                if (!checkIfShipLocationIsOccupiedByOther(ships, points)) {
                                    createdShip = new Ship(length, points);
                                    loop = false;
                                } else {
                                    final Point newPoint = randomizer.randomShipStartPoint(position, direction, length);
                                    startX = newPoint.getX();
                                    startY = newPoint.getY();
                                }
                            } while (loop);
                        }
                    }
                }
                case Randomizer.POSITION_VERTICAL -> {
                    switch (direction) {
                        case (Randomizer.DIRECTION_UP) -> {
                            do {
                                for (int i = 0; i < length - 1; i++) {
                                    newY = ++startY;
                                    points.add(new Point(startX, newY));
                                }
                                createdShip = getShip(length, position, direction, ships, points);
                            } while (createdShip.getPoints().size() != length);
                        }
                        case (Randomizer.DIRECTION_DOWN) -> {
                            do {
                                for (int i = 0; i < length - 1; i++) {
                                    newY = --startY;
                                    points.add(new Point(startX, newY));
                                }
                                createdShip = getShip(length, position, direction, ships, points);
                            } while (createdShip.getPoints().size() != length);
                        }
                    }
                }
            }
        }
        return createdShip;
    }

    private Ship getShip(int length,
                         String position,
                         String direction,
                         List<Ship> ships,
                         List<Point> points) {

        Ship getShip = new Ship();
        if (!checkIfShipLocationIsOccupiedByOther(ships, points)) {
            getShip = new Ship(length, points);
        } else {
            randomizer.randomShipStartPoint(position, direction, length);
        }
        return getShip;
    }

    Boolean checkIfShipLocationIsOccupiedByOther(List<Ship> ships, List<Point> shipToCreate) {
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

