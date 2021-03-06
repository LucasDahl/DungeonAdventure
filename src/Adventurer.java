import java.util.*;

/**
 * @author Lucas Dahl - LDahl
 * @version 1.0
 *
 *  This is the Adventurer class. The Adventurer is the hero of
 *  the game(the player)
 *
 */
public class Adventurer {

    // **************************** Fields ****************************

    private Hero myCharacter;
    private int myNumberOfHealingPotions;
    private int myNumberOfVisionPotions;
    private Set<String> myListOfPillars;

    // ************************** Constructors ************************


    public Adventurer(final String thePlayerName, final Hero theHero) {

        myNumberOfHealingPotions = 0;
        myNumberOfVisionPotions = 0;
        myListOfPillars = Collections.emptySet();
        myCharacter = theHero;
        myCharacter.setName(thePlayerName);

    }

    // **************************** Methods ***************************


    /**
     * This method will heal the player if they
     * have any health potions.
     */
    public String heal() {

        // If the player has at least one healing potion
        if(myNumberOfHealingPotions > 0) {
            myCharacter.setHealth(myCharacter.getHealth() + 20);
            return "Healed 20 health";
        } else {
            return "Not enough healing potions";
        }
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
     * @return All the pilalrs of OO the player has found
     */
    public String getListOfPillars() {
        return myListOfPillars.toString();
    }

    //========
    // Setters
    //========

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


    //=================
    // Override Methods
    //=================

    @Override
    public String toString() {
        return "";
    }

}
