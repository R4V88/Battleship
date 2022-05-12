import model.Map;
import model.Point;
import model.Ship;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public static final int MAP_SIZE = 10;

    private static Randomizer randomizer = new Randomizer();

    private static Map map = new Map(new int[MAP_SIZE][MAP_SIZE]);

    public static void main(String[] args) {
        MapIllustrator strikesMap = new MapIllustrator();
        List<Point> strikes = List.of(new Point(1, 4), new Point(1, 5, false), new Point(5, 7, true), new Point(5, 9, true), new Point(1, 1, false), new Point(10, 10, true));

        MapIllustrator shipsMap = new MapIllustrator();
        Ship ship1 = new Ship();
        ship1.setPoints(List.of(new Point(2, 3), new Point(3, 3), new Point(4, 3 ), new Point(5, 3)));
        Ship ship2 = new Ship();
        ship2.setPoints(List.of(new Point(5, 5, false), new Point(5, 6), new Point(5, 7, true), new Point(5, 8)));
        List<Point> ships = new ArrayList<>(List.of());
        ships.addAll(ship1.getPoints());
        ships.addAll(ship2.getPoints());

        List<Point> opponentStrikes = List.of(new Point(1, 4, false), new Point(1, 5, false), new Point(5, 7, true), new Point(5, 9, false), new Point(1, 1, false), new Point(10, 10, false));

        strikesMap.illustrateStrikeMap(map, strikes);
        shipsMap.illustrateShipMap(map, ships, opponentStrikes);
    }
}
