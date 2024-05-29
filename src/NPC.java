public class NPC extends Entity 
{
    private int health;
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

    public NPC(String name, int health, int power, int maxWeight, int startX, int startY, String description,Boolean isfriend) 
    {
        super(name, health, power, maxWeight, startX, startY);
        this.health = health;
        this.description = description;
        this.isAlive = true;
        setCanMove(true);
        isFriendly=isfriend;
    }

    public boolean isAlive() { return isAlive; }

    public boolean isFriendly() { return isFriendly; }

    public boolean setIsFriendly(Boolean setting) { return isFriendly=setting; }

    public String getDescription() { return description; }

    public void takeDamage(int damage) 
    {
        if (!isAlive) 
        {
            System.out.println(getName() + " is already dead.");
            return;
        }
        this.health -= damage;
        System.out.println(ANSI_RED + getName() + " has taken " + damage + " damage and now has " + this.health + " health left." + ANSI_RESET);
        if (this.health <= 0) { die(); }
    }

    public void attack(Main_Character hero, int damage)
    {
        if (!isAlive) { return; }
        hero.takeDamage(damage);
    }

    private void die() 
    {
        System.out.println(getName() + " has died.");
        this.isAlive = false;
    }


    public void describe() { System.out.println(getName() + " is here. " + getDescription()); }

    public void NpcInteraction(NPC target,String interaction,Main_Character hero){
        // interazione semplice, se npc is friendly allora puoi fare questo senno combatti e basta
         //in base al npc scelto e all'interazione facciamo cose
         //evitiamo di esagerare con le interazioni senno  diventa pesante
         if(target.isFriendly()&&target.getName().equals("lost explorer")){
         if(interaction.equals("free")){
         //placeholder, da cambiare in futuro, richede un hasInv Item
         }
         }
        }
}
