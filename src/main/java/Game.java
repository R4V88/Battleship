import model.Map;
import model.Point;

import java.util.List;

public class Game {
    public static final int MAP_SIZE = 10;

    private static Randomizer randomizer = new Randomizer();

    private static Map map = new Map(new int[MAP_SIZE][MAP_SIZE]);

    public static void main(String[] args) {
        MapIllustrator mapIllustrator = new MapIllustrator();
        System.out.println("check for hits: ");
        List<Point> strikes = List.of(new Point(1, 4), new Point(1, 5, false), new Point(5, 7, true), new Point(5, 9, true), new Point(1, 1, false), new Point(10, 10, true));

//        HashSet<Point> checkedList = mapIllustrator.gotHit(strikes,
//                List.of(new Point(1, 4), new Point(1, 6), new Point(5, 7), new Point(5, 8), new Point(5, 9), new Point(2, 5)));

        System.out.println("Player strikes map:");
        mapIllustrator.illustrateStrikeMap(map, strikes);
        System.out.println("Player Ships map:");
//        mapIllustrator.illustrateShipMap(map,
//                List.of(new Point(1, 3), new Point(2, 3), new Point(3, 3)), List.of());
    }
}
