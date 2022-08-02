/**
 * @author lucasdahl -> LDahl
 * @version 1.0
 *
 * This class is a Hero Factory that creates
 * Heros.
 *
 */
public class HeroFactory {

    /**
     *  This method will create a Hero.
     *
     * @param theHero the name of the hero.
     * @return this is the created hero
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


