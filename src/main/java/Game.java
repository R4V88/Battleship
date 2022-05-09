import model.Map;

public class Game {

    public Map createNewMap(int size){
        return new Map(new int[size][size]);
    }

    public static void main(String[] args) {

    }
}
