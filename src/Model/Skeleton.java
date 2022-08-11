package Model;

/**
 * @author Lucas Dahl - LDahl
 * @version 1.0
 *
 *
 */
public class Skeleton extends Monster {

    // **************************** Fields ****************************
    // ************************** Constructors ************************

    /**
     *  This is the default constructor
     */
    public Skeleton() {
        super("Skeleton", 100, 3, 0.8, 30, 50, 0.3, 30, 50, 1);
    }

    // **************************** Methods ***************************

    //=================
    // Override Methods
    //=================

    @Override
    public String toString() {
        return super.toString();
    }

//    @Override
//    public void attackBehavior(final Model.DungeonCharacter theOther) {
//        super.attackBehavior(theOther);
//    }

    @Override
    public boolean isDead() {
        return super.isDead();
    }
}
