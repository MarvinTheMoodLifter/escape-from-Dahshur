import java.util.*;
public class Room {
    
//vector dinamico di oggetti generici 
private boolean roomstat;
private ArrayList<Items> roomItems = new ArrayList<Items>();
private HashMap<String, Boolean> walls = new HashMap<String, Boolean>();
private ArrayList<Entity> roomnpc = new ArrayList<Entity>();
public Room(){
    walls.put("up",false);
    walls.put("right",false);
    walls.put("down",false);
    walls.put("left",false);
    roomstat = true;
}
public void addItem(Items groundItem){
   roomItems.addElement(groundItem);
}
public void deleteItem(Items target){
    roomItems.remove(target);
}
public void clearItem(){
    roomItems.clear();
}
public boolean hasItem(){
return !roomItems.isEpmty();
}
public boolean getWall(String wallpos){
    if(walls.containsKey(wallpos.toLowerCase())){
        walls.get(wallpos);
 } 
}
public void setWall(String direction,boolean setting){
    if(walls.containsKey(direction.toLowerCase())){
        walls.replace(direction.toLowerCase(),setting);
 } 
}
public boolean hasWall(){
   return walls.containsValue(true);
 }
 public boolean listwall(){
    System.out.println(walls);
 }
 public boolean hasEntity(){
    return !roomnpc.isEmpty();
  }
  public void addEntity(Entity addednpc){
    roomnpc.add(addednpc);
  }
  public void removeEntity(Entity target){
    roomnpc.remove(target);
  }
  public boolean isOpen(){
    return roomstat;
  }
  public void setRoomStat(Boolean setting){
    roomstat= setting;
  }
}
 