package Model;


import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;

import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        try {
            HeroFactory factory = new HeroFactory();
            MonsterFactory mFactory = new MonsterFactory();
            warrior = (Warrior) factory.createHero("warrior");
            thief = (Thief) factory.createHero("thief");
            priestess = (Priestess) factory.createHero("priestess");
            ogre = mFactory.createMonster("ogre");
            skeleton =  mFactory.createMonster("skeleton");;
            gremlin = mFactory.createMonster("gremlin");;
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
    }

    @BeforeAll
    public void setUpHeros() {
        try {
            HeroFactory factory = new HeroFactory();
            warrior1 = (Warrior) factory.createHero("warrior");
            thief1 = (Thief) factory.createHero("thief");
            priestess1 = (Priestess) factory.createHero("priestess");
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
    }


    //==========
    // Test Name
    //==========

    @Test
    @DisplayName("Test the getName method for Model.Warrior")
    public void testWarriorName() {
        setUpDungeonCharacters();
        assertEquals("Warrior", warrior.getName(),"testWarriorName failed");
    }

    @Test
    @DisplayName("Test the getName method  for Model.Thief")
    public void testThiefName() {
        setUpDungeonCharacters();
        assertEquals("Thief", thief.getName(), "testThiefName failed");
    }

    @Test
    @DisplayName("Test the getName method for Model.Priestess")
    public void testPriestessName() {
        setUpDungeonCharacters();
        assertEquals("Priestess", priestess.getName(), "testPriestessName failed");
    }

    //==============
    // Test toString
    //==============

    @Test
    @DisplayName("Test the toString method for Warrior")
    public void testWarriorToString() {
        setUpDungeonCharacters();

        assertEquals("Name: Warrior Health: 125.0 Max Damage range: 60.0 Min damage range: 35.0 Attack speed: 4.0 Chance to hit: 0.8 Number of attacks: 1 Chance to block: 0.2", warrior.toString(), "testWarriorToString failed");
    }

    @Test
    @DisplayName("Test the toString method for Thief")
    public void testThiefToString() {
        setUpDungeonCharacters();
        assertEquals("Name: Thief Health: 75.0 Max Damage range: 40.0 Min damage range: 20.0 Attack speed: 4.0 Chance to hit: 0.8 Number of attacks: 1 Chance to block: 0.4", thief.toString() , "testThiefToString failed");
    }

    @Test
    @DisplayName("Test the toString method for Priestess")
    public void testPriestessToString() {
        setUpDungeonCharacters();
        assertEquals( "Name: Priestess Health: 75.0 Max Damage range: 45.0 Min damage range: 25.0 Attack speed: 5.0 Chance to hit: 0.7 Number of attacks: 1 Chance to block: 0.3", priestess.toString(),"testPriestessToString failed");
    }

    @Test
    @DisplayName("Test the toString method for Ogre")
    public void testOgreToString() {
        setUpDungeonCharacters();
        assertEquals( "Name: Ogre Health: 200.0 Max Damage range: 60.0 Min damage range: 30.0 Attack speed: 2.0 Chance to hit: 0.6 Number of attacks: 1", ogre.toString(), "testOgreToString failed");
    }

    @Test
    @DisplayName("Test the toString method for Skeleton")
    public void testSkeletonToString() {
        setUpDungeonCharacters();
        assertEquals( "Name: Skeleton Health: 100.0 Max Damage range: 50.0 Min damage range: 30.0 Attack speed: 3.0 Chance to hit: 0.8 Number of attacks: 1", skeleton.toString(), "testSkeletonToString failed");
    }

    @Test
    @DisplayName("Test the toString method for Gremlin")
    public void testGremlinToString() {
        setUpDungeonCharacters();
        assertEquals( "Name: Gremlin Health: 70.0 Max Damage range: 30.0 Min damage range: 15.0 Attack speed: 5.0 Chance to hit: 0.8 Number of attacks: 1", gremlin.toString(), "testGremlinToString failed");
    }


    //============
    // Test Health
    //============

    @Test
    @DisplayName("Test the getHealth method for Warrior")
    public void testWarriorGetHealth() {
        setUpDungeonCharacters();
        //assertEquals("testWarriorGetHealth failed", 125, warrior.getHealth(), 0);
        assertEquals(125, warrior.getHealth(), 0, "testWarriorGetHealth failed" );
    }

    @Test
    @DisplayName("Test the getHealth method for Thief")
    public void testThiefGetHealth() {
        setUpDungeonCharacters();
        assertEquals( 75, thief.getHealth(), 0, "testThiefGetHealth failed");
    }

    @Test
    @DisplayName("Test the getHealth method for Priestess")
    public void testPriestessGetHealth() {
        setUpDungeonCharacters();
        assertEquals( 75, priestess.getHealth(), 0, "testPriestessGetHealth failed");
    }

    @Test
    @DisplayName("Test the getHealth method for Ogre")
    public void testOgreGetHealth() {
        setUpDungeonCharacters();
        assertEquals( 200, ogre.getHealth(), 0, "testOgreGetHealth failed");
    }

    @Test
    @DisplayName("Test the getHealth method for Skeleton")
    public void testSkeletonGetHealth() {
        setUpDungeonCharacters();
        assertEquals( 100, skeleton.getHealth(), 0, "testSkeletonGetHealth failed");
    }

    @Test
    @DisplayName("Test the getHealth method for MGremlin")
    public void testGremlinGetHealth() {
        setUpDungeonCharacters();
        assertEquals( 70, gremlin.getHealth(), 0, "testGremlinGetHealth failed");
    }

    //================
    // Test set Health
    //================

    @Test
    @DisplayName("Test the setHealth method for Warrior")
    public void testWarriorSetHealth() {
        setUpDungeonCharacters();
        warrior.setHealth(5);
        assertEquals( 5, warrior.getHealth(), 0, "testWarriorSetHealth failed");
    }

    @Test
    @DisplayName("Test the setHealth method for Thief")
    public void testThiefSetHealth() {
        setUpDungeonCharacters();
        thief.setHealth(51);
        assertEquals( 51, thief.getHealth(), 0, "testThiefSetHealth failed");
    }

    @Test
    @DisplayName("Test the setHealth method for Priestess")
    public void testPriestessSetHealth() {
        setUpDungeonCharacters();
        priestess.setHealth(25);
        assertEquals( 25, priestess.getHealth(), 0, "testPriestessSetHealth failed");
    }

    @Test
    @DisplayName("Test the setHealth method for Ogre")
    public void testOgreSetHealth() {
        setUpDungeonCharacters();
        ogre.setHealth(27.3);
        assertEquals( 27.3, ogre.getHealth(), 3, "testOgreSetHealth failed");
    }

    @Test
    @DisplayName("Test the setHealth method for Skeleton")
    public void testSkeletonSetHealth() {
        setUpDungeonCharacters();
        skeleton.setHealth(42.42);
        assertEquals( 42.42, skeleton.getHealth(), 42, "testOgreSetHealth failed");
    }

    @Test
    @DisplayName("Test the setHealth method for Gremlin")
    public void testGremlinSetHealth() {
        setUpDungeonCharacters();
        gremlin.setHealth(78.99);
        assertEquals( 78.99, gremlin.getHealth(), 99, "testGremlinSetHealth failed");
    }

    //============
    // Test isDead
    //============

    @Test
    @DisplayName("Test the setHealth method for Warrior")
    public void testWarriorIsDead() {
        setUpDungeonCharacters();
        warrior.setHealth(0);
        assertTrue("testWarriorIsDead failed",warrior.isDead());
    }

    //===========================
    // Test Special skills - name
    //===========================

    @Test
    @DisplayName("Test the getSpecialSkill method for Warrior")
    public void testWarriorSpecialSkill() {
        setUpHeros();
        assertEquals( "Crushing Blow", warrior1.getSpecialSkill(), "testWarriorSpecialSkill failed");
    }

    @Test
    @DisplayName("Test the getSpecialSkill method for Thief")
    public void testThiefSpecialSkill() {
        setUpHeros();
        assertEquals( "Sneak Attack", thief1.getSpecialSkill(), "testThiefSpecialSkill failed");
    }

    @Test
    @DisplayName("Test the getSpecialSkill method for Priestess")
    public void testPriestessSpecialSkill() {
        setUpHeros();
        assertEquals( "Heal", priestess1.getSpecialSkill(), "testPriestessSpecialSkill failed");
    }

    //=============================
    // Test Special skills - chance
    //=============================

    @Test
    @DisplayName("Test the getSpecialSkill method for Warrior")
    public void testWarriorSpecialSkillChance() {
        setUpHeros();
        assertEquals( 0, warrior1.getSpecialChance(), 4, "testWarriorSpecialSkillChance failed");
    }

    @Test
    @DisplayName("Test the getSpecialSkillChance method for Thief")
    public void testThiefSpecialSkillChance() {
        setUpHeros();
        assertEquals( 0, thief1.getSpecialChance(), 4, "testThiefSpecialSkillChance failed");
    }

    //============================
    // Test Special skills - range
    //============================

    @Test
    @DisplayName("Test the getSpecialSkill method for Warrior")
    public void testWarriorSpecialAttack() {

        // Set characters
        setUpHeros();
        setUpDungeonCharacters();

        // Use the skill
        double  currentHealth = ogre.getHealth();
        warrior1.specialSkill(ogre);


        assertTrue("testWarriorSpecialAttack",  ogre.getHealth() <= currentHealth);
    }

    @Test
    @DisplayName("Test the getSpecialSkill method for Thief")
    public void testThiefSpecialAttack() {

        // Set characters
        setUpHeros();
        setUpDungeonCharacters();

        // Use the skill
        double  currentHealth = ogre.getHealth();
        thief1.specialSkill(ogre);


        assertTrue("testThiefSpecialAttack",  ogre.getHealth() <= currentHealth);
    }

    @Test
    @DisplayName("Test the getSpecialSkill method for Priestess")
    public void testPriestessSpecialAttack() {

        // Set characters
        setUpHeros();
        setUpDungeonCharacters();

        // Use the skill
        double  currentHealth = priestess.getHealth();
        priestess1.specialSkill(ogre);


        assertTrue("testPriestessSpecialAttack",  priestess1.getHealth() >= currentHealth);
    }

}
