package com.fixtube;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Ignore;
import org.junit.Test;

public class FixtubeTest {
  @Test @Ignore public void fixture() throws Throwable {
    Fixtube.fixture("criar-administrador-sistema", "84571713304", "Hudson Leite");
  }

  @Test public void parse_params() throws Throwable {
    ParseParameters parser = new ParseParameters("1");
    assertThat(parser.getParameters().size(), equalTo(1));
    assertThat(parser.getParameters().get(0).toString(), equalTo("1"));

    parser = new ParseParameters("1, 2");
    assertThat(parser.getParameters().size(), equalTo(2));
    assertThat(parser.getParameters().get(0).toString(), equalTo("1"));
    assertThat(parser.getParameters().get(1).toString(), equalTo("2"));

    parser = new ParseParameters("1, {2}");
    assertThat(parser.getParameters().size(), equalTo(2));
    assertThat(parser.getParameters().get(0).toString(), equalTo("1"));
    assertThat(parser.getParameters().get(1).toString(), equalTo("{2}"));

    parser = new ParseParameters("1, \"2\"");
    assertThat(parser.getParameters().size(), equalTo(2));
    assertThat(parser.getParameters().get(0).toString(), equalTo("1"));
    assertThat(parser.getParameters().get(1).toString(), equalTo("2"));

    parser = new ParseParameters("1, 	\"2\"");
    assertThat(parser.getParameters().size(), equalTo(2));
    assertThat(parser.getParameters().get(0).toString(), equalTo("1"));
    assertThat(parser.getParameters().get(1).toString(), equalTo("2"));

    parser = new ParseParameters("1,  \"2\"    ");
    assertThat(parser.getParameters().size(), equalTo(2));
    assertThat(parser.getParameters().get(0).toString(), equalTo("1"));
    assertThat(parser.getParameters().get(1).toString(), equalTo("2"));

    parser = new ParseParameters("1, 	\"2\"         ");
    assertThat(parser.getParameters().size(), equalTo(2));
    assertThat(parser.getParameters().get(0).toString(), equalTo("1"));
    assertThat(parser.getParameters().get(1).toString(), equalTo("2"));

    parser = new ParseParameters("1, \"2, 3 ou 4\", 5");
    assertThat(parser.getParameters().size(), equalTo(3));
    assertThat(parser.getParameters().get(0).toString(), equalTo("1"));
    assertThat(parser.getParameters().get(1).toString(), equalTo("2, 3 ou 4"));
    assertThat(parser.getParameters().get(2).toString(), equalTo("5"));

    parser = new ParseParameters("1, \"2, 3 ou 4\"      , 5");
    assertThat(parser.getParameters().size(), equalTo(3));
    assertThat(parser.getParameters().get(0).toString(), equalTo("1"));
    assertThat(parser.getParameters().get(1).toString(), equalTo("2, 3 ou 4"));
    assertThat(parser.getParameters().get(2).toString(), equalTo("5"));

    parser = new ParseParameters("1, 2\\, 3 ou 4, 5");
    assertThat(parser.getParameters().size(), equalTo(3));
    assertThat(parser.getParameters().get(0).toString(), equalTo("1"));
    assertThat(parser.getParameters().get(1).toString(), equalTo("2, 3 ou 4"));
    assertThat(parser.getParameters().get(2).toString(), equalTo("5"));

    parser = new ParseParameters("1, \\\"2\\, 3 ou 4\\\", 5");
    assertThat(parser.getParameters().size(), equalTo(3));
    assertThat(parser.getParameters().get(0).toString(), equalTo("1"));
    assertThat(parser.getParameters().get(1).toString(), equalTo("\"2, 3 ou 4\""));
    assertThat(parser.getParameters().get(2).toString(), equalTo("5"));
  }
}
