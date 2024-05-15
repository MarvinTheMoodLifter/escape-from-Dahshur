import java.io.FileWriter;
import java.io.IOException;

public class GameSaver {
  private final S3Manager s3Manager;

  public GameSaver(S3Manager s3Manager) { this.s3Manager = s3Manager; }

  public void saveGame(String saveFileName, String gameData) {
    String tempFilePath = "/tmp/" + saveFileName;
    try (FileWriter writer = new FileWriter(tempFilePath)) {
      writer.write(gameData);
    } catch (IOException e) {
      e.printStackTrace();
    }

    s3Manager.uploadFile(saveFileName, tempFilePath);
  }
}
