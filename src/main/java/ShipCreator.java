import model.Direction;
import model.Point;
import model.Position;
import model.Ship;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShipCreator {
    private final Randomizer randomizer = new Randomizer();

    public Ship createShip(int length, List<Ship> ships) {
        Ship createdShip = new Ship();
        createdShip.setShipLength(length);

        boolean loop = true;

        do {
            Position position = randomizer.randomPosition();
            Direction direction = randomizer.randomDirection(position);
            Point startPoint = randomizer.randomShipStartPoint(position, direction, createdShip.getShipLength());
            List<Point> points = new ArrayList<>(List.of(startPoint));
            int startX = startPoint.getX();
            int startY = startPoint.getY();

            switch (position) {
                case HORIZONTAL -> {
                    switch (direction) {
                        case LEFT -> {
                            for (int i = 0; i < length - 1; i++) {
                                --startX;
                                points.add(new Point(startX, startY));
                            }
                            if (!checkIfShipLocationIsOccupiedByOther(ships, points)) {
                                createdShip = new Ship(length, points);
                                loop = false;
                            }
                        }
                        case RIGHT -> {
                            for (int i = 0; i < length - 1; i++) {
                                ++startX;
                                points.add(new Point(startX, startY));
                            }
                            if (!checkIfShipLocationIsOccupiedByOther(ships, points)) {
                                createdShip = new Ship(length, points);
                                loop = false;
                            }
                        }
                    }
                }
                case VERTICAL -> {
                    switch (direction) {
                        case UP -> {
                            for (int i = 0; i < length - 1; i++) {
                                ++startY;
                                points.add(new Point(startX, startY));
                            }
                            if (!checkIfShipLocationIsOccupiedByOther(ships, points)) {
                                createdShip = new Ship(length, points);
                                loop = false;
                            }
                        }
                        case DOWN -> {
                            for (int i = 0; i < length - 1; i++) {
                                --startY;
                                points.add(new Point(startX, startY));
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

