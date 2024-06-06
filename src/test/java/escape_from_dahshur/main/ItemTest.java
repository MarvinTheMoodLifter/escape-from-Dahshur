package escape_from_dahshur.main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
  private Item item;

  @BeforeEach
  void setUp() {
    item = new Item("Sword", 10, 5, 15, "A sharp blade used in battles.");
  }

  @Test
  void testGetName() {
    assertEquals("Sword", item.getName());
  }

  @Test
  void testGetItemScore() {
    assertEquals(10, item.getItemScore());
  }

  @Test
  void testGetWeight() {
    assertEquals(5, item.getWeight());
  }

  @Test
  void testGetDamage() {
    assertEquals(15, item.getDamage());
  }

  @Test
  void testGetItemDesc() {
    assertEquals("A sharp blade used in battles.", item.getItemDesc());
  }

  @Test
  void testSetItemDesc() {
    item.setItemDesc("A powerful magic sword.");
    assertEquals("A powerful magic sword.", item.getItemDesc());
  }
}
