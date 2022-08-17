package Model;

import View.DungeonView;

import java.io.Serializable;
import java.util.*;

public class Dungeon implements Serializable {

    // **************************** Nested Class ****************************

    /**
     * This class stores a pair of integers representing an x and a y value,
     * Coordinates, in the 2D array of Rooms composing Dungeon. Not designed
     * for use outside of Dungeon.
     */
    public class Coordinates {
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
         * @param theX the value to add or subtract to the current X
         */
        void updateX(int theX) {
            myX += theX;
            myCurrentRoom = myMazeOfRooms[myX][myY];
        }

        /**
         * @param theY the value to add or subtract to the current Y
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
    private Coordinates myCurrentLocation;
    private Coordinates myEntrance;
    private Coordinates myExit;
    private final Room[][] myMazeOfRooms;
    public Room myCurrentRoom;

    private final int myColumns;
    private final int myRows;

    // ************************** Constructors ************************

    /**
     * @param theRows number of rows for 2D array of Rooms
     * @param theColumns number of rows for 2D array of Rooms
     */
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
     * This method updates the adventurer's inventory counts based on what they
     * find in the room
     * @param theAdventurer the adventurer in the dungeon
     */
    private void autoPickUpItems(final Adventurer theAdventurer){
        pickUpPillar(theAdventurer);
        pickUpPotions(theAdventurer);
    }

    /**
     * Checks the room for a pillar and notifies the player what pillar
     * they've found. It then updates the adventurer to have the pillar
     * in their collection and removes the pillar from the room.
     * @param theAdventurer the adventurer exploring the dungeon
     */
    private void pickUpPillar(final Adventurer theAdventurer) {
        if(!myCurrentRoom.getPillar().equals("")) {
            StringBuilder sb = new StringBuilder("You have found the pillar of ");
            String pillar = myCurrentRoom.getPillar();
            switch (pillar) {
                case "A" -> sb.append("Abstraction!");
                case "E" -> sb.append("Encapsulation!");
                case "I" -> sb.append("Inheritance!");
                case "P" -> sb.append("Polymorphism!");
                default -> sb.append("Dungeon: pickUpPillar broke");
            }
            DungeonView.informUser(sb.toString());
            theAdventurer.setListOfPillars(pillar); //update Adventurer's pillars
            DungeonView.informUser("Your current Pillars: " +
                    theAdventurer.getListOfPillars());
            myCurrentRoom.setPillar(""); //remove pillar from the room
        }
    }

