import java.util.*;
public class Room {
  private boolean roomstat;
  private String roomdescription;
  private ArrayList<Item> roomItems = new ArrayList<Item>();
  private HashMap<String, Boolean> walls = new HashMap<String, Boolean>();
  private ArrayList<NPC> roomnpc = new ArrayList<NPC>();

  public Room() {
      walls.put("up", false);
      walls.put("right", false);
      walls.put("down", false);
      walls.put("left", false);
      roomstat = true;
      roomdescription="";
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

  public void addEntity(NPC addednpc) {
      roomnpc.add(addednpc);
  }

  public void removeEntity(NPC target) {
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

  public List<Item> getRoomItems() { return roomItems; }

  public void describeEntities()
  {
      if (roomnpc.isEmpty()) { System.out.println("There are no characters in the room."); }
      else
      {
          for (Entity entity : roomnpc)
          {
              if (entity instanceof NPC) { ((NPC) entity).describe(); }
              else { System.out.println(entity.getName() + " is here."); }
          }
      }
  }

  public void talkToNPC(String npcName)
  {
      for (Entity entity : roomnpc)
      {
          if (entity instanceof NPC && entity.getName().equalsIgnoreCase(npcName))
          {
              System.out.println(npcName + " says: 'Hello, I am the " + npcName + ". " + ((NPC) entity).getDescription() + "'");
              return;
          }
      }
      System.out.println("No NPC named '" + npcName + "' found in the room.");
  }

  public NPC findNPCByName(String name)
  {
      for (NPC npc : roomnpc)
      {
          if (npc.getName().equalsIgnoreCase(name)) { return npc; }
      }
      return null;
  }

  public Item findItemByName(String name)
  {
      for (Item item : roomItems)
      {
          if (item.getName().equalsIgnoreCase(name)) { return item; }
      }
      return null;
  }
  public void setRoomDesc(String desc){
    roomdescription= desc;
  }
  public String getRoomDesc(){
    return roomdescription;
  }
  public void printRoomDesc(String desc){
    System.out.println(roomdescription);
  }
}
