package mestihudson.dbunit;

import java.io.File;
import java.text.MessageFormat;
import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;

public class DB {
  public static void load(String name, Object... parameters) throws Throwable {
    fill(name, parameters);
    execute(name);
  }

  private static void fill(String name, Object... parameters) throws Throwable {
    File file = file(name);
    String content = FileUtils.readFileToString(file, Charset.defaultCharset());
    content = MessageFormat.format(content, parameters);
    FileUtils.writeStringToFile(file, content, Charset.defaultCharset());
  }

  private static void execute(String name) throws Throwable {
    System.out.println(FileUtils.readFileToString(file(name)));
  }

  private static File file(String name) throws Throwable {
    return new File(DB.class.getClassLoader().getResource(name).getPath());
  }
}
