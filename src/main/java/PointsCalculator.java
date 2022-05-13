import model.Player;
import model.Point;
import model.Ship;

import java.util.List;

public class PointsCalculator {

    ShipCreator shipCreator = new ShipCreator();

    public Boolean checkHit(Point target, List<Ship> ships) {
        boolean isHit = false;
        List<Point> shipsPoints = shipCreator.getAllShipsPoints(ships);

        for (Point point : shipsPoints) {
            if (point.equals(target)) {
                isHit = true;
            }
        }
        return isHit;
    }

    public void recalculatePoints(Player player, Boolean value) {
        if (value) {
            player.setScore(player.getScore() + 1);
        }
    }
}
