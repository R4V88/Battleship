import model.Player;
import model.Point;
import model.Ship;

import java.util.List;

public class PointsCalculator {

    Boolean checkHit(Point target, List<Ship> ships) {
        List<Point> shipsPoints = new java.util.ArrayList<>(List.of());
        for (Ship ship : ships) {
            shipsPoints.addAll(ship.getPoints());
        }

        boolean isHit = false;

        for (Point point : shipsPoints) {
            if (target.equals(point)) {
                isHit = true;
            }
        }
        return isHit;
    }

    void recalculatePoints(Player player, Boolean value) {
        if(value) {
            player.setScore(player.getScore() + 1);
        }
    }
}
