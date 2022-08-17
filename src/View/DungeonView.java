package View;

import Controller.DungeonAdventure;
import Model.HeroFactory;
import Model.Priestess;
import Model.Thief;
import Model.Warrior;

import java.util.Scanner;

public class DungeonView {
    // singleton DungeonView
    private static final DungeonView myDungeonView = new DungeonView();

    private DungeonView() {
        // purposely left empty

    }
    // view is what the user sees
    // view sends info to controller

    public DungeonView getDungeonView() {
        return myDungeonView;
    }
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

    public static void informUser(String theDisplayMessage) {
        System.out.println(theDisplayMessage);
    }

    public static String promptUserForString(String theDisplayString) {
        Scanner input = new Scanner(System.in);
        System.out.println(theDisplayString);
        return input.next();
    }
}