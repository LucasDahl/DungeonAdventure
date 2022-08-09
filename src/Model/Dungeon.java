package Model;

import View.DungeonView;

import java.io.Serializable;
import java.util.*;

public class Dungeon implements Serializable {

    // **************************** Nested Class ****************************

    /**
     * This class stores a pair of integers representing an x and a y value,
     * Coordinates, in the 2D array of Rooms composing Dungeon
     */
    class Coordinates {
        // **************************** Fields ****************************
        int myX;
        int myY;
        // ************************** Constructors ************************
        Coordinates(int theX, int theY) {
            myX = theX;
            myY = theY;
        }
        // **************************** Methods ***************************
        //========
        // Getters
        //========

        int getX() {
            return myX;
        }

        int getY() {
            return myY;
        }

        //========
        // Setters
        //========
        void updateX(int theX) {
            myX += theX;
            myCurrentRoom = myMazeOfRooms[myX][myY];
        }

        void updateY(int theY) {
            myY += theY;
            myCurrentRoom = myMazeOfRooms[myX][myY];
        }

        //=================
        // Override Methods
        //=================
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("(").append(myX).append(", ").append(myY).append(")");
            return sb.toString();
        }
    }

    // ******************************* Fields *******************************
    private Coordinates myEntrance;
    private Coordinates myExit;
    private Coordinates myCurrentLocation;
    private final Room[][] myMazeOfRooms;
    public Room myCurrentRoom;

    private final int myColumns;
    private final int myRows;

    public Dungeon(final int theRows, final int theColumns) {
        myRows = theRows;
        myColumns = theColumns;
        myMazeOfRooms = new Room[myRows][myColumns];

        for (int row = 0; row < myMazeOfRooms.length; row++) {
            for (int col = 0; col < myMazeOfRooms[row].length; col++) {
                myMazeOfRooms[row][col] = new Room();
            }
        }
        generateMaze();
        placeEntrance();
        placeExit();
        placePillars();
        closeEdgeDoors();

    }

    // ******************************* Methods ******************************
    /**
     * This method goes through the dungeon and cloes all the doors
     * at the edge of the dungeon.
     */
    private void closeEdgeDoors() {
        //top edge
        for (int i = 0; i < myColumns; i++){
            myMazeOfRooms[0][i].setNorthDoor(DoorStatus.CLOSED);
        }
        // right edge
        for (int i = 0; i < myRows; i++) {
            myMazeOfRooms[i][myColumns-1].setEastDoor(DoorStatus.CLOSED);
        }
        // bottom edge
        for (int i = 0; i < myColumns; i++) {
            myMazeOfRooms[myRows-1][i].setSouthDoor(DoorStatus.CLOSED);
        }
        // left edge
        for (int i = 0; i < myRows; i++) {
            myMazeOfRooms[i][0].setWestDoor(DoorStatus.CLOSED);
        }
    }

    /**
     * This method randomly generates a maze
     */
    private void generateMaze() {
        // in progress
        // recursive backtracker

    }
    private boolean isTraversalPossible() {
        // in progress
        boolean isTraversalPossible = false;
        Coordinates entrance = getEntrance();
        // try to reach the exit
        Set<Room> visitedRooms = new HashSet<Room>();
        visitedRooms.add(myCurrentRoom);
        return isTraversalPossible;
    }
    /**
     * This method randomly chooses a room in the dungeon and assigns
     * it as the dungeon entrance.
     */
    private void placeEntrance() {
        Random rand = new Random();
        int x, y;
        x = rand.nextInt(myRows);
        y = rand.nextInt(myColumns);
        myMazeOfRooms[x][y].setEntrance(true);

        myEntrance = new Coordinates(x,y);
        myCurrentLocation = new Coordinates(x,y);
        myCurrentRoom = myMazeOfRooms[x][y];
    }

    /**
     * This method randomly chooses a room in the dungeon and assigns
     * it as the dungeon exit.
     */
    private void placeExit() {
        Random rand = new Random();
        int x, y;
        x = rand.nextInt(myRows);
        y = rand.nextInt(myColumns);
        while(myMazeOfRooms[x][y].getEntrance()) {
            x = rand.nextInt(myRows);
            y = rand.nextInt(myColumns);
        }
        myMazeOfRooms[x][y].setExit(true);
        myExit = new Coordinates(x, y);
    }

    /**
     * This method finds a room that isn't the entrance, the exit, or
     * has any pillars.
     */
    private void placePillars() {
        getEmptyRoom("A");
        getEmptyRoom("E");
        getEmptyRoom("I");
        getEmptyRoom("P");
    }

    //========
    // Getters
    //========

    /**
     * This method checks if a random room is an entrance, exit, or
     * has pillars. If the room doesn't have any pillars, this method would
     * set the parameter the Pillar as the room's pillar.
     * @param thePillar a letter (a, e, i, p) representing a Pillar of OO
     */
    private void getEmptyRoom(String thePillar){
        Random rand = new Random();
        int x, y;
        x = rand.nextInt(myRows);
        y = rand.nextInt(myColumns);
        while(myMazeOfRooms[x][y].getEntrance() ||
                myMazeOfRooms[x][y].getExit() ||
                myMazeOfRooms[x][y].getPillar() != "") {
            x = rand.nextInt(myRows);
            y = rand.nextInt(myColumns);
        }
        myMazeOfRooms[x][y].setPillar(thePillar);
    }

    /**
     * @return the Coordinates of the entrance
     */
    public Coordinates getEntrance() {
        return myEntrance;
    }

    /**
     * Checks if the adventurer is on the edge of the map and
     * updates the Coordinates of the adventurer
     * @param theMove the direction to move
     */
    public void move(Direction theMove) {
        if (theMove.equals(Direction.LEFT) && (getAdventurerY()-1 >=0)) {
            myCurrentLocation.updateY(-1);
        } else if (theMove.equals(Direction.RIGHT) && getAdventurerY() + 1 < myColumns) {
            myCurrentLocation.updateY(1);
        } else if (theMove.equals(Direction.UP) && getAdventurerX() -1 >= 0) {
            myCurrentLocation.updateX(-1);
        } else if (theMove.equals(Direction.DOWN) && getAdventurerX() + 1 < myRows) {
            myCurrentLocation.updateX(1);
        } else {
            System.out.println("Never should've gotten here");
        }
        System.out.println(myMazeOfRooms[getAdventurerX()][getAdventurerY()].toString());
    }

    /**
     * @return the Adventurer's X coordinate
     */
    int getAdventurerX() {
        return myCurrentLocation.myX;
    }

    /**
     * @return the Adventurer's Y coordinate
     */
    int getAdventurerY() {
        return myCurrentLocation.myY;
    }

    /**
     * @return the current Coordinates of the adventurer
     */
    Coordinates getCurrentLocation() {
        return myCurrentLocation;
    }

    /**
     * Precondition: It must be used for east or west doors
     * This method checks if the door is open or closed and returns
     * the character representation of the door.
     * @param theDoor DoorStatus OPEN or CLOSED
     * @return * is a closed door. | is an open east/west door.
     */
    String printEWDoor(DoorStatus theDoor) {
        String str = "";
        if(theDoor.equals(DoorStatus.CLOSED)) {
            str = "*";
        } else {
            str = "|";
        }
        return str;
    }
    /**
     * Precondition: It must be used for north or south doors
     * This method checks if the door is open or closed and returns
     * the character representation of the door.
     * @param theDoor DoorStatus OPEN or CLOSED
     * @return * is a closed door. - is an open north/south door.
     */
    String printNSDoor(DoorStatus theDoor) {
        String str = "";
        if(theDoor.equals(DoorStatus.CLOSED)){
            str = "*";
        } else {
            str = "-";
        }
        return str;
    }
    /**
     * update the current room according to the Adventurer's coordinates
     */
    void updateCurrentRoom() {
        myCurrentRoom = myMazeOfRooms[getAdventurerX()][getAdventurerY()];
    }

    /**
     * Use to manually set the Dungeon's current room
     * @param theRoom Coordinates to set as the current room
     */
    void updateCurrentRoom(Coordinates theRoom) {
        myCurrentRoom = myMazeOfRooms[theRoom.getX()][theRoom.getY()];
    }

    //=================
    // Override Methods
    //=================
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < myRows; i++) {

            // create Model.Room top row
            for (int j = 0; j < myColumns; j++) {
                sb.append("*"); // top-left corner
                sb.append((printNSDoor(myMazeOfRooms[i][j].getNorthDoor()))); // North door
                //sb.append("*"); // top-right corner
            }
            sb.append("*\n"); // go to middle row

            // create Model.Room middle row
            for (int j = 0; j < myColumns; j++) {
                sb.append((printEWDoor(myMazeOfRooms[i][j].getWestDoor()))); // West door
                sb.append((myMazeOfRooms[i][j].getMiddle())); // Contents of Model.Room
                //sb.append(printEWDoor(myMazeOfRooms[i][j].getEastDoor())); // East door
            }
            sb.append(printEWDoor(myMazeOfRooms[i][myColumns - 1].getEastDoor())); // East door
            sb.append("\n"); // go to bottom row

            if (i == (myRows - 1)) {
                // create Model.Room bottom row
                for (int j = 0; j < myColumns; j++) {
                    sb.append("*"); // bottom-left corner
                    sb.append(printNSDoor((myMazeOfRooms[i][j].getSouthDoor()))); // South door

                }
                sb.append("*"); // bottom-right corner
            }
        }
        return sb.toString();
    }

    // only here for testing
    public static void main(String[] args) {
        Dungeon dungeon = new Dungeon(3, 3);
        System.out.println("Brand new dungeon");
        System.out.println(dungeon);
    }
    }

