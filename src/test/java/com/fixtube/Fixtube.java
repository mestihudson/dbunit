package com.fixtube;

import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.text.MessageFormat;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.apache.commons.io.FileUtils;

public class Fixtube {
  public static void fixture(String name, Object... parameters) throws Throwable {
    execute(load(fix(name), parameters));
  }

  private static String load(File file, Object... parameters) throws Throwable {
    return compile(FileUtils.readFileToString(file), parameters);
  }

  private static String compile(String content, Object... parameters) throws Throwable {
    List<String> lines = Arrays.asList(content.split("\\n"));
    String result = "";
    String pattern = "^\\!(([\\w][\\w\\-]*)(\\/[\\w][\\w\\-]*)*)\\:(.+)$";
    for(String line : lines){
      Matcher matcher = Pattern.compile(pattern).matcher(line);
      result += matcher.find() ? indirection(matcher.group(1), matcher.group(4)) : (MessageFormat.format(line.replaceAll("'", "''"), parameters) + "\n");
    }
    return result;
  }

  private static String indirection(String name, String parameters) throws Throwable {
    String pattern = "([^,]+)";
    Matcher matcher = Pattern.compile(pattern).matcher(parameters);
    List params = new ArrayList();
    while(matcher.find()){
      params.add(matcher.group(1).trim());
    }
    return load(fix(name), params.toArray());
  }

  private static void execute(String content) throws Throwable {
    System.err.println(content);
  }

  private static File fix(String name) throws Throwable {
    return file(name + ".fixture");
  }

  private static File file(String name) throws Throwable {
    File file;
    try{
      file = new File(Fixtube.class.getClassLoader().getResource(name).getPath());
    }catch(Throwable t) {
      throw new Exception("Arquivo [" + name + "] não localizado.");
    }
    return file;
  }
}
