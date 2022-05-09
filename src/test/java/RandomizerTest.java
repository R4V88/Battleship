import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class RandomizerTest {
    private final Randomizer randomizer = new Randomizer();

    @Test
    void randomPositionShouldBeHorizontalOrVertical() {
        //WHEN
        String position = randomizer.randomPosition();

        //THEN
//        if(position.equals("Horizontal")) {
//            Assertions.assertEquals("Horizontal", position);
//        }
//        if(position.equals("Vertical")) {
            Assertions.assertEquals("Vertical", position);
//        }

    }
}