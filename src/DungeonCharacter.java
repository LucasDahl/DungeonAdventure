/**
 * @author Lucas Dahl - LDahl
 * @version 1.0
 *
 *  This is the parent class for all the dungeon characters.
 *
 */
public abstract class DungeonCharacter {

    // **************************** Fields ****************************

    protected String myName;
    protected double myHealthPoints;
    protected double myDamageRangeMin;
    protected double myDamageRangeMax;
    protected double myAttackSpeed;
    protected double myChanceToHit;

    // ************************** Constructors ************************

    /**
     *  This is the default constructor that
     *  will initialize the fields.
     */
    public DungeonCharacter() {
        myName = "unKnown";
        myHealthPoints = 100;
        myDamageRangeMin = 0;
        myDamageRangeMax = 10;
        myAttackSpeed = 1;
        myChanceToHit = 1;
    }

    // **************************** Methods ***************************

    // Getters
    public abstract String getName();
    public abstract double getHealth();
    public abstract double getDamageRangeMin();
    public abstract double getDamageRangeMax();
    public abstract double getAttackSpeed();
    public abstract double getChanceToHit();

    // Setters
    public abstract void setName(final String THE_NAME);
    public abstract void setHealth(final double THE_HEALTH);
    public abstract void setDamageRangeMin(final double THE_RANGE_MIN);
    public abstract void setDamageRangeMax(final double THE_RANGE_MAX);
    public abstract void setAttackSpeed(final double THE_ATTACK_SPEED);
    public abstract void setChanceToHit(final double THE_CHANCE_TO_HIT);

    public abstract void attackBehavior();

}
