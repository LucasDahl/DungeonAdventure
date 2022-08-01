/**
 * @author lucasdahl -> LDahl
 * @version 1.0
 *
 * This class is a Hero Factory that creates
 * Heros.
 *
 */
public class MonsterFactory {

    /**
     * This method will create a Monster.
     *
     * @param theMonster the name of the monster.
     * @return the created monster
     */
    public Monster createMonster(final String theMonster) {

        if(theMonster == null || theMonster.isEmpty()) {
            return null;
        }

        switch (theMonster) {
            case "Ogre":
                return new Ogre();
            case "Skeleton":
                return new Skeleton();
            case "Gremlin":
                return new Gremlin();
            default:
                throw new IllegalArgumentException("Unknown Monster " + theMonster);
        }
    }
}
