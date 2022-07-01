/**
 * @Author Lucas Dahl - LDahl
 * @Version 1.0
 *
 *  This is the parent class for all the dungeon characters.
 *
 */
public abstract class DungeonCharacter {

    // **************************** Fields ****************************

    private String myName;
    private double myHealthPoints;
    private double myDamageRangeMin;
    private double myDamageRangeMax;
    private double myAttackSpeed;
    private double myChanceToHit;

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
    public abstract String setName();
    public abstract double setHealth();
    public abstract double setDamageRangeMin();
    public abstract double setDamageRangeMax();
    public abstract double setAttackSpeed();
    public abstract double setChanceToHit();

    public abstract void attackBehavior();

}
