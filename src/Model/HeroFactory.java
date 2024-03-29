package Model;

import java.sql.SQLException;

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
     * This method will create a Model.Hero.
     *
     * @param theHero theHero the name of the hero.
     * @return this is the created hero
     * @throws SQLException sql error
     */
    public Hero createHero(final String theHero) {

        // Properties
        DBConnection sql = null;

        try {

            sql = new DBConnection();
            return sql.extractHero(theHero);

        } catch(Exception e) {
            System.out.println("Error: " + e);
        }

        return null;
    }
}

