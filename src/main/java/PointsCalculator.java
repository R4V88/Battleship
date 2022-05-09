import model.Player;
import model.Point;

import java.util.List;

public class PointsCalculator {

    Boolean checkHit(Point target, List<Point> ship) {
        boolean isHit = false;
        for (Point point : ship) {
            if (point.getX() == target.getX() && point.getY() == target.getY()) {
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
