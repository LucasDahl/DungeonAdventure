package Controller;

import Model.*;
import View.DungeonView;

import java.util.Scanner;


/*
 * TCSS 360 - Summer 2022
 * Instructor: Tom Capaul
 * Model.Room class for Model.Dungeon Adventure game
 * Package condition: Must be placed in the same package
 * as all component classes.
 */
public class DungeonAdventure {
    private static MockDungeon myDungeon;
    private static Adventurer myAdventurer;

    private String myPlayerName;
    final static int DUNGEON_ROWS = 4;
    final static int DUNGEON_COLUMNS = 4;

    public DungeonAdventure() {

    }


    public void setPlayerName(final String theName) {
        myPlayerName = theName;
    }


//    private static void exit() {
//        if (myAdventurer.getListOfPillars().length() > 3) {
//            // play win music
//        } else {
//            displayOptions();
//        }
//    }

    private static void checkMonster() {
        if (!myDungeon.myCurrentRoom.getMonster().isDead()) {
            System.out.println("f - fight Monster");
        }
    }


    public void setPlayerClass(final String theHeroChoice) {
        if (theHeroChoice.equals("t")) {
            myAdventurer = new Adventurer(new Thief(myPlayerName));
            System.out.println("Thief Created");
        }
        if (theHeroChoice.equals("w")) {
            myAdventurer = new Adventurer(new Warrior(myPlayerName));
            System.out.println("Warrior Created");
        }
        if (theHeroChoice.equals("p")) {
            myAdventurer = new Adventurer(new Priestess(myPlayerName));
            System.out.println("Priestess Created");
        }

    }

    public static void main(String[] args) {
        myDungeon = new MockDungeon(DUNGEON_ROWS, DUNGEON_COLUMNS);
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

    }
}





