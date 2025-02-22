package ru.javawebinar.basejava;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainFile {

  public static void main(String[] args) {
    String filePath = ".gitignore";
    File file = new File(filePath);
    try {
      System.out.println(file.getCanonicalPath());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    File dir = new File("/Users/vladimirsafronov/Desktop/java/projects/basejava/src");
    String[] listOfFiles = dir.list();
    if (listOfFiles != null) {
      for (String name : listOfFiles) {
        System.out.println(name);
      }
    }

    try (FileInputStream fis = new FileInputStream(filePath)) {
      System.out.println(fis.read());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    showAllFiles(dir, "");
  }

  private static void showAllFiles(File dir, String offset) {
    File[] files = dir.listFiles();
    if (files != null) {
      for (File file : files) {
        if (file.isFile()) {
          System.out.println(offset + "file: " + file.getName());
        } else if (file.isDirectory()) {
          System.out.println(offset + "directory: " + file.getName());
          showAllFiles(file, offset + "  ");
        }
      }
    }
  }
}
