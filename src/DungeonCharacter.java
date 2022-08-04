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

    private String myName;
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
    protected DungeonCharacter(final String theName, final double theHealth, final double theDamageRangeMin, final double theDamageRangeMax, final double theAttackSpeed, final double theHitChance, final int theNumberOfAttacks) {
        myName = theName;
        myHealthPoints = theHealth;
        MY_DAMAGE_MIN = theDamageRangeMin;
        MY_DAMAGE_MAX = theDamageRangeMax;
        MY_ATTACK_SPEED = theAttackSpeed;
        MY_CHANCE_TO_HIT = theHitChance;
        myNumberOfAttacks = theNumberOfAttacks;
        myRandom = new Random();
    }

    // **************************** Methods ***************************

    // Make a battle method

    /**
     *  This method will indicate if the
     *  character is dead or not.
     *
     * @return The status of the character life.
     */
    protected boolean isDead() {
        return myHealthPoints <= 0;
    }

    //========
    // Getters
    //========

    // Get the name
    protected String getName() {
        return myName;
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
        double num = theMin + (theMax - theMin) * myRandom.nextDouble();
        return num;
    }

    //========
    // Setters
    //========

    protected void setNumberOfAttacks(final int theNumberOfAttacks) {
        myNumberOfAttacks = theNumberOfAttacks;
    }

    // This method will set the health
    protected void setHealth(final double theHealth) {
        myHealthPoints = theHealth;
    }

    // Sets the name
    protected void setName(final String theName) {
        myName = theName;
    }


    @Override
    public String toString() {
        return "Name: " + myName + " Health: " + myHealthPoints + " Max Damage range: " + MY_DAMAGE_MAX + " Min damage range: " + MY_DAMAGE_MIN + " Attack speed: " + MY_ATTACK_SPEED + " Chance to hit: " + MY_CHANCE_TO_HIT + " Number of attacks: " + myNumberOfAttacks;
    }


    // This is the attack method for one turn.
    protected void attackBehavior(final DungeonCharacter theOther) {

        // Set the number of attacks for the Warrior
        setNumberOfAttacks(theOther.getNumberOfAttacks() + 1);

        // Attack the other character
        for(int i = 0; i < this.getNumberOfAttacks(); i++) {

            double attackHit = getMyRandomRange(0, 100);
            double damage = getDamage();

            // The Warrior hit the enemy
            if(attackHit > getChanceToHit()) {
                theOther.setHealth(theOther.getHealth() - damage);
            }

        }

        // Set the number of attacks back  down to 1(for the next encounter)
        setNumberOfAttacks(1);

    }

}
