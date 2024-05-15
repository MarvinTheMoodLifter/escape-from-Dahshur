package edids.escape_from_dahshur;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GameLoader {
  private final S3Manager s3Manager;

  public GameLoader(S3Manager s3Manager) { this.s3Manager = s3Manager; }

  public String loadGame(String saveFileName) {
    String tempFilePath = "/tmp/" + saveFileName;
    s3Manager.downloadFile(saveFileName, tempFilePath);

    try {
      return new String(Files.readAllBytes(Paths.get(tempFilePath)));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
