/*
 * TCSS 360 - Summer 2022
 * Instructor: Tom Capaul
 * Room class for Dungeon Adventure game
 * Package condition: Must be placed in the same package as DungeonAdventure
 */

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests all fields and methods pertaining to a Room as part
 * of the Dungeon environment for the associated Dungeon Adventure game.
 *
 * @author Jane Kennerly janekennerly@gmail.com
 * @version 24 July 2022
 */

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RoomTest {
    Room room;

    @BeforeAll
    void setUp() {
        room = new Room();
    }

    @Test
    @DisplayName("Test the default entrance setting")
    void testGetEntrance() {
        assertFalse(room.getEntrance(),
                "a default room should NOT be an entrance");
    }

    @Test
    void testSetEntrance1() {
        room.setEntrance(true);
        assertTrue(room.getEntrance(),
                "entrance was not set to \"true\" as expected");
    }

    @Test
    void testSetEntrance2() {
        room.setEntrance(true);
        room.setEntrance(false);
        assertFalse(room.getEntrance(),
                "entrance was not set to \"false\" as expected");
    }

    @Test
    void testGetExit1() {
        assertFalse(room.getExit(),
                "a default room should NOT be an exit");
    }

    @Test
    void testGetExit2() {
        room.setExit(true);
        assertTrue(room.getExit(), "Exit wasn't true as expected");
    }

    @Test
    void testSetExit1() {
        room.setExit(false); // same as default setting
        assertFalse(room.getExit(), "Expected false, got: " + room.getExit());
    }

    @Test
    void testSetExit2() {
        room.setExit(true);
        assertTrue(room.getExit(), "Expected true, got: " + room.getExit());
    }

    @Test
    void testGetPillar() {
        room.emptyRoom();
        assertEquals("", room.getPillar(),
                "Expected \"\", got: " + room.getPillar());
    }

    @Test
    void testGetSetPillar1() {
        room.setPillar("A");
        assertEquals("A", room.getPillar(),
                "Expected \"A\", got: " + room.getPillar());
    }

    @Test
    void testGetSetPillar2() {
        room.setPillar("E");
        assertEquals("E", room.getPillar(),
                "Expected \"E\", got: " + room.getPillar());
    }
    @Test
    void testGetSetPillar3() {
        room.setPillar("I");
        assertEquals("I", room.getPillar(),
                "Expected \"I\", got: " + room.getPillar());
    }
    @Test
    void testGetSetPillar4() {
        room.setPillar("P");
        assertEquals("P", room.getPillar(),
                "Expected \"P\", got: " + room.getPillar());
    }
    @Test
    void testSetPillar() {
        room.setPillar("A");
        assertEquals("A", room.getPillar(),
                "Expected \"A\", got: " + room.getPillar());
        room.setPillar("");
        assertEquals("", room.getPillar(),
                "Expected \"\", got: " + room.getPillar());
        room.setPillar("P");
        assertEquals("P", room.getPillar(),
                "Expected \"P\", got: " + room.getPillar());
        room.setPillar("I");
        assertEquals("I", room.getPillar(),
                "Expected \"I\", got: " + room.getPillar());
        room.setPillar("E");
        assertEquals("E", room.getPillar(),
                "Expected \"E\", got: " + room.getPillar());
    }

    @Test
    void testGetPit1() {
        room.setPit(true);
        assertTrue(room.getPit(), "Expected true, got :" + room.getPit());
    }

    @Test
    void testGetPit2() {
        room.setPit(false);
        assertFalse(room.getPit(), "Expected false, got :" + room.getPit());
    }

    @Test
    void testSetPit() {
        room.setPit(false);
        assertFalse(room.getPit(), "Expected false, got :" + room.getPit());
        room.setPit(true);
        assertTrue(room.getPit(), "Expected true, got :" + room.getPit());
    }
    @Test
    void testGetVisionPotion1() {
        room.emptyRoom();
        assertFalse(room.getVisionPotion(), "Expected false after clearing room");
    }
    @Test
    void testGetVisionPotion2() {
        room.setVisionPotion(true);
        assertTrue(room.getVisionPotion(), "Expected vision potion true");


    }
    @Test
    void testSetVisionPotion() {
        room.setVisionPotion(true);
        assertTrue(room.getVisionPotion(), "Expected vision potion true");
        room.setVisionPotion(false);
        assertFalse(room.getVisionPotion(), "Expected vision potion false");
    }

    @Test
    void testGetHealingPotion() {
        room.emptyRoom();
        assertFalse(room.getHealingPotion(), "Expected false after clearing room");
        room.setHealingPotion(true);
        assertTrue(room.getHealingPotion(), "Expected healing potion true");
    }

    @Test
    void testSetHealingPotion1() {
        room.setHealingPotion(true);
        assertTrue(room.getHealingPotion(), "Expected healing potion true");
    }
    @Test
    void testSetHealingPotion2() {
        room.setHealingPotion(false);
        assertFalse(room.getHealingPotion(), "Expected healing potion false");
    }

    @Test
    void testGetMyDoorsNESW1() {
        int[] doors = {1, 1, 1, 1}; // array of NESW doors all open
        assertEquals(Arrays.toString(doors), Arrays.toString(room.getMyDoorsNESW()),
                "Expected: " + Arrays.toString(room.getMyDoorsNESW()));
    }

    @Test
    void testSetMyDoorsNESW1() {
        int[] doors = {1,0,0,1};
        room.setMyDoorsNESW(doors);
        assertEquals(doors, room.getMyDoorsNESW(),
                "Expected: " + Arrays.toString(room.getMyDoorsNESW()));
    }

    @Test
    void testSetMyDoorsNESW2() {
        int[] doors = {0,0,0,0};
        room.setMyDoorsNESW(doors);
        assertEquals(doors, room.getMyDoorsNESW(),
                "Expected: " + Arrays.toString(room.getMyDoorsNESW()));
    }

    @Test
    void testSetMyDoorsNESW3() {
        int[] doors = {0,1,0,1};
        room.setMyDoorsNESW(doors);
        assertEquals(doors, room.getMyDoorsNESW(),
                "Expected: " + Arrays.toString(room.getMyDoorsNESW()));
    }
    @Test
    void testToString_Empty_Room() {
        int[] doors = {1,1,1,1}; // open doors
        room.setMyDoorsNESW(doors);
        room.emptyRoom();
        assertEquals("*-*\n| |\n*-*", room.toString());
    }

    @Test
    void testToString_Entrance() {
        int[] doors = {1,1,1,1}; // open doors
        room.setMyDoorsNESW(doors);
        room.setEntrance(true);
        assertEquals("*-*\n|i|\n*-*", room.toString());
    }
    @Test
    void testToString_Exit() {
        int[] doors = {1,1,1,1}; // open doors
        room.setMyDoorsNESW(doors);
        room.setExit(true);
        assertEquals("*-*\n|O|\n*-*", room.toString());
    }
    @Test
    void testToString_PillarA() {
        int[] doors = {1,1,1,1}; // open doors
        room.setMyDoorsNESW(doors);
        room.emptyRoom();
        room.setPillar("A");
        assertEquals("*-*\n|A|\n*-*", room.toString());
    }


    @Test
    void testToString_PillarP() {
        int[] doors = {1,1,1,1}; // open doors
        room.setMyDoorsNESW(doors);
        room.emptyRoom();
        room.setPillar("P");
        assertEquals("*-*\n|P|\n*-*", room.toString());
    }

    @Test
    void testToString_PillarI() {
        int[] doors = {1,1,1,1}; // open doors
        room.setMyDoorsNESW(doors);
        room.emptyRoom();
        room.setPillar("I");
        assertEquals("*-*\n|I|\n*-*", room.toString());
    }

    @Test
    void testToString_PillarE() {
        int[] doors = {1,1,1,1}; // open doors
        room.setMyDoorsNESW(doors);
        room.emptyRoom();
        room.setPillar("E");
        assertEquals("*-*\n|E|\n*-*", room.toString());
    }


    @Test
    void testToString_Pit() {
        int[] doors = {1,1,1,1}; // open doors
        room.setMyDoorsNESW(doors);
        room.emptyRoom();
        room.setPit(true);
        assertEquals("*-*\n|X|\n*-*", room.toString());
    }
    @Test
    void testToString_HealingPotion() {
        room.emptyRoom();
        int[] doors = {1,1,1,1}; // open doors
        room.setMyDoorsNESW(doors);
        room.setHealingPotion(true);
        assertEquals("*-*\n|H|\n*-*", room.toString());
    }
    @Test
    void testToString_VisionPotion() {
        room.emptyRoom();
        int[] doors = {1,1,1,1}; // open doors
        room.setMyDoorsNESW(doors);
        room.setVisionPotion(true);
        assertEquals("*-*\n|V|\n*-*", room.toString());
    }
    @Test
    void testToString_ClosedDoors() {
        room.emptyRoom();
        int[] doors = {0,0,0,0}; // open doors
        room.setMyDoorsNESW(doors);
        assertEquals("***\n* *\n***", room.toString());
    }
}