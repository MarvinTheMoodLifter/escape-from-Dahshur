package escape_from_dahshur.main;

public class NPC extends Entity {
    private String description;
    private boolean isAlive;
    private boolean isFriendly;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public NPC(String name, int health, int power, int maxWeight, int startX,
               int startY, String description, Boolean isfriend) {
        super(name, health, power, maxWeight, startX, startY);
        this.health = health;
        this.description = description;
        this.isAlive = true;
        setCanMove(true);
        isFriendly = isfriend;
    }

    public boolean isAlive() { return isAlive; }

    public boolean isFriendly() { return isFriendly; }

    public boolean setIsFriendly(Boolean setting) { return isFriendly = setting; }

    public String getDescription() { return description; }

    public void setDescription(String desc) { description=desc; }

    public void takeDamage(int damage) {
        if (!isAlive) {
            System.out.println(getName() + " is already dead.");

        }
        this.health -= damage;
        System.out.println(ANSI_RED + getName() + " has taken " + damage +
                " damage and now has " + this.health +
                " health left." + ANSI_RESET);
        if (this.health <= 0) {
            die();
            setDescription("a lifeless corpse is all that remains on the ground");
        }
    }

    public void attack(Main_Character hero, int damage) {
        if (!isAlive) {
            return;
        }
        hero.takeDamage(damage);
    }

    private void die() {
        System.out.println(getName() + " has died.");
        this.isAlive = false;

    }

    public void describe() {
        System.out.println(ANSI_PURPLE + getName() + ANSI_RESET + " is here. " + getDescription());
    }

    public void NpcInteraction(String target, String interaction,
                               Main_Character hero, Room room) {
        try{
            if (room.findNPCByName(target).isFriendly() &&
                    room.findNPCByName(target).getName().equals("lost explorer")) {
                if (interaction.equals("free") &&
                        hero.getEqItem().getName().equals("book of the dead")) {
                    System.out.println(
                            "as you free the aventurer his form begins to vanish and the " +
                                    "book you are holding goes up in flames. Your heart tightens at " +
                                    "the loss of such precious knowledge, however the adventurer " +
                                    "before disappearing places an item in your hand");
                    hero.setEqItem(
                            new Item("mysterious idol", 20, 10, 0,
                                    "you can't quite tell wether or not it belongs to a " +
                                            "diety or some sort of demon, all you know is that it " +
                                            "emanates a powerful aura. It is probably safe to keep " +
                                            "now that the curse is gona you think"));
                    room.removeEntity(room.findNPCByName(target));
                } else {
                    System.out.println("as you hold " +
                            hero.getEqItem().getName() +
                            (" nothing happens, peraphs you could try looking " +
                                    "for an ancient tome"));
                }
            }
        }catch(NullPointerException e){
            System.out.println("nothing happens as you aren't holding any items");
        }
    }
}
