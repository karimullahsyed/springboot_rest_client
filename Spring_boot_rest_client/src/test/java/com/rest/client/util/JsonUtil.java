package com.rest.client.util;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonUtil {

	public static void jsonFileWrite(String responseBody) throws Exception {
		try {
			FileWriter file = new FileWriter("src/main/resources/access_token.json");
			file.write(responseBody);
			file.flush();
			file.close();
			System.out.println("JSon file created");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static String jsonFileRead(String responseBody) throws Exception {
		JSONParser jsonParser = new JSONParser();
		 String value = null;
	     try {
  	    	 Object object = jsonParser.parse(new FileReader("src/main/resources/access_token.json"));
  	    	JSONObject jsonObject =  (JSONObject) object;
  	    	 value = (String) jsonObject.get(responseBody);
             System.out.println(value);   
	      } catch (IOException e) {
	    	  e.printStackTrace();
	      }
	     return value;
	}
	
	
}
