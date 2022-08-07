package Model;

/**
 * @author lucasdahl -> LDahl
 * @version 1.0
 *
 * This class is a Model.Hero Factory that creates
 * Heros.
 *
 */
public class HeroFactory {

    /**
     *  This method will create a Model.Hero.
     *
     * @param theHero the name of the hero.
     * @return this is the created hero
     */
    public Hero createHero(final String theHero, final String theName ) {

        if(theHero == null || theHero.isEmpty()) {
            return null;
        }

        switch (theHero) {
            case "Model.Warrior":
                return new Warrior(theName);
            case "Model.Priestess":
                return new Priestess(theName);
            case "Model.Thief":
                return new Thief(theName);
            default:
                throw new IllegalArgumentException("Unknown Model.Hero " + theHero);
        }
    }
}


