package Model;

/**
 * @author lucasdahl -> LDahl
 * @version 1.0
 *
 * This class is a Model.Hero Factory that creates
 * Heros.
 *
 */
public class MonsterFactory {

    /**
     * This method will create a Model.Monster.
     *
     * @param theMonster the name of the monster.
     * @return the created monster
     */
    public Monster createMonster(final String theMonster) {

        if(theMonster == null || theMonster.isEmpty()) {
            return null;
        }

        switch (theMonster) {
            case "Model.Ogre":
                return new Ogre();
            case "Model.Skeleton":
                return new Skeleton();
            case "Model.Gremlin":
                return new Gremlin();
            default:
                throw new IllegalArgumentException("Unknown Model.Monster " + theMonster);
        }
    }
}
