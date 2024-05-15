package edids.escape_from_dahshur;

import java.nio.file.Paths;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

public class S3Manager {
  private final S3Client s3;
  private final String bucketName;

  public S3Manager(String accessKeyId, String secretAccessKey, String region,
                   String bucketName) {
    AwsBasicCredentials awsCreds =
        AwsBasicCredentials.create(accessKeyId, secretAccessKey);
    this.s3 =
        S3Client.builder()
            .region(Region.of(region))
            .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
            .build();
    this.bucketName = bucketName;
  }

  public void uploadFile(String key, String filePath) {
    PutObjectRequest putObjectRequest =
        PutObjectRequest.builder().bucket(bucketName).key(key).build();

    s3.putObject(putObjectRequest, Paths.get(filePath));
  }

  public void downloadFile(String key, String filePath) {
    GetObjectRequest getObjectRequest =
        GetObjectRequest.builder().bucket(bucketName).key(key).build();

    s3.getObject(getObjectRequest, Paths.get(filePath));
  }
}
