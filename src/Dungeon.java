import java.util.Arrays;
import java.util.Random;

public class Dungeon {
    private Room[][] myMazeOfRooms;
    private int myAdventurerLocationX;
    private int myAdventurerLocationY;

    private int myColumns;
    private int myRows;

    public Dungeon(final int theRows, final int theColumns) {
        myRows = theRows;
        myColumns = theColumns;
        myMazeOfRooms = new Room[myRows][myColumns];
//        Room room = new Room();
        //   System.out.println(room);

        generateRandomMaze();
        placeEntrance();

        for (int i = 0; i < myMazeOfRooms.length; i++) {
            for (int j = 0; j < myMazeOfRooms[i].length; j++) {
                System.out.print(myMazeOfRooms[i][j]);
            }
            System.out.println();
        }

        //  System.out.println(Arrays.deepToString(myMazeOfRooms));

    }

    private void generateRandomMaze() {
        for (int row = 0; row < myMazeOfRooms.length; row++) {
            for (int col = 0; col < myMazeOfRooms[row].length; col++) {
                myMazeOfRooms[row][col] = new Room();
            }
        }

    }

    private boolean isTraversalPossible() {
        return false;
    }

    private void placeEntrance() {


        Random random = new Random();


        myMazeOfRooms[random.nextInt(myRows)][random.nextInt(myColumns)].setEntrance(true);


//        System.out.println(myMazeOfRooms[0][0].getEntrance());

    }

    private void placeExit() {
        Random random = new Random();


        myMazeOfRooms[random.nextInt(myRows)][random.nextInt(myColumns)].getEntrance();


    }

    private void placePillars() {
    }

    public String toString() {
        return "";
    }


    public static void main(String args[]) {
        Dungeon dungeon = new Dungeon(4,4);
    }

}
