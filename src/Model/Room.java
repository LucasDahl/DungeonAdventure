package Model;
/*
 * TCSS 360 - Summer 2022
 * Instructor: Tom Capaul
 * Room class for Dungeon Adventure game
 */

import java.util.Objects;
import java.util.Random;
import java.io.Serializable;

/**
 * This class contains all fields and methods pertaining to a Room as part
 * of the Dungeon environment for the associated Dungeon Adventure game.
 *
 * @author Jane Kennerly janekennerly@gmail.com
 * @version 26 July 2022
 */
public class Room implements Serializable {

    // ******************************* Fields *******************************
    private String myPillarLetter;
    private boolean myVisionPotion;
    private boolean myHealingPotion;
    private boolean myPit;
    private Monster myMonster1;
    private DoorStatus myNorthDoor;
    private DoorStatus myEastDoor;
    private DoorStatus mySouthDoor;
    private DoorStatus myWestDoor;
    private boolean myNorthPath;
    private boolean myEastPath;
    private boolean mySouthPath;
    private boolean myWestPath;
    private boolean myVisitedStatus;
    private boolean myEntrance;
    private boolean myExit;
    private int myItemCount;
    private static final int PIT_CHANCE = 20; // default 20
    private static final int VISION_POTION_CHANCE = 10; // default 10
    private static final int HEALING_POTION_CHANCE = 20; // default 20
    private static final int MONSTER_CHANCE = 20; // default 20
    // ***************************** Constructors ***************************

    /**
     * Standard Room creation, no pillar set, no items, and all doors are open
     */
    public Room() {

        myPillarLetter = "";
        myItemCount = 0;    // counts: pits, potions, pillars,
        // not counted: entrance, exit
        openAllDoors();
        myEntrance = false;
        myExit = false;
        defaultPaths();
        populateRoom();
    }

    // ******************************* Methods ******************************

    private void defaultPaths() {
        myNorthPath = false;
        myEastPath = false;
        mySouthPath = false;
        myWestPath = false;
        myVisitedStatus = false;
    }

    /**
     * Sets all doors to CLOSED
     */
     void closeAllDoors() {
        setNorthDoor(DoorStatus.CLOSED);
        setEastDoor(DoorStatus.CLOSED);
        setSouthDoor(DoorStatus.CLOSED);
        setWestDoor(DoorStatus.CLOSED);
    }

