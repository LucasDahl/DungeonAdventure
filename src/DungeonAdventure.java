import java.util.Arrays;
import java.util.Scanner;

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

    private static void intro() {
        Scanner input = new Scanner(System.in);
        String defaultName = "nameless bum";
        String playerName = "";
        System.out.println("You are trapped in a dungeon!");
        System.out.println("Only by finding the four Pillars of OO can you leave.");
        System.out.print("What is your name? ");
        String inputName = input.nextLine();
        if (inputName == "") {
            playerName = defaultName;
        } else {
            playerName = inputName;
        }
        System.out.println("Select your class " + playerName + ".\n");
        displayHeroChoices();
        System.out.print ("Type \"w\" for Warrior, \"t\" for Thief, \"p\" for Priestess: ");
        String heroChoice = input.next();
        System.out.println("Hero choice is: " + heroChoice);
        if (heroChoice.equals("w")) {
            myAdventurer = new Adventurer(playerName, new Warrior(playerName));
            System.out.println("Good luck, Warrior " + playerName);
        } else if (heroChoice.equals("t")) {
            myAdventurer = new Adventurer(playerName, new Thief(playerName));
            System.out.println("Good luck, Thief " + playerName);
        } else if (heroChoice.equals("p")) {
            myAdventurer = new Adventurer(playerName, new Priestess(playerName));
            System.out.println("Good luck, Priestess " + playerName);
        } else {
            myAdventurer = new Adventurer(playerName, new Warrior(playerName));
            System.out.println("Good luck, Warrior " + playerName);
        }


    }

    private static void displayHeroChoices() {
        Warrior warrior = new Warrior("Warrior guy");
        Thief thief = new Thief("Thief guy");
        Priestess priestess = new Priestess("Priestess lady");
        // Warrior Thief Priestess
        System.out.println("Warrior's special skill is' : " + warrior.getSpecialSkill());
        System.out.println("Thief's special skill is': " + thief.getMY_SPECIAL_SKILL());
        System.out.println("Priestess's special skill is' : " + priestess.getMySpecialSkill());
    }
    public static void main(String[] args) {
        myDungeon = new MockDungeon(myDungeonRows,myDungeonColumns);
        //System.out.println("Entrance: " + Arrays.toString(myDungeon.getEntrance()));
        //System.out.println("Exit: " + Arrays.toString(myDungeon.getExit()));

        intro();
        System.out.println("Showing dungeon: ");
        System.out.println(myDungeon.toString());

        System.out.println("Dungeon Entrance: ");
        //myDungeon.myAdventurerLocation

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
    private Room[][] myMazeOfRooms;
    private int myAdventurerLocationX;
    private int myAdventurerLocationY;

    private int myColumns;
    private int myRows;

    public MockDungeon(final int theRows, final int theColumns) {
        myRows = theRows;
        myColumns = theColumns;
        myMazeOfRooms = new Room[myRows][myColumns];

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
    private void placeEntrance() {
        myMazeOfRooms[0][0].setEntrance(true);
    }
    private void placeExit() {
        myMazeOfRooms[myRows-1][0].setExit(true);
    }

    private void placePillars() {
        myMazeOfRooms[0][1].setPillar("A");
        myMazeOfRooms[0][2].setPillar("E");
        myMazeOfRooms[1][2].setPillar("I");
        myMazeOfRooms[1][1].setPillar("P");
    }
    private void move() {
        // move adventurer based on user input?
        // what class is doing it?
    }


}



