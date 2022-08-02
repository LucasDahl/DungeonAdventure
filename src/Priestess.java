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
     * @param theName the name of the Hero
     */
    public Priestess(final String theName) {
        super(theName, 75, 5, 0.7, 25, 45, 0.3, 1);
        MY_SPECIAL_SKILL = "Heal";
    }

    // **************************** Methods ***************************



    /**
     *  This method will heal the Priestess
     *  with a random value from 1 - 100;
     */
    public void heal() {
        super.setHealth(super.getHealth() + healPoints());
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
    public String getMySpecialSkill() {
        return MY_SPECIAL_SKILL;
    }

    // The amount of heal points the Priestess will heal
    private double healPoints() {
        return getMyRandomRange(1, 100);
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
        return "Special Skill: Heal, Heal Range: 1 - 100";
    }
}
