import java.util.Random;

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
     * @param theName the name of the Hero
     */
    public Warrior(final String theName) {
        super(theName, 125, 4, 0.8, 35, 60, 0.4, 1);
        MY_SPECIAL_SKILL = "Crushing Blow";
        mySpecialDamage = 75;
        MY_SPECIAL_CHANCE = 0.4;
    }

    // **************************** Methods ***************************


    //========
    // Getters
    //========

    /**
     *  Returns the Hero's name.
     *
     * @return the name of the Hero.
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
    protected void specialSkill(Monster theEnemy) {

        // Display the special skill
        System.out.println(getSpecialSkill());

        // The enemy was hit.
        if(super.getMyRandomRange(0, 100) < MY_SPECIAL_CHANCE) {
            theEnemy.setHealth(theEnemy.getHealth() - getMySpecialDamage());
            System.out.println("The " + theEnemy.getName() + " took " + mySpecialDamage + " points of damage.");
        } else {
            System.out.println("Attack missed.");
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
