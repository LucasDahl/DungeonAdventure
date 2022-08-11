package Model;

/**
 * @author Lucas Dahl - LDahl
 * @version 1.0
 *
 *
 */
public abstract class Hero extends DungeonCharacter {

    // **************************** Fields ****************************

    private final double MY_CHANCE_TO_BLOCK;

    // ************************** Constructors ************************

    /**
     *  This constructor will set all the values for the class,
     *  by call the super class constructor.
     *
     * @param theName This is the name of the Model.Hero
     * @param theHealth This is the total starting health of the Model.Hero.
     * @param theDamageMin This is the min attack range for the Model.Hero
     * @param theDamageMax This is the max attack range for the Model.Hero
     * @param theAttackSpeed This is the attack speed for the Model.Hero
     * @param theHitChance This is the chance to hit for the Model.Hero
     * @param theBlockChance This is the chance to block for the Model.Hero
     * @param theNumOfAttacks This is the total number of attacks per turn the Model.Hero gets
     */
    public Hero(final String theName, final double theHealth, final double theDamageMin, final double theDamageMax, final double theAttackSpeed, final double theHitChance,final double theBlockChance, final int theNumOfAttacks) {
        super(theName, theHealth, theDamageMin, theDamageMax, theAttackSpeed, theHitChance, theNumOfAttacks);
        MY_CHANCE_TO_BLOCK = theBlockChance;
    }



    // **************************** Methods ***************************

    // This is the method for the player to use the special skill
    protected abstract void specialSkill(final DungeonCharacter theEnemy);

    // This method will have a character defend against another
    protected boolean defend() {
        if(super.getMyRandomRange(0, 100) < MY_CHANCE_TO_BLOCK) {
            return true;
        }
        return false;
    }

    //========
    // Getters
    //========

    // Get the chance top block
    protected double getChanceToBlock() {
        return MY_CHANCE_TO_BLOCK;
    }

    // Get the number of attacks
    protected int getNumberOfAttacks() {
        return super.getNumberOfAttacks();
    }

    //=================
    // Override Methods
    //=================

    @Override
    public boolean isDead() {
        return super.isDead();
    }

    @Override
    protected double getAttackSpeed() {
        return super.getAttackSpeed();
    }

    @Override
    protected double getChanceToHit() {
        return super.getChanceToHit();
    }

    @Override
    protected double getDamageRangeMax() {
        return super.getDamageRangeMax();
    }

    @Override
    protected double getDamageRangeMin() {
        return super.getDamageRangeMin();
    }

    @Override
    protected double getHealth() {
        return super.getHealth();
    }

    @Override
    protected void setHealth(double the_health) {
        super.setHealth(the_health);
    }

    @Override
    protected void setName(final String theName) {
        super.setName(theName);
    }

    @Override
    public String toString() {
        return super.toString() + " Chance to block: " + MY_CHANCE_TO_BLOCK;
    }

}
