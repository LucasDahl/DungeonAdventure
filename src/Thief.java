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
     *  This method will return the special skill.
     *
     * @return the name of the special skill
     */
    public String getMY_SPECIAL_SKILL() {
        return MY_SPECIAL_SKILL;
    }


    /**
     *  This method returns the special chance skill
     *  chance to hit.
     *
     * @return the special chance to hit
     */
    public double getMY_SPECIAL_CHANCE() {
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
}
