package escape_from_dahshur.main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NPCTest {
  private NPC npc;
  private Main_Character hero;
  private Room room;

  @BeforeEach
  void setUp() {
    npc = new NPC("Goblin", 30, 10, 50, 0, 0, "A small, green creature.", false);
    hero = new Main_Character("Hero", 100, 50, 100, 0, 0);
    room = new Room();
    room.addEntity(npc);
  }

  @Test
  void testIsAlive() {
    assertTrue(npc.isAlive());
  }

  @Test
  void testIsFriendly() {
    assertFalse(npc.isFriendly());
  }

  @Test
  void testSetIsFriendly() {
    npc.setIsFriendly(true);
    assertTrue(npc.isFriendly());
  }

  @Test
  void testGetDescription() {
    assertEquals("A small, green creature.", npc.getDescription());
  }

  @Test
  void testTakeDamage() {
    npc.takeDamage(10);
    assertEquals(20, npc.getHealth());
  }

  @Test
  void testTakeDamageAndDie() {
    npc.takeDamage(30);
    assertEquals(0, npc.getHealth());
    assertFalse(npc.isAlive());
  }

  @Test
  void testAttack() {
    npc.attack(hero, 10);
    assertEquals(90, hero.getHealth());
  }

  @Test
  void testNpcInteraction() {
    room.removeEntity(npc);
    NPC lostExplorer = new NPC("lost explorer", 10, 0, 20, 0, 0, "lost explorer", true);
    String target = "lost explorer";
    String interaction = "free";
    Item book = new Item("book of the dead", 10, 0, 0, "book of the dead");
    hero.setEqItem(book);
    room.addEntity(lostExplorer);
    npc.NpcInteraction(target, interaction, hero, room);
    assertTrue(hero.isAlive());
    assertEquals(hero.getEqItem().getName(), "mysterious idol");
    // Assert that the explorer is no longer in the room
    assertFalse(room.hasEntity());
  }
}
