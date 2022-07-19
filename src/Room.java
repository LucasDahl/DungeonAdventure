import java.util.Random;

public class Room {

    private String myPillarLetter;
    private boolean myPit;
    private boolean myVisionPotion;
    private boolean myHealingPotion;
    private Monster myMonster1;
    private int[] myDoorsNESW;
    private int myItemCount;

    public Room() {
        myPillarLetter = "";
        myItemCount = 0;
        myDoorsNESW = new int[] {1, 1, 1, 1};
        populateRoom();
    }

    String getPillar() {
        return myPillarLetter;
    }
    void setPillar(String thePillarLetter) {
        myPillarLetter = thePillarLetter;
        if (thePillarLetter == "") {
            myItemCount--;
        } else {
            myItemCount++;
        }
    }

    public boolean getPit() {
        return myPit;
    }

    public boolean getVisionPotion() {
        return myVisionPotion;
    }
    public void setVisionPotion(boolean theVisionPotion) {
        boolean previous = myVisionPotion;
        if (previous && !theVisionPotion) {
            myItemCount--;
        } else if (!previous && theVisionPotion) {
            myItemCount++;
        }
        myVisionPotion = theVisionPotion;
    }

    public boolean getHealingPotion() {
        return myHealingPotion;
    }
    public void setHealingPotion(boolean theHealingPotion) {
        boolean previous = myVisionPotion;
        if (previous && !theHealingPotion) {
            myItemCount--;
        } else if (!previous && theHealingPotion) {
            myItemCount++;
        }
        this.myHealingPotion = theHealingPotion;
    }

    int[] getMyDoorsNESW() {
        return myDoorsNESW;
    }

    void setMyDoorsNESW(int[] theDoors) {
        myDoorsNESW = theDoors;
    }

    private void populateRoom() {
        final int PIT_CHANCE = 20;
        final int VISION_POTION_CHANCE = 10;
        final int HEALING_POTION_CHANCE = 20;
        final int MONSTER_CHANCE = 20;

        Random rand = new Random();
        if ((rand.nextInt(100) + 1) <= PIT_CHANCE) {
            myPit = true;
            myItemCount++;
        } else {
            myPit = false;
        }
        if ((rand.nextInt(100) + 1) <= VISION_POTION_CHANCE) {
            myVisionPotion = true;
            myItemCount++;
        } else {
            myVisionPotion = false;
        }
        if ((rand.nextInt(100) + 1) <= HEALING_POTION_CHANCE) {
            myHealingPotion = true;
            myItemCount++;
        } else {
            myHealingPotion = false;
        }

        if((rand.nextInt(100) + 1) <= MONSTER_CHANCE) {
            int pick = rand.nextInt(100) + 1;
            if (pick % 3 == 0) {
                myMonster1 = new Ogre();
            } else if (pick % 3 == 1) {
                myMonster1 = new Gremlin();
            } else {
                myMonster1 = new Skeleton();
            }
        }
    }

    private String getItem() {
        String item = "";
        if (myItemCount > 1) {
            item = "M";
        } else if (myItemCount < 1) {
            item = " ";
        } else {
            if (getPillar() != "") {
                item = getPillar();
            } else if (getVisionPotion()) {
                item = "V";
            } else if (getHealingPotion()) {
                item = "H";
            } else if (getPit()) {
                item = "X";
            }
        }
        return item;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (getMyDoorsNESW()[0] == 1) {
            sb.append("*-*\n");
        } else {
            sb.append("***\n");
        }
        if (getMyDoorsNESW()[3] == 1) {
            sb.append("|");
        } else {
            sb.append("*");
        }
        sb.append(getItem());
        if (getMyDoorsNESW()[1] == 1) {
            sb.append("|\n");
        } else {
            sb.append("*\n");
        }
        if (getMyDoorsNESW()[2] == 1) {
            sb.append("*-*\n");
        } else {
            sb.append("***\n");
        }
        return sb.toString();
    }

}
