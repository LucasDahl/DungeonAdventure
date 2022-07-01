public class Hero extends DungeonCharacter {

    // **************************** Fields ****************************
    // ************************** Constructors ************************
    // **************************** Methods ***************************


    @Override
    public String getName() {
        return null;
    }

    @Override
    public double getHealth() {
        return 0;
    }

    @Override
    public double getDamageRangeMin() {
        return 0;
    }

    @Override
    public double getDamageRangeMax() {
        return 0;
    }

    @Override
    public double getAttackSpeed() {
        return 0;
    }

    @Override
    public double getChanceToHit() {
        return 0;
    }

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

    @Override
    public void attackBehavior() {

    }

}
