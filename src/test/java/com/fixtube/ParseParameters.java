package com.fixtube;

import java.util.ArrayList;
import java.util.List;

public class ParseParameters {
  private final String content;
  private List parameters = null;

  public ParseParameters(String content) {
    this.content = content;
  }

  public List getParameters() {
    if(parameters == null){
      parameters = parse();
    }
    return parameters;
  }

  private List parse() {
    List params = new ArrayList();
    String arg = "";
    for(int i = 0, l = content.length(); i < l; i++){
    	switch(content.charAt(i)){
    	case ' ':
    	case '\t':
        if(arg.equals("")){
          continue;
        }else{
        	arg += content.charAt(i);
        }
    		break;
    	case ',':
        params.add(arg);
        arg = "";
    		break;
    	case '"':
        if(arg.length() == 0){
        	i++;
          while(true){
          	if(content.charAt(i) == '"'){
          		params.add(arg);
          		arg = "";
          		if(i == l - 1) {
          			break;
          		}
          		i++;
          		if(content.charAt(i) == ','){
          	  	break;
          	  }
          		boolean canExit = false;
          		while(content.charAt(i) == ' ' || content.charAt(i) == '\t'){
          			i++;
          			if(i == l - 1) {
          				canExit = true;
          				break;
          			}
            		if(content.charAt(i) == ','){
            			canExit = true;
            	  	break;
            	  }
          		}
          		if(canExit){
          			break;
          		}
         	  }else{
         	  	arg += content.charAt(i);
         	  	i++;
         	  }
          }
        }
    		break;
    	case '\\':
    		if(!(i == l - 1)) {
    			i++;
    		}
    		arg += content.charAt(i);
    		break;
    	default:
    		arg += content.charAt(i);
    		if(i == l - 1) {
    			params.add(arg);
    		}
    	}
    }
    return params;
  }
}
