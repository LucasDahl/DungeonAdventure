package Model;/*
 * TCSS 360 - Summer 2022
 * Instructor: Tom Capaul
 * Model.Room class for Model.Dungeon Adventure game
 * Package condition: Must be placed in the same package as DungeonAdventure
 */

import java.util.Objects;
import java.util.Random;
import java.io.Serializable;

enum DoorStatus {
    OPEN, CLOSED
}

/**
 * This class contains all fields and methods pertaining to a Model.Room as part
 * of the Model.Dungeon environment for the associated Model.Dungeon Adventure game.
 *
 * @author Jane Kennerly janekennerly@gmail.com
 * @version 26 July 2022
 */
public class Room implements Serializable {

    private String myPillarLetter;
    private boolean myVisionPotion;
    private boolean myHealingPotion;
    private boolean myPit;
    private Monster myMonster1;
    private DoorStatus myNorthDoor;
    private DoorStatus myEastDoor;
    private DoorStatus mySouthDoor;
    private DoorStatus myWestDoor;

    private boolean myEntrance;
    private boolean myExit;
    private int myItemCount;

    /**
     * Standard Model.Room creation, no pillar set, no items, and all doors are open/exist
     */
    public Room() {

        myPillarLetter = "";
        myItemCount = 0;    // counts: pits, potions, pillars,
                            // not counted: entrance, exit
        myNorthDoor = DoorStatus.OPEN;
        myEastDoor = DoorStatus.OPEN;
        mySouthDoor = DoorStatus.OPEN;
        myWestDoor = DoorStatus.OPEN;
        myEntrance = false;
        myExit = false;
        populateRoom();
    }

    public Monster getMonster() {
        return myMonster1;
    }
    /**
     * @return whether the room is the dungeon entrance or not
     */
    boolean getEntrance() {
        return myEntrance;
    }

    /**
     * Set a room to be or not be a dungeon entrance
     * @param theEntrance of the dungeon
     */
    void setEntrance(final boolean theEntrance) {
        if (theEntrance) {
            emptyRoom();
        }
        myEntrance = theEntrance;
    }

    /**
     * @return whether the room is the dungeon exit or not
     */
    public boolean getExit() {
        return myExit;
    }

    /**
     * Set a room to be or not be a dungeon exit
     * @param theExit of the dungeon
     */
    void setExit(final boolean theExit) {
        if (theExit) {
            emptyRoom();
        }
        myExit = theExit;
    }

    /**
     * Valid returns are "A", "E", "I", or "P"
     * @return the letter of the pillar in the room if there is one
     */
    String getPillar() {
        return myPillarLetter;
    }

    /**
     * Designate the room to contain a pillar
     * @param thePillarLetter "A", "E", "I", or "P"
     */
    void setPillar(String thePillarLetter) {
        if (Objects.equals(thePillarLetter, "") && myPillarLetter.length()>0) {
            myItemCount--;
        } else if (Objects.equals(myPillarLetter, "") && thePillarLetter.length() >0){
            myItemCount++;
        }
        myPillarLetter = thePillarLetter;
    }

    /**
     * Get the pit state of the room (true = pit, false = no pit)
     * @return whether or not there is a pit in the room
     */
    boolean getPit() {
        return myPit;
    }

    void setPit(boolean thePit) {
        if (!thePit && myPit) { // removing pit when there was a pit
            myItemCount--;
        } else if (!myPit && thePit){ // had no pit, adding a pit
            myItemCount++;
        }
        myPit = thePit;
    }

    /**
     * Get the vision potion state of the room
     * @return whether or not there is a vision potion
     */
    boolean getVisionPotion() {
        return myVisionPotion;
    }

    /**
     * Designate whether the room has a vision potion
     * @param theVisionPotion true places vision potion, false removes it
     */
    void setVisionPotion(final boolean theVisionPotion) {
        if (myVisionPotion && !theVisionPotion) { //had potion, removing it
            myItemCount--;
        } else if (!myVisionPotion && theVisionPotion) { //no potion, adding it
            myItemCount++;
        }
        myVisionPotion = theVisionPotion;
    }

    boolean getHealingPotion() {
        return myHealingPotion;
    }

    /**
     * Designate whether the room has a healing potion
     * @param theHealingPotion true places healing potion, false removes it
     */
    void setHealingPotion(final boolean theHealingPotion) {
        if (myHealingPotion && !theHealingPotion) { // had potion, removing it
            myItemCount--;
        } else if (!myHealingPotion && theHealingPotion) { // no potion, adding it
            myItemCount++;
        }
        this.myHealingPotion = theHealingPotion;
    }