    /**
     * Sets all doors to OPEN
     */
    void openAllDoors() {
        setDoor(Direction.NORTH, DoorStatus.OPEN);
        setDoor(Direction.EAST, DoorStatus.OPEN);
        setDoor(Direction.SOUTH, DoorStatus.OPEN);
        setDoor(Direction.WEST, DoorStatus.OPEN);
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
     * @return whether the Room has an alive monster
     */
    public boolean hasLiveMonster() {
        boolean monsterIsAlive = false;
        if (myMonster1 != null) {
            monsterIsAlive = !getMonster().isDead();
        }
        return monsterIsAlive;
    }

    /**
     * Randomly assigns a pit, vision potion, healing potion, and a monster
     * to the Model.Room.
     */
    private void populateRoom() {
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
        // if random rolls a number less than monster chance, create a monster
        // depending on the modulus of the roll
        if ((rand.nextInt(100) + 1) <= MONSTER_CHANCE) {

            // Properties
            int pick = rand.nextInt(100) + 1;
            MonsterFactory factory = new MonsterFactory();

            if (pick % 3 == 0) {
                myMonster1 = factory.createMonster("Ogre");
            } else if (pick % 3 == 1) {
                myMonster1 = factory.createMonster("Gremlin");
            } else {
                myMonster1 = factory.createMonster("Skeleton");
            }
        }
    }

    //========
    // Door Getters
    //========
    public DoorStatus getNorthDoor() {
        return myNorthDoor;
    }

    public DoorStatus getEastDoor() {
        return myEastDoor;
    }

    public DoorStatus getSouthDoor() {
        return mySouthDoor;
    }

    public DoorStatus getWestDoor() {
        return myWestDoor;
    }

    //========
    // Everything else Getters
    //========

    /**
     * @return whether the room is the dungeon entrance or not
     */
    public boolean getEntrance() {
        return myEntrance;
    }

    /**
     * @return whether the room is the dungeon exit or not
     */
    public boolean getExit() {
        return myExit;
    }

    public boolean getHealingPotion() {
        return myHealingPotion;
    }

    /**
     * Pillars: A = Abstraction, E = Encapsulation
     * I = Inheritance, P = Polymorphism
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
                if (getPillar().compareTo("") != 0) { // if pillar not ""
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

    /**
     * Get the Monster in the room
     *
     * @return the Monster of the room
     */
    public Monster getMonster() {
        return myMonster1;
    }

    /**
     * @param theDirection the direction requested
     * @return whether the room has a path in the given direction
     */
    boolean getPath(final Direction theDirection) {
        boolean theRequestedPath;
        switch (theDirection) {
            case NORTH, UP -> theRequestedPath = myNorthPath;
            case EAST, RIGHT -> theRequestedPath = myEastPath;
            case SOUTH, DOWN -> theRequestedPath = mySouthPath;
            case WEST, LEFT -> theRequestedPath = myWestPath;
            default -> theRequestedPath = false;
        }
        return theRequestedPath;
    }

    /**
     * Valid returns are "A", "E", "I", or "P"
     *
     * @return the letter of the pillar in the room if there is one
     */
    String getPillar() {
        return myPillarLetter;
    }

    /**
     * Get the pit state of the room (true = pit, false = no pit)
     *
     * @return whether there is a pit in the room
     */
    boolean getPit() {
        return myPit;
    }

    /**
     * @return whether the room has been visited (for maze generation)
     */
    boolean getVisitedStatus() {
        return myVisitedStatus;
    }

    /**
     * Get the vision potion state of the room
     *
     * @return whether there is a vision potion
     */
    boolean getVisionPotion() {
        return myVisionPotion;
    }


    //========
    // Door Setters
    //========

    /**
     * Set the north door as open or closed
     *
     * @param theNorthDoor uses enumerated type DoorStatus
     */
    void setNorthDoor(final DoorStatus theNorthDoor) {
        myNorthDoor = theNorthDoor;
    }

    /**
     * Set the east door as open or closed
     *
     * @param theEastDoor uses enumerated type DoorStatus
     */
    void setEastDoor(final DoorStatus theEastDoor) {
        myEastDoor = theEastDoor;
    }

    /**
     * Set the south door as open or closed
     *
     * @param theSouthDoor uses enumerated type DoorStatus
     */
    void setSouthDoor(final DoorStatus theSouthDoor) {
        mySouthDoor = theSouthDoor;
    }

    /**
     * Set the west door as open or closed
     *
     * @param theWestDoor uses enumerated type DoorStatus
     */
    void setWestDoor(final DoorStatus theWestDoor) {
        myWestDoor = theWestDoor;
    }

    void setDoor(final Direction theDirection, final DoorStatus theStatus) {
        switch (theDirection) {
            case NORTH, UP -> myNorthDoor = theStatus;
            case EAST, RIGHT -> myEastDoor = theStatus;
            case SOUTH, DOWN -> mySouthDoor = theStatus;
            case WEST, LEFT -> myWestDoor = theStatus;
        }
    }
    //========
    // Everything else Setters
    //========

    /**
     * Set a room to be or not be a dungeon entrance
     *
     * @param theEntrance of the dungeon
     */
    void setEntrance(final boolean theEntrance) {
        if (theEntrance) {
            emptyRoom();
        }
        myEntrance = theEntrance;
        setMonster(null);
    }

    /**
     * Set a room to be or not be a dungeon exit
     *
     * @param theExit of the dungeon
     */
    void setExit(final boolean theExit) {
        if (theExit) {
            emptyRoom();
        }
        myExit = theExit;
        setMonster(null);
    }

    /**
     * Designate whether the room has a healing potion
     *
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
    public void setMonster(final Monster theMonster) {
        myMonster1 = theMonster;
    }
    /**
     * @param theDirection  A Direction to set
     * @param thePathExists true - the path exists, false - there is no path
     */
    void setPath(final Direction theDirection, final boolean thePathExists) {
        switch (theDirection) {
            case NORTH, UP -> myNorthPath = thePathExists;
            case EAST, RIGHT -> myEastPath = thePathExists;
            case SOUTH, DOWN -> mySouthPath = thePathExists;
            case WEST, LEFT -> myWestPath = thePathExists;
        }
    }

    /**
     * Designate the room to contain a pillar
     *
     * @param thePillarLetter "A", "E", "I", or "P"
     */
    void setPillar(final String thePillarLetter) {
        if (Objects.equals(thePillarLetter, "") &&
                myPillarLetter.length() > 0) {
            myItemCount--;
        } else if (Objects.equals(myPillarLetter, "") &&
                thePillarLetter.length() > 0) {
            myItemCount++;
        }
        myPillarLetter = thePillarLetter;
    }

    /**
     * Designate whether the room has a pit
     *
     * @param thePit true creates pit, false removes it
     */
    void setPit(final boolean thePit) {
        if (!thePit && myPit) { // removing pit when there was a pit
            myItemCount--;
        } else if (!myPit && thePit) { // had no pit, adding a pit
            myItemCount++;
        }
        myPit = thePit;
    }

    /**
     * Designate whether the room has a vision potion
     *
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

    /**
     * @param theStatus set true to mean room was visited,
     *                  false means the room wasn't visited
     */
    void setVisitedStatus(final boolean theStatus) {
        myVisitedStatus = theStatus;
    }

    //=================
    // Override Methods
    //=================
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