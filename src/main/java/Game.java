import model.Map;
import model.Player;
import model.Point;
import model.Ship;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    public static final int MAP_SIZE = 10;

    private final static Map map = new Map(new int[MAP_SIZE][MAP_SIZE]);

    public static void main(String[] args) {
        boolean loop = true;
//        MapIllustrator playerStrikesMap = new MapIllustrator();
//        List<Point> strikes = List.of(new Point(1, 4), new Point(1, 5, false), new Point(5, 7, true), new Point(5, 9, true), new Point(1, 1, false), new Point(10, 10, true));
//
//        MapIllustrator playerShipsMap = new MapIllustrator();
//        Ship ship1 = new Ship();
//        ship1.setPoints(List.of(new Point(2, 3), new Point(3, 3), new Point(4, 3 ), new Point(5, 3)));
//        Ship ship2 = new Ship();
//        ship2.setPoints(List.of(new Point(5, 5, false), new Point(5, 6), new Point(5, 7, true), new Point(5, 8)));
//        List<Point> ships = new ArrayList<>(List.of());
//        ships.addAll(ship1.getPoints());
//        ships.addAll(ship2.getPoints());
//
//        List<Point> opponentStrikes = List.of(new Point(1, 4, false), new Point(1, 5, false), new Point(5, 7, true), new Point(5, 9, false), new Point(1, 1, false), new Point(10, 10, false));
//
//        strikesMap.illustrateStrikeMap(map, strikes);
//        shipsMap.illustrateShipMap(map, ships, opponentStrikes);

        Randomizer randomizer = new Randomizer();
        Scanner scanner = new Scanner(System.in);
        int round = 1;
        int playerScore = 0;
        int aiScore = 0;
        ShipCreator shipCreator = new ShipCreator();
        PointsCalculator pointsCalculator = new PointsCalculator();

        MapIllustrator playerStrikesMap = new MapIllustrator();
        MapIllustrator playerShipsMap = new MapIllustrator();

        //player section
        Player player = new Player();
        List<Ship> playerShips = new ArrayList<>(List.of());
        Ship playerBattleship = shipCreator.createShip(5, playerShips);
        playerShips.add(playerBattleship);
        Ship playerDestroyer1 = shipCreator.createShip(4, playerShips);
        playerShips.add(playerDestroyer1);
        Ship playerDestroyer2 = shipCreator.createShip(4, playerShips);
        playerShips.add(playerDestroyer2);
        final List<Point> playerAllShipsPoints = shipCreator.getAllShipsPoints(playerShips);

        List<Point> playerStrikes = new ArrayList<>(List.of());

        player.setHits(playerStrikes);
        player.setShips(playerShips);
        player.setScore(playerScore);
        // ai section
        Player ai = new Player();
        List<Ship> aiShips = new ArrayList<>(List.of());
        Ship aiBattleship = shipCreator.createShip(5, aiShips);
        aiShips.add(aiBattleship);
        Ship aiDestroyer1 = shipCreator.createShip(4, aiShips);
        aiShips.add(aiDestroyer1);
        Ship aiDestroyer2 = shipCreator.createShip(4, aiShips);
        aiShips.add(aiDestroyer2);

        List<Point> aiStrikes = new ArrayList<>(List.of());

        ai.setName("AI");
        ai.setHits(aiStrikes);
        ai.setShips(aiShips);
        ai.setScore(aiScore);

        //game
        System.out.println("Welcome into Battleships game");
        System.out.print("Please type your name: ");
        String name = scanner.next();
        System.out.println(name + " and AIs ships are randomly generated");
        System.out.println("To shoot type (X,Y) coordinates when asked for");
        System.out.println("Generating maps...");

        player.setName(name);
//            TODO: user input validation
        do {
            System.out.println("Round: " + round);
            System.out.println(name + " score: " + player.getScore());
            System.out.println("AI score: " + ai.getScore());
            playerStrikesMap.illustrateStrikeMap(map, player.getHits());
            playerShipsMap.illustrateShipMap(map, player.getShips(), ai.getHits());
            System.out.println("Enter coordinates to shoot, remember: X range (1 - 10), Y range (A -J)");
            System.out.print("Y: ");
            int x = scanner.nextInt();
            System.out.print("X: ");
            int y = scanner.nextInt();
            System.out.println("Your point is: (" + y + "," + x + ")");
            Point playerTarget = new Point(x, y);
            boolean isHit;
            isHit = pointsCalculator.checkHit(playerTarget, aiShips);
            if(isHit) {
                pointsCalculator.recalculatePoints(player, isHit);
                System.out.println("You hit the enemy target!");
                playerTarget.setHit(true);
            }
            player.getHits().add(playerTarget);
            if(player.getScore() == 13) {
                loop = false;
                System.out.println("Congratulations " + player.getName() + " you won!");
            }

            Point aiTarget = randomizer.getRandomPoint();
            isHit = pointsCalculator.checkHit(aiTarget, playerShips);
            if(isHit) {
                pointsCalculator.recalculatePoints(ai, isHit);
                System.out.println("WARNING your ship is under attack!");
                aiTarget.setHit(true);
                //TODO: if hit validate user ships -> change isHit to true at target
            }
            ai.getHits().add(aiTarget);
            if(ai.getScore() == 13) {
                loop = false;
                System.out.println("Congratulations " + player.getName() + " you won!");
            }

            round ++;
        } while (loop);

    }
}
