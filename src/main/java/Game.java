import model.Map;
import model.Player;
import model.Point;
import model.Ship;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static model.Colors.ANSI_GREEN;
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
    private final int overRange = 100;
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
            boolean win;

            isHit = pointsCalculator.checkHit(playerTarget, ai.getShips());
            playerTarget.setHit(isHit);

            if (playerTarget.isHit() && !player.getHits().contains(playerTarget)) {
                pointsCalculator.recalculatePoints(player, isHit);
                System.out.println(ANSI_GREEN.getColor() + "You hit the enemy target!");
                player.getHits().add(playerTarget);
            } else if (!playerTarget.isHit() && !player.getHits().contains(playerTarget)) {
                player.getHits().add(playerTarget);
            }

            win = isWin(ai, player.getName());
            if (win) {
                loop = false;
            }

            Point aiTarget = randomizer.getRandomPoint(ai.getHits());

            isHit = pointsCalculator.checkHit(aiTarget, player.getShips());
            aiTarget.setHit(isHit);

            if (aiTarget.isHit() && !ai.getHits().contains(aiTarget)) {
                pointsCalculator.recalculatePoints(ai, isHit);
                System.out.println(ANSI_RED.getColor() + "WARNING your ship is under attack!");
                aiTarget.setHit(true);
                final List<Ship> updatedShips = changePointIsHitValue(aiTarget, player);
                player.setShips(updatedShips);
                ai.getHits().add(aiTarget);
            } else if (!aiTarget.isHit() && !ai.getHits().contains(aiTarget)) {
                ai.getHits().add(aiTarget);
            }

            win = isWin(player, ai.getName());
            if (win) {
                loop = false;
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

    private Boolean isWin(Player player, String name) {
        if (player.getShips().stream().filter(Ship::getIsSunk).count() == player.getShips().size()) {
            System.out.println(ANSI_GREEN.getColor() + "Congratulations " + name + " won!");
            return true;
        }
        return false;
    }

    private List<Ship> changePointIsHitValue(Point target, Player player) {
        List<Ship> ships = player.getShips();
        for (Ship ship : ships) {
            for (Point point : ship.getPoints()) {
                if (point.getX() == target.getX() && point.getY() == target.getY()) {
                    point.setHit(true);
                }
            }
            boolean value = isShipSunk(ship, player.getName());
            ship.setIsSunk(value);
        }
        return ships;
    }

    private Boolean isShipSunk(Ship ship, String name) {
        List<Point> hitShips = ship.getPoints().stream().filter(Point::isHit).collect(Collectors.toList());
        if (hitShips.size() == ship.getPoints().size()) {
            System.out.println("WARNING " + name + "'s ship is sunk...");
            return true;
        }
        return false;
    }

    private Point addNewPointValidation(List<Point> hits, Scanner scanner) {
        boolean loop = true;
        Point newHit = new Point();
        do {
            System.out.println(ANSI_WHITE.getColor() + "Enter coordinates to shoot, remember: X range (1 - 10), Y range (A -J) for example: a10");
            String coordinates = scanner.next();
            String[] coords = validateUserInput(coordinates);
            String xString = coords[0];
            String yString = coords[1];
            if (inputXValidation(yString) && inputYValidation(xString)) {
                List<String> alphabet = List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J");
                int x;
                int y;
                if (alphabet.contains(xString.toUpperCase())) {
                    x = letterToInt(xString);
                    y = stringToInt(yString);
                    if (!hits.contains(new Point(x, y)) && !hits.contains(new Point(x, y, true))) {
                        newHit.setX(x);
                        newHit.setY(y);
                        loop = false;
                        System.out.println("Your point is: (" + xString.toUpperCase() + "," + yString + ")");
                    } else {
                        System.out.println("Wrong point coordinates, try again!");
                    }
                } else {
                    System.out.println("Wrong point coordinates, try again!");
                }
            } else {
                System.out.println("Wrong point coordinates, try again!");
            }
        } while (loop);
        return newHit;
    }

    private String[] validateUserInput(String coordinates) {
        String xString = null;
        String yString = null;
        if (coordinates == null || coordinates.length() > 3 || coordinates.length() < 2) {
            System.out.println("Your input is not valid");
        } else if (coordinates.length() == 3) {
            xString = coordinates.substring(0, 1);
            yString = coordinates.substring(1, 3);
        } else {
            xString = coordinates.substring(0, 1);
            yString = coordinates.substring(1, 2);
        }

        return new String[]{xString, yString};
    }

    private Boolean inputXValidation(String x) {
        try {
            int getX = Integer.parseInt(x);
            return getX >= 1 && getX <= 10;
        } catch (Exception e) {
            return false;
        }
    }

    private Integer stringToInt(String x) {
        int getX = overRange;
        try {
            getX = Integer.parseInt(x);
            return getX;
        } catch (Exception e) {
            System.out.println("Your input is not a valid number");
        }
        return getX;
    }

    private Boolean inputYValidation(String y) {
        String message = "Y value is invalid, should contain one character";
        if (y == null) {
            System.out.println(message);
            return false;
        }
        if (y.length() == 1) {
            return true;
        } else {
            System.out.println(message);
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
