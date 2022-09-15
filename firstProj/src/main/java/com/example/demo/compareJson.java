package com.example.demo;
import com.google.common.collect.MapDifference;
import com.google.common.collect.MapDifference.ValueDifference;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

public class compareJson {

	public static String[] compareJsons(String json1, String json2)
	{
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

}
