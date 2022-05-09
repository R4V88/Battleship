import model.Point;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

class MapIllustratorTest {
    private final MapIllustrator mapIllustrator = new MapIllustrator();
    List<Point> hits;
    List<Point> ships;
    List<Point> checkedList;

    @DisplayName("Generated set should cointan 2 points")
    @Test
    void shouldContainTwoElements() {
        //GIVEN
        hits = List.of(new Point(1, 4), new Point(1, 6));
        ships = List.of(new Point(1, 4), new Point(1, 6));

        //WHEN
        HashSet<Point> points = mapIllustrator.gotHit(hits, ships);

        //THEN
        Assertions.assertEquals(2, points.size());
    }

    @DisplayName("Added points should have isHit value set to true")
    @Test
    void whenObjectsShouldAddWithPositiveValue() {
        //GIVEN
        hits = List.of(new Point(1, 4), new Point(1, 6));
        ships = List.of(new Point(1, 4), new Point(1, 6));

        //WHEN
        HashSet<Point> points = mapIllustrator.gotHit(hits, ships);

        //THEN
        for (Point point : points) {
            Assertions.assertTrue(point.isHit());
        }
    }

    @DisplayName("Added point should have isHit value set to false")
    @Test
    void whenObjectsShouldAddWithNegativeValue() {
        //GIVEN
        hits = List.of(new Point(1, 4), new Point(1, 6));
        ships = List.of(new Point(2, 4), new Point(1, 3));

        //WHEN
        HashSet<Point> points = mapIllustrator.gotHit(hits, ships);

        //THEN
        for (Point point : points) {
            Assertions.assertFalse(point.isHit());
        }
    }

    @DisplayName("Amount of hit targets should be equal to missed targets")
    @Test
    void whenShouldBeTheSameAmountOfPositiveAndNegativeValues() {
        //GIVEN
        hits = List.of(new Point(1, 4), new Point(1, 6), new Point(4, 8), new Point(3,6), new Point(2,4));
        ships = List.of(new Point(2, 4), new Point(1, 6), new Point(3 , 6), new Point(7, 1));

        //WHEN
        HashSet<Point> points = mapIllustrator.gotHit(hits, ships);

        //THEN
        List<Point> positives = new java.util.ArrayList<>(Collections.emptyList());
        List<Point> negatives = new java.util.ArrayList<>(List.of());
        for (Point point : points) {
            if(point.isHit()){
                positives.add(point);
            } else {
                negatives.add(point);
            }
        }
        Assertions.assertEquals(3, positives.size());
        Assertions.assertEquals(2, negatives.size());
        for(Point point: positives) {
            Assertions.assertTrue(point.isHit());
        }
        for (Point point: negatives){
            Assertions.assertFalse(point.isHit());
        }
    }
}