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
     * @param the_name This is the name of the Hero
     * @param the_health This is the total starting health of the Hero.
     * @param the_damage_range_min This is the min attack range for the Hero
     * @param the_damage_range_max This is the max attack range for the Hero
     * @param the_attack_speed This is the attack speed for the Hero
     * @param the_hit_chance This is the chance to hit for the Hero
     * @param the_block_chance This is the chance to block for the Hero
     * @param the_num_of_attacks This is the total number of attacks per turn the Hero gets
     */
    public Hero(final String the_name, final double the_health, final double the_attack_speed, final double the_hit_chance, final double the_damage_range_min, final double the_damage_range_max, final double the_block_chance, final int the_num_of_attacks) {
        super(the_name, the_health, the_damage_range_min, the_damage_range_max, the_attack_speed, the_hit_chance, the_num_of_attacks);
        MY_CHANCE_TO_BLOCK = the_block_chance;
    }

    // **************************** Methods ***************************

    // This is the method for the player to use the special skill
    protected void specialSkill(final DungeonCharacter theEnemy) {

    }

    // This method will have a character defend against another
    protected void defend(final DungeonCharacter theEnemy) {

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

//    @Override // This should be done here, not subclasses
//    protected void attackBehavior(final DungeonCharacter theOther) {
//
//        // Set the number of attacks for the Warrior
//        setNumberOfAttacks(theOther.getNumberOfAttacks() + 1);
//
//        // Attack the other character
//        for(int i = 0; i < this.getNumberOfAttacks(); i++) {
//
//            double attackHit = getMyRandomRange(0, 100);
//            double damage = super.getDamage();
//
//            // The Warrior hit the enemy
//            if(attackHit > getChanceToHit()) {
//                theOther.setHealth(theOther.getHealth() - damage);
//            }
//
//        }
//
//        // Set the number of attacks back  down to 1(for the next encounter)
//        setNumberOfAttacks(1);
//    }
}
