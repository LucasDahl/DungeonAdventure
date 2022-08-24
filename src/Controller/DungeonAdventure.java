package Controller;

import Model.*;
import View.DungeonView;
import View.MusicPlayer;


import java.io.*;
import java.io.Serializable;

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

/**
 * This class contains all the logic to run the Dungeon Adventure game.
 *
 * @author Lucas Dahl, Jane Kennerly, Luke McAlpine
 * @version 19 August 2022
 */
public class DungeonAdventure implements Runnable, Serializable {

    //singleton - eager instance
    private static DungeonAdventure myDungeonAdventure = new DungeonAdventure();
    private Dungeon myDungeon;
    private Adventurer myAdventurer;
    private String myPlayerName;
    private boolean flag = false;
    private boolean gameOver = false;
    private static final int DUNGEON_ROWS = 4;
    private static final int DUNGEON_COLUMNS = 4;
    private MusicPlayer musicPlayer;

    private transient Thread myGameThread;

    /**
     * Constructor for the game class
     */
    private DungeonAdventure() {
        myDungeon = new Dungeon(DUNGEON_ROWS, DUNGEON_COLUMNS);
        musicPlayer = new MusicPlayer();
    }

    /**
     * Single point of access for DungeonAdventure
     *
     * @return the only instance of DungeonAdventure allowed
     */
    public static synchronized DungeonAdventure getDungeonAdventure() {
        return myDungeonAdventure;
    }


    /**
     * This will start the game
     */
//    private void startGameThread() {
//
//        // Starting the menu
//        startingMenu();
//
//        if(flag) {
//
//            myDungeon = myDungeonAdventure.myDungeon;
//            myAdventurer = myDungeonAdventure.myAdventurer;
//            gameLoop();
//        } else {
//            intro();
//            gameLoop();
//        }
//    }

    /**
     * This method starts the game
     */
    private void gameStart() {
        myDungeon = new Dungeon(DUNGEON_ROWS, DUNGEON_COLUMNS);
        //startGameThread();
        run();
    }

    /**
     * Shows the starting menu
     */
    private void startingMenu() {

        // Properties
        Scanner input = new Scanner(System.in);

        DungeonView.informUser("\t\t Dungeon Adventure\n");

        // Display the options
        DungeonView.informUser("""
                Please select using your keyboard:
                \t1. New Game
                \t2. Load Game
                \t3. Exit\n"""
        );

        int userChoice = 0;

        while(true) {
            try {
                userChoice = Integer.parseInt(input.next());
                if(userChoice > 0 && userChoice < 4) {
                    break;
                } else {
                    throw new Exception("Invalid option, please pick again: ");
                }
            } catch(Exception e) {
                DungeonView.informUser("Invalid option, please pick again: ");
            }
        }

        switch (userChoice) {
            case 1:
                break;
            case 2:
                loadGame();
                break;
            case 3:
                DungeonView.informUser("\nThank you! Have an okay day! \n");
                System.exit(0);
                break;
            default:
                DungeonView.informUser("Cannot follow instructions, goodbye!");
        }
    }

    /**
     * This method displays the intro
     */
    private void intro() {

        String inputName;
        String defaultName = "nameless bum";
        String playerName;
        DungeonView.informUser("You are trapped in a dungeon!");
        DungeonView.informUser("Only by finding the four Pillars of OO can you leave.");
        inputName = DungeonView.promptUserForString("What is your name? ");

        if (inputName.equals("")) {
            playerName = defaultName;
        } else {
            playerName = inputName;
        }

        DungeonView.informUser("Select your class " + playerName + ".\n");
        DungeonView.displayHeroChoices();
        String heroChoice;
        heroChoice = DungeonView.promptUserForString("Type \"w\" for Warrior," +
                " \"t\" for Thief, \"p\" for Priestess: ");

        heroChoice.toLowerCase();
        if (!(heroChoice.equals("w") || heroChoice.equals("t") || heroChoice.equals("p"))) {
            DungeonView.informUser("Invalid option. Warrior selected.");
            heroChoice = "w";
        } else {
            DungeonView.informUser("Hero choice is: " + heroChoice);
        }
        setHero(playerName, heroChoice);
    }

    /**
     * This method set the hero
     * @param thePlayerName String of the player's name
     * @param theHeroChoice String value of hero type
     */
    private void setHero(final String thePlayerName, final String theHeroChoice) {
        myDungeonAdventure.setPlayerName(thePlayerName);
        myDungeonAdventure.setPlayerClass(theHeroChoice);
        myPlayerName = myAdventurer.getCharacter().getName();
        DungeonView.informUser("Good luck, " + myPlayerName);
    }

