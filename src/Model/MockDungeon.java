package Model;

import Model.DoorStatus;
import Model.Room;

import java.util.*;

public class MockDungeon {


    enum Direction {
        UP, DOWN, LEFT, RIGHT
    }
        // **************************** Nested Class ****************************
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

        public MockDungeon(final int theRows, final int theColumns) {
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

        }

        // ******************************* Methods ******************************
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

        private void placePillars() {
            getEmptyRoom("A");
            getEmptyRoom("E");
            getEmptyRoom("I");
            getEmptyRoom("P");
        }

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

        public Coordinates getEntrance() {
            return myEntrance;
        }
        void move(Direction theMove) {
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

        int getAdventurerX() {
            return myCurrentLocation.myX;
        }

        int getAdventurerY() {
            return myCurrentLocation.myY;
        }
        Coordinates getCurrentLocation() {
            return myCurrentLocation;
        }
        String printNSDoor(DoorStatus theDoor) {
            String str = "";
            if(theDoor.equals(DoorStatus.CLOSED)){
                str = "*";
            } else {
                str = "-";
            }
            return str;
        }
        String printEWDoor(DoorStatus theDoor) {
            String str = "";
            if(theDoor.equals(DoorStatus.CLOSED)) {
                str = "*";
            } else {
                str = "|";
            }
            return str;
        }

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
    }

