package escape_from_dahshur.main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.FileWriter;
import java.io.IOException;

class PyramidTest {
  private Pyramid pyramid;

  @BeforeEach
  void setUp() {
      pyramid = new Pyramid();
  }

  @Test
  void testGetRoom() {
      assertNotNull(pyramid.getRoom(0, 0));
      assertNotNull(pyramid.getRoom(2, 2));
      assertNull(pyramid.getRoom(-1, 0));
      assertNull(pyramid.getRoom(0, 3));
  }

  @Test
  void testSetPyramidDesc() {
      String newDescription = "A grand pyramid.";
      pyramid.setPyramidDesc(newDescription);
      assertEquals(newDescription, pyramid.getPyramidDesc());
  }

  @Test
  void testToJsonAndFromJson() {
      String json = pyramid.toJson();
      Pyramid newPyramid = Pyramid.fromJson(json);
      assertEquals(pyramid.getPyramidDesc(), newPyramid.getPyramidDesc());
      assertEquals(pyramid.getGamemap().length, newPyramid.getGamemap().length);
  }

  @Test
  void testToJsonFileAndFromJsonFile() throws IOException {
      String filePath = "pyramid_test.json";
      try (FileWriter writer = new FileWriter(filePath)) {
          writer.write(pyramid.toJson());
      }

      Pyramid newPyramid = Pyramid.fromJsonFile(filePath);
      assertEquals(pyramid.getPyramidDesc(), newPyramid.getPyramidDesc());
      assertEquals(pyramid.getGamemap().length, newPyramid.getGamemap().length);
  }

  @Test
  void testUpdateFrom() {
      Pyramid other = new Pyramid();
      other.setPyramidDesc("Updated description");
      pyramid.updateFrom(other);
      assertEquals("Updated description", pyramid.getPyramidDesc());
  }
}
