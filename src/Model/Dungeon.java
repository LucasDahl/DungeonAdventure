package Model;

import java.io.Serializable;
import java.util.*;

public class Dungeon implements Serializable {

    // **************************** Nested Class ****************************

    /**
     * This class stores a pair of integers representing an x and a y value,
     * Coordinates, in the 2D array of Rooms composing Dungeon. Not designed
     * for use outside of Dungeon.
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

        /**
         * @param theX is the value to add or subtract to the current X
         */
        void updateX(int theX) {
            myX += theX;
            myCurrentRoom = myMazeOfRooms[myX][myY];
        }

        /**
         * @param theY is the value to add or subtract to the current Y
         */
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
        placeEntrance();
        placeExit();
        placePillars();
        closeEdgeDoors();
        generateMaze();
    }

    // ******************************* Methods ******************************

    /**
     * This method goes through the dungeon and closes all the doors
     * at the edge of the dungeon.
     */
    private void closeEdgeDoors() {
        //top edge
        for (int i = 0; i < myColumns; i++) {
            myMazeOfRooms[0][i].setNorthDoor(DoorStatus.CLOSED);
        }
        // right edge
        for (int i = 0; i < myRows; i++) {
            myMazeOfRooms[i][myColumns - 1].setEastDoor(DoorStatus.CLOSED);
        }
        // bottom edge
        for (int i = 0; i < myColumns; i++) {
            myMazeOfRooms[myRows - 1][i].setSouthDoor(DoorStatus.CLOSED);
        }
        // left edge
        for (int i = 0; i < myRows; i++) {
            myMazeOfRooms[i][0].setWestDoor(DoorStatus.CLOSED);
        }
    }

    /**
     * Precondition: the method placeEntrance() must have been executed
     * before this one.
     * This method randomly generates a maze
     */
    private void generateMaze() {
        int tempX = myCurrentLocation.getX();
        int tempY = myCurrentLocation.getY();
        Coordinates temp = new Coordinates(tempX,tempY);
        int visitedRooms;
        Stack<Coordinates> mazeStack = new Stack<Coordinates>();
        mazeStack.push(temp);
        myCurrentRoom.setVisitedStatus(true);
        visitedRooms = 1;
        while (visitedRooms < (myRows * myColumns)) {
            // Create a set of unvisited neighbors
            Vector<Direction> neighbor = new Vector<>();
            // North neighbor -
            if ((mazeStack.peek().getX() > 0) &&
                    !roomOffset(mazeStack, Direction.NORTH).getVisitedStatus()) {
                neighbor.add(Direction.NORTH);
            }
            // East neighbor
            if ((mazeStack.peek().getY() < myColumns - 1) &&
                    !roomOffset(mazeStack, Direction.EAST).getVisitedStatus()) {
                neighbor.add(Direction.EAST);
            }
            // South neighbor
            if (mazeStack.peek().getX() < myRows - 1 &&
                    !roomOffset(mazeStack, Direction.SOUTH).getVisitedStatus()) {
                neighbor.add(Direction.SOUTH);
            }
            // West neighbor
            if ((mazeStack.peek().getY() > 0) &&
                    !roomOffset(mazeStack, Direction.WEST).getVisitedStatus()) {
                neighbor.add(Direction.WEST);
            }

            // Are there any neighbors?
            if (!neighbor.isEmpty()) {
                Random rand = new Random();
                // choose a random neighbor
                Direction nextDirection = neighbor.get(rand.nextInt(neighbor.size()));

                // Create a path between the neighbor and current room
                switch (nextDirection) {
                    case NORTH, UP: {
                        roomOffset(mazeStack).setPath(Direction.NORTH, true);
                        roomOffset(mazeStack, Direction.NORTH).setPath(Direction.SOUTH, true);
                        myCurrentLocation.updateX(-1);
                        break;
                    }
                    case EAST, RIGHT: {
                        roomOffset(mazeStack).setPath(Direction.EAST, true);
                        roomOffset(mazeStack, Direction.EAST).setPath(Direction.WEST, true);
                        myCurrentLocation.updateY(1);
                        break;
                    }
                    case SOUTH, DOWN: {
                        roomOffset(mazeStack).setPath(Direction.SOUTH, true);
                        roomOffset(mazeStack, Direction.SOUTH).setPath(Direction.NORTH, true);
                        myCurrentLocation.updateX(1);
                        break;
                    }
                    case WEST, LEFT: {
                        roomOffset(mazeStack).setPath(Direction.WEST, true);
                        roomOffset(mazeStack, Direction.WEST).setPath(Direction.EAST, true);
                        myCurrentLocation.updateY(-1);
                        break;
                    }
                }
                tempX = myCurrentLocation.getX();
                tempY = myCurrentLocation.getY();
                Coordinates temp2 = new Coordinates(tempX,tempY);
                mazeStack.push(temp2);
                myCurrentRoom.setVisitedStatus(true);
                visitedRooms++;

            } else {
                myCurrentLocation = mazeStack.pop();
            }
            //if
        }
        myCurrentLocation = getEntrance();
        closeMazeDoors();
    }

    private Room roomOffset(Stack<Coordinates> theStack) {
        int x = theStack.peek().getX();
        int y = theStack.peek().getY();
        return myMazeOfRooms[x][y];
    }

    private Room roomOffset(Stack<Coordinates> theStack, Direction theDirection) {
        int x = theStack.peek().getX();
        int y = theStack.peek().getY();
        switch (theDirection) {
            case NORTH, UP -> x--;
            case EAST, RIGHT -> y++;
            case SOUTH, DOWN -> x++;
            case WEST, LEFT -> y--;
        }
        return myMazeOfRooms[x][y];
    }

    /**
     * This is a helper method for generate maze that iterates through
     * the dungeon and checks for paths. If a path doesn't exist between
     * two rooms, the shared door is closed.
     */
    private void closeMazeDoors() {
        for (int i = 0; i < myRows; i++) {
            for (int j = 0; j < myColumns; j++) {
                // if there is no path east in my current room
                if (!myMazeOfRooms[i][j].getPath(Direction.EAST)) {
                    myMazeOfRooms[i][j].setEastDoor(DoorStatus.CLOSED);
                    if (j + 1 < myColumns) { // if I'm not already at the right edge
                        myMazeOfRooms[i][j + 1].setWestDoor(DoorStatus.CLOSED);
                    }
                }
                if (!myMazeOfRooms[i][j].getPath(Direction.SOUTH)) {
                    myMazeOfRooms[i][j].setSouthDoor(DoorStatus.CLOSED);
                    if (i + 1 < myRows) { //if I'm not already at the bottom edge
                        myMazeOfRooms[i + 1][j].setNorthDoor(DoorStatus.CLOSED);
                    }
                }
            }
        }
    }

    private boolean isTraversalPossible() {
        // STILL IN PROGRESS!!! 8/11/2022
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
        myMazeOfRooms[x][y].setVisitedStatus(true);
        myEntrance = new Coordinates(x, y);
        myCurrentLocation = new Coordinates(x, y);
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
        while (myMazeOfRooms[x][y].getEntrance()) {
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
     *
     * @param thePillar a letter (a, e, i, p) representing a Pillar of OO
     */
    private void getEmptyRoom(String thePillar) {
        Random rand = new Random();
        int x, y;
        x = rand.nextInt(myRows);
        y = rand.nextInt(myColumns);
        while (myMazeOfRooms[x][y].getEntrance() ||
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
     *
     * @param theMove the direction to move
     */
    public void move(Direction theMove) {
        if (theMove.equals(Direction.LEFT) && (getAdventurerY() - 1 >= 0)) {
            myCurrentLocation.updateY(-1);
        } else if (theMove.equals(Direction.RIGHT) && getAdventurerY() + 1 < myColumns) {
            myCurrentLocation.updateY(1);
        } else if (theMove.equals(Direction.UP) && getAdventurerX() - 1 >= 0) {
            myCurrentLocation.updateX(-1);
        } else if (theMove.equals(Direction.DOWN) && getAdventurerX() + 1 < myRows) {
            myCurrentLocation.updateX(1);
        } else {
            System.out.println("You cannot go through walls");
        }
        updateCurrentRoom();
        // should this be passed to controller?
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
     *
     * @param theDoor DoorStatus OPEN or CLOSED
     * @return * is a closed door. | is an open east/west door.
     */
    String printEWDoor(DoorStatus theDoor) {
        String str = "";
        if (theDoor.equals(DoorStatus.CLOSED)) {
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
     *
     * @param theDoor DoorStatus OPEN or CLOSED
     * @return * is a closed door. - is an open north/south door.
     */
    String printNSDoor(DoorStatus theDoor) {
        String str = "";
        if (theDoor.equals(DoorStatus.CLOSED)) {
            str = "*";
        } else {
            str = "-";
        }
        return str;
    }

    /**
     * update the current room according to the Adventurer's coordinates.
     * This method should be called when manually updating the current Coordinates
     */
    void updateCurrentRoom() {
        myCurrentRoom = myMazeOfRooms[getAdventurerX()][getAdventurerY()];
    }

    /**
     * Use to manually set the Dungeon's current room
     *
     * @param theRoom Coordinates to set as the current room
     */
    void updateCurrentRoom(Coordinates theRoom) {
        myCurrentLocation = theRoom;
        myCurrentRoom = myMazeOfRooms[theRoom.getX()][theRoom.getY()];
    }

    void updateCurrentRoom(Direction theDirection) {
        switch (theDirection) {
            case NORTH, UP: {
                myCurrentLocation.updateX(1);
            }
        }
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
        System.out.println(dungeon);

    }
}

