package com.fixtube;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Ignore;
import org.junit.Test;

public class FixtubeTest {
  @Test @Ignore public void fixture() throws Throwable {
    Fixtube.fixture("criar-administrador-sistema", "84571713304", "Hudson Leite");
  }

  private void assertParserParameters(String content, int size, String... values) {
    ParseParameters parser = new ParseParameters(content);
    assertThat(parser.getParameters().size(), equalTo(size));
    for(int i = 0, l = parser.getParameters().size(); i < l; i++){
      assertThat(parser.getParameters().get(i).toString(), equalTo(values[i]));
    }
  }

  @Test public void parse_params() throws Throwable {
    assertParserParameters("1", 1, "1");
    assertParserParameters("1, 2", 2, "1", "2");
    assertParserParameters("1, {2}", 2, "1", "{2}");
    assertParserParameters("1, \"2\"", 2, "1", "2");
    assertParserParameters("1,  \"2\"", 2, "1", "2");
    assertParserParameters("1,  \"2\"    ", 2, "1", "2");
    assertParserParameters("1,  \"2\"         ", 2, "1", "2");
    assertParserParameters("1, \"2, 3 ou 4\", 5", 3, "1", "2, 3 ou 4", "5");
    assertParserParameters("1, \"2, 3 ou 4\"      , 5", 3, "1", "2, 3 ou 4", "5");
    assertParserParameters("1, 2\\, 3 ou 4, 5", 3, "1", "2, 3 ou 4", "5");
    assertParserParameters("1, \\\"2\\, 3 ou 4\\\", 5", 3, "1", "\"2, 3 ou 4\"", "5");
  }
}
