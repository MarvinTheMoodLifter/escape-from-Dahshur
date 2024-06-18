package escape_from_dahshur.main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

class Landscape_EntityTest {
  private Landscape_Entity landscape;
  private String description;
  private int power;
  private int startX;
  private int startY;
  private String landmarkdescription;
  private int landmarkpower;
  private Main_Character hero;
  private Room room;
  private Item sword;

  @BeforeEach
  void setUp() {
    landscape = new Landscape_Entity("hole", 10, 0, 0, "a big fat hole in the ground");
    description = "a big fat hole in the ground";
    startX = 0;
    startY = 0;
    hero = new Main_Character("hero", 100, 50, 100, 0, 2);
    room = new Room();
    sword = new Item("sword", 10, 2, 5, "weapon");
    room.addItem(sword);
    room.addLandmark(landscape);
  }

  @Test
  void testGetLandscapeDesc() {
    assertEquals(description, landscape.getLandscapeDesc());
    assertNotEquals("new description", landscape.getLandscapeDesc());
  }

  @Test
  void testSetLandscapeDesc() {
    landscape.setLandscapeDesc("new description");
    assertEquals("new description", landscape.getLandscapeDesc());
    assertNotEquals(description, landscape.getLandscapeDesc());
  }

  @Test
  void testDamageCharacter() {
    int damage = 10;
    int heroHealth = hero.getHealth();
    landscape.damageCharacter(hero);
    assertEquals(heroHealth - damage, hero.getHealth());
  }

  @Test
  void testLandmarkInteraction() {
    String target = "hole";
    String interaction = "enter";
    landscape.landmarkInteraction(target, interaction, hero, room);
    assertEquals(false, hero.isAlive());

    Main_Character hero2 = new Main_Character("hero", 100, 50, 100, 0, 2);
    String interaction2 = "exit";
    landscape.landmarkInteraction(target, interaction2, hero2, room);
    assertEquals(true, hero2.isAlive());

    Landscape_Entity chest = new Landscape_Entity("chest", 10, 0, 0, "an old chest");
    Item key = new Item("key", 10, 0, 0, "a key");
    room.addItem(key);
    room.addLandmark(chest);
    hero2.takeItem(key, room);
    hero2.equipItem("key");
    String target2 = "chest";
    String interaction3 = "open";
    landscape.landmarkInteraction(target2, interaction3, hero2, room);
    assertEquals(true, hero2.isAlive());
    assertEquals("golden bracelet", hero2.getEqItem().getName());
  }
}
