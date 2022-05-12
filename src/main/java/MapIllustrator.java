import model.Map;
import model.Point;
import model.Ship;

import java.util.List;

public class MapIllustrator {
    private final ShipCreator shipCreator = new ShipCreator();
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_PURPLE = "\u001B[35m";

    public void illustrateStrikeMap(Map map, List<Point> strikes) {
        char alphabetStart = 'A';
        illustrateStrikesMapHeader(map);

        for (int i = 1; i <= map.getMap()[1].length; i++) {
            System.out.print(ANSI_YELLOW + alphabetStart + " ");
            alphabetStart++;

            for (int j = 1; j <= map.getMap()[0].length; j++) {
                String color = returnTextColorAndSignForStrikeMap(strikes, i, j);
                System.out.print(color + " ");
            }
            System.out.println();
        }
    }

    private void illustrateStrikesMapHeader(Map map) {
        System.out.println(ANSI_GREEN + "Player Hits map: ");
        System.out.print(" ");
        for (int i = 1; i <= map.getMap()[1].length; i++)
            System.out.print(" " + ANSI_YELLOW + i);
        System.out.println();
    }

    private String returnTextColorAndSignForStrikeMap(List<Point> strikes, int x, int y) {
        String color = ANSI_BLUE + "~";
        for (Point point : strikes) {
            if (point.getX() == x && point.getY() == y) {
                if (point.isHit()) {
                    color = ANSI_GREEN + "+" + ANSI_BLUE;
                } else {
                    color = ANSI_RED + "-" + ANSI_BLUE;
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
            System.out.print(ANSI_YELLOW + alphabetStart + " ");
            alphabetStart++;

            for (int j = 1; j <= map.getMap()[0].length; j++) {
                String color = returnTextColorAndSignForShipsMap(shipsPoints, i, j, opponentStrikes);
                System.out.print(color + " ");
            }
            System.out.println();
        }

    }

    private void illustrateShipsMapHeader(Map map) {
        System.out.println(ANSI_PURPLE + "Player Ships map: ");
        System.out.print(" ");
        for (int i = 1; i <= map.getMap()[1].length; i++)
            System.out.print(" " + ANSI_YELLOW + i);
        System.out.println();
    }

    private String returnTextColorAndSignForShipsMap(List<Point> ships, int x, int y, List<Point> opponentStrikes) {
        String color = ANSI_BLUE + "~";

        for(Point strike: opponentStrikes) {
            if(strike.getX() == x &&  strike.getY() == y){
                if (!strike.isHit()) {
                    color = ANSI_PURPLE + "X" + ANSI_BLUE;
                }
            }
        }

        for(Point ship : ships) {
            if(ship.getX() == y && ship.getY() == x ) {
                color = ANSI_GREEN + "O" + ANSI_BLUE;
                if(ship.isHit()) {
                    color = ANSI_RED + "X" + ANSI_BLUE;
                }
            }
        }
        return color;
    }
}
