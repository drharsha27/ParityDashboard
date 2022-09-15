package com.example.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.demo.compareJson;
import com.example.demo.curlToJson;
import com.example.demo.dao.CurlRepo;
import com.example.demo.model.curls;

@Controller
public class HomeController {
	
	@Autowired
	CurlRepo repo;

	@RequestMapping("/")
	public String home()
	{
		return "home.jsp";
	}
	
	@ResponseBody
	@GetMapping("/compare")
	public ResponseEntity<Object> compare(curls curl) throws IOException, IllegalThreadStateException
	{
		List<JSONObject> mv = new ArrayList<JSONObject>();
		JSONObject m = new JSONObject();
		String curl1 = "curl " + curl.preProdCurl +" -H \"Accept: application/json\"";
		String curl2 = "curl " + curl.prodCurl +" -H \"Accept: application/json\"";
		/*Process p = Runtime.getRuntime().exec(curl1);
		BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line;
		String response = "";
		while ((line = reader.readLine()) != null) {
		   response = response+line;
		}
		System.out.println(response);*/
		String jsonPreProd = curlToJson.curlToJsons(curl1);
		String jsonProd = curlToJson.curlToJsons(curl2);
		//System.out.println(jsonPreProd);
		//System.out.println(jsonProd);
		String[] fourStr = compareJson.compareJsons(jsonPreProd, jsonProd);
		m.put("EntriesMissingPreProd", fourStr[0]);
		m.put("EntriesMissingProd", fourStr[1]);
		m.put("EntriesMissmatching", fourStr[2]);
		m.put("EntriesInCommon", fourStr[3]);
		//System.out.println(m);
		mv.add(m);
		
		return new ResponseEntity<Object>(mv,HttpStatus.OK);
	}


	/*public String[] compareJsons(String json1, String json2) {
		// TODO Auto-generated method stub
		Gson g = new Gson();
	    Type mapType = new TypeToken<Map<String, Object>>() {}.getType();
	    Map<String, Object> firstMap = g.fromJson(json1, mapType);
	    Map<String, Object> secondMap = g.fromJson(json2, mapType);
	    MapDifference<String, Object> difference = Maps.difference(firstMap, secondMap);
	    Map<String, Object> entryMissingJson1 = difference.entriesOnlyOnRight();
	    Map<String, Object> entryMissingJson2 = difference.entriesOnlyOnLeft();
	    Map<String, ValueDifference<Object>> entryMissmatching = difference.entriesDiffering();
	    Map<String, Object> entryInCommon = difference.entriesInCommon();
	    String emj1 = g.toJson(entryMissingJson1);
	    String emj2 = g.toJson(entryMissingJson2);
	    String emm = g.toJson(entryMissmatching);
	    String eic = g.toJson(entryInCommon);
	    return new String[] {emj1,emj2,emm,eic};
		
	}


	private String curlToJson(String str) throws IOException{
		// TODO Auto-generated method stub
		Process p = Runtime.getRuntime().exec(str);
		BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line;
		String response = "";
		while((line = reader.readLine()) != null)
		{
			response += line;
		}
		return response;
	}*/
	
}
 