    DoorStatus getNorthDoor() {
        return myNorthDoor;
    }

    void setNorthDoor(DoorStatus theNorthDoor) {
        myNorthDoor = theNorthDoor;
    }

    DoorStatus getEastDoor() {
        return myEastDoor;
    }

    void setEastDoor(DoorStatus theEastDoor) {
        myEastDoor = theEastDoor;
    }

    DoorStatus getSouthDoor() {
        return mySouthDoor;
    }

    void setSouthDoor(DoorStatus theSouthDoor) {
        mySouthDoor = theSouthDoor;
    }

    DoorStatus getWestDoor() {
        return myWestDoor;
    }

    void setWestDoor(DoorStatus theWestDoor) {
        myWestDoor = theWestDoor;
    }

    void openAllDoors() {
        myNorthDoor = DoorStatus.OPEN;
        myWestDoor = DoorStatus.OPEN;
        myEastDoor = DoorStatus.OPEN;
        mySouthDoor = DoorStatus.OPEN;
    }

    void closeAllDoors() {
        myNorthDoor = DoorStatus.CLOSED;
        myWestDoor = DoorStatus.CLOSED;
        myEastDoor = DoorStatus.CLOSED;
        mySouthDoor = DoorStatus.CLOSED;
    }

    /**
     * Randomly assigns a pit, vision potion, healing potion, and a monster
     * to the Model.Room.
     */
    void populateRoom() {
        final int PIT_CHANCE = 20;
        final int VISION_POTION_CHANCE = 10;
        final int HEALING_POTION_CHANCE = 20;
        final int MONSTER_CHANCE = 20;

        Random rand = new Random();
        if ((rand.nextInt(100) + 1) <= PIT_CHANCE) {
            myPit = true;
            myItemCount++;
        } else {
            myPit = false;
        }
        if ((rand.nextInt(100) + 1) <= VISION_POTION_CHANCE) {
            myVisionPotion = true;
            myItemCount++;
        } else {
            myVisionPotion = false;
        }
        if ((rand.nextInt(100) + 1) <= HEALING_POTION_CHANCE) {
            myHealingPotion = true;
            myItemCount++;
        } else {
            myHealingPotion = false;
        }

        if((rand.nextInt(100) + 1) <= MONSTER_CHANCE) {
            int pick = rand.nextInt(100) + 1;
            if (pick % 3 == 0) {
                myMonster1 = new Ogre();
            } else if (pick % 3 == 1) {
                myMonster1 = new Gremlin();
            } else {
                myMonster1 = new Skeleton();
            }
        }
    }

    /**
     * Clears all the items in the room.
     */
    void emptyRoom() {
        setPillar("");
        setPit(false);
        setHealingPotion(false);
        setVisionPotion(false);
        if (myMonster1 != null) {
            myMonster1.setHealth(0);
        }
        setEntrance(false);
        setExit(false);
    }

    /**
     * Pillars: A = Abstraction, E = Encapsulation
     *          I = Inheritance, P = Polymorphism
     * H = healing potion only
     * V = vision potion only
     * X = pit only
     * M = more than 1 item
     *
     * @return Gets the letter that best represents the contents of the room
     */
    String getMiddle() {
        String middle = "";
        if (getEntrance()) {
            middle = "i";
        } else if (getExit()) {
            middle = "O";
        } else {
            if (myItemCount > 1) {
                middle = "M";
            } else if (myItemCount < 1) {
                middle = " ";
            } else {
                if (getPillar().compareTo("") != 0) { // if pillar not empty String
                    middle = getPillar();
                } else if (getVisionPotion()) {
                    middle = "V";
                } else if (getHealingPotion()) {
                    middle = "H";
                } else if (getPit()) {
                    middle = "X";
                }
            }
        }
        return middle;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (getNorthDoor() == DoorStatus.OPEN) {
            sb.append("*-*\n");
        } else {
            sb.append("***\n");
        }
        if (getWestDoor() == DoorStatus.OPEN) {
            sb.append("|");
        } else {
            sb.append("*");
        }
        sb.append(getMiddle());
        if (getEastDoor() == DoorStatus.OPEN) {
            sb.append("|\n");
        } else {
            sb.append("*\n");
        }
        if (getSouthDoor() == DoorStatus.OPEN) {
            sb.append("*-*");
        } else {
            sb.append("***");
        }
        return sb.toString();
    }

}
