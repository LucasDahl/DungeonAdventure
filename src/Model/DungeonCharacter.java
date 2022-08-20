package Model;

import Controller.DungeonAdventure;
import View.DungeonView;

import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Lucas Dahl - LDahl
 * @version 1.0
 *
 *  This is the parent class for all the dungeon characters.
 *
 */
public abstract class DungeonCharacter implements Serializable {

    // **************************** Fields ****************************

    private String myName;
    private double myHealthPoints;
    private final double MY_DAMAGE_MIN;
    private final double MY_DAMAGE_MAX;
    private final double MY_ATTACK_SPEED;
    private final double MY_CHANCE_TO_HIT;
    private int myNumberOfAttacks;
    private Random myRandom;


    // ************************** Constructors ************************

    /**
     *  This is the default constructor that
     *  will initialize the fields with passed in values.
     */
    protected DungeonCharacter(final String theName, final double theHealth, final double theDamageRangeMin, final double theDamageRangeMax, final double theAttackSpeed, final double theHitChance, final int theNumberOfAttacks) {
        myName = theName;
        myHealthPoints = theHealth;
        MY_DAMAGE_MIN = theDamageRangeMin;
        MY_DAMAGE_MAX = theDamageRangeMax;
        MY_ATTACK_SPEED = theAttackSpeed;
        MY_CHANCE_TO_HIT = theHitChance;
        myNumberOfAttacks = theNumberOfAttacks;
        myRandom = new Random();
    }

    // **************************** Methods ***************************

    /**
     *  This is the battle method shared by all subclasses.
     *  it will have a hero and a monster fight.
     *
     * @param theEnemy the enemy of the hero.
     * @param theAdventurer the hero(player)
     */
    public void battle(final Model.Monster theEnemy, final Adventurer theAdventurer) {

        // Properties
        Hero theHero = theAdventurer.getCharacter();
        boolean flag = false;

        // Inform the user
        DungeonView.informUser(theEnemy.getName() + " Attacked!");

        // Battle
        while(!theEnemy.isDead() && !theHero.isDead()) {

            // properties
            int attackType = 0;
            Scanner input = new Scanner(System.in);

            // Get input from the user
            DungeonView.informUser("Press 1 for normal attack, 2 for special skill 3 to heal, or 4 to flee: ");

            // Make sure the user entered a value
            while(true) {
                try {
                    attackType = Integer.parseInt( input.next());
                    flag = true;
                    break;
                } catch(Exception e) {
                    DungeonView.informUser("Invalid option, please pick again: ");
                }
                input.next();
            }


            if(attackType == 1) {
                theHero.attack(theEnemy);
            } else if(attackType == 2) {
                theHero.specialSkill(theEnemy);
            } else if(attackType == 3) {
                theAdventurer.useHealPotion();
            } else if(attackType == 4) {
                return;
            }

            // If the player blocks, enemy doesn't attack
            if(!theHero.defend()) {
                theEnemy.attack(theHero);
            } else {

                DungeonView.informUser("You blocked the attack");
            }

            // The monster has a chance to heal
            theEnemy.heal();

            // Show the health
            DungeonView.informUser(theHero.getName() + "'s health: " + (int) theHero.getHealth());
            DungeonView.informUser(theEnemy.getName() + "'s health : " + (int) theEnemy.getHealth());

        }

        // Battle over!
        DungeonView.informUser("Battle over!");

        String winner, loser;

        // Get the winner and loser
        if(theHero.isDead()) {
            winner = theEnemy.getName();
            loser = theHero.getName();
        } else {
            winner = theHero.getName();
            loser = theEnemy.getName();
        }

        // Display the message
        DungeonView.informUser(winner + " defeated " + loser + "!" );
    }

    /**
     *  This method is the attack method shared by all
     *  subclasses, and is used to attack an enemy.
     *
     * @param theEnemy the enemy of the class calling the method
     */
    public void attack(final DungeonCharacter theEnemy) {

        // Set the number of attacks for the Model.Warrior
        if(getAttackSpeed() > theEnemy.getAttackSpeed() * 2) {
            setNumberOfAttacks(theEnemy.getNumberOfAttacks() + 1);
        }

        // Attack the other character
        for(int i = 0; i < this.getNumberOfAttacks(); i++) {

            double attackHit = getMyRandomRange(0, 100);
            double damage = getDamage();

            // The Model.Warrior hit the enemy
            if(attackHit > getChanceToHit()) {
                DungeonView.informUser(theEnemy.getName() + " took " + (int)  damage + " from " + getName());
                theEnemy.setHealth(theEnemy.getHealth() - damage);
            }
        }

        // Set the number of attacks back  down to 1(for the next encounter)
        setNumberOfAttacks(1);

    }

    // This method will let
    protected boolean isDead() {
        return myHealthPoints <= 0;
    }

    //========
    // Getters
    //========

    // Get the name
    public String getName() {
        return myName;
    }

    // Get the health
    protected double getHealth() {
        return myHealthPoints;
    }

    // Get the damage min
    protected double getDamageRangeMin() {
        return MY_DAMAGE_MIN;
    }

    // Get the damage max
    protected double getDamageRangeMax() {
        return MY_DAMAGE_MAX;
    }

    // Get the attack speed
    protected double getAttackSpeed() {
        return MY_ATTACK_SPEED;
    }

    // Get the chance to hit
    protected double getChanceToHit() {
        return MY_CHANCE_TO_HIT;
    }

    // Get the number of attacks
    protected int getNumberOfAttacks() {return  myNumberOfAttacks;};

    // Get the damage based min and max.
    protected double getDamage() {
        return getDamageRangeMin() + (getDamageRangeMax() - getDamageRangeMin()) * myRandom.nextDouble();
    }

    // Get a random double within a range
    protected double getMyRandomRange(final double theMin, final double theMax) {
        double num = theMin + (theMax - theMin) * myRandom.nextDouble();
        return num;
    }

    //========
    // Setters
    //========

    protected void setNumberOfAttacks(final int theNumberOfAttacks) {
        myNumberOfAttacks = theNumberOfAttacks;
    }

    // This method will set the health
    protected void setHealth(final double theHealth) {
        if(theHealth < 0) {
            myHealthPoints = 0;
        } else {
            myHealthPoints = theHealth;
        }
    }

    // Sets the name
    protected void setName(final String theName) {
        myName = theName;
    }

    @Override
    public String toString() {
        return "Name: " + myName + " Health: " + myHealthPoints + " Max Damage range: " + MY_DAMAGE_MAX + " Min damage range: " + MY_DAMAGE_MIN + " Attack speed: " + MY_ATTACK_SPEED + " Chance to hit: " + MY_CHANCE_TO_HIT + " Number of attacks: " + myNumberOfAttacks;
    }
}
