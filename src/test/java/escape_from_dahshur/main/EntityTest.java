package escape_from_dahshur.main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class EntityTest {
  private Entity entity;
  private Pyramid pyramid;

  @BeforeEach
  void setUp() {
    pyramid = new Pyramid();
    entity = new Entity("Hero", 100, 50, 100, 1, 1); // start at (1, 1)
  }

  @Test
  void testInitialValues() {
    assertEquals("Hero", entity.getName());
    assertEquals(100, entity.getHealth());
    assertEquals(50, entity.getPower());
    assertEquals(100, entity.getMaxWeight(), 0.01);
    assertEquals(0, entity.getScore());
    assertArrayEquals(new int[]{1, 1}, entity.getCurrentPosition());
    assertTrue(entity.getItems().isEmpty());
  }

  @Test
  void testMoveNorth() {
    entity.move(pyramid, "up");
    assertArrayEquals(new int[]{1, 2}, entity.getCurrentPosition());
    assertTrue(entity.getHasMoved());

    entity.move(pyramid, "up");
    assertArrayEquals(new int[]{1, 2}, entity.getCurrentPosition());
    assertFalse(entity.getHasMoved());
  }

  @Test
  void testMoveSouth() {
    entity.move(pyramid, "down");
    assertArrayEquals(new int[]{1, 0}, entity.getCurrentPosition());
    assertTrue(entity.getHasMoved());

    entity.move(pyramid, "down");
    assertArrayEquals(new int[]{1, 0}, entity.getCurrentPosition());
    assertFalse(entity.getHasMoved());
  }

  @Test
  void testMoveEast() {
    entity.move(pyramid, "right");
    assertArrayEquals(new int[]{2, 1}, entity.getCurrentPosition());
    assertTrue(entity.getHasMoved());

    entity.move(pyramid, "right");
    assertArrayEquals(new int[]{2, 1}, entity.getCurrentPosition());
    assertFalse(entity.getHasMoved());
  }

  @Test
  void testMoveWest() {
    entity.move(pyramid, "left");
    assertArrayEquals(new int[]{0, 1}, entity.getCurrentPosition());
    assertTrue(entity.getHasMoved());

    entity.move(pyramid, "left");
    assertArrayEquals(new int[]{0, 1}, entity.getCurrentPosition());
    assertFalse(entity.getHasMoved());
  }

  @Test
  void testAddItem() {
    Item item = new Item("Artifact", 10, 2, 5, "weapon");
    entity.addItem(item);

    List<Item> items = entity.getItems();
    assertEquals(1, items.size());
    assertEquals("Artifact", items.get(0).getName());
    assertEquals(2, items.get(0).getWeight());
  }

  @Test
  void testSetAndGetScore() {
    entity.setScore(50);
    assertEquals(50, entity.getScore());
  }

  @Test
  void testSetAndGetHealth() {
    entity.setHealth(80);
    assertEquals(80, entity.getHealth());
  }

  @Test
  void testSetAndGetPower() {
    entity.setPower(60);
    assertEquals(60, entity.getPower());
  }

  @Test
  void testSetAndGetMaxWeight() {
    entity.setMaxWeight(150);
    assertEquals(150, entity.getMaxWeight(), 0.01);
  }

  @Test
  void testSetAndGetCurrentPosition() {
    entity.setCurrentPosition(2, 2);
    assertArrayEquals(new int[]{2, 2}, entity.getCurrentPosition());
  }
}
