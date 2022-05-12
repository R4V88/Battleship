import model.Direction;
import model.Point;
import model.Position;

import java.util.Random;

import static model.Direction.DOWN;
import static model.Direction.LEFT;
import static model.Direction.RIGHT;
import static model.Direction.UP;
import static model.Position.HORIZONTAL;
import static model.Position.VERTICAL;

public class Randomizer {

    Random random = new Random();

    public Position randomPosition() {
        Position position = null;

        int value = random.nextInt(1, 3);
        if (value == 2) {
            position = VERTICAL;
        }
        if (value == 1) {
            position = HORIZONTAL;
        }
        return position;
    }

    public Direction randomDirection(Position position) {
        Direction direction = null;

        int value = random.nextInt(1, 3);
        switch (position) {
            case HORIZONTAL -> {
                if (value == 1) {
                    direction = LEFT;
                }
                if (value == 2) {
                    direction = RIGHT;
                }
            }
            case VERTICAL -> {
                if (value == 1) {
                    direction = UP;
                }
                if (value == 2) {
                    direction = DOWN;
                }
            }
        }
        return direction;
    }

    public Point randomShipStartPoint(Position position, Direction direction, int shipLength) {
        int x = 0;
        int y = 0;

        switch (position) {
            case HORIZONTAL -> {
                switch (direction) {
                    case LEFT -> {
                        x = random.nextInt(shipLength, 11);
                    }
                    case RIGHT -> {
                        x = random.nextInt(1, 11 - shipLength);
                    }
                }
                y = random.nextInt(1, 11);
            }
            case VERTICAL -> {
                switch (direction) {
                    case UP -> {
                        y = random.nextInt(1, 11 - shipLength);
                    }
                    case DOWN -> {
                        y = random.nextInt(shipLength, 11);
                    }
                }
                x = random.nextInt(1, 11);
            }
        }
        return new Point(x, y);
    }

    public Point getRandomPoint() {
        int x = random.nextInt(1, 11);
        int y = random.nextInt(1, 11);
        return new Point(x, y);
    }
}