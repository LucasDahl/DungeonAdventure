import java.util.Random;

/**
 * @author Lucas Dahl - LDahl
 * @version 1.0
 *
 *
 */
public abstract class Monster extends DungeonCharacter {

    // **************************** Fields ****************************
    private Random myRandom;


    // ************************** Constructors ************************

    /**
     *  This is the default constructor
     */
    public Monster(final String the_name, final double the_health, final double the_attack_speed, final double the_hit_chance, final double the_damage_range_min, final double the_damage_range_max, final double the_chance_to_heal, final double the_min_heal_points, final double the_max_heal_points, final int the_number_of_attacks) {
        super(the_name, the_health, the_damage_range_min, the_damage_range_max, the_attack_speed, the_hit_chance, the_number_of_attacks);
    }

    // **************************** Methods ***************************

    //=================
    // Override Methods
    //=================

    public  String toString() {
       return super.toString();
    }

    @Override
    public void attackBehavior(final DungeonCharacter theOther) {

        // Attack the other character
        for(int i = 0; i < getMyNumberOfAttacks(); i++) {

            double attackHit = myRandom.nextDouble() * 100.0;
            double damage = getDamageRangeMin() + (getDamageRangeMax() - getDamageRangeMin()) * myRandom.nextDouble();

            // The Warrior hit the enemy
            if(attackHit > getChanceToHit()) {
                theOther.setHealth(theOther.getHealth() - damage);
            }
        }
    }
}
