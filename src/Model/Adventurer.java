package Model;

import View.DungeonView;

import java.io.Serializable;
import java.util.*;

/**
 * @author Lucas Dahl - LDahl
 * @version 1.0
 *
 *  This is the Model.Adventurer class. The Model.Adventurer is the hero of
 *  the game(the player)
 *
 */
public class Adventurer implements Serializable {

    // **************************** Fields ****************************

    private Hero myCharacter;
    private int myNumberOfHealingPotions;
    private int myNumberOfVisionPotions;
    private int potionHealth;
    private Set<String> myListOfPillars;

    // ************************** Constructors ************************


    /**
     *  This is the default constructor for the adventure
     *
     * @param theName the name of the player
     * @param theClass the class the player picked.
     */
    public Adventurer(final String theName, final String theClass) {

        myNumberOfHealingPotions = 0;
        myNumberOfVisionPotions = 0;
        myListOfPillars = new HashSet<String>();

        Scanner input = new Scanner(System.in);
        String name = theName;

        // Enable health cheat for player and potions
        if(theName.toLowerCase().equals("health")) {
            potionHealth = 100000;
            DungeonView.informUser("Please enter your name: ");
            name = input.next();
        } else {
            potionHealth = 35;
        }

        try  {
            myCharacter = new HeroFactory().createHero(theClass);
            myCharacter.setName(name);
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
        }

        // If the player activated the cheat, give them more health
        if(potionHealth > 35) {
            myCharacter.setHealth(100000);
        }
    }

    // **************************** Methods ***************************

    /**
     *  This method will show the player
     *  their inventory and current health.
     */
    public void getInventory() {
        DungeonView.informUser("Inventory:\n");
        DungeonView.informUser("Health: " + myCharacter.getHealth() + "\n");
        DungeonView.informUser("Total Health potions: " + myNumberOfHealingPotions + "\n");
        DungeonView.informUser("Total Vision potions: " + myNumberOfVisionPotions + "\n");
        DungeonView.informUser("Pillars of OO found: " + getListOfPillars() + "\n");
    }

    /**
     *  This method will let the controller
     *  know if the player has found all the pillars.
     *
     * @return indicates if the player has all the pillars
     */
    public boolean hasAllPillars() {
        if(myListOfPillars.size() >= 4) {
            return true;
        }

        return false;
    }


    /**
     * This method will heal the player if they
     * have any health potions.
     *

     */
    public void useHealPotion() {

        // If the player has at least one healing potion
        if(myNumberOfHealingPotions > 0) {
            myCharacter.setHealth(myCharacter.getHealth() + potionHealth);
            myNumberOfHealingPotions--;
            DungeonView.informUser("Healed " + potionHealth + " health, health now at " + myCharacter.getHealth());
        } else {
            DungeonView.informUser("Not enough healing potions");
        }
    }

    /**
     *  This method will use a vision potion and return
     *  true if the player has one, and false if they do not.
     *
     * @return weather a healing potion is used
     */
    public boolean useVisionPotion() {

        if(myNumberOfVisionPotions > 0) {
            myNumberOfVisionPotions--;
            return true;
        }

        return false;
    }


    /**
     * This method will deal damage to the player.
     *
     * @param theDamage the damage to the player
     */
    public void takeDamage(final int theDamage) {
        myCharacter.setHealth(myCharacter.getHealth() - theDamage);
    }

    //========
    // Getters
    //========

    /**
     * This method will return the
     * total number of healing potions
     * the player has.
     *
     * @return The total number of healing potions
     */
    public int getHealingPotions() {
        return myNumberOfHealingPotions;
    }

    /**
     * This method will return the
     * total number of vision potions
     * the player has.
     *
     * @return The total number of vision potions
     */
    public int getVisionPotions() {
        return myNumberOfVisionPotions;
    }

    /**
     *  This method will return the pillars
     *  of OO that the player has collected.
     *
     * @return All the pillars of OO the player has found
     */
    public String getListOfPillars() {
        return myListOfPillars.toString();
    }

    /**
     *  This is the character class for the
     *  hero.
     *
     * @return the HEro character.
     */
    public Hero getCharacter() {
        return myCharacter;
    }

    //========
    // Setters
    //========

    /**
     *  This will set the HEro class of the player,
     *  which will account for changes in the object.
     *
     * @param theHero the hero with changed values.
     */
    public void setCharacter(final Hero theHero) {
        myCharacter = theHero;
    }

    /**
     * This method will set the number of
     * healing potions for the player.
     *
     * @param theNumberOfPotions This is the number of healing potions to add for the player.
     */
    public void setHealingPotions(final int theNumberOfPotions) {
        myNumberOfHealingPotions += theNumberOfPotions;
    }

    /**
     * This method will set the number of
     * vision potions for the player.
     *
     * @param theNumberOfPotions This is the number of vision potions to add for the player.
     */
    public void setVisionPotions(final int theNumberOfPotions) {
        myNumberOfVisionPotions += theNumberOfPotions;
    }

    /**
     *  This method will add a found pillar
     *  of OO to the list of pillars for the player
     *
     * @param thePillar The pillar to add to the players list.
     */
    public void setListOfPillars(final String thePillar) {
        myListOfPillars.add(thePillar);
    }



    @Override
    public String toString() {
        return myCharacter.toString() + " Number of healing potions: " + myNumberOfHealingPotions + " Number of vision potions: " + myNumberOfVisionPotions + " Pillars collected: " + getListOfPillars();
    }

}
