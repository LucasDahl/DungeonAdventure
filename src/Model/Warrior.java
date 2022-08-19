package Model;

import View.DungeonView;

/**
 * @author Lucas Dahl - LDahl
 * @version 1.0
 *
 *
 */
public class Warrior extends Hero {

    // **************************** Fields ****************************

    private final String MY_SPECIAL_SKILL;
    private double mySpecialDamage;
    private final double MY_SPECIAL_CHANCE;


    // ************************** Constructors ************************

    /**
     *  This is the default constructor
     *
     * @param theName the name of the Model.Hero
     */
    public Warrior(final String theName, final double theHealth, final double theDamageMin, final double theDamageMax, final double theAttackSpeed, final double theHitChance,final double theBlockChance, final int theNumOfAttacks) {
        super(theName, theHealth, theDamageMin, theDamageMax, theAttackSpeed, theHitChance, theBlockChance, theNumOfAttacks);
        MY_SPECIAL_SKILL = "Crushing Blow";
        mySpecialDamage = 75;
        MY_SPECIAL_CHANCE = 0.4;
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

    /**
     *  This method returns the special skill damage
     *
     * @return the damage to the special skill.
     */
    public double getMySpecialDamage() {
        return  mySpecialDamage = super.getMyRandomRange(75, 150);
    }

    // switch to protected
    /**
     *  This method returns the special chance skill
     *  chance to hit.
     *
     * @return the special chance to hit
     */
    public double getSpecialChance() {
        return MY_SPECIAL_CHANCE;
    }

    //=================
    // Override Methods
    //=================

    @Override
    public void specialSkill(DungeonCharacter theEnemy) {

        // Display the special skill
        System.out.println(getSpecialSkill());

        // The enemy was hit.
        if(super.getMyRandomRange(0, 100) < (MY_SPECIAL_CHANCE * 100)) {
            theEnemy.setHealth(theEnemy.getHealth() - getMySpecialDamage());
            DungeonView.informUser("The " + theEnemy.getName() + " took " + (int) mySpecialDamage + " points of damage.");
        } else {
            DungeonView.informUser("Attack missed.");
        }
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
        return "Special Skill: Crushing Blow, Chance to Hit: 40%, Special Attack Damage: 75 - 150";
    }

}
