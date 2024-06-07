package escape_from_dahshur.main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {
  private Room room;

  @BeforeEach
  void setUp() {
    room = new Room();
  }

  @Test
  void testAddAndRemoveItem() {
    Item item = new Item("Torch", 1, 1, 0, "A bright torch.");
    room.addItem(item);
    assertTrue(room.hasItem());
    room.deleteItem(item);
    assertFalse(room.hasItem());
  }

  @Test
  void testGetWall() {
    room.setWall("up", true);
    assertTrue(room.getWall("up"));
    assertNull(room.getWall("nonexistent"));
  }

  @Test
  void testHasWall() {
    room.setWall("left", true);
    assertTrue(room.hasWall());
    room.setWall("left", false);
    assertFalse(room.hasWall());
  }

  @Test
  void testListWall() {
    room.setWall("up", true);
    room.setWall("down", false);
    room.listwall();
    // Output check manually
  }

  @Test
  void testAddAndRemoveEntity() {
    NPC npc = new NPC("Guard", 100, 10, 20, 0, 0, "A vigilant guard.", true);
    room.addEntity(npc);
    assertTrue(room.hasEntity());
    room.removeEntity(npc);
    assertFalse(room.hasEntity());
  }

  @Test
  void testAddAndRemoveLandmark() {
    Landscape_Entity landmark = new Landscape_Entity("Statue", 0, 0, 0, "An ancient statue.");
    room.addLandmark(landmark);
    assertTrue(room.hasLandmark());
    room.removeLandmark(landmark);
    assertFalse(room.hasLandmark());
  }

  @Test
  void testIsOpenAndSetRoomStat() {
    assertTrue(room.isOpen());
    room.setRoomStat(false);
    assertFalse(room.isOpen());
  }

  @Test
  void testPrintItems() {
    Item item = new Item("Torch", 1, 1, 0, "A bright torch.");
    room.addItem(item);
    room.printItems();
    // Output check manually
  }

  @Test
  void testDescribeEntities() {
    NPC npc = new NPC("Guard", 100, 10, 20, 0, 0, "A vigilant guard.", true);
    room.addEntity(npc);
    room.describeEntities();
    // Output check manually
  }

  @Test
  void testDescribeLandmarks() {
    Landscape_Entity landmark = new Landscape_Entity("Statue", 0, 0, 0, "An ancient statue.");
    room.addLandmark(landmark);
    room.describeLandmarks();
    // Output check manually
  }

  @Test
  void testTalkToNPC() {
    NPC npc = new NPC("Guard", 100, 10, 20, 0, 0, "A vigilant guard.", true);
    room.addEntity(npc);
    room.talkToNPC("Guard");
    // Output check manually
  }

  @Test
  void testFindNPCByName() {
    NPC npc = new NPC("Guard", 100, 10, 20, 0, 0, "A vigilant guard.", true);
    room.addEntity(npc);
    assertEquals(npc, room.findNPCByName("Guard"));
    assertNull(room.findNPCByName("Nonexistent"));
  }

  @Test
  void testFindLandmarkByName() {
    Landscape_Entity landmark = new Landscape_Entity("Statue", 0, 0, 0, "An ancient statue.");
    room.addLandmark(landmark);
    assertEquals(landmark, room.findLandmarkByName("Statue"));
    assertNull(room.findLandmarkByName("Nonexistent"));
  }

  @Test
  void testFindItemByName() {
    Item item = new Item("Torch", 1, 1, 0, "A bright torch.");
    room.addItem(item);
    assertEquals(item, room.findItemByName("Torch"));
    assertNull(room.findItemByName("Nonexistent"));
  }

  @Test
  void testSetAndGetRoomDesc() {
    String description = "A dark room with stone walls.";
    room.setRoomDesc(description);
    assertEquals(description, room.getRoomDesc());
  }

  @Test
  void testSetAndGetEntrance() {
    room.setEntrance(true);
    assertTrue(room.getEntrance());
    room.setEntrance(false);
    assertFalse(room.getEntrance());
  }
}

