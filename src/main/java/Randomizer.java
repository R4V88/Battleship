import model.Point;
import model.Ship;

import java.util.Random;

public class Randomizer {

    private final static String POSITION_HORIZONTAL = "horizontal";
    private final static String POSITION_VERTICAL = "vertical";
    private final static String DIRECTION_UP = "up";
    private final static String DIRECTION_DOWN = "down";
    private final static String DIRECTION_LEFT = "left";
    private final static String DIRECTION_RIGHT = "right";

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
            case POSITION_HORIZONTAL -> {
                if (value == 1) {
                    direction = DIRECTION_LEFT;
                }
                if (value == 2) {
                    direction = DIRECTION_RIGHT;
                }
            }
            case POSITION_VERTICAL -> {
                if (value == 1) {
                    direction = DIRECTION_UP;
                }
                if (value == 2) {
                    direction = DIRECTION_DOWN;
                }
            }
        }
        return direction;
    }

    public Point randomShipStartPoint(String position, String direction, Ship ship) {
        int x = 0;
        int y = 0;
        final Integer shipLength = ship.getShipLength();

        switch (position) {
            case POSITION_HORIZONTAL -> {
                switch (direction) {
                    case (DIRECTION_LEFT) -> {
                        x = random.nextInt(shipLength, 11);
                    }
                    case (DIRECTION_RIGHT) -> {
                        x = random.nextInt(1, 11 - shipLength);
                    }
                }
                y = random.nextInt(1, 11);
            }
            case POSITION_VERTICAL -> {
                switch (direction) {
                    case (DIRECTION_UP) -> {
                        y = random.nextInt(1, 11 - shipLength);
                    }
                    case (DIRECTION_DOWN) -> {
                        y = random.nextInt(shipLength, 11);
                    }
                }
                x = random.nextInt(1, 11);
            }
        }
        return new Point(x, y);
    }
}