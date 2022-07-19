/**
 * @author Lucas Dahl - LDahl
 * @version 1.0
 *
 *
 */
public class Warrior extends Hero {

    // **************************** Fields ****************************

    private final String MY_SPECIAL_SKILL;
    private final double MY_SPECIAL_DAMAGE;
    private final double MY_SPECIAL_CHANCE;


    // ************************** Constructors ************************

    /**
     *  This is the default constructor
     */
    public Warrior() {
        super("Warrior", 125, 4, 0.8, 35, 60, 0.4, 1);

        MY_SPECIAL_SKILL = "Crushing Blow";
        MY_SPECIAL_DAMAGE = super.getMyRandomRange(75, 150);
        MY_SPECIAL_CHANCE = 0.4;
    }

    // **************************** Methods ***************************

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
    public double getMySpecialDamge() {
        return MY_SPECIAL_DAMAGE;
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
}
