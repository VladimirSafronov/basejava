package basejava.util;

import basejava.TestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import basejava.model.Resume;
import basejava.model.Section;
import basejava.model.SectionLine;

class JsonParserTest {

  @Test
  void testResume() {
    String json = JsonParser.write(TestData.R1);
    System.out.println(json);
    Resume resume = JsonParser.read(json, Resume.class);
    Assertions.assertEquals(TestData.R1, resume);
  }

  @Test
  void testSection() {
    Section section = new SectionLine("Personal1");
    String json = JsonParser.write(section, Section.class);
    System.out.println(json);
    Section section2 = JsonParser.read(json, Section.class);
    Assertions.assertEquals(section, section2);
  }
}