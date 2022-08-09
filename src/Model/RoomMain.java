package Model;

import Model.Room;

public class RoomMain {
    public static void main(String[] args) {
        Room room = new Room();
        System.out.println("Printing Model.Room");
        System.out.println(room);

        System.out.println("Closing all doors");
        int[] doors = {0,0,0,0}; //declare array first
       // room.setMyDoorsNESW(doors);
        System.out.println(room);

        System.out.println("Setting room as entrance");
        room.setEntrance(true);
        System.out.println(room);

        System.out.println("Set room as not entrance");
        room.setEntrance(false);
        System.out.println(room);

        System.out.println("Set room as exit");
        room.setExit(true);
        System.out.println(room);

        System.out.println("Set room as not exit");
        room.setExit(false);
        System.out.println(room);

        System.out.println("Place pillar");
        room.setPillar("A");
        System.out.println(room);

        System.out.println("Place pit");
        room.emptyRoom();
        room.setPit(true);
        System.out.println(room);

        System.out.println("Place Healing Potion");
        room.emptyRoom();
        room.setHealingPotion(true);
        System.out.println(room);

        System.out.println("Place Vision Potion");
        room.emptyRoom();
        room.setVisionPotion(true);
        System.out.println(room);
    }
}
