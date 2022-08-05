import java.util.Scanner;


/*
 * TCSS 360 - Summer 2022
 * Instructor: Tom Capaul
 * Room class for Dungeon Adventure game
 * Package condition: Must be placed in the same package
 * as all component classes.
 */
class DungeonAdventure {
    private static MockDungeon myDungeon;
    private static Adventurer myAdventurer;
    final static int DUNGEON_ROWS = 4;
    final static int DUNGEON_COLUMNS = 4;
    public static void main(String[] args) {
        myDungeon = new MockDungeon(DUNGEON_ROWS, DUNGEON_COLUMNS);
//        System.out.println(myDungeon);
        //System.out.println("Entrance: " + Arrays.toString(myDungeon.getEntrance()));
        //System.out.println("Exit: " + Arrays.toString(myDungeon.getExit()));

        intro();
        System.out.println();

        System.out.println("Showing dungeon: ");
        System.out.println("Dungeon Entrance: ");
        System.out.println(myDungeon.getEntrance());



        System.out.println(myDungeon);

    }

    private static void intro() {
        Scanner input = new Scanner(System.in);
        String defaultName = "nameless bum";
        String playerName;
        System.out.println("You are trapped in a dungeon!");
        System.out.println("Only by finding the four Pillars of OO can you leave.");
        System.out.print("What is your name? ");
        String inputName = input.nextLine();
        if (inputName.equals("")) {
            playerName = defaultName;
        } else {
            playerName = inputName;
        }
        System.out.println("Select your class " + playerName + ".\n");
        displayHeroChoices();
        System.out.print ("Type \"w\" for Warrior, \"t\" for Thief, \"p\" for Priestess: ");
        String heroChoice = input.next();
        System.out.println("Hero choice is: " + heroChoice);
        switch (heroChoice) {
            case "t" -> {
                myAdventurer = new Adventurer(new Thief(playerName));
                System.out.println("Good luck, Thief " + playerName);
            }
            case "p" -> {
                myAdventurer = new Adventurer(new Priestess(playerName));
                System.out.println("Good luck, Priestess " + playerName);
            }
            default -> {
                myAdventurer = new Adventurer(new Warrior(playerName));
                System.out.println("Good luck, Warrior " + playerName);
            }
        }


    }

    private static void displayHeroChoices() {
        Warrior warrior = new Warrior("Warrior guy");
        Thief thief = new Thief("Thief guy");
        Priestess priestess = new Priestess("Priestess lady");
        // Warrior Thief Priestess
        System.out.println("Warrior's special skill is: " + warrior.getSpecialSkill());
        System.out.println("Thief's special skill is: " + thief.getSpecialSkill());
        System.out.println("Priestess's special skill is: " + priestess.getSpecialSkill());
    }

    private static void displayOptions(){
        // p -pickup, h - heal, v - vision f - fight
        StringBuilder sb = new StringBuilder();
        if (myDungeon.myCurrentRoom.getExit()) {

        }
//        exit(); - they win
//        pickUpEverything();
//        useHealingPotion();
//        useVisionPotion();
//        displayMoveOptions();
//        checkMonster();
    }
    private static void exit(){
        if(myAdventurer.getListOfPillars().length() > 3) {
            // play win music
        } else {
            displayOptions();
        }
    }
    private static void checkMonster() {
        if(!myDungeon.myCurrentRoom.getMonster().isDead()) {
            System.out.println("f - fight Monster");
        }
    }

}





