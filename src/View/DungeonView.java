package View;

import Controller.DungeonAdventure;
import Model.HeroFactory;
import Model.Priestess;
import Model.Thief;
import Model.Warrior;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.io.*;

/**
 *
 */
public class DungeonView {
    // singleton DungeonView
    private static final DungeonView myDungeonView = new DungeonView();
    private static DungeonAdventure myGame;

    private DungeonView() {
        // purposely left empty

    }
    // view is what the user sees
    // view sends info to controller

    public DungeonView getDungeonView() {
        return myDungeonView;
    }

    /**
     *
     */
    public static void displayHeroChoices() {

        Warrior warrior;
        Thief thief;
        Priestess priestess;

        try {
            warrior = (Warrior) new HeroFactory().createHero("warrior");
            thief = (Thief) new HeroFactory().createHero("thief");
            priestess = (Priestess) new HeroFactory().createHero("priestess");

            // Warrior, Thief, Priestess
            System.out.println("Warrior's special skill is: " + warrior.getSpecialSkill());
            System.out.println("Thief's special skill is: " + thief.getSpecialSkill());
            System.out.println("Priestess's special skill is: " + priestess.getSpecialSkill());

        } catch (Exception e) {
            System.out.println("ERROR: " + e);
        }

    }

    /**
     *
     * @param theDisplayMessage
     */
    public static void informUser(String theDisplayMessage) {
        System.out.println(theDisplayMessage);
    }

    /**
     *
     * @param theDisplayString
     * @return
     */
    public static String promptUserForString(String theDisplayString) {
        Scanner input = new Scanner(System.in);
        System.out.println(theDisplayString);
        return input.next();
    }

    public static DungeonAdventure getController() {
        return myGame;
    }
}