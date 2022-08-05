/**
 * @author Lucas Dahl - LDahl
 * @version 1.0
 *
 *
 */
public class Skeleton extends Monster {

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
    protected void heal() {
        super.heal();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean isDead() {
        return super.isDead();
    }
}
