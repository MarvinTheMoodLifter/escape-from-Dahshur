package escape_from_dahshur.main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main_Character extends Entity
{
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_RESET = "\u001B[0m";

    private Item equippedItem;
    private boolean hasEquippedItem;
    private HashMap<String, Item> inventory;

    public Main_Character(String name, int health, int power, int maxWeight,
                          int startX, int startY)
    {
        super(name, health, power, maxWeight, startX, startY);
        this.equippedItem = null;
        this.hasEquippedItem = false;
        this.inventory = new HashMap<>();
        setCanMove(true);
    }

    public HashMap<String, Item> getInventory() { return inventory; }

    public void inspectItemByName(Room room, String itemName)
    {
        List<Item> items = room.getRoomItems();
        for (Item item : items) {
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
        if (equippedItem != null) { damage += equippedItem.getDamage(); }
        if (npc.isAlive()) { npc.takeDamage(damage); }
        else
        {
            System.out.println(npc.getName() +
                    " is already dead and cannot be attacked.");
        }
    }

    public void takeDamage(int damage)
    {
        this.setHealth(this.getHealth() - damage);
        System.out.println(ANSI_PURPLE + this.getName() + " has taken " + damage +
                " damage and now has " + this.getHealth() +
                " health left." + ANSI_RESET);
        if (this.getHealth() <= 0) { die(); }
    }

    public void equipItem(String itemName)
    {
        Item item = inventory.get(itemName.toLowerCase());

        if (item != null) {
            if (!hasEquippedItem)
            {
                this.equippedItem = item;
                this.hasEquippedItem = true;
                System.out.println(item.getName() +
                        " equipped. Your damage increased by " +
                        item.getDamage());
                inventory.remove(item.getName().toLowerCase());
            }
            else
            {
                System.out.println(
                        "You are already holding an item. Unequip the current item first.");
            }
        }
        else { System.out.println("Item not found in inventory."); }
    }

    public boolean isAlive() { return this.getHealth() > 0; }

    private void die() { System.out.println(this.getName() + " has died."); }

    public void takeItem(Item item, Room room)
    {
        if (getInvWeight() + item.getWeight() <= getMaxWeight()) {
            inventory.put(item.getName().toLowerCase(), item);
            room.deleteItem(item);
            System.out.println(item.getName() + " taken.");
        }
        else { System.out.println("Your backpack is too heavy."); }
    }

    public void dropItem(Item item, Room room)
    {
        room.addItem(item);
        inventory.remove(item.getName().toLowerCase());
        System.out.println(item.getName() + " dropped.");
    }

    public int getInvWeight()
    {
        int totalWeight = 0;
        for (Item item : inventory.values()) { totalWeight += item.getWeight(); }
        return totalWeight;
    }

    public int getInvScore()
    {
        int totalScore = 0;
        for (Item item : inventory.values()) { totalScore += item.getItemScore(); }
        if(hasEquippedItem) { totalScore=totalScore+equippedItem.getItemScore(); }
        return totalScore;
    }

    public void setHasEqItem(boolean setting) { hasEquippedItem = setting; }

    public boolean getHasEqItem() { return hasEquippedItem; }

    public Item getEqItem() { return equippedItem; }

    public void setEqItem(Item item) { equippedItem = item; }

    public void unequipItem(Room room)
    {
        if (hasEquippedItem && equippedItem != null)
        {
            if (getInvWeight() + equippedItem.getWeight() <= getMaxWeight())
            {
                inventory.put(equippedItem.getName().toLowerCase(), equippedItem);
                System.out.println(equippedItem.getName() + " unequipped.");
                equippedItem = null;
                hasEquippedItem = false;
            }
            else
            {
                dropItem(equippedItem, room);
                equippedItem = null;
                hasEquippedItem = false;
            }
        }
        else { System.out.println("No item is equipped."); }
    }

    public Item getInvItem(String name) { return inventory.getOrDefault(name.toLowerCase(), null); }

    public Item setInvItem(Item item) { return inventory.replace(item.getName().toLowerCase(), item); }

    public void printInventory()
    {
        if (inventory.isEmpty()) { System.out.println("Your inventory is empty."); }
        else
        {
            System.out.println(ANSI_PURPLE + "Your inventory:");
            for (Item item : inventory.values()) { System.out.println("- " + item.getName()); }
        }

        if (hasEquippedItem && equippedItem != null)
        {
            System.out.println(ANSI_PURPLE +
                    "Equipped item: " + equippedItem.getName());
        }
        else { System.out.println(ANSI_PURPLE + "No item equipped."); }
        System.out.println("your health is: "+getHealth());
        System.out.println("your weight is: "+getInvWeight()+" out of "+ getMaxWeight());
        System.out.println("you have: "+ getScore()+" score from defeating npcs and "+getInvScore()+" score from items");
        System.out.println("your current total score is: "+(getScore()+getInvScore()));
    }

    public String toJson()
    {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }

    public static Main_Character fromJson(String json)
    {
        Gson gson = new Gson();
        return gson.fromJson(json, Main_Character.class);
    }

    public static Main_Character fromJsonFile(String filePath)
            throws IOException
    {
        Gson gson = new Gson();
        FileReader reader = new FileReader(filePath);
        Main_Character character = gson.fromJson(reader, Main_Character.class);
        reader.close();
        return character;
    }

    public void updateFrom(Main_Character other)
    {
        this.name = other.getName();
        this.health = other.getHealth();
        this.power = other.getPower();
        this.maxWeight = other.getMaxWeight();
        this.items = other.getItems();
        this.score = other.getScore();
        this.currentPosition = other.getCurrentPosition();
        this.hasmoved = other.getHasMoved();
        this.canmove = other.getCanMove();
        this.hasEquippedItem = other.getHasEqItem();
        this.equippedItem = other.getEqItem();
        this.inventory = other.getInventory();
    }
}
