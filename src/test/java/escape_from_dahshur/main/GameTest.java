package escape_from_dahshur.main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class GameTest {

  private Pyramid pyramid;
  private Main_Character hero;
  private File saveFile;
  private NPC npc;

  @BeforeEach
  void setUp() {
    pyramid = new Pyramid();
    hero = new Main_Character("Hero", 100, 50, 100, 0, 2);
    saveFile = new File("test_savegame.json");
    npc = new NPC("Mummy", 100, 50, 100, 0, 2, "Ugly mummy", false);
  }

  @AfterEach
  void tearDown() {
    if (saveFile.exists()) {
      saveFile.delete();
    }
  }

  // Test cases for the save game method
  @Test
  void testSaveGame() {
    Game.saveGame(hero, pyramid, "test_savegame");
    
    assertTrue(saveFile.exists());

    try {
      Gson gson = new Gson();
      FileReader reader = new FileReader(saveFile);
      JsonObject root = gson.fromJson(reader, JsonObject.class);
      reader.close();

      assertNotNull(root.get("hero"));
      assertNotNull(root.get("pyramid"));

      Main_Character loadedHero = gson.fromJson(root.get("hero"), Main_Character.class);
      Pyramid loadedPyramid = gson.fromJson(root.get("pyramid"), Pyramid.class);

      assertEquals(hero.getName(), loadedHero.getName());
      assertEquals(hero.getHealth(), loadedHero.getHealth());
      assertEquals(hero.getPower(), loadedHero.getPower());
    } catch (IOException e) {
      fail("Error reading save file: " + e.getMessage());
    }
  }

  // Test cases for the load game method
  @Test
  void testLoadGame() {
    Game.saveGame(hero, pyramid, "test_savegame");

    hero.setHealth(50);
    hero.setPower(20);

    Game.loadGame(hero, pyramid, "test_savegame");
    assertEquals("Hero", hero.getName());
    assertEquals(100, hero.getHealth());
    assertEquals(50, hero.getPower());
  }

  // Test cases for the inventory management
  @Test
  void testTakeItem() {
    Room room = pyramid.getRoom(0, 0);
    Item sword = new Item("sword", 10, 2, 5, "weapon");
    room.addItem(sword);

    hero.takeItem(sword, room);
    assertTrue(hero.getInventory().containsKey("sword"));
    assertFalse(room.getRoomItems().contains(sword));
  }

  @Test
  void testEquipItem() {
    Item sword = new Item("sword", 10, 2, 5, "weapon");
    hero.setEqItem(sword);
    assertEquals(10, hero.getEqItem().getItemScore());
    assertEquals(sword, hero.getEqItem());
  }

  // Test cases for combat mechanics
  @Test
  void testAttackNPC() {
    hero.attack(npc);
    assertEquals(50, npc.getHealth());
  }

  @Test
  void testNPCDies() {
    hero.attack(npc);
    hero.attack(npc);
    hero.attack(npc);
    assertFalse(npc.isAlive());
  }

  @Test
  void testHeroDies() {
    NPC strongNPC = new NPC("Very big mummy", 200, 150, 100, 0, 2, "Uglier", true);
    strongNPC.attack(hero, 150);
    assertFalse(hero.isAlive());
  }

  // Test cases for the movement logic
  @Test
  void testMoveValidDirection() {
    hero.move(pyramid, "down");
    assertEquals(2, hero.getCurrentPosition()[1]);
  }

  @Test
  void testMoveInvalidDirection() {
    hero.move(pyramid, "upwards");
    assertFalse(hero.getHasMoved());
  }
}
