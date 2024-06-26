package escape_from_dahshur.main;

public class Landscape_Entity extends Entity {
    private String landmarkdescription;
    private int landmarkpower;
    public Landscape_Entity(String name, int power, int startX, int startY,
                            String landmarkdescription) {
        super(name, 0, power, 1000, startX, startY);

        setCanMove(false);
        this.landmarkdescription = landmarkdescription;
        this.landmarkpower = power;
    }

    public String getLandscapeDesc() { return landmarkdescription; }

    public void setLandscapeDesc(String desc) { landmarkdescription = desc; }

    public void damageCharacter(Main_Character victim) {
        victim.takeDamage(landmarkpower);
    }
    public void describeLandscape() {
        System.out.println(getName() + " is here. " + getLandscapeDesc());
    }
    public void landmarkInteraction(String target, String interaction,
                                    Main_Character hero, Room room) {
        try{
            switch (room.findLandmarkByName(target).getName()) {
                case "hole":
                    if (interaction.toLowerCase().equals("enter")) {
                        System.out.println("you jump in, your curiosity got the better of you.\n"+
                                "...\n"+
                                "...\n"+
                                "...\n"+
                                Game.wrapText("After what feels like an eternity you finally reach the bottom, unfortunately there is nothing to cushon your fall.\n"+
                                        "you die instantly.\n", Game.CONSOLE_WIDTH));
                        hero.setHealth(0);
                        break;
                    } else {
                        break;
                    }
                case "chest":
                    if (interaction.toLowerCase().equals("open") &&
                            (hero.getEqItem().getName().equals("key"))) {
                        room.findLandmarkByName(target).setLandscapeDesc(
                                Game.wrapText("as you open the chest the key becomes one with the lock. The " +
                                        "chest has been opened it is of no use now", Game.CONSOLE_WIDTH));
                        System.out.println("you pick up a golden bracelet");
                        hero.setEqItem(new Item(
                                "golden bracelet", 10, 1, 0,
                                "a golden bracelet with intricate patters and precious gems"));
                        break;
                    } else {
                        System.out.println("you can't open the chest, you need a key");
                        break;
                    }
            }
        } catch(NullPointerException e){
            System.out.println("nothing happens as you aren't holding an item");
        }
    }
}
