package Controller;

import Model.*;
import View.DungeonView;

import java.util.Scanner;

import static Model.Direction.*;


/*
 * TCSS 360 - Summer 2022
 * Instructor: Tom Capaul
 * Model.Room class for Model.Dungeon Adventure game
 * Package condition: Must be placed in the same package
 * as all component classes.
 */

// SERIALIZE HERE! MAKE DUNGEON AND ADVENTURER INSTANCES, NOT STATIC
public class DungeonAdventure implements Runnable {
    //singleton - eager instance
    private static DungeonAdventure myDungeonAdventure = new DungeonAdventure();
    private Dungeon myDungeon;
    private Adventurer myAdventurer;

    private String myPlayerName;
    final static int DUNGEON_ROWS = 4;
    final static int DUNGEON_COLUMNS = 4;

    //private boolean myAreDoorsOpen;


    private Thread myGameThread;

    private DungeonAdventure() {
        myDungeon = new Dungeon(DUNGEON_ROWS, DUNGEON_COLUMNS);
        // startGameThread(); in DungeonView

    }

    /**
     * Single point of access for DungeonAdventure
     *
     * @return the only instance of DungeonAdventure allowed
     */
    public static synchronized DungeonAdventure getDungeonAdventure() {
        return myDungeonAdventure;
    }

    public void startGameThread() {

        if (myPlayerName != null) {
            // how we instantiate a thread;
            // will call the run method
            myGameThread = new Thread(this);
            myGameThread.start();
        }

    }


    public void setPlayerName(final String theName) {
        myPlayerName = theName;
    }

    private boolean checkExitConditions() {
        boolean canExitHere = false;
        int pillarsCount = 0;
        String currentPillars = myAdventurer.getListOfPillars();
        String[] neededPillars = {"A", "E", "I", "P"};
        if (myDungeon.myCurrentRoom.getExit()) {
            DungeonView.informUser("You found the exit!");
            // Check for all 4 pillars
            for (int i = 0; i < neededPillars.length; i++) {
                if (currentPillars.contains(neededPillars[i])) {
                    pillarsCount++;
                }
            }
            if (pillarsCount == neededPillars.length) {
                canExitHere = true;
                DungeonView.informUser("Congratulations " +
                        myPlayerName + "! You win!");
            }
        }
        return canExitHere;
    }

    /**
     * Check if the monster is alive. If a room has an alive monster,
     * the adventurer should not be able to move out of the room.
     */
    private String checkMonster() {
        StringBuilder sb = new StringBuilder();
        if (!(myDungeon.myCurrentRoom.getMonster() == null)) {
            if (!myDungeon.myCurrentRoom.hasLiveMonster()) {
                sb.append("f - fight\n");
            }
        } else {
            sb.append(reportOpenDoors());
        }
        return sb.toString();
    }

    /**
     * This method looks at the current room's doors to see if they are open.
     * If open, the adventurer will be notified of the keyboard input to choose
     * one of the open doors.
     *
     * @return a String of all the doors the adventurer can choose
     */
    private String reportOpenDoors() {
        StringBuilder sb = new StringBuilder();
        if (myDungeon.myCurrentRoom.getNorthDoor().equals(DoorStatus.OPEN)) {
            sb.append("w - go through North Door\n");
        }
        if (myDungeon.myCurrentRoom.getWestDoor().equals(DoorStatus.OPEN)) {
            sb.append("a - go through West Door\n");
        }
        if (myDungeon.myCurrentRoom.getSouthDoor().equals(DoorStatus.OPEN)) {
            sb.append("s - go through South Door\n");
        }
        if (myDungeon.myCurrentRoom.getEastDoor().equals(DoorStatus.OPEN)) {
            sb.append("d - go through East Door\n");
        }
        return sb.toString();
    }

    private String reportOptions() {
        StringBuilder sb = new StringBuilder();
        sb.append(checkMonster());
        return sb.toString();
    }

    public void setPlayerClass(final String theHeroChoice) {
        if (theHeroChoice.equals("t")) {
            myAdventurer = new Adventurer(myPlayerName, "thief");
            System.out.println("Thief Created");
        }
        if (theHeroChoice.equals("w")) {
            myAdventurer = new Adventurer(myPlayerName, "warrior");
            System.out.println("Warrior Created");
        }
        if (theHeroChoice.equals("p")) {
            myAdventurer = new Adventurer(myPlayerName, "priestess");
            System.out.println("Priestess Created");
        }

    }

    // temp method - only here to test game loop
    private Direction getPlayerMove() {


        String userPrompt = "Type of the following movement characters:\n" +
                "w - up\ta - left\ts - down\td - right";
        String playerInput = DungeonView.promptUserForString(userPrompt);
        playerInput = playerInput.toLowerCase();
        while (!(playerInput.equals("w") || playerInput.equals("a") ||
                playerInput.equals("s") || playerInput.equals("d"))) {

            playerInput = DungeonView.promptUserForString(userPrompt);
        }
        Direction direction;
        switch (playerInput) {
            case "w" -> direction = UP;
            case "a" -> direction = LEFT;
            case "s" -> direction = DOWN;
            default -> direction = RIGHT;
        }
        return direction;
    }

    public static void main(String[] args) {
        DungeonView view = new DungeonView(getDungeonAdventure());
        DungeonAdventure game = DungeonAdventure.getDungeonAdventure();
        //System.out.println(game.myDungeon.getEntrance());

//        Adventurer adventurer = new Adventurer("dude", "warrior");
//        MonsterFactory factory = new MonsterFactory();
//        Monster skeleton = factory.createMonster("Skeleton");
//        skeleton.battle(skeleton,adventurer.getCharacter());


//        Thread thread = new Thread();
//        thread.start();
    }

    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {

        DungeonView.informUser(myDungeon.getCurrentRoom().toString());
        DungeonView.informUser(myDungeon.getCurrentLocation().toString());
        while (myGameThread != null) {
            DungeonView.informUser(reportOptions());

            if (getDungeonAdventure().checkExitConditions()) {
                myGameThread = null; // force stop the game?
            } else {
                myDungeon.move(getPlayerMove(), myAdventurer);

                while (myDungeon.myCurrentRoom.hasLiveMonster()) {
                    //  System.out.println("FIGHT");

                    myDungeon.myCurrentRoom.getMonster().battle(myDungeon.myCurrentRoom.getMonster(), myAdventurer.getCharacter());

                    if (myAdventurer.getCharacter().isDead()) {
                        break;
                    }
                    if (myDungeon.myCurrentRoom.getMonster().isDead()) {
                        break;
                    }
                }

                if (myAdventurer.getCharacter().isDead()) {
                    break;
                }
            }


        }
    }
}





