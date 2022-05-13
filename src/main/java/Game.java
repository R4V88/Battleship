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

        do {
            System.out.println("\n\n\nRound: " + round);
            System.out.println(player.getName() + " score: " + player.getScore());
            System.out.println("AI score: " + ai.getScore());
            playerStrikesMap.illustrateStrikeMap(map, player.getHits());
            playerShipsMap.illustrateShipMap(map, player.getShips(), ai.getHits());

            Point playerTarget = addNewPointValidation(player.getHits(), scanner);
            boolean isHit;
            isHit = pointsCalculator.checkHit(playerTarget, aiShips);
            if (isHit) {
                pointsCalculator.recalculatePoints(player, isHit);
                System.out.println("You hit the enemy target!");
                playerTarget.setHit(true);
            }
            player.getHits().add(playerTarget);
            if (player.getScore() == 13) {
                loop = false;
                System.out.println("Congratulations " + player.getName() + " you won!");
            }

            Point aiTarget = randomizer.getRandomPoint(ai.getHits());
            isHit = pointsCalculator.checkHit(aiTarget, playerShips);
            if (isHit) {
                pointsCalculator.recalculatePoints(ai, isHit);
                System.out.println("WARNING your ship is under attack!");
                aiTarget.setHit(true);
                final List<Ship> updatedShips = changePointIsHitValue(aiTarget, player.getShips());
                player.setShips(updatedShips);
            }
            ai.getHits().add(aiTarget);
            if (ai.getScore() == 13) {
                loop = false;
                System.out.println("Congratulations " + player.getName() + " you won!");
            }

            round++;
        } while (loop);

    }

    private static List<Ship> changePointIsHitValue(Point target, List<Ship> ships) {
        for (Ship ship : ships) {
            for (Point point : ship.getPoints()) {
                if (point.getX() == target.getX() && point.getY() == target.getY()) {
                    point.setHit(true);
                }
            }
        }
        return ships;
    }

    private static Point addNewPointValidation(List<Point> hits, Scanner scanner) {
        boolean loop = true;
        Point newHit = new Point();
        do {
            System.out.println("Enter coordinates to shoot, remember: X range (1 - 10), Y range (A -J)");
            System.out.print("Y: ");
            String xString = scanner.next();
            System.out.print("X: ");
            int y = scanner.nextInt();
            if (inputXValidation(y) && inputYValidation(xString)) {
                List<String> alphabet = List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J");
                int x;
                if (alphabet.contains(xString.toUpperCase())) {
                    x = letterToInt(xString);
                    if (!hits.contains(new Point(x, y))) {
                        newHit.setX(x);
                        newHit.setY(y);
                        loop = false;
                        System.out.println("Your point is: (" + x + "," + y + ")");
                    } else {
                        System.out.println("Wrong point coordinates, try again!");
                    }
                } else {
                    System.out.println("Wrong Y argument");
                }
            }
        } while (loop);
        return newHit;
    }

    private static Boolean inputXValidation(int x) {
        return x >= 1 && x <= 10;
    }

    private static Boolean inputYValidation(String y) {
        if (y.length() == 1) {
            return true;
        } else {
            System.out.println("Y value is invalid, should contain one character");
        }
        return false;
    }

    private static Integer letterToInt(String y) {
        int returnCoordinate = 0;
        switch (y.toUpperCase()) {
            case "A" -> returnCoordinate = 1;
            case "B" -> returnCoordinate = 2;
            case "C" -> returnCoordinate = 3;
            case "D" -> returnCoordinate = 4;
            case "E" -> returnCoordinate = 5;
            case "F" -> returnCoordinate = 6;
            case "G" -> returnCoordinate = 7;
            case "H" -> returnCoordinate = 8;
            case "I" -> returnCoordinate = 9;
            case "J" -> returnCoordinate = 10;
        }
        return returnCoordinate;
    }
}
