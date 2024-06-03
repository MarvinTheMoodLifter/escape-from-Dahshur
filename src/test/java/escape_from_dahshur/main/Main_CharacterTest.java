package escape_from_dahshur.main;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Main_CharacterTest {

  @Test
  void testGetters() {
    Main_Character hero = new Main_Character("Hero", 100, 50, 100, 0, 2);
    assertEquals("Hero", hero.getName());
    assertEquals(100, hero.getHealth());
    assertEquals(50, hero.getPower());
    assertEquals(100, hero.getMaxWeight());
    assertEquals(0, hero.getCurrentPosition()[0]);
    assertEquals(2, hero.getCurrentPosition()[1]);
  }

  @Test
  void testEquipItem() {
    Main_Character hero = new Main_Character("Hero", 100, 50, 100, 0, 2);
    Item sword = new Item("sword", 10, 2, 5, "weapon");
    hero.setEqItem(sword);
    assertEquals(10, hero.getEqItem().getItemScore());
    assertEquals(2, hero.getEqItem().getWeight());
  }

}
