package View;

import Controller.DungeonAdventure;
import Model.Adventurer;
import Model.Priestess;
import Model.Thief;
import Model.Warrior;

import java.util.Scanner;

public class DungeonView {

    private DungeonAdventure dungeonAdventure;

    public DungeonView() {
        dungeonAdventure = new DungeonAdventure();
        intro();
    }

    // view is what the user sees
    // view sends info to controller

    // so a printout line is view but the user input should be sent to controller.

    private void intro() {
        Scanner input = new Scanner(System.in);
        String defaultName = "nameless bum";
        String playerName;
        System.out.println("You are trapped in a dungeon!");
        System.out.println("Only by finding the four Pillars of OO can you leave.");
        System.out.print("What is your name? ");
        String inputName = input.nextLine();


        // take the input and pass it to controller.
        // pass the name, pass the class selection and then create the adventurer in DungeonAdventure

        if (inputName.equals("")) {
            playerName = defaultName;
        } else {
            playerName = inputName;
        }
        System.out.println("Select your class " + playerName + ".\n");
        displayHeroChoices();
        System.out.print("Type \"w\" for Warrior, \"t\" for Thief, \"p\" for Priestess: ");
        String heroChoice = input.next();
        System.out.println("Hero choice is: " + heroChoice);
        setHero(playerName, heroChoice);


    }

    private void setHero(final String thePlayerName, final String theHeroChoice) {
        dungeonAdventure.setPlayerName(thePlayerName);
        dungeonAdventure.setPlayerClass(theHeroChoice);
        System.out.println("Good luck, " + thePlayerName);

    }


    private static void displayHeroChoices() {
        Warrior warrior = new Warrior("Warrior guy");
        Thief thief = new Thief("Thief guy");
        Priestess priestess = new Priestess("Priestess lady");
        // Warrior, Thief, Priestess
        System.out.println("Warrior's special skill is: " + warrior.getSpecialSkill());
        System.out.println("Thief's special skill is: " + thief.getSpecialSkill());
        System.out.println("Priestess's special skill is: " + priestess.getSpecialSkill());
    }

    private static void displayOptions() {
        // p -pickup, h - heal, v - vision f - fight
        StringBuilder sb = new StringBuilder();
//        if (myDungeon.myCurrentRoom.getExit()) {
//
//        }


    }
    private String promptUserForString(String theDisplayString) {
        Scanner input = new Scanner(System.in);
        System.out.println(theDisplayString);
        return input.next();
    }
}