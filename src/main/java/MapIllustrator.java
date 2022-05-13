import model.Map;
import model.Point;
import model.Ship;

import java.util.List;

import static model.Colors.ANSI_BLUE;
import static model.Colors.ANSI_GREEN;
import static model.Colors.ANSI_PURPLE;
import static model.Colors.ANSI_RED;
import static model.Colors.ANSI_YELLOW;

public class MapIllustrator {
    private final ShipCreator shipCreator = new ShipCreator();

    public void illustrateStrikeMap(Map map, List<Point> strikes) {
        char alphabetStart = 'A';
        illustrateStrikesMapHeader(map);

        for (int i = 1; i <= map.getMap()[1].length; i++) {
            System.out.print(ANSI_YELLOW.getColor() + alphabetStart + " ");
            alphabetStart++;

            for (int j = 1; j <= map.getMap()[0].length; j++) {
                String color = returnTextColorAndSignForStrikeMap(strikes, i, j);
                System.out.print(color + " ");
            }
            System.out.println();
        }
    }

    private void illustrateStrikesMapHeader(Map map) {
        System.out.println(ANSI_GREEN.getColor() + "Player Hits map: ");
        System.out.print(" ");
        for (int i = 1; i <= map.getMap()[1].length; i++)
            System.out.print(" " + ANSI_YELLOW.getColor() + i);
        System.out.println();
    }

    private String returnTextColorAndSignForStrikeMap(List<Point> strikes, int x, int y) {
        String color = ANSI_BLUE.getColor() + "~";
        for (Point point : strikes) {
            if (point.getX() == x && point.getY() == y) {
                if (point.isHit()) {
                    color = ANSI_GREEN.getColor() + "+" + ANSI_BLUE.getColor();
                } else {
                    color = ANSI_RED.getColor() + "-" + ANSI_BLUE.getColor();
                }
            }
        }
        return color;
    }

    public void illustrateShipMap(Map map, List<Ship> ships, List<Point> opponentStrikes) {
        List<Point> shipsPoints = shipCreator.getAllShipsPoints(ships);
        char alphabetStart = 'A';
        illustrateShipsMapHeader(map);

        for (int i = 1; i <= map.getMap()[1].length; i++) {
            System.out.print(ANSI_YELLOW.getColor() + alphabetStart + " ");
            alphabetStart++;

            for (int j = 1; j <= map.getMap()[0].length; j++) {
                String color = returnTextColorAndSignForShipsMap(shipsPoints, i, j, opponentStrikes);
                System.out.print(color + " ");
            }
            System.out.println();
        }

    }

    private void illustrateShipsMapHeader(Map map) {
        System.out.println(ANSI_PURPLE.getColor() + "Player Ships map: ");
        System.out.print(" ");
        for (int i = 1; i <= map.getMap()[1].length; i++)
            System.out.print(" " + ANSI_YELLOW.getColor() + i);
        System.out.println();
    }

    private String returnTextColorAndSignForShipsMap(List<Point> ships, int x, int y, List<Point> opponentStrikes) {
        String color = ANSI_BLUE.getColor() + "~";

        for (Point strike : opponentStrikes) {
            if (strike.getX() == x && strike.getY() == y) {
                if (!strike.isHit()) {
                    color = ANSI_PURPLE.getColor() + "X" + ANSI_BLUE.getColor();
                }
            }
        }

        for (Point ship : ships) {
            if (ship.getX() == y && ship.getY() == x) {
                color = ANSI_GREEN.getColor() + "O" + ANSI_BLUE.getColor();
                if (ship.isHit()) {
                    color = ANSI_RED.getColor() + "X" + ANSI_BLUE.getColor();
                }
            }
        }
        return color;
    }
}
