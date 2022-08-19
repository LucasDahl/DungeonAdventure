package Model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DungeonTest {
    static final int DUN_ROWS = 3;
    static final int DUN_COLS = 3;
    static final Dungeon myDungeon = new Dungeon(DUN_ROWS, DUN_COLS);
    static final Adventurer myAdventurer = new Adventurer("Jane", "warrior");


    @Test
    void testTeleport1() {
        myDungeon.teleport(0,0, myAdventurer);
        assertEquals("(0, 0)", myDungeon.getCurrentLocation().toString(),
                "Expected (0, 0), got " + myDungeon.getCurrentLocation());
    }

    @Test
    void testTeleport2() {
        myDungeon.teleport(0,1, myAdventurer);
        assertEquals("(0, 1)", myDungeon.getCurrentLocation().toString(),
                "Expected (0, 1), got " + myDungeon.getCurrentLocation());
    }

    @Test
    void testGetCurrentRoom() {
        myDungeon.teleport(0,0, myAdventurer);
        myDungeon.getCurrentRoom().emptyRoom();
        myDungeon.getCurrentRoom().openAllDoors();
        assertEquals("*-*\n| |\n*-*", myDungeon.getCurrentRoom().toString());
    }

    @Test
    void testGetEntrance() {
        String entrance = myDungeon.getEntrance().toString();
        myDungeon.updateCurrentRoom(myDungeon.getEntrance());
        String currentCoordinates = myDungeon.getCurrentLocation().toString();
        assertEquals(entrance, currentCoordinates);
    }

    @Test
    void testMoveNorth() {
        myDungeon.teleport(1,1,myAdventurer);
        DoorStatus isDoorOpen = myDungeon.getCurrentRoom().getNorthDoor();
        myDungeon.move(Direction.UP, myAdventurer);
        if(isDoorOpen.equals(DoorStatus.OPEN)) {
            assertEquals("(0, 1)", myDungeon.getCurrentLocation().toString(),
                    "Expected (0, 1), got " + myDungeon.getCurrentLocation());
        } else {
            assertEquals("(1, 1)", myDungeon.getCurrentLocation().toString(),
                    "Expected (1, 1), got " + myDungeon.getCurrentLocation());
        }
    }

    @Test
    void testMoveEast() {
        myDungeon.teleport(1,1,myAdventurer);
        DoorStatus isDoorOpen = myDungeon.getCurrentRoom().getEastDoor();
        myDungeon.move(Direction.RIGHT, myAdventurer);
        if(isDoorOpen.equals(DoorStatus.OPEN)) {
            assertEquals("(1, 2)", myDungeon.getCurrentLocation().toString(),
                    "Expected (1, 2), got " + myDungeon.getCurrentLocation());
        } else {
            assertEquals("(1, 1)", myDungeon.getCurrentLocation().toString(),
                    "Expected (1, 1), got " + myDungeon.getCurrentLocation());
        }

    }

    @Test
    void testMoveSouth() {
        myDungeon.teleport(1,1,myAdventurer);
        DoorStatus isDoorOpen = myDungeon.getCurrentRoom().getSouthDoor();
        myDungeon.move(Direction.DOWN, myAdventurer);
        if(isDoorOpen.equals(DoorStatus.OPEN)) {
            assertEquals("(2, 1)", myDungeon.getCurrentLocation().toString(),
                    "Expected (2, 1), got " + myDungeon.getCurrentLocation());
        } else {
            assertEquals("(1, 1)", myDungeon.getCurrentLocation().toString(),
                    "Expected (1, 1), got " + myDungeon.getCurrentLocation());
        }


    }

    @Test
    void testMoveWest() {
        myDungeon.teleport(1,1,myAdventurer);
        DoorStatus isDoorOpen = myDungeon.getCurrentRoom().getWestDoor();
        myDungeon.move(Direction.LEFT, myAdventurer);
        if(isDoorOpen.equals(DoorStatus.OPEN)) {
            assertEquals("(1, 0)", myDungeon.getCurrentLocation().toString(),
                    "Expected (1, 0), got " + myDungeon.getCurrentLocation());
        } else {
            assertEquals("(1, 1)", myDungeon.getCurrentLocation().toString(),
                    "Expected (1, 1), got " + myDungeon.getCurrentLocation());
        }

    }

    @Test
    void testGetCurrentLocation() {
        myDungeon.teleport(0, 0, myAdventurer);
        assertEquals("(0, 0)", myDungeon.getCurrentLocation().toString(),
                "Expected (0, 0), got " + myDungeon.getCurrentLocation());
    }

    @Test
    void testGetVisionPotionView() {
        myDungeon.teleport(1,1,myAdventurer);
        int expectedLength = ((((DUN_ROWS - 1) * 2) + 3))  * (((DUN_COLS  - 1) * 2) + 3) +
                (DUN_ROWS * 2); //accounts for new line characters
        assertEquals(expectedLength, myDungeon.toString().length());
    }

    @Test
    void testUpdateCurrentRoom() {
        myDungeon.updateCurrentRoom(myDungeon.getEntrance());
        assertTrue(myDungeon.getCurrentRoom().getEntrance());
    }

    @Test
    void testToString() {
        int expectedLength = ((((DUN_ROWS - 1) * 2) + 3))  * (((DUN_COLS  - 1) * 2) + 3) +
                (DUN_ROWS * 2); //accounts for new line characters
        assertEquals(expectedLength, myDungeon.toString().length());
    }
}