    /**
     *  This method will set the player name.
     *
     * @param theName the name of the player
     */
    public void setPlayerName(final String theName) {
        myPlayerName = theName;
    }

    /**
     * Lets the player exit if they have all the pillars
     * @return whether player has met exit conditions
     */
    private boolean quietCheckExitConditions() {
        String currentPillars = myAdventurer.getListOfPillars();
        String[] neededPillars = {"A", "E", "I", "P"};
        int pillarsCount = 0;
        for (int i = 0; i < neededPillars.length; i++) {
            if (currentPillars.contains(neededPillars[i])) {
                pillarsCount++;
            }
        }
        return (myDungeon.getCurrentRoom().getExit() &&
                (pillarsCount>= neededPillars.length));
    }

    /**
     * Checks if the room is the exit
     * @return whether the room is an exit
     */
    private boolean isRoomExit() {
        boolean canExitHere = false;
        int pillarsCount = 0;
        String currentPillars = myAdventurer.getListOfPillars();
        String[] neededPillars = {"A", "E", "I", "P"};
        if (myDungeon.getCurrentRoom().getExit()) {
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
        if (!(myDungeon.getCurrentRoom().getMonster() == null)) {
            if (myDungeon.getCurrentRoom().hasLiveMonster()) {
                sb.append("b - battle\n");
            }
        }
        return sb.toString();
    }

    /**
     * This method will check for potions
     */
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
        if (myDungeon.getCurrentRoom().getNorthDoor().equals(DoorStatus.OPEN)) {
            sb.append("w - go through North Door\n");
        }
        if (myDungeon.getCurrentRoom().getWestDoor().equals(DoorStatus.OPEN)) {
            sb.append("a - go through West Door\n");
        }
        if (myDungeon.getCurrentRoom().getSouthDoor().equals(DoorStatus.OPEN)) {
            sb.append("s - go through South Door\n");
        }
        if (myDungeon.getCurrentRoom().getEastDoor().equals(DoorStatus.OPEN)) {
            sb.append("d - go through East Door\n");
        }
        return sb.toString();
    }

    /**
     * This method will display the options
     * @return a String representing all the options available to the player
     */
    private String reportOptions() {
        StringBuilder sb = new StringBuilder();
        sb.append(reportOpenDoors());
        sb.append(checkPotions());
        sb.append(checkMonster());
        sb.append("i - inventory and stats\n");
        sb.append("save - type the full word \"save\"\n");
        return sb.toString();
    }

    /**
     * This  method will let the player pick the
     * hero they want to play.
     *
     * @param theHeroChoice the hero the player wants to play as
     */
    public void setPlayerClass(final String theHeroChoice) {
        if (theHeroChoice.equals("t")) {
            myAdventurer = new Adventurer(myPlayerName, "thief");
            DungeonView.informUser("Thief Created");
        }
        if (theHeroChoice.equals("w")) {
            myAdventurer = new Adventurer(myPlayerName, "warrior");
            DungeonView.informUser("Warrior Created");
        }
        if (theHeroChoice.equals("p")) {
            myAdventurer = new Adventurer(myPlayerName, "priestess");
            DungeonView.informUser("Priestess Created");
        }
    }

    /**
     * This method will ask the user if they want to play again
     */
    private void askReplay() {
        String resetPrompt= "Do you want to restart the game? [y/n]";
        String userInput = DungeonView.promptUserForString(resetPrompt);
        userInput.toLowerCase();
        if (userInput.equals("y")) {
            gameStart();
        } else {
            myGameThread = null;
        }
    }

    /**
     * This method checks if the player is alive
     */
    private void checkPlayerDeath() {
        if (myAdventurer.getCharacter().isDead()) {
            DungeonView.informUser("You died. Better luck next time.");
            //askReplay();
        }
    }

    /**
     * This method translates the player's input to a Direction
     * @param thePlayerInput String representation of player's move
     * @return the Direction player wants to go
     */
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

    /**
     * This method will start a battle
     */
    private void battle() {
        if (myDungeon.getCurrentRoom().hasLiveMonster()) {
            myDungeon.getCurrentRoom().getMonster().battle(myDungeon.
                    getCurrentRoom().getMonster(), myAdventurer);
        } else {
            DungeonView.informUser("Nothing to battle.");
        }
    }

