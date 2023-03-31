package basejava.storage;


import basejava.TestData;
import basejava.model.ContactType;
import basejava.model.Resume;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import basejava.exception.ExistStorageException;
import basejava.exception.NotExistStorageException;
import basejava.Config;

public abstract class AbstractStorageTest {

  protected static final File STORAGE_DIR = Config.get().getStorageDir();

  protected Storage storage;

  protected AbstractStorageTest(Storage storage) {
    this.storage = storage;
  }

  @BeforeEach
  public void setUp() {
    storage.clear();
    storage.save(TestData.R1);
    storage.save(TestData.R2);
    storage.save(TestData.R3);
  }

  @Test
  public void update() {
    Resume newResume = new Resume(TestData.UUID_1, "New Name");
    TestData.R1.setContact(ContactType.EMAIL, "m1@google.com");
    TestData.R1.setContact(ContactType.SKYPE, "newSkype");
    TestData.R1.setContact(ContactType.MOBILE_PHONE, "+79877654321");
    storage.update(newResume);
    Assertions.assertEquals(newResume, storage.get(TestData.UUID_1));
  }

  @Test
  public void resumeNotExist() {
    Assertions.assertThrows(NotExistStorageException.class,
        () -> storage.get("dummy"));
  }

  @Test
  public void size() {
    assertSize(3);
  }

  @Test
  public void save() {
    storage.save(TestData.R4);
    assertSize(4);
    assertGet(TestData.R4);
  }

  @Test
  public void saveExist() {
    Assertions.assertThrows(ExistStorageException.class,
        () -> storage.save(TestData.R1));
  }

  @Test
  public void delete() {
    storage.delete(TestData.UUID_1);
    assertSize(2);
    Assertions.assertThrows(NotExistStorageException.class,
        () -> storage.delete(TestData.UUID_1));
  }

  @Test
  public void deleteNotExist() {
    Assertions.assertThrows(NotExistStorageException.class,
        () -> storage.delete("dummy"));
  }

  @Test
  public void get() {
    assertGet(TestData.R1);
    assertGet(TestData.R2);
    assertGet(TestData.R3);
  }

  @Test
  public void getNotExist() throws Exception {
    Assertions.assertThrows(NotExistStorageException.class,
        () -> storage.get("dummy"));
  }

  @Test
  public void getAllSorted() throws Exception {
    List<Resume> testStorage = storage.getAllSorted();
    Assertions.assertEquals(3, testStorage.size());
    List<Resume> sortedList = Arrays.asList(TestData.R1, TestData.R2, TestData.R3);
    Collections.sort(sortedList);
    Assertions.assertEquals(sortedList, testStorage);

  }

  @Test
  public void clear() throws Exception {
    storage.clear();
    assertSize(0);
  }

  private void assertSize(int expectedSize) {
    Assertions.assertEquals(expectedSize, storage.size());
  }

  private void assertGet(Resume r) {
    Assertions.assertEquals(r, storage.get(r.getUuid()));
  }
}