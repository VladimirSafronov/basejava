package basejava.storage.serializer;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import basejava.model.Resume;
import basejava.util.JsonParser;

public class JsonStreamSerializer implements StreamSerializer {

  @Override
  public void doWrite(Resume r, OutputStream os) throws IOException {
    try (Writer writer = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
      JsonParser.write(r, writer);
    }
  }

  @Override
  public Resume doRead(InputStream is) throws IOException {
    try (Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
      return JsonParser.read(reader, Resume.class);
    }
  }
}