    /**
     * This method will allow the player to take a turn
     */
    private void nextTurn(){
        musicPlayer.playVictoryMusic();
        String[] cheatsList = {"ko", "map", "teleport"};
        Set<String> cheats = new HashSet<>(List.of(cheatsList));

        String[] movesList = {"w", "a", "s", "d"};
        Set<String> moves = new HashSet<>(List.of(movesList));

        String[] otherOptions = {"h", "v", "b", "i", "save"};
        Set<String> other = new HashSet<>(List.of(otherOptions));

        Set<String> expectedInputs = new HashSet<>();
        expectedInputs.addAll(cheats);
        expectedInputs.addAll(moves);
        expectedInputs.addAll(other);

        String playerInput = DungeonView.promptUserForString(reportOptions());
        playerInput.toLowerCase();
        while (!expectedInputs.contains(playerInput)) {
            if(myAdventurer.getCharacter().isDead() || quietCheckExitConditions()) {
                break;
            }
            DungeonView.informUser("Invalid choice. Please select again");
            playerInput = DungeonView.promptUserForString(reportOptions());
        }

        if (moves.contains(playerInput)) {
            myDungeon.move(translateMove(playerInput),myAdventurer);
            isRoomExit();

        } else {
            switch (playerInput) {
                case "i": {
                    myAdventurer.getInventory();
                    break;
                }
                case "save": {
                    saveGame();
                    break;
                }
                case "h": {
                    myAdventurer.useHealPotion();
                    DungeonView.informUser("You have " +
                            myAdventurer.getHealingPotions() + " healing potions remaining.");
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
                    myDungeon.getCurrentRoom().setMonster(null);
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
                    isRoomExit();
                    break;
                }
                default: {
                    break; //should never be here
                }
            }
        }
    }

    /**
     *  This is the main driver for the game.
     *
     * @param args parameters to pass in
     */
    public static void main(String[] args) {
        DungeonAdventure game = DungeonAdventure.getDungeonAdventure();
        //game.startGameThread();
        game.run();
    }

    /**
     *  This is the game loop.
     */
    private void gameLoop() {
        DungeonView.informUser("Starting Coordinates: " +
                myDungeon.getCurrentLocation().toString());
        DungeonView.informUser(myDungeon.getCurrentRoom().toString());

        while (!gameOver) {

            if (quietCheckExitConditions() || myAdventurer.getCharacter().isDead()) {
                checkPlayerDeath();
                askReplay();
                break;
            } else {
                nextTurn();
            }
        }
    }


    // ************************** Loading and Saving ************************


    /**
     * This method actually loads the saved game
     * @param theSavedGame the saved game file
     */
    private void loadASaveGame(final File theSavedGame){

        DungeonView.informUser("Loading save file " + theSavedGame.getName());

        // Reading data
        try {

            // Files
            FileInputStream saveFileInput = new FileInputStream(theSavedGame);
            ObjectInputStream objectIS = new ObjectInputStream(saveFileInput);

            // Set the instance
            // was myGame
            myDungeonAdventure = (DungeonAdventure) objectIS.readObject();
            flag = true;

            DungeonView.informUser("\nSuccessfully loaded the game!\n");

        } catch (Exception e) {
            DungeonView.informUser("ERROR loading game: " + e);
        }
    }

    /**
     * This method displays the games the user has saved.
     * It also will allow them to load one.
     */
    private void loadGame(){

        File saveFile = new File("save\\DungeonAdventure.ser");

        // Load the game
        loadASaveGame(saveFile);

    }


    /**
     * This method will save the game to a file for the user
     */
    private void saveGame(){

        try{

            // Set the file name
            String fileName = "DungeonAdventure";

            // Crerate the file
            FileOutputStream fileOutputStream = new FileOutputStream("save\\" + fileName + ".ser");
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);

            // Write the file
            outputStream.writeObject(myDungeonAdventure);
            outputStream.flush();

            // close the file
            outputStream.close();

            DungeonView.informUser("Saved!");
        }
        catch(Exception e){
            DungeonView.informUser("Error in saving the file: " + e);
        }
    }

    @Override
    public void run() {
        //musicPlayer.playVictoryMusic();
        // Starting the menu
        startingMenu();

        if(flag) {

            myDungeon = myDungeonAdventure.myDungeon;
            myAdventurer = myDungeonAdventure.myAdventurer;
            gameLoop();
        } else {
            intro();
            gameLoop();
        }
    }
}





