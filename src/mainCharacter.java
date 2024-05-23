import java.util.*;

public class Main_Character extends Entity 
{
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_RESET = "\u001B[0m";

    private Item equippedItem;
    private Boolean hasEquippedItem;
    private HashMap<String,Item> inventory;

    public Main_Character(String name, int health, int power, int maxWeight, int startX, int startY)
    {
        super(name, health, power, maxWeight, startX, startY);
        this.equippedItem = null;
        inventory= new HashMap<>();
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
        if(hasEquippedItem){
        this.equippedItem = item;
        hasEquippedItem=true;
        System.out.println(item.getName() + " equipped. Your damage increased by " + item.getDamage() );
    }else{
        System.out.println("you are already holding an item");
    }
    }

    public boolean isAlive() { return this.getHealth() > 0; }

    private void die() { System.out.println(this.getName() + " has died."); }
  
    public void takeItem(Item item,Room room){
    if(getInvWeight()<getMaxWeight()){
        inventory.put(item.getName(),item);
        room.deleteItem(item);
    }else{
        System.out.println("your backpack is too heavy");
    } 
    }
     public void dropItem(Item item,Room room){
        room.addItem(item);
        inventory.remove(item.getName());
    }
    public int getInvWeight(){
        int s=0;
        for(String entry : inventory.keySet()) {
            s=inventory.get(entry).getWeight()+s;
            }
         return s;
    }
    public int getInvScore(){
        int s=0;
        for(String entry : inventory.keySet()) {
            s=inventory.get(entry).getItemScore()+s;
            }
         return s;
    }
    public void equipInvItem(Item item){
        if(hasEquippedItem){
        equipItem(item);
        inventory.remove(item.getName());
    }
    }
    public void setHasEqItem(Boolean setting){
    hasEquippedItem=setting;
    }
    public Boolean getHasEqItem(){
        return hasEquippedItem;
    }
    public void unequipItem(Item item,Room room){
        hasEquippedItem=false;
        if(getInvWeight()<getMaxWeight()){
            inventory.put(item.getName(),item);
            equippedItem=null;
        }else{
            dropItem(item,room);
        }
    }
    public Item getInvItem(String name){
        return inventory.get(name);
    }
    public Item setInvItem(Item item){
        return inventory.replace(item.getName(),item);
    }
}
