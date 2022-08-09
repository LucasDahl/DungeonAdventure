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
https://shanemcd.org/2020/01/24/how-to-set-up-sqlite-with-jdbc-in-eclipse-on-windows/</a>
 *
 */
public class DBConnection {

    // **************************** Fields ****************************

    private Connection mySQLConnection;

    // Monster
    private SQLiteDataSource myMonsterTable;
    private Statement myStatementMonster;
    private String myQueryMonster;
    private int myValueMonster;
    private ResultSet myResultMonster;

    // Hero
    private SQLiteDataSource myHeroTable;
    private String myQueryHero;
    private ResultSet myResultHero;
    private Statement myStatementHero;
    private int myValueHero;


    // ************************** Constructors ************************

    /**
     *  This is the default constructor that
     *  sets up the DB.
     *
     * @throws SQLException DQL exception error.
     */
    public DBConnection() throws SQLException {
        createMonsterTable();
        fillMonsterTable();
        createHeroTable();
        fillHeroTable();
    }

    // **************************** Methods ***************************

    private void createMonsterTable() {

        // Properties
        myMonsterTable = new SQLiteDataSource();

        // Set up the create table statement
        myQueryMonster = "CREATE TABLE monsterDB (" + "NAME TEXT PRIMARY KEY," + "HEALTH TEXT NOT NULL," + "DAMAGE_MIN TEXT NOT NULL," + "DAMAGE_MAX TEXT NOT NULL," + "ATTACK_SPEED TEXT NOT NULL," +  "HIT_CHANCE TEXT NOT NULL," + "NUMBER_OF_ATTACKS TEXT NOT NULL,";

        try {

            // Get the DB connection and create the table/statement
            mySQLConnection =  myMonsterTable.getConnection();
            myStatementMonster = mySQLConnection.createStatement();
            myValueMonster = myStatementMonster.executeUpdate(myQueryMonster);

        } catch (SQLException e) {

            // There was an error
            System.out.println("ERROR creating monster data from table: " + e);
            System.exit(0);
        }
    }

    private void fillMonsterTable() {

        // Query the data to fill the table
        myQueryMonster = "INSERT INTO 'monsterDB' ('NAME','HEALTH','DAMAGE_MIN','DAMAGE_MAX','ATTACK_SPEED','HIT_CHANCE','NUMBER_OF_ATTACKS') VALUES" +
                "('Ogre', '200','2','0.6','30','60','0.1','30', '60', '1')," +
                "('Skeleton', '100','3','0.8','30','50','0.3','30', '50', '1')," +
                "('Gremlin', '70','5','0.8','15','30','0.4','20', '40', '1'),";

        try {

            // Update the value
            myValueMonster = myStatementMonster.executeUpdate(myQueryMonster);

        } catch (SQLException e) {

            // There was an error
            System.out.println("Error filling values into enemy table");
            System.exit(0);
        }
    }

    private void createHeroTable() {

        // Properties
        myHeroTable = new SQLiteDataSource();

        // Set up the create table statement
        myQueryHero = "CREATE TABLE monsterDB (" + "NAME TEXT PRIMARY KEY," + "HEALTH TEXT NOT NULL," + "DAMAGE_MIN TEXT NOT NULL," + "DAMAGE_MAX TEXT NOT NULL," + "ATTACK_SPEED TEXT NOT NULL," +  "HIT_CHANCE TEXT NOT NULL," + "NUMBER_OF_ATTACKS TEXT NOT NULL,";

        try {

            // Get the DB connection and create the table/statement
            mySQLConnection =  myMonsterTable.getConnection();
            myStatementHero = mySQLConnection.createStatement();
            myValueHero = myStatementHero.executeUpdate(myQueryHero);

        } catch (SQLException e) {

            // There was an error
            System.out.println("ERROR creating monster data from table: " + e);
            System.exit(0);
        }
    }

    private void fillHeroTable() {

        // Query the data to fill the table
        myQueryHero = "INSERT INTO 'heroDB' ('NAME','HEALTH','DAMAGE_MIN','DAMAGE_MAX','ATTACK_SPEED','HIT_CHANCE','NUMBER_OF_ATTACKS', 'BLOCK_CHANCE') VALUES" +
                "('Warrior', '125', '35', '60', '4', '0.8', '1', '0.2')," +
                "('Priestess', '75', '25', '45', '5', '0.7', '1', '0.3')," +
                "('Thief', '125', '20', '40', '6', '0.8', '1', '0.4'),";

        try {

            // Update the value
            myValueHero = myStatementHero.executeUpdate(myQueryHero);

        } catch (SQLException e) {

            // There was an error
            System.out.println("Error filling values into enemy table");
            System.exit(0);
        }
    }

    public String extractHero() {

        // Properties
        String data = "";
        myQueryHero = "SELECT * FROM heroDB LIMIT 1 OFFSET 1";

        try {

            // Set up the result
            myResultHero = myStatementHero.executeQuery(myQueryHero);

            // Get the data
            while(this.myResultMonster.next()) {
                data += myResultHero.getString("NAME") + "\n";
                data += myResultHero.getString("HEALTH") + "\n";
                data += myResultHero.getString("DAMAGE_MIN") + "\n";
                data += myResultHero.getString("DAMAGE_MAX") + "\n";
                data += myResultHero.getString("ATTACK_SPEED") + "\n";
                data += myResultHero.getString("HIT_CHANCE") + "\n";
                data += myResultHero.getString("NUMBER_OF_ATTACKS") + '\n';
                data += myResultHero.getString("BLOCK_CHANCE");
            }
        } catch (SQLException e) {

            // There was an error
            System.out.println("ERROR getting data from table: " + e);
            System.out.println(e.getMessage());
            System.exit(0);
        }

        return data;
    }

    public String extractMonster() {

        // Properties
        String data = "";
        myQueryMonster = "SELECT * FROM monsterDB LIMIT 1 OFFSET 1";

        try {

            // Set up the result
            myResultMonster = myStatementMonster.executeQuery(myQueryMonster);

            // Get the data
            while(this.myResultMonster.next()) {
                data += myResultMonster.getString("NAME") + "\n";
                data += myResultMonster.getString("HEALTH") + "\n";
                data += myResultMonster.getString("DAMAGE_MIN") + "\n";
                data += myResultMonster.getString("DAMAGE_MAX") + "\n";
                data += myResultMonster.getString("ATTACK_SPEED") + "\n";
                data += myResultMonster.getString("HIT_CHANCE") + "\n";
                data += myResultMonster.getString("NUMBER_OF_ATTACKS");
            }

        } catch (SQLException e) {

            // There was an error
            System.out.println("ERROR getting data from table: " + e);
            System.exit(0);
        }
        return data;
    }
}