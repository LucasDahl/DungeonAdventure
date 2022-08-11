package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.sqlite.SQLiteDataSource;

/**
 *
 * @author Lucas Dahl -> LDahl
 * @version 1.0
 *
 * @see <a href="https://shanemcd.org/2020/01/24/how-to-set-up-sqlite-with-jdbc-in-eclipse-on-windows/">
 * https://shanemcd.org/2020/01/24/how-to-set-up-sqlite-with-jdbc-in-eclipse-on-windows/</a>
 *
 */
public class DBConnection {

    // **************************** Fields ****************************

    // Monster
    private SQLiteDataSource myMonsterTable;
    private String myQueryMonster;

    // Hero
    private SQLiteDataSource myHeroTable;
    private String myQueryHero;


    // ************************** Constructors ************************

    /**
     *  This is the default constructor that
     *  sets up the DB.
     *
     * @throws SQLException DQL exception error.
     */
    public DBConnection() throws SQLException {

        createMonsterTable();

        // Fill the DB
//        if(checkDBSize("monster")) {
//            fillMonsterTable();
//        }

        createHeroTable();
        fillHeroTable();
        // Fill the DB
//        if(checkDBSize("monster")) {
//            fillHeroTable();
//        }

    }

    // **************************** Methods ***************************

    private void createMonsterTable() {

        // Properties
        myMonsterTable = null;

        try {

            // Get the DB connection and create the table/statement
            myMonsterTable = new SQLiteDataSource();
            myMonsterTable.setUrl("jdbc:sqlite:monster.db");

        } catch (Exception e) {

            // There was an error
            System.out.println("ERROR creating monster data from table: " + e);
            System.exit(0);
        }

        // Set up table statement
        myQueryMonster = "CREATE TABLE IF NOT EXISTS monster ( " +
                "NAME TEXT NOT NULL, " +
                "HEALTH TEXT NOT NULL, " +
                "DAMAGE_MIN TEXT NOT NULL, " +
                "DAMAGE_MAX TEXT NOT NULL, " +
                "ATTACK_SPEED TEXT NOT NULL, " +
                "HIT_CHANCE TEXT NOT NULL, " +
                "NUMBER_OF_ATTACKS TEXT NOT NULL)";


        try(Connection conn = myMonsterTable.getConnection(); Statement stmt = conn.createStatement(); ) {

            int rv = stmt.executeUpdate( myQueryMonster );

        } catch(Exception e) {
            System.out.println("Error: " + e);
        }

        //System.out.println( "Created monsters table successfully" );

    }

    private void fillMonsterTable() {
        //next insert two rows of data
        //System.out.println( "Attempting to insert two rows into questions table" );

        // Query the data
        myQueryMonster = "INSERT INTO monster (NAME, HEALTH, DAMAGE_MIN, DAMAGE_MAX , ATTACK_SPEED , HIT_CHANCE , HEAL_CHANCE, MIN_HEAL, MAX_HEAL, NUMBER_OF_ATTACKS) VALUES ( 'Ogre', '200','30', '60', '2', '0.6', '0.1', '30', '60', '1') , ('Skeleton', '100','30', '50', '3', '0.8', '0.3', '30', '50', '1'), ('Gremlin', '70','30', '50', '3', '0.6', '0.4', '20', '40', '1'), ('Unkown', '1','1', '1', '1', '0.1', '0.1', '1', '1', '1')";

        try ( Connection conn = myMonsterTable.getConnection(); Statement stmt = conn.createStatement(); ) {
           int rv = stmt.executeUpdate( myQueryMonster );
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }

    }

    /**
     *  This method will extract a monster.
     *
     * @param theMonster the name oif the monster to extract.
     * @return this the monster to return.
     */
    public Monster extractMonster(final String theMonster) {

        // Debug statement
        //System.out.println( "Selecting all rows from monster table" );

        String query = theMonster;
        Monster monster = null;

        // Determine the select statement
        switch (query) {
            case "Ogre":
                query = "SELECT * FROM monster WHERE NAME = 'Ogre'";
            case "Skeleton":
                query = "SELECT * FROM monster WHERE NAME = 'Skeleton'";
            case "Gremlin":
                query = "SELECT * FROM monster WHERE NAME = 'Gremlin'";
            default:
                query = "SELECT * FROM monster WHERE NAME = 'Unkown'";
        }

        try ( Connection conn = myMonsterTable.getConnection(); Statement stmt = conn.createStatement(); ) {

            ResultSet rs = stmt.executeQuery(query);

            String name = rs.getString( "NAME" );
            Double health = Double.parseDouble(rs.getString( "HEALTH" ));
            Double damageMin = Double.parseDouble(rs.getString( "DAMAGE_MIN" ));
            Double damageMax = Double.parseDouble(rs.getString( "DAMAGE_MAX" ));
            Double attackSpeed = Double.parseDouble(rs.getString( "ATTACK_SPEED" ));
            Double hitChance = Double.parseDouble(rs.getString( "HIT_CHANCE" ));
            Double chanceToHeal = Double.parseDouble(rs.getString( "HEAL_CHANCE" ));
            Double minHealPoints = Double.parseDouble(rs.getString( "MIN_HEAL" ));
            Double maxHealPoints = Double.parseDouble(rs.getString( "MAX_HEAL" ));
            Integer numberOfAttacks = Integer.parseInt(rs.getString( "NUMBER_OF_ATTACKS" ));


            monster = new Model.Monster(name, health, damageMin, damageMax, attackSpeed, hitChance, chanceToHeal, minHealPoints, maxHealPoints, numberOfAttacks);

        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }

        return monster;
    }

