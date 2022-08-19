package Model;

import View.DungeonView;

/**
 * @author Lucas Dahl - LDahl
 * @version 1.0
 *
 *
 */
public class Priestess extends Hero {

    // **************************** Fields ****************************

    private final String MY_SPECIAL_SKILL;

    // ************************** Constructors ************************

    /**
     *  This is the default constructor
     *
     * @param theName the name of the Model.Hero
     */
    public Priestess(final String theName, final double theHealth, final double theDamageMin, final double theDamageMax, final double theAttackSpeed, final double theHitChance,final double theBlockChance, final int theNumOfAttacks) {
        super(theName, theHealth, theDamageMin, theDamageMax, theAttackSpeed, theHitChance, theBlockChance, theNumOfAttacks);
        MY_SPECIAL_SKILL = "Heal";
    }

    // **************************** Methods ***************************


    //========
    // Getters
    //========

    /**
     *  Returns the Model.Hero's name.
     *
     * @return the name of the Model.Hero.
     */
    public String getName() {
        return super.getName();
    }

    /**
     *  This method will return the special skill.
     *
     * @return the name of the special skill
     */
    public String getSpecialSkill() {
        return MY_SPECIAL_SKILL;
    }

    // The amount of heal points the Model.Priestess will heal
    private double healPoints() {
        return getMyRandomRange(1, 100);
    }

    //=================
    // Override Methods
    //=================

    @Override
    public void specialSkill(DungeonCharacter theEnemy) {

        // Properties
        double totalHealed = healPoints();

        // Set the health
        super.setHealth(super.getHealth() + totalHealed);

        // Let the user know how much they healed
        DungeonView.informUser("Healed for : " + (int) totalHealed + " points.");
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean isDead() {
        return super.isDead();
    }

    //===============
    // Static Methods
    //===============

    /**
     *  This method will return the special ability
     *  to display in the character selection screen
     *
     * @return the special skill info
     */
    public static String specialSkill() {
        return "Special Skill: Heal, Heal Range: 1 - 100";
    }
}
