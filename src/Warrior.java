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

    /**
     *  This method will use the characters special attack
     *   if a number that is higher than the hit chance
     *   is rolled.
     *
     * @return the special attack damage.
     */
    public double specialAttack() {

        Random rand = new Random();

        if(MY_SPECIAL_CHANCE > rand.nextDouble()) {
            return getMySpecialDamage();
        }
        return 0;
    }

    //========
    // Getters
    //========

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
    public String toString() {
        return super.toString();
    }

    @Override
    public void attackBehavior(final DungeonCharacter theOther) {
        super.attackBehavior(theOther);
    }

    @Override
    public boolean isDead() {
        return super.isDead();
    }

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
