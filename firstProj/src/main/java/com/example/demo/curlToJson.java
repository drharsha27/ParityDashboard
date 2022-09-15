package com.example.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class curlToJson {
	
	public static String curlToJsons(String str) throws IOException{
		
		Process p = Runtime.getRuntime().exec(str);
		BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line;
		String response = "";
		while((line = reader.readLine()) != null)
		{
			response += line;
		}
		return response;
	}

}
