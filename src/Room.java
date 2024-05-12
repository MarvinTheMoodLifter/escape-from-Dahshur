import java.util.*;

public class Room {
    private boolean roomstat;
    private ArrayList<Item> roomItems = new ArrayList<Item>();
    private HashMap<String, Boolean> walls = new HashMap<String, Boolean>();
    private ArrayList<Entity> roomnpc = new ArrayList<Entity>();

    public Room() {
        walls.put("up", false);
        walls.put("right", false);
        walls.put("down", false);
        walls.put("left", false);
        roomstat = true;
    }

    public void addItem(Item groundItem) {
        roomItems.add(groundItem);
    }

    public void deleteItem(Item target) {
        roomItems.remove(target);
    }

    public void clearItem() {
        roomItems.clear();
    }

    public boolean hasItem() {
        return !roomItems.isEmpty();
    }

    public Boolean getWall(String wallpos) {
        return walls.getOrDefault(wallpos.toLowerCase(), null);
    }

    public void setWall(String direction, boolean setting) {
        walls.replace(direction.toLowerCase(), setting);
    }

    public boolean hasWall() {
        return walls.containsValue(true);
    }

    public void listwall() {
        System.out.println(walls);
    }

    public boolean hasEntity() {
        return !roomnpc.isEmpty();
    }

    public void addEntity(Entity addednpc) {
        roomnpc.add(addednpc);
    }

    public void removeEntity(Entity target) {
        roomnpc.remove(target);
    }

    public boolean isOpen() {
        return roomstat;
    }

    public void setRoomStat(boolean setting) {
        roomstat = setting;
    }

    public void printItems()
    {
        if (roomItems.isEmpty()) { System.out.println("No items in the room."); }
        else
        {
            System.out.println("Items in the room:");
            for (Item item : roomItems) { System.out.println("- " + item.getName()); }
        }
    }

}
