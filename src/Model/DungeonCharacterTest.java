package Model;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;

import static org.junit.Assert.*;

/**
 * @author lucasdahl -> Ldahl
 * @version 1.0
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DungeonCharacterTest {

    // Properties
    DungeonCharacter warrior;
    DungeonCharacter thief;
    DungeonCharacter priestess;
    DungeonCharacter ogre;
    DungeonCharacter skeleton;
    DungeonCharacter gremlin;

    Warrior warrior1;
    Thief thief1;
    Priestess priestess1;

// make monster not inherit and take out other mosnters
    // so it reads from a DB AND MAKE A FACTORY
    // make constructor package level
    // Make an attack that uses chance to hit and deals damage and returns a message

    @BeforeAll
    public void setUpDungeonCharacters() {
        warrior = new Warrior("Model.Warrior");
        thief = new Thief("Model.Thief");
        priestess = new Priestess("Model.Priestess");
        ogre = new Ogre();
        skeleton = new Skeleton();
        gremlin = new Gremlin();
    }

    @BeforeAll
    public void setUpHeros() {
        warrior1 = new Warrior("Warrior1");
        thief1 = new Thief("Thief1");
        priestess1 = new Priestess("Priestess1");
    }

    //==========
    // Test Name
    //==========

    @Test
    @DisplayName("Test the getName method for Model.Warrior")
    public void testWarriorName() {
        setUpDungeonCharacters();
        assertEquals("testWarriorName failed", "Model.Warrior", warrior.getName());
    }

    @Test
    @DisplayName("Test the getName method  for Model.Thief")
    public void testThiefName() {
        setUpDungeonCharacters();
        assertEquals("testThiefName failed", "Model.Thief", thief.getName());
    }

    @Test
    @DisplayName("Test the getName method for Model.Priestess")
    public void testPriestessName() {
        setUpDungeonCharacters();
        assertEquals("testPriestessName failed", "Model.Priestess", priestess.getName());
    }

    //==============
    // Test toString
    //==============

    @Test
    @DisplayName("Test the toString method for Model.Warrior")
    public void testWarriorToString() {
        setUpDungeonCharacters();
        assertEquals("testWarriorToString failed", "Name: Model.Warrior Health: 125.0 Max Damage range: 60.0 Min damage range: 35.0 Attack speed: 4.0 Chance to hit: 0.8 Number of attacks: 1 Chance to block: 0.4", warrior.toString());
    }

    @Test
    @DisplayName("Test the toString method for Model.Thief")
    public void testThiefToString() {
        setUpDungeonCharacters();
        assertEquals("testThiefToString failed", "Name: Model.Thief Health: 75.0 Max Damage range: 40.0 Min damage range: 20.0 Attack speed: 6.0 Chance to hit: 0.8 Number of attacks: 1 Chance to block: 0.4", thief.toString());
    }

    @Test
    @DisplayName("Test the toString method for Model.Priestess")
    public void testPriestessToString() {
        setUpDungeonCharacters();
        assertEquals("testPriestessToString failed", "Name: Model.Priestess Health: 75.0 Max Damage range: 45.0 Min damage range: 25.0 Attack speed: 5.0 Chance to hit: 0.7 Number of attacks: 1 Chance to block: 0.3", priestess.toString());
    }

    @Test
    @DisplayName("Test the toString method for Model.Ogre")
    public void testOgreToString() {
        setUpDungeonCharacters();
        assertEquals("testOgreToString failed", "Name: Model.Ogre Health: 200.0 Max Damage range: 60.0 Min damage range: 30.0 Attack speed: 2.0 Chance to hit: 0.6 Number of attacks: 1", ogre.toString());
    }

    @Test
    @DisplayName("Test the toString method for Model.Skeleton")
    public void testSkeletonToString() {
        setUpDungeonCharacters();
        assertEquals("testSkeletonToString failed", "Name: Model.Skeleton Health: 100.0 Max Damage range: 50.0 Min damage range: 30.0 Attack speed: 3.0 Chance to hit: 0.8 Number of attacks: 1", skeleton.toString());
    }

    @Test
    @DisplayName("Test the toString method for Model.Gremlin")
    public void testGremlinToString() {
        setUpDungeonCharacters();
        assertEquals("testGremlinToString failed", "Name: Model.Gremlin Health: 70.0 Max Damage range: 30.0 Min damage range: 15.0 Attack speed: 5.0 Chance to hit: 0.8 Number of attacks: 1", gremlin.toString());
    }


    //============
    // Test Health
    //============

    @Test
    @DisplayName("Test the getHealth method for Model.Warrior")
    public void testWarriorGetHealth() {
        setUpDungeonCharacters();
        assertEquals("testWarriorGetHealth failed", 125, warrior.getHealth(), 0);
    }

    @Test
    @DisplayName("Test the getHealth method for Model.Thief")
    public void testThiefGetHealth() {
        setUpDungeonCharacters();
        assertEquals("testThiefGetHealth failed", 75, thief.getHealth(), 0);
    }

    @Test
    @DisplayName("Test the getHealth method for Model.Priestess")
    public void testPriestessGetHealth() {
        setUpDungeonCharacters();
        assertEquals("testPriestessGetHealth failed", 75, priestess.getHealth(), 0);
    }

    @Test
    @DisplayName("Test the getHealth method for Model.Ogre")
    public void testOgreGetHealth() {
        setUpDungeonCharacters();
        assertEquals("testOgreGetHealth failed", 200, ogre.getHealth(), 0);
    }

    @Test
    @DisplayName("Test the getHealth method for Model.Skeleton")
    public void testSkeletonGetHealth() {
        setUpDungeonCharacters();
        assertEquals("testSkeletonGetHealth failed", 100, skeleton.getHealth(), 0);
    }

    @Test
    @DisplayName("Test the getHealth method for Model.Gremlin")
    public void testGremlinGetHealth() {
        setUpDungeonCharacters();
        assertEquals("testGremlinGetHealth failed", 70, gremlin.getHealth(), 0);
    }

    //================
    // Test set Health
    //================

    @Test
    @DisplayName("Test the setHealth method for Model.Warrior")
    public void testWarriorSetHealth() {
        setUpDungeonCharacters();
        warrior.setHealth(5);
        assertEquals("testWarriorSetHealth failed", 5, warrior.getHealth(), 0);
    }

    @Test
    @DisplayName("Test the setHealth method for Model.Thief")
    public void testThiefSetHealth() {
        setUpDungeonCharacters();
        thief.setHealth(51);
        assertEquals("testThiefSetHealth failed", 51, thief.getHealth(), 0);
    }

    @Test
    @DisplayName("Test the setHealth method for Model.Priestess")
    public void testPriestessSetHealth() {
        setUpDungeonCharacters();
        priestess.setHealth(25);
        assertEquals("testPriestessSetHealth failed", 25, priestess.getHealth(), 0);
    }

    @Test
    @DisplayName("Test the setHealth method for Model.Ogre")
    public void testOgreSetHealth() {
        setUpDungeonCharacters();
        ogre.setHealth(27.3);
        assertEquals("testOgreSetHealth failed", 27.3, ogre.getHealth(), 3);
    }

    @Test
    @DisplayName("Test the setHealth method for Model.Skeleton")
    public void testSkeletonSetHealth() {
        setUpDungeonCharacters();
        skeleton.setHealth(42.42);
        assertEquals("testOgreSetHealth failed", 42.42, skeleton.getHealth(), 42);
    }

    @Test
    @DisplayName("Test the setHealth method for Model.Gremlin")
    public void testGremlinSetHealth() {
        setUpDungeonCharacters();
        gremlin.setHealth(78.99);
        assertEquals("testGremlinSetHealth failed", 78.99, gremlin.getHealth(), 99);
    }

    //============
    // Test isDead
    //============

    @Test
    @DisplayName("Test the setHealth method for Model.Warrior")
    public void testWarriorIsDead() {
        setUpDungeonCharacters();
        warrior.setHealth(0);
        assertTrue("testWarriorIsDead failed",warrior.isDead());
    }

    //===========================
    // Test Special skills - name
    //===========================

    @Test
    @DisplayName("Test the getSpecialSkill method for Model.Warrior")
    public void testWarriorSpecialSkill() {
        setUpHeros();
        assertEquals("testWarriorSpecialSkill failed", "Crushing Blow", warrior1.getSpecialSkill());
    }

    @Test
    @DisplayName("Test the getSpecialSkill method for Model.Thief")
    public void testThiefSpecialSkill() {
        setUpHeros();
        assertEquals("testThiefSpecialSkill failed", "Sneak Attack", thief1.getSpecialSkill());
    }

    @Test
    @DisplayName("Test the getSpecialSkill method for Model.Priestess")
    public void testPriestessSpecialSkill() {
        setUpHeros();
        assertEquals("testPriestessSpecialSkill failed", "Heal", priestess1.getSpecialSkill());
    }

    //=============================
    // Test Special skills - chance
    //=============================

    @Test
    @DisplayName("Test the getSpecialSkill method for Model.Warrior")
    public void testWarriorSpecialSkillChance() {
        setUpHeros();
        assertEquals("testWarriorSpecialSkillChance failed", 0, warrior1.getSpecialChance(), 4);
    }

    @Test
    @DisplayName("Test the getSpecialSkillChance method for Model.Thief")
    public void testThiefSpecialSkillChance() {
        setUpHeros();
        assertEquals("testThiefSpecialSkillChance failed", 0, thief1.getSpecialChance(), 4);
    }

    //============================
    // Test Special skills - range
    //============================

//    @Test
//    @DisplayName("Test the getSpecialSkill method for Model.Warrior")
//    public void testWarriorSpecialAttack() {
//        setUpHeros();
//        assertTrue(75.0 <= warrior1.specialAttack() && warrior1.specialAttack() <= 150.0);
//    }

}
