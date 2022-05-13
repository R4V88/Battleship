import org.junit.jupiter.api.BeforeEach;

class GameTest {
    private Game underTest;

    @BeforeEach
    void setUp() {
        underTest = new Game();
    }

//    @Test
//    void newMapWithGivenSizeShouldBeCreated(){
//        //Given
//        int mapSize = 10;
//
//        //When
//        Map newMap = underTest.createNewMap(mapSize);
//
//        //Then
//        assertEquals(10, newMap.getMap()[0].length);
//        assertEquals(10, newMap.getMap()[1].length);
//    }
}