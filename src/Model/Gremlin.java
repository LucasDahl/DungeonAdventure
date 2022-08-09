package Model;

/**
 * @author Lucas Dahl - LDahl
 * @version 1.0
 *
 *
 */
public class Gremlin extends Monster {

    // **************************** Fields ****************************

    // ************************** Constructors ************************

    /**
     *  This is the default constructor
     */
    public Gremlin() {
        super("Model.Gremlin", 70, 5, 0.8, 15, 30, 0.4, 20, 40, 1);
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
}