    private void createHeroTable() {

        // Properties
        myHeroTable = null;

        try {

            // Get the DB connection and create the table/statement
            myHeroTable = new SQLiteDataSource();
            myHeroTable.setUrl("jdbc:sqlite:hero.db");

        } catch (Exception e) {

            // There was an error
            System.out.println("ERROR creating hero table: " + e);
            System.exit(0);
        }

        // Set up table statement
        myQueryHero = "CREATE TABLE IF NOT EXISTS hero ( " +
                "NAME TEXT NOT NULL, " +
                "HEALTH TEXT NOT NULL, " +
                "DAMAGE_MIN TEXT NOT NULL, " +
                "DAMAGE_MAX TEXT NOT NULL, " +
                "ATTACK_SPEED TEXT NOT NULL, " +
                "HIT_CHANCE TEXT NOT NULL, " +
                "BLOCK_CHANCE TEXT NOT NULL, " +
                "NUMBER_OF_ATTACKS TEXT NOT NULL)";

        try(Connection conn = myHeroTable.getConnection();
            Statement stmt = conn.createStatement(); ) {

            int rv = stmt.executeUpdate( myQueryHero );

        } catch(Exception e) {
            System.out.println("Error: " + e);
        }

        //System.out.println( "Created hero table successfully" );

    }

    private void fillHeroTable() {

        //next insert two rows of data
        //System.out.println( "Attempting to insert two rows into hero table" );

        // Query the data
        myQueryHero = "INSERT INTO hero (NAME, HEALTH, DAMAGE_MIN, DAMAGE_MAX , ATTACK_SPEED , HIT_CHANCE, BLOCK_CHANCE, NUMBER_OF_ATTACKS) VALUES ( 'Warrior', '125', '35', '60', '4', '0.8', '0.2', '1' ) , ('Priestess', '75', '25', '45', '5', '0.7', '0.3', '1'), ('Thief', '75', '20', '40', '4', '0.8', '0.4', '1'), ('Unkown', '1', '1', '1', '1', '0.1', '0.1', '1')";

        try ( Connection conn = myHeroTable.getConnection(); Statement stmt = conn.createStatement(); ) {
            int rv = stmt.executeUpdate( myQueryHero );
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }

    }

    /**
     *  This method will extract a hero.
     *
     * @param theHero the name oif the hero to extract.
     * @return this the hero to return.
     */
    public Hero extractHero(final String theHero) {

        // Debug statement
        //System.out.println( "Selecting all rows from hero table" );

        String query = theHero.toUpperCase();
        Hero hero = null;

        // Determine the select statement
        switch (query) {
            case "WARRIOR":
                query = "SELECT * FROM hero WHERE NAME = 'Warrior'";
                break;
            case "PRIESTESS":
                query = "SELECT * FROM hero WHERE NAME = 'Priestess'";
                break;
            case "THIEF":
                query = "SELECT * FROM hero WHERE NAME = 'Thief'";
                break;
            default:
                query = "SELECT * FROM hero WHERE NAME = 'Unkown'";
                break;
        }

        try ( Connection conn = myHeroTable.getConnection(); Statement stmt = conn.createStatement(); ) {

            ResultSet rs = stmt.executeQuery(query);

            String name = rs.getString( "NAME" );
            Double health = Double.parseDouble(rs.getString( "HEALTH" ));
            Double damageMin = Double.parseDouble(rs.getString( "DAMAGE_MIN" ));
            Double damageMax = Double.parseDouble(rs.getString( "DAMAGE_MAX" ));
            Double attackSpeed = Double.parseDouble(rs.getString( "ATTACK_SPEED" ));
            Double hitChance = Double.parseDouble(rs.getString( "HIT_CHANCE" ));
            Double blockChance = Double.parseDouble(rs.getString( "BLOCK_CHANCE" ));
            Integer numberOfAttacks = Integer.parseInt(rs.getString( "NUMBER_OF_ATTACKS" ));

            switch (theHero.toUpperCase()) {
                case "WARRIOR":
                    hero = new Warrior(name, health, damageMin, damageMax, attackSpeed, hitChance, blockChance, numberOfAttacks);
                    break;
                case "PRIESTESS":
                    hero = new Priestess(name, health, damageMin, damageMax, attackSpeed, hitChance, blockChance, numberOfAttacks);
                    break;
                case "THIEF":
                    hero = new Thief(name, health, damageMin, damageMax, attackSpeed, hitChance, blockChance, numberOfAttacks);
                    break;
                default:
                    query = "SELECT * FROM monster WHERE NAME = 'Unkown'";
            }


        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }

        return hero;
    }

    private boolean checkDBSize(final String theTable) {

        // Properties
        String query = "SELECT * FROM hero";
        SQLiteDataSource table;
        int num = 0;

        // pick which table to check
        if(theTable.equals("hero")) {
            table = myHeroTable;
        } else {
            table = myMonsterTable;
        }

        try ( Connection conn = table.getConnection(); Statement stmt = conn.createStatement(); ) {

            ResultSet rs = stmt.executeQuery(query);

            while ( rs.next() ) {
               num++;
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }

        if(num > 0) {
            return false;
        } else {
            return true;
        }

    }
}
