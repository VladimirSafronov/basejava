package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;
import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

  @Override
  protected Integer getSearchKey(String uuid) {
    Resume searchKey = new Resume(uuid);
    return Arrays.binarySearch(storage, 0, storageSize, searchKey);
  }

  @Override
  protected void insertElement(Resume r, int index) {
    int insertIndex = -index - 1;
    System.arraycopy(storage, insertIndex, storage, insertIndex + 1, storageSize - insertIndex);
    storage[insertIndex] = r;
  }

  @Override
  protected void fillDeletedElement(int index) {
    int numMoved = storageSize - 1 - index;
    if (numMoved >= 0) {
      System.arraycopy(storage, index + 1, storage, index, numMoved);
    }
  }
}
