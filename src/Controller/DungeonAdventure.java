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

    private boolean checkExitConditions() {
        boolean canExitHere = false;
        int pillarsCount = 0;
        String currentPillars = myAdventurer.getListOfPillars();
        String[] neededPillars= {"a", "e", "i", "p"};
        if (myDungeon.myCurrentRoom.getExit()) {
            // Check for all 4 pillars
            for (int i = 0; i < neededPillars.length; i++) {
                if(currentPillars.contains(neededPillars[i])){
                    pillarsCount++;
                }
            }
            if(pillarsCount == neededPillars.length){
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
            if (!myDungeon.myCurrentRoom.getMonster().isDead()) {
                sb.append("f - fight");
            }
        }
        return sb.toString();
    }

    /**
     * This method looks at the current room's doors to see if they are open.
     * If open, the adventurer will be notified of the keyboard input to choose
     * one of the open doors.
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





