package escape_from_dahshur.main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

class Main_CharacterTest {
  private Main_Character hero;
  private Item sword;
  private Room room;
  private Pyramid pyramid;

  @BeforeEach
  void setUp() {
    hero = new Main_Character("Hero", 100, 50, 100, 0, 2);
    sword = new Item("Sword", 10, 2, 5, "A sharp blade used in battles.");
    room = new Room();
    Pyramid pyramid = new Pyramid();
    room.addItem(sword);
  }

  @Test
  void testGetters() {
    assertEquals("Hero", hero.getName());
    assertEquals(100, hero.getHealth());
    assertEquals(50, hero.getPower());
    assertEquals(100, hero.getMaxWeight());
    assertEquals(0, hero.getCurrentPosition()[0]);
    assertEquals(2, hero.getCurrentPosition()[1]);
  }

  @Test
  void testEquipItem() {
    hero.takeItem(sword, room);
    hero.equipItem("Sword");
    assertEquals(sword.getName(), hero.getEqItem().getName());
    assertTrue(hero.getHasEqItem());
  }

  @Test
  void testEquipItemNotInInventory() {
    Item axe = new Item("Axe", 10, 2, 5, "Useful for opening skulls");
    hero.equipItem("Axe");
    assertNull(hero.getEqItem());
    assertFalse(hero.getHasEqItem());
  }

  @Test
  void testUnequipItem() {
    hero.takeItem(sword, room);
    hero.unequipItem(room);
    assertNull(hero.getEqItem());
    assertFalse(hero.getHasEqItem());
  }

  @Test
  void testTakeDamage() {
    hero.takeDamage(10);
    assertEquals(90, hero.getHealth());
  }

  @Test
  void testDie() {
    hero.takeDamage(100);
    assertEquals(0, hero.getHealth());
    assertFalse(hero.isAlive());
  }

  @Test
  void testAttack() {
    NPC npc = new NPC("Goblin", 30, 2, 20, 1, 1, "Little goblin", false);
    hero.attack(npc);
    assertEquals(30 - 50, npc.getHealth());
  }

  @Test
  void testAttackWithEquippedItem() {
    NPC npc = new NPC("Goblin", 90, 2, 20, 1, 1, "Little goblin", false);
    hero.addItem(sword);
    hero.setEqItem(sword);
    hero.attack(npc);
    assertEquals(90 - (50 + 5), npc.getHealth());
  }

  @Test
  void testTakeItem() {
    hero.takeItem(sword, room);
    assertEquals(sword, hero.getInvItem("sword"));
    assertTrue(hero.getInventory().containsKey("sword"));
  }

  @Test
  void testDropItem() {
    hero.takeItem(sword, room);
    hero.dropItem(sword, room);
    assertFalse(hero.getInventory().containsKey("sword"));
    assertTrue(room.getRoomItems().contains(sword));
  }

  @Test
  void testGetInvWeight() {
    hero.takeItem(sword, room);
    assertEquals(2, hero.getInvWeight());
  }

  @Test
  void testGetInvScore() {
    hero.takeItem(sword, room);
    assertEquals(10, hero.getInvScore());
  }

  @Test
  void testToJson() {
    String json = hero.toJson();
    assertNotNull(json);
    assertTrue(json.contains("Hero"));
  }

  @Test
  void testFromJson() {
    String json = hero.toJson();
    Main_Character newHero = Main_Character.fromJson(json);
    assertEquals(hero.getName(), newHero.getName());
    assertEquals(hero.getHealth(), newHero.getHealth());
  }

  @Test
  void testFromJsonFile() throws IOException {
    String jsonFilePath = "test_hero.json";
    FileWriter writer = new FileWriter(jsonFilePath);
    writer.write(hero.toJson());
    writer.close();

    Main_Character loadedHero = Main_Character.fromJsonFile(jsonFilePath);
    assertEquals(hero.getName(), loadedHero.getName());
    assertEquals(hero.getHealth(), loadedHero.getHealth());
  }

  @Test
  void testUpdateFrom() {
    Main_Character otherHero = new Main_Character("OtherHero", 80, 30, 80, 1, 1);
    otherHero.takeItem(sword, room);
    hero.updateFrom(otherHero);

    assertEquals("OtherHero", hero.getName());
    assertEquals(80, hero.getHealth());
    assertEquals(30, hero.getPower());
    assertTrue(hero.getInventory().containsKey("sword"));
  }
}

