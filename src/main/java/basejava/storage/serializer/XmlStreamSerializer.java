package basejava.storage.serializer;

import basejava.model.Link;
import basejava.model.Organization;
import basejava.model.Resume;
import basejava.model.SectionLine;
import basejava.model.SectionList;
import basejava.model.SectionOrganization;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import basejava.model.*;
import basejava.util.XmlParser;

public class XmlStreamSerializer implements StreamSerializer {

  private final XmlParser xmlParser;

  public XmlStreamSerializer() {
    xmlParser = new XmlParser(Resume.class, Organization.class, Link.class,
        SectionOrganization.class,
        SectionList.class, SectionLine.class, Organization.Position.class);
  }


  @Override
  public void doWrite(Resume r, OutputStream os) throws IOException {
    try (Writer writer = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
      xmlParser.marshall(r, writer);
    }
  }

  @Override
  public Resume doRead(InputStream is) throws IOException {
    try (InputStreamReader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
      return xmlParser.unmarshall(reader);
    }
  }
}
