import model.Map;
import model.Point;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class MapIllustrator {
    private static char alphabetStart = 'A';
    private static final String ANSI_RED_MISS = "\u001B[31m";
    private static final String ANSI_BLUE_SEA = "\u001B[34m";
    private static final String ANSI_GREEN_HIT = "\u001B[32m";
    private static final String ANSI_YELLOW_NUMBERS = "\u001B[33m";
    private static final String ANSI_PURPLE_SHIP = "\u001B[35m";

    public void illustrateStrikeMap(Map map, HashSet<Point> checkedList) {
        illustrateMapHeader(map);

        for (int i = 1; i <= map.getMap()[1].length; i++) {
            System.out.print(ANSI_YELLOW_NUMBERS + alphabetStart + " ");
            alphabetStart++;

            for (int j = 1; j <= map.getMap()[0].length; j++) {
                String color = returnTextColorAndSignForStrikeMap(checkedList, i, j);
                System.out.print(color + " ");
            }
            System.out.println();
        }
    }

    private void illustrateMapHeader(Map map) {
        System.out.println(ANSI_GREEN_HIT + "Playes Hits map: ");
        System.out.print(" ");
        for (int i = 1; i <= map.getMap()[1].length; i++)
            System.out.print(" " + ANSI_YELLOW_NUMBERS + i);
        System.out.println();
    }

    private String returnTextColorAndSignForStrikeMap(HashSet<Point> checkedList, int x, int y) {
        String color = ANSI_BLUE_SEA + "~";
        for (Point point : checkedList) {
            if (point.getX() == y && point.getY() == x) {
                if (point.isHit()) {
                    color = ANSI_GREEN_HIT + "+" + ANSI_BLUE_SEA;
                } else {
                    color = ANSI_RED_MISS + "-" + ANSI_BLUE_SEA;
                }
            }
        }
        return color;
    }

    public HashSet<Point> gotHit(List<Point> strikes, List<Point> ships) {
        HashSet<Point> checkedList = new java.util.HashSet<>(Collections.emptySet());
        for (Point ship : ships) {
            for (Point hit : strikes) {
                if (hit.getX() == ship.getX() && hit.getY() == ship.getY()) {
                    hit.setHit(true);
                }
                checkedList.add(hit);
            }
        }
        return checkedList;
    }
}
