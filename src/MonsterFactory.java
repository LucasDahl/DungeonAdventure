/**
 *
 */
public class MonsterFactory {

    /**
     *
     * @param theMonster
     * @return
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
