package Model;

import java.util.Random;

public class Dungeon {
    private Room[][] myMazeOfRooms;
    private int myAdventurerLocationX;
    private int myAdventurerLocationY;

    private int myColumns;
    private int myRows;

    private Room myPillarALocation, myPillarELocation, MyPillarILocation, myPillarPLocation, myCurrentRoom;


    private int myEntranceXCoordinate, myEntranceYCoordinate, myExitXCoordinate, myExitYCoordinate;


    public Dungeon(final int theRows, final int theColumns) {
        myRows = theRows;
        myColumns = theColumns;
        myMazeOfRooms = new Room[myRows][myColumns];
//        Model.Room room = new Model.Room();
        //   System.out.println(room);

        generateRandomMaze();
        placeEntrance();
        placeExit();
        placePillars();
        move(0, 1);


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
        // use Model.Room toString() to possibly display each room for now.
        myAdventurerLocationX = theXCoordinate;
        myAdventurerLocationY = theYCoordinate;


        //how can I move from the entrance to another room?

        // need a field for the current room?

        // the current room by default will always be the entrance

        System.out.println("Current room:");
        System.out.println(myCurrentRoom);

        // to move from one room just update the coordinates of myCurrentRoom

        myCurrentRoom = myMazeOfRooms[theXCoordinate][theYCoordinate];

        System.out.println("The current room after moving:");
        System.out.println(myCurrentRoom);


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
            myEntranceXCoordinate = 0;
            myEntranceYCoordinate = 0;
            myMazeOfRooms[0][0].emptyRoom();
            myMazeOfRooms[0][0].setEntrance(true);
            //  myEntranceLocation =
            myCurrentRoom = myMazeOfRooms[0][0];
//            System.out.println("ENTRANCE");
//            System.out.println(Arrays.deepToString(myEntranceLocation));

        }


//        System.out.println(myMazeOfRooms[0][0].getEntrance());

    }

    private void placeExit() {
        Random random = new Random();


        //todo place the exit in a random location
        // I need to check if the random location that I want to place the exit in doesn't have the entrance in it

        //myMazeOfRooms[random.nextInt(myRows)][random.nextInt(myColumns)].getEntrance();

        boolean isEntrance = myMazeOfRooms[0][1].getEntrance();
        System.out.println(isEntrance);
        // if this room is not the entrance then I want to set it to be the exit
        if (!isEntrance) {
            myExitXCoordinate = 0;
            myEntranceYCoordinate = 1;
            myMazeOfRooms[0][1].emptyRoom();
            myMazeOfRooms[0][1].setExit(true);
            // myExitLocation = myMazeOfRooms[0][1];
            System.out.println("EXIT");
            //System.out.println(myExitLocation);


        }

        System.out.println(isEntrance);


    }

    public int[] getEntrance() {
        int[] coordinates = {myEntranceXCoordinate, myEntranceYCoordinate};
        return coordinates;
    }

    public int[] getExit() {
        int[] coordinates = {myExitXCoordinate, myExitYCoordinate};
        return coordinates;
    }

    public int[] getAdventurerLocation() {
        int[] coordinates = {myAdventurerLocationX, myAdventurerLocationY};
        return coordinates;
    }

    private void placePillars() {

        myMazeOfRooms[0][2].setPillar("A");
        myMazeOfRooms[1][0].setPillar("E");
        myMazeOfRooms[1][1].setPillar("I");
        myMazeOfRooms[1][2].setPillar("P");


        // if this room where the pillar will be placed is not the entrance or exit
        // and this room does not have any other pillars
//        boolean isEntrance_A = myMazeOfRooms[0][2].getEntrance();
//        boolean isExit_A = myMazeOfRooms[0][2].getExit();
//        String pillarLetter_A = myMazeOfRooms[0][2].getPillar();
//
//        boolean isEntrance_E = myMazeOfRooms[0][3].getEntrance();
//        boolean isExit_E = myMazeOfRooms[0][3].getExit();
//        String pillarLetter_E = myMazeOfRooms[0][3].getPillar();
//
//        boolean isEntrance_I = myMazeOfRooms[1][0].getEntrance();
//        boolean isExit_I = myMazeOfRooms[1][0].getExit();
//        String pillarLetter_I = myMazeOfRooms[1][0].getPillar();
//
//        boolean isEntrance_P = myMazeOfRooms[1][1].getEntrance();
//        boolean isExit_P = myMazeOfRooms[1][1].getExit();
//        String pillarLetter_P = myMazeOfRooms[1][1].getPillar();
//
//        String letterToPlace = getRandomPillarLetter();
//
//        int count = 0;
//
//        if (!isEntrance_A && !isExit_A && pillarLetter_A.equals("")) {
////            myMazeOfRooms[1][1].setPillar(letterToPlace);
//            if (letterToPlace.equals("A") && count < 1) {
//                System.out.println("Place A");
//                myMazeOfRooms[0][2].setPillar(letterToPlace);
//                count += 1;
//            }
//        }
//        if (!isEntrance_E && !isExit_E && pillarLetter_E.equals("")) {
//            if (letterToPlace.equals("E")) {
//                System.out.println("Place E");
//                myMazeOfRooms[0][3].setPillar(letterToPlace);
//            }
//        }
//
//        if (!isEntrance_I && !isExit_I && pillarLetter_I.equals("")) {
//            if (letterToPlace.equals("I")) {
//                System.out.println("Place I");
//                myMazeOfRooms[1][0].setPillar(letterToPlace);
//            }
//        }
//
//        if (!isEntrance_P && !isExit_P && pillarLetter_P.equals("")) {
//            if (letterToPlace.equals("P")) {
//                System.out.println("Place P");
//                myMazeOfRooms[1][1].setPillar(letterToPlace);
//            }
//        }
//
//        System.out.println("Here");
    }

    private String getRandomPillarLetter() {
        String[] validLetters = {"A", "E", "I", "P"};

        Random random = new Random();
        int randomNumber = random.nextInt(validLetters.length);
        System.out.println(validLetters[randomNumber]);

        return validLetters[randomNumber];
    }

    public String toString() {

        //todo possible refactoring

        // show the information in each room in the dungeon


        StringBuilder dungeonInformation = new StringBuilder();


        System.out.println("MAZE LENGTH" + myMazeOfRooms.length);

        for (int row = 0; row < myMazeOfRooms.length; row++) {
            for (int col = 0; col < myMazeOfRooms[row].length; col++) {
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

                dungeonInformation.append("\nModel.Room ").append(row).append(",").append(col).append(" has:").append("\npillar: ").append(pillar).append("\nentrance status: ").append(entrance).append("\nexit status: ").append(exit).append("\npit status: ").append(pit).append("\nhealing potion status: ").append(healingPotion).append("\nvision potion status: ").append(visionPotion).append("\neast door status: ").append(eastDoor).append("\nnorth door status: ").append(northDoor).append("\nsouth door status: ").append(southDoor).append("\nwest door status: ").append(westDoor).append("\n");


            }
        }


        return dungeonInformation.toString();


    }


    public static void main(String args[]) {

        // Model.Room room = new Model.Room();
        // System.out.println(room);
        Dungeon dungeon = new Dungeon(2, 3);
        //   System.out.println(dungeon.myMazeOfRooms[1][1]);
        // System.out.println(dungeon);
        //System.out.println(dungeon);
    }

}
