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
        assertEquals(false, room.getEntrance(), "a default room should NOT be an entrance");
    }

    @Test
    void testSetEntrance1() {
        room.setEntrance(true);
        assertEquals(true,room.getEntrance(), "entrance was not set to \"true\" as expected");
    }

    @Test
    void testSetEntrance2() {
        room.setEntrance(true);
        room.setEntrance(false);
        assertEquals(false,room.getEntrance(), "entrance was not set to \"false\" as expected");
    }

    @Test
    void testGetExit1() {
        assertEquals(false,room.getExit(), "a default room should NOT be an exit");
    }

    @Test
    void testGetExit2() {
        room.setExit(true);
        assertEquals(true,room.getExit(), "Exit wasn't true as expected");
    }

    @Test
    void testSetExit1() {
        room.setExit(false); // same as default setting
        assertEquals(false, room.getExit(), "Expected false, got: " + room.getExit());
    }

    @Test
    void testSetExit2() {
        room.setExit(true);
        assertEquals(true, room.getExit(), "Expected true, got: " + room.getExit());
    }

    @Test
    void testGetPillar() {
        assertEquals("", room.getPillar(), "Expected \"\", got: " + room.getPillar());
    }

    @Test
    void testGetSetPillar1() {
        room.setPillar("A");
        assertEquals("A", room.getPillar(), "Expected \"A\", got: " + room.getPillar());
    }

    @Test
    void testGetSetPillar2() {
        room.setPillar("E");
        assertEquals("E", room.getPillar(), "Expected \"E\", got: " + room.getPillar());
    }
    @Test
    void testGetSetPillar3() {
        room.setPillar("I");
        assertEquals("I", room.getPillar(), "Expected \"I\", got: " + room.getPillar());
    }
    @Test
    void testGetSetPillar4() {
        room.setPillar("P");
        assertEquals("P", room.getPillar(), "Expected \"P\", got: " + room.getPillar());
    }
    @Test
    void testSetPillar() {
        room.setPillar("A");
        room.setPillar("");
        room.setPillar("P");
        room.setPillar("I");
        room.setPillar("E");
        assertEquals("E", room.getPillar(), "Expected \"E\", got: " + room.getPillar());
    }

    @Test
    void getPit1() {
        assertEquals(true, room.getPit(), "Expected true, got :" + room.getPit());
    }

    @Test
    void getPit2() {
        assertEquals(false, room.getPit(), "Expected false, got :" + room.getPit());
    }

    @Test
    void getVisionPotion() {
    }

    @Test
    void setVisionPotion() {
    }

    @Test
    void getHealingPotion() {
    }

    @Test
    void setHealingPotion() {
    }

    @Test
    void getMyDoorsNESW() {
    }

    @Test
    void setMyDoorsNESW() {
    }

    @Test
    void testToString() {
    }
}