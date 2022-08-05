/**
 * @author Lucas Dahl - LDahl
 * @version 1.0
 *
 *
 */
public abstract class Monster extends DungeonCharacter {

    // **************************** Fields ****************************
    private double myChanceToHeal;
    private int myHealMin;
    private int myHealMax;

    // ************************** Constructors ************************

    /**
     *  This is the default constructor
     *
     * @param theName the name of the monster
     * @param theHealth the health of the monster
     * @param theAttackSpeed the attack speed of the monster
     * @param theHitChance the hit chance of the monster
     * @param theDamageRangeMin the damage min of the monster
     * @param theDamageRangeMax the damage max of the monster
     * @param theChanceToHeal the chance to heal of the monster
     * @param theMinHealPoints the min heal points of the monster
     * @param theMaxHealPoints the max heal points of the monster
     * @param theNumberOfAttacks the total number of attacks
     */
    public Monster(final String theName, final double theHealth, final double theAttackSpeed, final double theHitChance, final double theDamageRangeMin, final double theDamageRangeMax, final double theChanceToHeal, final int theMinHealPoints, final int theMaxHealPoints, final int theNumberOfAttacks) {
        super(theName, theHealth, theDamageRangeMin, theDamageRangeMax, theAttackSpeed, theHitChance, theNumberOfAttacks);
        myChanceToHeal = theChanceToHeal;
        myHealMax = theMaxHealPoints;
        myHealMin = theMinHealPoints;
    }

    // **************************** Methods ***************************

    // This method will have a monster heal its self
    protected void heal() {

        // Properties
        double chance = super.getMyRandomRange(0, 100);
        double healPoints = super.getMyRandomRange(myHealMin, myHealMax);

        if(chance <= myChanceToHeal) {
            super.setHealth(super.getHealth() + healPoints);
        }
    }

    //=================
    // Override Methods
    //=================

    @Override
    public  String toString() {
       return super.toString();
    }

    @Override
    public boolean isDead() {
        return super.isDead();
    }
}
