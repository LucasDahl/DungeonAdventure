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
public class DungeonAdventure implements Runnable {
    private static Dungeon myDungeon;
    private static Adventurer myAdventurer;

    private String myPlayerName;
    final static int DUNGEON_ROWS = 4;
    final static int DUNGEON_COLUMNS = 4;

    private boolean myAreDoorsOpen;


    private Thread myGameThread;

    public DungeonAdventure() {
        // startGameThread();


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
        String[] neededPillars = {"a", "e", "i", "p"};
        if (myDungeon.myCurrentRoom.getExit()) {
            // Check for all 4 pillars
            for (int i = 0; i < neededPillars.length; i++) {
                if (currentPillars.contains(neededPillars[i])) {
                    pillarsCount++;
                }
            }
            if (pillarsCount == neededPillars.length) {
                canExitHere = true;
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
                // have room class have a method called hasLiveMonster()
                sb.append("f - fight");
            }
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
        myDungeon = new Dungeon(DUNGEON_ROWS, DUNGEON_COLUMNS);
        new DungeonView();
//        System.out.println(myDungeon);
        //System.out.println("Entrance: " + Arrays.toString(myDungeon.getEntrance()));
        //System.out.println("Exit: " + Arrays.toString(myDungeon.getExit()));

        // intro();
        System.out.println();

        System.out.println("Showing dungeon: ");
        System.out.println("Dungeon Entrance: ");
        System.out.println(myDungeon.getEntrance());

        System.out.println(myDungeon);

        //System.out.println("Press any key to start");
        Thread thread = new Thread();
        thread.start();
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
        while (myGameThread != null) {

            myAreDoorsOpen = myDungeon.myCurrentRoom.getIfDoorsAreOpen();
            // System.out.println("The game is playing");

            // want to move from one dungeon to another.
//            Scanner scanner = new Scanner(System.in);
//
//            String input = scanner.nextLine();
            //Dungeon.Direction direction = myDungeon.getDirection(input);

            //myDungeon.move(direction);

            if (myAreDoorsOpen) {
                myDungeon.move(getPlayerMove());
            }

            Monster monster = myDungeon.getCurrentRoom().getMonster();
            System.out.println(monster);
            // then there is a monster
            if (monster != null) {
                System.out.println("fight monster");


                // want to close doors to lock the player into fighting
                // close all doors

                myDungeon.myCurrentRoom.closeAllDoors();

                // if all doors are closed then you cant move

                System.out.println(!myAreDoorsOpen);
                if (!myAreDoorsOpen) {
                    myGameThread.interrupt();
                    System.out.println("door is closed");

                }


            }
        }
    }
}





