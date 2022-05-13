import model.Map;
import model.Player;
import model.Point;
import model.Ship;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static model.Colors.ANSI_RED;
import static model.Colors.ANSI_WHITE;

public class Game {
    private final Randomizer randomizer = new Randomizer();
    private final Scanner scanner = new Scanner(System.in);
    private final ShipCreator shipCreator = new ShipCreator();
    private final PointsCalculator pointsCalculator = new PointsCalculator();
    private final MapIllustrator mapIllustrator = new MapIllustrator();
    private final int mapSize = 10;
    private final Map map = new Map(new int[mapSize][mapSize]);
    private int round = 1;

    public void start() {
        boolean loop = true;

        final String name = welcomeCommunicate(scanner);

        Player player = createNewPlayer(name, shipCreator);
        Player ai = createNewPlayer("ai", shipCreator);

        do {
            System.out.println(ANSI_RED.getColor() + "\n\n\nRound: " + round);
            System.out.println(player.getName() + " score: " + player.getScore());
            System.out.println("AI score: " + ai.getScore());
            mapIllustrator.illustrateStrikeMap(map, player.getHits());
            mapIllustrator.illustrateShipMap(map, player.getShips(), ai.getHits());

            Point playerTarget = addNewPointValidation(player.getHits(), scanner);
            boolean isHit;
            isHit = pointsCalculator.checkHit(playerTarget, ai.getShips());
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
            isHit = pointsCalculator.checkHit(aiTarget, player.getShips());
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

    private String welcomeCommunicate(Scanner scanner) {
        System.out.println(ANSI_WHITE.getColor() + "Welcome into Battleships game");
        System.out.print("Please type your name: ");
        String name = scanner.next();
        System.out.println(name + " and AIs ships are randomly generated");
        System.out.println("To shoot type (X,Y) coordinates when asked for");
        System.out.println("Generating maps...");
        return name;
    }

    private List<Ship> changePointIsHitValue(Point target, List<Ship> ships) {
        for (Ship ship : ships) {
            for (Point point : ship.getPoints()) {
                if (point.getX() == target.getX() && point.getY() == target.getY()) {
                    point.setHit(true);
                }
            }
        }
        return ships;
    }

    private Point addNewPointValidation(List<Point> hits, Scanner scanner) {
        boolean loop = true;
        Point newHit = new Point();
        do {
            System.out.println(ANSI_WHITE.getColor() + "Enter coordinates to shoot, remember: X range (1 - 10), Y range (A -J)");
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
                    System.out.println("Wrong Y coordinate");
                }
            } else {
                System.out.println("Wrong X coordinate");
            }
        } while (loop);
        return newHit;
    }

    private Boolean inputXValidation(int x) {
        return x >= 1 && x <= 10;
    }

    private Boolean inputYValidation(String y) {
        if (y.length() == 1) {
            return true;
        } else {
            System.out.println("Y value is invalid, should contain one character");
        }
        return false;
    }

    private Integer letterToInt(String y) {
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

    private Player createNewPlayer(String name, ShipCreator shipCreator) {
        Player player = new Player();
        List<Ship> ships = new ArrayList<>(List.of());
        Ship aiBattleship = shipCreator.createShip(5, ships);
        ships.add(aiBattleship);
        Ship aiDestroyer1 = shipCreator.createShip(4, ships);
        ships.add(aiDestroyer1);
        Ship aiDestroyer2 = shipCreator.createShip(4, ships);
        ships.add(aiDestroyer2);

        List<Point> strikes = new ArrayList<>(List.of());

        player.setHits(strikes);
        player.setShips(ships);
        player.setScore(0);
        player.setName(name);

        return player;
    }
}
