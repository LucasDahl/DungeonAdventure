/**
 *
 */
public class HeroFactory {

    /**
     *
     * @param theHero
     * @return
     */

    public Hero createHero(final String theHero, final String theName ) {
        if(theHero == null || theHero.isEmpty()) {
            return null;
        }
        switch (theHero) {
            case "Warrior":
                return new Warrior(theName);
            case "Priestess":
                return new Priestess(theName);
            case "Thief":
                return new Thief(theName);
            default:
                throw new IllegalArgumentException("Unknown Hero " + theHero);
        }
    }
}