    /**
     * This method checks the room for healing potions and vision potions.
     * It then updates the adventurer and room of the change, and passes a
     * message to the view class to display
     * @param theAdventurer the adventurer exploring the dungeon
     */
    private void pickUpPotions(final Adventurer theAdventurer) {
        if(myCurrentRoom.getHealingPotion()) {
            DungeonView.informUser("You have found a healing potion!");
            theAdventurer.setHealingPotions(1);
            myCurrentRoom.setHealingPotion(false);
            DungeonView.informUser("You currently have " +
                    theAdventurer.getHealingPotions() + " healing potions.");
        }
        if(myCurrentRoom.getVisionPotion()) {
            DungeonView.informUser("You have found a vision potion!");
            theAdventurer.setVisionPotions(1);
            myCurrentRoom.setVisionPotion(false);
            DungeonView.informUser("You currently have " +
                    theAdventurer.getVisionPotions() + " vision potions.");
        }
    }
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
        Coordinates temp = new Coordinates(tempX, tempY);
        int visitedRooms;
        Stack<Coordinates> mazeStack = new Stack<Coordinates>();
        mazeStack.push(temp);
        myCurrentRoom.setVisitedStatus(true);
        visitedRooms = 1;
        // visit rooms until all rooms are visited
        while (visitedRooms < (myRows * myColumns) + 1) {
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
                Coordinates temp2 = new Coordinates(tempX, tempY);
                mazeStack.push(temp2);
                myCurrentRoom.setVisitedStatus(true);
                visitedRooms++;

            } else {
                if(visitedRooms == myRows * myColumns) {
                    break;
                }
                myCurrentLocation = mazeStack.pop();
            }
        }
        myCurrentLocation = getEntrance();
        updateCurrentRoom(myCurrentLocation);
        closeMazeDoors();
    }

    /**
     * @param theStack a stack of Rooms
     * @return the Room at the top of the stack without offset
     */
    private Room roomOffset(Stack<Coordinates> theStack) {
        int x = theStack.peek().getX();
        int y = theStack.peek().getY();
        return myMazeOfRooms[x][y];
    }

    /**
     * Precondition: offsets applied must lead to an existing Room
     * @param theStack a stack of Rooms
     * @param theDirection the direction to offset
     * @return the Room based on the Room at the top of the stack offset
     * in the given direction
     */
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

    public void teleport(final int theX, final int theY, Adventurer theAdventurer) {
        Coordinates teleportLocation = new Coordinates(theX,theY);
        updateCurrentRoom(teleportLocation);
        DungeonView.informUser("You are currently at: " + myCurrentLocation);
        DungeonView.informUser(myCurrentRoom.toString());
        adventurerInteractions(theAdventurer);
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
     * Checks if the adventurer is on the edge of the map and the direction
     * they want to move has an open door before updating the Coordinates
     * of the adventurer
     *
     * @param theMove the direction to move
     */
    public void move(final Direction theMove, final Adventurer theAdventurer) {
        if (theMove.equals(Direction.LEFT) && (getAdventurerY() - 1 >= 0) &&
                myCurrentRoom.getWestDoor().equals(DoorStatus.OPEN)) {
            myCurrentLocation.updateY(-1);
        } else if (theMove.equals(Direction.RIGHT) && getAdventurerY() + 1 < myColumns &&
                myCurrentRoom.getEastDoor().equals(DoorStatus.OPEN)) {
            myCurrentLocation.updateY(1);
        } else if (theMove.equals(Direction.UP) && getAdventurerX() - 1 >= 0 &&
                myCurrentRoom.getNorthDoor().equals(DoorStatus.OPEN)) {
            myCurrentLocation.updateX(-1);
        } else if (theMove.equals(Direction.DOWN) && getAdventurerX() + 1 < myRows &&
                myCurrentRoom.getSouthDoor().equals(DoorStatus.OPEN)) {
            myCurrentLocation.updateX(1);
        } else {

        }
        DungeonView.informUser("You are currently at " +
                myCurrentLocation.toString());
        DungeonView.informUser(myCurrentRoom.toString());
        updateCurrentRoom();
        adventurerInteractions(theAdventurer);
    }

    /**
     * This method summarizes the interactions an adventurer would have.
     * @param theAdventurer the adventurer wandering the dungeon
     */
    private void adventurerInteractions(Adventurer theAdventurer){
        checkPitInteraction(theAdventurer);
        autoPickUpItems(theAdventurer);
        checkForMonsters();
    }

    /**
     * Checks to see if there's a pit in the room and causes the adventurer
     * to take damage if there is.
     * @param theAdventurer the adventurer wandering the dungeon
     */
    private void checkPitInteraction(Adventurer theAdventurer) {
        int pitDamage = 10;
        if(myCurrentRoom.getPit()) {
            theAdventurer.takeDamage(pitDamage);

            DungeonView.informUser("You have fallen into a pit! -"
                    + pitDamage + " health");
            DungeonView.informUser("Your current health is: " +
                    theAdventurer.getCharacter().getHealth());
        }
    }

    /**
     * Checks the current room for a monster and generates a notification for the player
     */
    private void checkForMonsters(){
        if (myCurrentRoom.hasLiveMonster()) {
            DungeonView.informUser("You have encountered a monster: " +
                myCurrentRoom.getMonster().getName());
        }
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
    public Coordinates getCurrentLocation() {
        return myCurrentLocation;
    }

    /**
     * @return the String representation of the Dungeon after using a vision potion
     */
    public String getVisionPotionView() {
        int x = getAdventurerX();
        int y = getAdventurerY();
        int xStart;
        int xStop;
        int yStart;
        int yStop;

        // set x boundaries
        if(x > 0) {
            xStart = x - 1;
        } else {
            xStart = x;
        }
        if (x < myRows - 1) {
            xStop = x + 1;
        } else {
            xStop = x;
        }

        // set y boundaries
        if(y > 0) {
            yStart = y - 1;
        } else {
            yStart = y;
        }
        if (y < myColumns - 1) {
            yStop = y + 1;
        } else {
            yStop = y;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = xStart; i < xStop + 1; i++) {

            // create Model.Room top row
            for (int j = yStart; j < yStop + 1; j++) {
                sb.append("*"); // top-left corner
                sb.append((printNSDoor(myMazeOfRooms[i][j].getNorthDoor()))); // North door
                //sb.append("*"); // top-right corner
            }
            sb.append("*\n"); // go to middle row

            // create Model.Room middle row
            for (int j = yStart; j < yStop + 1; j++) {
                sb.append((printEWDoor(myMazeOfRooms[i][j].getWestDoor()))); // West door
                sb.append((myMazeOfRooms[i][j].getMiddle())); // Contents of Model.Room
                //sb.append(printEWDoor(myMazeOfRooms[i][j].getEastDoor())); // East door
            }
            sb.append(printEWDoor(myMazeOfRooms[i][myColumns - 1].getEastDoor())); // East door
            sb.append("\n"); // go to bottom row

            if (i == (myRows - 1)) {
                // create Model.Room bottom row
                for (int j = yStart; j < yStop + 1; j++) {
                    sb.append("*"); // bottom-left corner
                    sb.append(printNSDoor((myMazeOfRooms[i][j].getSouthDoor()))); // South door
                }
                sb.append("*"); // bottom-right corner
            }
        }
        return sb.toString();
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
    public void updateCurrentRoom(Coordinates theRoom) {
        myCurrentLocation = theRoom;
        myCurrentRoom = myMazeOfRooms[theRoom.getX()][theRoom.getY()];
    }

    public Room getCurrentRoom() {
        return myCurrentRoom;
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
//    public static void main(String[] args) {
//        Dungeon dungeon = new Dungeon(4, 4);
//        System.out.println(dungeon);
//        System.out.println();
//        System.out.println(dungeon.getCurrentLocation().toString());
//        System.out.println("Vision potion view");
//        System.out.println(dungeon.getVisionPotionView());
//
//    }
}