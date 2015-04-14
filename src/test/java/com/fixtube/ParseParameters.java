package com.fixtube;

public class ParseParameters {
  private final String content;
  private List parameters = null;

  public ParseParameters(String content) {
    this.content = content;
  }

  public List getParameters() {
    if(parameters == null){
      parameters = new ArrayList();
      parse();
    }
    return parameters;
  }

  private void parse() {
    
  }
}
