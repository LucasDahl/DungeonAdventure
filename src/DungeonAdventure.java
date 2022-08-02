import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

/*
 * TCSS 360 - Summer 2022
 * Instructor: Tom Capaul
 * Room class for Dungeon Adventure game
 * Package condition: Must be placed in the same package
 * as all component classes.
 */
class DungeonAdventure {
    static MockDungeon myDungeon;
    static Adventurer myAdventurer;
    final static int myDungeonRows = 2;
    final static int myDungeonColumns = 3;
    public static void main(String[] args) {
        myDungeon = new MockDungeon(myDungeonRows,myDungeonColumns);
        //System.out.println("Entrance: " + Arrays.toString(myDungeon.getEntrance()));
        //System.out.println("Exit: " + Arrays.toString(myDungeon.getExit()));

        intro();
        System.out.println();

        System.out.println("Showing dungeon: ");
        System.out.println("Dungeon Entrance: ");
        System.out.println(myDungeon.getEntrance());

        System.out.println("Showing Adventurer Location: ");
        System.out.println(myDungeon.getAdventurerLocation());

        System.out.println("Move right");
        myDungeon.move("d");
        System.out.println("Showing Adventurer Location: ");
        System.out.println(myDungeon.getAdventurerLocation());

        System.out.println("Move right");
        myDungeon.move("d");
        System.out.println("Showing Adventurer Location: ");
        System.out.println(myDungeon.getAdventurerLocation());

        System.out.println("Move down");
        myDungeon.move("s");
        System.out.println("Showing Adventurer Location: ");
        System.out.println(myDungeon.getAdventurerLocation());
    }
    private static void intro() {
        Scanner input = new Scanner(System.in);
        String defaultName = "nameless bum";
        String playerName;
        System.out.println("You are trapped in a dungeon!");
        System.out.println("Only by finding the four Pillars of OO can you leave.");
        System.out.print("What is your name? ");
        String inputName = input.nextLine();
        if (inputName.equals("")) {
            playerName = defaultName;
        } else {
            playerName = inputName;
        }
        System.out.println("Select your class " + playerName + ".\n");
        displayHeroChoices();
        System.out.print ("Type \"w\" for Warrior, \"t\" for Thief, \"p\" for Priestess: ");
        String heroChoice = input.next();
        System.out.println("Hero choice is: " + heroChoice);
        switch (heroChoice) {
            case "t" -> {
                myAdventurer = new Adventurer(new Thief(playerName));
                System.out.println("Good luck, Thief " + playerName);
            }
            case "p" -> {
                myAdventurer = new Adventurer(new Priestess(playerName));
                System.out.println("Good luck, Priestess " + playerName);
            }
            default -> {
                myAdventurer = new Adventurer(new Warrior(playerName));
                System.out.println("Good luck, Warrior " + playerName);
            }
        }


    }

    private static void displayHeroChoices() {
        Warrior warrior = new Warrior("Warrior guy");
        Thief thief = new Thief("Thief guy");
        Priestess priestess = new Priestess("Priestess lady");
        // Warrior Thief Priestess
        System.out.println("Warrior's special skill is: " + warrior.getSpecialSkill());
        System.out.println("Thief's special skill is: " + thief.getMY_SPECIAL_SKILL());
        System.out.println("Priestess's special skill is: " + priestess.getMySpecialSkill());
    }

    private static void displayOptions(){
        // p -pickup, h - heal, v - vision f - fight
        //exit(); - they win
        //pickUpEverything();
        //useHealingPotion();
        //useVisionPotion();
        //displayMoveOptions();
        //checkMonster();
    }
    private static void checkMonster() {
        if(!myDungeon.myCurrentRoom.myMonster1.isDead()) {
            System.out.println("f - fight Monster");
        }
    }

}

// MockDungeon will be 2x3
// *********
// *i||A||E*
// *-**-**-*
// *-**-**-*
// *O||P||I*
// *********
class MockDungeon {
    class Coordinates {
        int myX;
        int myY;

        Coordinates(int theX, int theY) {
            myX = theX;
            myY = theY;
        }
        void updateX(int theX) {
            myX += theX;
            myCurrentRoom = myMazeOfRooms[myX][myY];
        }
        
        void updateY(int theY) {
            myY += theY;
            myCurrentRoom = myMazeOfRooms[myX][myY];
        }
        
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("(").append(myX).append(", ").append(myY).append(")");
            return sb.toString();
        }
    }

    private Coordinates myEntrance;
    private Coordinates myExit;
    private Coordinates myPillarALocation;
    private Coordinates myPillarELocation;
    private Coordinates myPillarILocation;
    private Coordinates myPillarPLocation;
    private Coordinates myCurrentLocation;
    private final Room[][] myMazeOfRooms;
    Room myCurrentRoom;

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
        generateRandomMaze();
    }
    private void generateRandomMaze() {
        Stack path = new Stack();
        // create a random path from the entrance and visit all cells

    }
    private void placeEntrance() {
        myMazeOfRooms[0][0].setEntrance(true);
        myEntrance = new Coordinates(0,0);
        myCurrentLocation = new Coordinates(0,0);
        myCurrentRoom = myMazeOfRooms[0][0];
    }
    private void placeExit() {
        myMazeOfRooms[myRows-1][0].setExit(true);
        myExit = new Coordinates(myRows-1, 0);
    }

    private void placePillars() {
        myMazeOfRooms[0][1].setPillar("A");
        myPillarALocation = new Coordinates(0,1);
        myMazeOfRooms[0][2].setPillar("E");
        myPillarELocation = new Coordinates(0,2);
        myMazeOfRooms[1][2].setPillar("I");
        myPillarILocation = new Coordinates(1,2);
        myMazeOfRooms[1][1].setPillar("P");
        myPillarPLocation = new Coordinates(1,1);
    }

    private boolean isTraversalPossible() {
        boolean pathExists = false;
        //Stack path = new Stack();
        return pathExists;
    }
    void move(String theMove) {
        if (theMove.equals("a") && (getAdventurerY()-1 >=0)) {
            myCurrentLocation.updateY(-1);
        } else if (theMove.equals("d") && getAdventurerY() + 1 < myColumns) {
            myCurrentLocation.updateY(1);

        } else if (theMove.equals("w") && getAdventurerX() -1 >= 0) {
            myCurrentLocation.updateX(-1);
        } else if (theMove.equals("s") && getAdventurerX() + 1 < myRows) {
            myCurrentLocation.updateX(1);
        }
        System.out.println(myMazeOfRooms[getAdventurerX()][getAdventurerY()].toString());
    }
    public Coordinates getEntrance() {
        return myEntrance;
    }

    public Coordinates getExit() {
        return myExit;
    }

    public Coordinates getAdventurerLocation() {
        return myCurrentLocation;
    }

    public int getAdventurerX() {
        return myCurrentLocation.myX;
    }

    public int getAdventurerY() {
        return myCurrentLocation.myY;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Print the dungeon please");
        return sb.toString();
    }
}



