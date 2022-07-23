import java.util.Random;

/**
 * @author Lucas Dahl - LDahl
 * @version 1.0
 *
 *  This is the parent class for all the dungeon characters.
 *
 */
public abstract class DungeonCharacter {

    // **************************** Fields ****************************

    private final String MY_NAME;
    private double myHealthPoints;
    private final double MY_DAMAGE_MIN;
    private final double MY_DAMAGE_MAX;
    private final double MY_ATTACK_SPEED;
    private final double MY_CHANCE_TO_HIT;
    private int myNumberOfAttacks;
    private Random myRandom;


    // ************************** Constructors ************************

    /**
     *  This is the default constructor that
     *  will initialize the fields with passed in values.
     */
    protected DungeonCharacter(final String the_name, final double the_health, final double the_damage_range_min, final double the_damage_range_max, final double the_attack_speed, final double the_hit_chance, final int theNumberOfAttacks) {
        MY_NAME = the_name;
        myHealthPoints = the_health;
        MY_DAMAGE_MIN = the_damage_range_min;
        MY_DAMAGE_MAX = the_damage_range_max;
        MY_ATTACK_SPEED = the_attack_speed;
        MY_CHANCE_TO_HIT = the_hit_chance;
        myNumberOfAttacks = theNumberOfAttacks;
        myRandom = new Random();
    }

    // **************************** Methods ***************************

    //========
    // Getters
    //========

    // Get the name
    private String getName() {
        return MY_NAME;
    }

    // Get the health
    protected double getHealth() {
        return myHealthPoints;
    }

    // Get the damage min
    protected double getDamageRangeMin() {
        return MY_DAMAGE_MIN;
    }

    // Get the damage max
    protected double getDamageRangeMax() {
        return MY_DAMAGE_MAX;
    }

    // Get the attack speed
    protected double getAttackSpeed() {
        return MY_ATTACK_SPEED;
    }

    // Get the chance to hit
    protected double getChanceToHit() {
        return MY_CHANCE_TO_HIT;
    }

    // Get the number of attacks
    protected int getNumberOfAttacks() {return  myNumberOfAttacks;};

    // Get the damage based min and max.
    protected double getDamage() {
        return getDamageRangeMin() + (getDamageRangeMax() - getDamageRangeMin()) * myRandom.nextDouble();
    }

    // Get a random double within a range
    protected double getMyRandomRange(final double theMin, final double theMax) {
        return theMin + (theMax - theMin) * myRandom.nextDouble();
    }

    //========
    // Setters
    //========

    protected void setNumberOfAttacks(final int the_number_of_attacks) {
        myNumberOfAttacks = the_number_of_attacks;
    }

    // This method will set the health
    protected void setHealth(final double the_health) {
        myHealthPoints = the_health;
    }


    @Override
    public String toString() {
        return "Name: " + MY_NAME + " Health: " + myHealthPoints + " Max Damage range: " + MY_DAMAGE_MAX + " Min damage range: " + MY_DAMAGE_MIN + " Attack speed: " + MY_ATTACK_SPEED + " Chance to hit: " + MY_CHANCE_TO_HIT + " Number of attacks: " + myNumberOfAttacks;
    }


    /**
     * This is the attack method for one turn.
     *
     * @param theOther this is the other character object
     */
    public abstract void attackBehavior(final DungeonCharacter theOther);

}
