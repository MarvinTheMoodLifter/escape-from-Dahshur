package edids.escape_from_dahshur;

import java.util.List;

public class Main_Character extends Entity 
{
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_RESET = "\u001B[0m";

    private Item equippedItem;

    public Main_Character(String name, int health, int power, int maxWeight, int startX, int startY)
    {
        super(name, health, power, maxWeight, startX, startY);
        this.equippedItem = null;
    }

    public void inspectItemByName(Room room, String itemName)
    {
        List<Item> items = room.getRoomItems();
        for (Item item : items)
        {
            if (item.getName().equalsIgnoreCase(itemName))
            {
                item.inspectItem();
                return;
            }
        }
        System.out.println("Item '" + itemName + "' not found in the room.");
    }

    public void attack(NPC npc)
    {
        int damage = this.getPower();
        if(equippedItem != null) { damage += equippedItem.getDamage(); }
        if (npc.isAlive()) { npc.takeDamage(damage); }
        else { System.out.println(npc.getName() + " is already dead and cannot be attacked."); }
    }

    public void takeDamage(int damage)
    {
        this.setHealth(this.getHealth() - damage);
        System.out.println(ANSI_PURPLE + this.getName() + " has taken " + damage + " damage and now has " + this.getHealth() + " health left." + ANSI_RESET);
        if (this.getHealth() <= 0) { die(); }
    }

    public void equipItem(Item item)
    {
        this.equippedItem = item;
        System.out.println(item.getName() + " equipped. Your damage increased by " + item.getDamage() );
    }

    public boolean isAlive() { return this.getHealth() > 0; }

    private void die() { System.out.println(this.getName() + " has died."); }
  
}
