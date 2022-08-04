import java.util.Random;

/**
 * @author Lucas Dahl - LDahl
 * @version 1.0
 *
 *
 */
public class Thief extends Hero {

    // **************************** Fields ****************************

    private final String MY_SPECIAL_SKILL;
    private final double MY_SPECIAL_CHANCE;

    // ************************** Constructors ************************

    /**
     *  This is the default constructor
     *
     * @param theName the name of the Hero
     */
    public Thief(final String theName) {
        super(theName, 75, 6, 0.8, 20, 40, 0.4, 1);
        MY_SPECIAL_SKILL = "Sneak Attack";
        MY_SPECIAL_CHANCE = 0.4;
    }

    // **************************** Methods ***************************

    /**
     *  This special skill will allow the Thief
     *  character to possible get another attack,
     *  but can also lose an attack during this turn.
     */
    public void sneakAttack() {
        Random rand = new Random();
        double chance = rand.nextDouble();
        if(MY_SPECIAL_CHANCE / 2.0 < chance) {
            super.setNumberOfAttacks(0);
        } else if(MY_SPECIAL_CHANCE > chance) {
            super.setNumberOfAttacks(getNumberOfAttacks() + 1);
        }
    }

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
        return "Special Skill: Sneak Attack, Chance to Hit: 40% with a 20% chance of getting caught";
    }

}
