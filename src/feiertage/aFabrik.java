package feiertage;

import java.util.ArrayList;

import org.json.JSONObject;

public class aFabrik {

	public static ArrayList<ArrayList<feiertag>> produceFromString(String s) {
		ArrayList<ArrayList<feiertag>> ret = new ArrayList<ArrayList<feiertag>>();
		
		
		
		
		
		return ret;
	}
	
	public static ArrayList<feiertag> produceFromJSON(JSONObject json){
		ArrayList<feiertag> ret = new ArrayList<feiertag>();
		for (Object key : json.keySet()) {
			String keyStr = (String)key;
			JSONObject keyvalue = json.getJSONObject(keyStr);
			if(keyvalue instanceof JSONObject) {
				ret.add(new feiertag( keyvalue.getString("datum"), keyStr, keyvalue.getString("hinweis")));
			}
		}
		return ret;
	}
		
		
		
	
	
	
	
}
