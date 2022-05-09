import model.Point;
import model.Ship;

import java.util.List;
import java.util.Random;

public class Randomizer {

    Random random = new Random();

    public String randomPosition() {
        String position = "";
        int value = random.nextInt(1, 3);
        if (value == 2) {
            position = "vertical";
        }
        if (value == 1) {
            position = "horizontal";
        }
        return position;
    }

    public String randomDirection(String position) {
        String direction = "";
        int value = random.nextInt(1, 3);
        switch (position) {
            case "horizontal" -> {
                if (value == 1) {
                    direction = "left";
                }
                if (value == 2) {
                    direction = "right";
                }
            }
            case "vertical" -> {
                if (value == 1) {
                    direction = "up";
                }
                if (value == 2) {
                    direction = "down";
                }
            }
        }
        return direction;
    }

    public Point randomShipStartPoint(String position, String direction, Ship ship, List<Ship> ships) {
        int x = 0;
        int y = 0;
        switch (position) {
            case "horizontal" -> {
                switch (direction) {
                    case ("left") -> {
                        x = random.nextInt(ship.getShipLength(), 11);
                    }
                    case ("right") -> {
                        x = random.nextInt(1, 11 - ship.getShipLength());
                    }
                }
                y = random.nextInt(1, 11);
            }
            case "vertical" -> {
                switch (direction) {
                    case ("up") -> {
                        y = random.nextInt(ship.getShipLength(), 11);
                    }
                    case ("down") -> {
                        y = random.nextInt(1, 11 - ship.getShipLength());
                    }
                }
                x = random.nextInt(1, 11);
            }
        }
        return new Point(x, y);
    }
}