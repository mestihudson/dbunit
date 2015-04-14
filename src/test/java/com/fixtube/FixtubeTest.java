package com.fixtube;

import org.junit.Test;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class FixtubeTest {
  @Test public void fixture() throws Throwable {
    Fixtube.fixture("criar-administrador-sistema", "84571713304", "Hudson Leite");
  }

  @Test public void parse_params() throws Throwable {
    String content = "1, \"Meu nome não é johnny, mas pode me chamar assim, se você quiser\", 35";
    ParseParameters pp = new ParseParameters(content);
  }

  private static List parse(String content) {
    String pattern = "([^,]+)";
    Matcher matcher = Pattern.compile(pattern).matcher(parameters);
    List params = new ArrayList();
    while(matcher.find()){
      params.add(matcher.group(1).trim());
    }
  }
}
