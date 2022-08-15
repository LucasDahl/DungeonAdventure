package Controller;

import Model.*;
import View.DungeonView;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

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
    private static final DungeonAdventure myDungeonAdventure = new DungeonAdventure();
    private Dungeon myDungeon;
    private Adventurer myAdventurer;

    private String myPlayerName;
    final static int DUNGEON_ROWS = 4;
    final static int DUNGEON_COLUMNS = 4;


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
            if (pillarsCount >= neededPillars.length) {
                canExitHere = true;
                DungeonView.informUser("Congratulations " +
                        myPlayerName + "! You win!");
            } else {
                DungeonView.informUser("Continue searching the dungeon" +
                        " for the remaining Pillars of OO!");
                DungeonView.informUser("Your current pillars: " +
                        myAdventurer.getListOfPillars());
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
            if (myDungeon.myCurrentRoom.hasLiveMonster()) {
                sb.append("b - battle\n");
            }
        }
        return sb.toString();
    }
    private String checkPotions() {
        StringBuilder sb = new StringBuilder();
        if (myAdventurer.getHealingPotions() > 0) {
            sb.append("h - use healing potion\n");
        }
        if (myAdventurer.getVisionPotions() > 0) {
            sb.append("v - use vision potion\n");
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
        StringBuilder sb = new StringBuilder("\nType one of valid letters " +
                "listed below\n");
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
        sb.append(reportOpenDoors());
        sb.append(checkPotions());
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
    private void checkPlayerDeath() {
        if (myAdventurer.getCharacter().isDead()) {
            DungeonView.informUser("You died. Better luck next time.");
            myGameThread = null;
        }
    }
    private Direction translateMove(String thePlayerInput) {
        Direction direction;
        switch (thePlayerInput) {
            case "w" -> direction = UP;
            case "a" -> direction = LEFT;
            case "s" -> direction = DOWN;
            case "d" -> direction = RIGHT;
            default -> direction = NEUTRAL;
        }
        return direction;
    }
    private void battle() {
        if (myDungeon.myCurrentRoom.hasLiveMonster()) {
            myDungeon.myCurrentRoom.getMonster().battle(myDungeon.
                    myCurrentRoom.getMonster(), myAdventurer);
        } else {
            DungeonView.informUser("Nothing to battle.");
        }
    }
    private void nextTurn(){
        String[] cheatsList = {"ko", "map", "teleport"};
        Set<String> cheats = new HashSet<>(List.of(cheatsList));

        String[] movesList = {"w", "a", "s", "d"};
        Set<String> moves = new HashSet<>(List.of(movesList));

        String[] otherOptions = {"h", "v", "b"};
        Set<String> other = new HashSet<>(List.of(otherOptions));

        Set<String> expectedInputs = new HashSet<>();
        expectedInputs.addAll(cheats);
        expectedInputs.addAll(moves);
        expectedInputs.addAll(other);

        String playerInput = DungeonView.promptUserForString(reportOptions());
        playerInput.toLowerCase();
        while (!expectedInputs.contains(playerInput)) {
            DungeonView.informUser("Invalid choice. Please select again");
            playerInput = DungeonView.promptUserForString(reportOptions());
        }

        if (moves.contains(playerInput)) {
            myDungeon.move(translateMove(playerInput),myAdventurer);
        } else {
            switch (playerInput) {
                case "h": {
                    myAdventurer.useHealPotion();
                    DungeonView.informUser("You have used a healing potion!");
                    DungeonView.informUser("You have " +
                            myAdventurer.getHealingPotions() + " healing potions remaining.");
//                    DungeonView.informUser("Current health: " +
//                            myAdventurer.getCharacter().getHealth());
                    break;
                }
                case "v": {
                    if(myAdventurer.useVisionPotion()) {
                        DungeonView.informUser( "You are currently at : " +
                                myDungeon.getCurrentLocation().toString());
                        DungeonView.informUser(myDungeon.getVisionPotionView());
                    }
                    break;
                }
                case "b": {
                    battle();
                    break;
                }
                case "ko": {
                    myDungeon.myCurrentRoom.setMonster(null);
                    DungeonView.informUser("You have ko'd the monster");
                    break;
                }
                case "map": {
                    DungeonView.informUser("You are currently at: " +
                            myDungeon.getCurrentLocation());
                    DungeonView.informUser(myDungeon.toString());
                    break;
                }
                case "teleport": {
                    String xInput = DungeonView.promptUserForString("X coordinate?");
                    String yInput = DungeonView.promptUserForString("Y coordinate?");
                    int x = Integer.parseInt(xInput);
                    int y = Integer.parseInt(yInput);
                    myDungeon.teleport(x, y, myAdventurer);
                    break;
                }
                default: {
                    nextTurn(); //should never be here
                }
            }
        }
        checkPlayerDeath();

    }

    public static void main(String[] args) {
        DungeonView view = new DungeonView(getDungeonAdventure());
        DungeonAdventure game = DungeonAdventure.getDungeonAdventure();
        game.startGameThread();
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
        DungeonView.informUser("Starting Coordinates: " +
                myDungeon.getCurrentLocation().toString());
        DungeonView.informUser(myDungeon.getCurrentRoom().toString());

        while (myGameThread != null) {

            if (getDungeonAdventure().checkExitConditions()) {
                myGameThread = null; // stop the game when they can exit/win
            } else {
                nextTurn();

            }
        }
    }
}





