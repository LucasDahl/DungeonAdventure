/**
 * @author Lucas Dahl - LDahl
 * @version 1.0
 *
 *
 */
public class Priestess extends Hero {

    // **************************** Fields ****************************

    private final String MY_SPECIAL_SKILL;
    private final double MY_HEAL_RANGE;

    // ************************** Constructors ************************

    /**
     *  This is the default constructor
     */
    public Priestess(final String theName) {
        super(theName, 75, 5, 0.7, 25, 45, 0.3, 1);
        MY_SPECIAL_SKILL = "Heal";
        MY_HEAL_RANGE = 50;
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
    public String getMySpecialSkill() {
        return MY_SPECIAL_SKILL;
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
