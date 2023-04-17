package basejava;

import basejava.storage.SqlStorage;
import basejava.storage.Storage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class Config {

  private static Config INSTANCE;
  private static File storageDir;
  private static Storage storage;

  public static Config get() {

    System.out.println("Get config");

    if (INSTANCE == null) {

      System.out.println("Create new configuration");

      Properties properties = new Properties();
      File fileWithProperties = new File(getHomeDir(), "config/resumes.properties");

      try (InputStream is = new FileInputStream(fileWithProperties)) {

        properties.load(is);
//        storage = new SqlStorage(properties.getProperty("db.url"),

        storageDir = new File(properties.getProperty("storage.dir"));

        String dbUrl = System.getenv("DATABASE_URL");

        String hostAndDB = "jdbc:postgresql://" + dbUrl.split("@")[1];
        String dbUser = dbUrl.split("//")[1].split(":")[0];
        String dbPass = dbUrl.split("//")[1].split(":")[1].split("@")[0];

        storage = new SqlStorage(
            properties.getProperty(hostAndDB),
            properties.getProperty(dbUser),
            properties.getProperty(dbPass));

      } catch (IOException e) {

        System.out.println("Error while getting configs: " + e.getMessage());

        throw new IllegalStateException(
            "Invalid config file " + fileWithProperties.getAbsolutePath());
      }
      INSTANCE = new Config();
    }
    return INSTANCE;
  }

  private Config() {
  }

  public File getStorageDir() {
    System.out.println("Getting storage dir");
    return storageDir;
  }

  public Storage getStorage() {
    System.out.println("Getting storage");
    return storage;
  }

  private static File getHomeDir() {
    System.out.println("Getting home dir");
    String prop = System.getProperty("homeDir");
    File homeDir = new File(prop != null ? prop : ".");
    if (!homeDir.isDirectory()) {
      throw new IllegalStateException(homeDir + " isn't directory!");
    }
    return homeDir;
  }
}
