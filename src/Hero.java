/**
 * @author Lucas Dahl - LDahl
 * @version 1.0
 *
 *
 */
public abstract class Hero extends DungeonCharacter {

    // **************************** Fields ****************************

    protected double myChanceToBlock;
    protected int myNumberOfAttacks;

    // ************************** Constructors ************************

    /**
     *  This is the default constructor
     */
    public Hero() {
        super();
        myChanceToBlock = 0.0;
        myNumberOfAttacks = 0;
    }

    // **************************** Methods ***************************


    @Override
    public String getName() {
        return super.myName;
    }

    @Override
    public double getHealth() {
        return super.myHealthPoints;
    }

    @Override
    public double getDamageRangeMin() {
        return super.myDamageRangeMin;
    }

    @Override
    public double getDamageRangeMax() {
        return super.myDamageRangeMax;
    }

    @Override
    public double getAttackSpeed() {
        return super.myAttackSpeed;
    }

    @Override
    public double getChanceToHit() {
        return super.myChanceToHit;
    }

    public abstract double getChanceToBlock();

    public abstract int getNumberOfAttacks();

    @Override
    public void setName(String THE_NAME) {
        super.myName = THE_NAME;
    }

    @Override
    public void setHealth(double THE_HEALTH) {
        super.myHealthPoints = THE_HEALTH;
    }

    @Override
    public void setDamageRangeMin(double THE_RANGE_MIN) {
        super.myDamageRangeMin = THE_RANGE_MIN;
    }

    @Override
    public void setDamageRangeMax(double THE_RANGE_MAX) {
        super.myDamageRangeMax = THE_RANGE_MAX;
    }

    @Override
    public void setAttackSpeed(double THE_ATTACK_SPEED) {
        super.myAttackSpeed = THE_ATTACK_SPEED;
    }

    @Override
    public void setChanceToHit(double THE_CHANCE_TO_HIT) {
        super.myChanceToHit = THE_CHANCE_TO_HIT;
    }

    public abstract void setChanceToBlock();

    public abstract void setNumberOfAttacks();

    @Override
    public void attackBehavior() {

    }

}
