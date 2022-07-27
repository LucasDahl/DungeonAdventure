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
        placeExit();
        placePillars();


    }

    private void generateRandomMaze() {


        for (int row = 0; row < myMazeOfRooms.length; row++) {
            for (int col = 0; col < myMazeOfRooms[row].length; col++) {
                myMazeOfRooms[row][col] = new Room();
            }
        }

    }

    private boolean isTraversalPossible() {

        //todo implement a recursive backtracking algorithm to ensure it is possible to traverse from the entrance to exit
        return false;
    }


    private void move(final int theXCoordinate, final int theYCoordinate) {
        //todo just try to move from one room to another.
        // use Room toString() to possibly display each room for now.


    }

    private void placeEntrance() {


        //todo place the entrance in a random location
        // possibly need to save this location
        // I need to check if the random location that I want to place the exit in doesn't have the exit in it
        Random random = new Random();


        //myMazeOfRooms[random.nextInt(myRows)][random.nextInt(myColumns)].setEntrance(true);

        boolean isExit = myMazeOfRooms[0][0].getExit();
        // if this room is not the exit then I want to set it to be the entrance
        if (!isExit) {
            myMazeOfRooms[0][0].emptyRoom();
            myMazeOfRooms[0][0].setEntrance(true);

        }


//        System.out.println(myMazeOfRooms[0][0].getEntrance());

    }

    private void placeExit() {
        Random random = new Random();


        //todo place the exit in a random location
        // I need to check if the random location that I want to place the exit in doesn't have the entrance in it

        //myMazeOfRooms[random.nextInt(myRows)][random.nextInt(myColumns)].getEntrance();

        boolean isEntrance = myMazeOfRooms[0][0].getEntrance();
        System.out.println(isEntrance);
        // if this room is not the entrance then I want to set it to be the exit
        if (!isEntrance) {
            myMazeOfRooms[0][0].emptyRoom();
            myMazeOfRooms[0][0].setExit(true);


        }

        System.out.println(isEntrance);


    }

    private void placePillars() {

        //todo make sure all 4 pillars are placed in a dungeon (maze)
        // ideas are have it where there is a count of how many pillars there are.
        // while the count is less than 4 keep placing a new pillar.
        // need to check if there is only 1 of each pillar. Don't want duplicates in the maze.


        // if this room where the pillar will be placed is not the entrance or exit
        // and this room does not have any other pillars
        boolean isEntrance = myMazeOfRooms[1][1].getEntrance();
        boolean isExit = myMazeOfRooms[1][1].getExit();

        String pillarLetter = myMazeOfRooms[1][1].getPillar();


        String[] validLetters = {"A", "E", "I", "P"};

        Random random = new Random();
        int randomNumber = random.nextInt(validLetters.length);
        System.out.println(validLetters[randomNumber]);

        String letterToPlace = validLetters[randomNumber];

        if (!isEntrance && !isExit && pillarLetter.equals("")) {
            myMazeOfRooms[1][1].setPillar(letterToPlace);
        }


        System.out.println("Here");
        System.out.println(pillarLetter);


    }

    public String toString() {

        //todo possible refactoring

        // show the information in each room in the dungeon


        StringBuilder dungeonInformation = new StringBuilder();


        for (int row = 0; row < myMazeOfRooms.length; row++) {
            for (int col = 0; col < myMazeOfRooms.length; col++) {
                String pillar = myMazeOfRooms[row][col].getPillar();
                boolean entrance = myMazeOfRooms[row][col].getEntrance();
                boolean exit = myMazeOfRooms[row][col].getExit();
                boolean pit = myMazeOfRooms[row][col].getPit();
                boolean healingPotion = myMazeOfRooms[row][col].getHealingPotion();
                boolean visionPotion = myMazeOfRooms[row][col].getVisionPotion();
                DoorStatus eastDoor = myMazeOfRooms[row][col].getEastDoor();
                DoorStatus northDoor = myMazeOfRooms[row][col].getNorthDoor();
                DoorStatus southDoor = myMazeOfRooms[row][col].getSouthDoor();
                DoorStatus westDoor = myMazeOfRooms[row][col].getWestDoor();

                dungeonInformation.append("\nRoom ").append(row).append(",").append(col).append(" has:").append("\npillar: ").append(pillar).append("\nentrance status: ").append(entrance).append("\nexit status: ").append(exit).append("\npit status: ").append(pit).append("\nhealing potion status: ").append(healingPotion).append("\nvision potion status: ").append(visionPotion).append("\neast door status: ").append(eastDoor).append("\nnorth door status: ").append(northDoor).append("\nsouth door status: ").append(southDoor).append("\nwest door status: ").append(westDoor).append("\n");


            }
        }


        return dungeonInformation.toString();


    }


    public static void main(String args[]) {

        // Room room = new Room();
        // System.out.println(room);
        Dungeon dungeon = new Dungeon(4, 4);
        System.out.println(dungeon.myMazeOfRooms[1][1]);
        System.out.println(dungeon);
        //System.out.println(dungeon);
    }

}
