package escape_from_dahshur.main;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.FileReader;
import java.io.IOException;

class GameTest {

  @Test
  void testSaveGame() {
    Pyramid pyramid = new Pyramid();
    Main_Character hero = new Main_Character("Hero", 100, 50, 100, 0, 2);
    Game.saveGame(hero, pyramid, "testSaveGame");

    try {
      FileReader file = new FileReader("testSaveGame.json");
      file.read();
    } catch (IOException e) {
      fail("File 'testSaveGame.json' should exist.");
    }
  }

  @Test
  void testLoadGame() {
    Pyramid pyramid = new Pyramid();
    Main_Character hero = new Main_Character("Hero", 100, 50, 100, 0, 2);
    Game.saveGame(hero, pyramid, "testLoadGame");

    Main_Character loadedHero = new Main_Character("Hero", 100, 50, 100, 0, 2);
    Pyramid loadedPyramid = new Pyramid();
    Game.loadGame(loadedHero, loadedPyramid, "testLoadGame");

    assertEquals(hero.getHealth(), loadedHero.getHealth(), "Hero health should have same health.");
    assertEquals(hero.getInventory().size(), loadedHero.getInventory().size(), "Hero inventory should have same size.");
  }
}
