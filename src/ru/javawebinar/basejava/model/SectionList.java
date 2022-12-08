package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SectionList extends Section {

  private final List<String> content;

  public SectionList(List<String> content) {
    Objects.requireNonNull(content, "content mustn't be null");
    this.content = content;
  }

  public List<String> getContent() {
    return content;
  }

  @Override
  public String toString() {
    return "SectionList{" +
        "content=" + content +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SectionList that = (SectionList) o;
    return content.equals(that.content);
  }

  @Override
  public int hashCode() {
    return Objects.hash(content);
  }
}
