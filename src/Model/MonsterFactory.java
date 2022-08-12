package Model;

import org.sqlite.core.DB;

import java.sql.SQLException;

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
     * @param theMonster theHero the name of the monster.
     * @return this is the created monster
     * @throws SQLException sql error
     */
    public Monster createMonster(final String theMonster) {

        DBConnection sql = null;

        if(theMonster == null || theMonster.isEmpty()) {
            return null;
        }

        try {
            sql = new DBConnection();
            return sql.extractMonster(theMonster);
        } catch(Exception e) {
            System.out.println("Error: " + e);
        }

        return null;

    }
}
