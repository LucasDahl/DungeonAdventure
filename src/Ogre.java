/**
 * @author Lucas Dahl - LDahl
 * @version 1.0
 *
 *
 */
public class Ogre extends Monster {

    // ************************** Constructors ************************

    /**
     *  This is the default constructor
     */
    public Ogre() {
        super("Ogre", 200, 2, 0.6, 30, 60, 0.1, 30, 60, 1);
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
