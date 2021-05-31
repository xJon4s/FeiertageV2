package feiertage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

public class aFabrik {

	public static ArrayList<ArrayList<feiertag>> produceFromString(File[] f) {
		ArrayList<ArrayList<feiertag>> ret = new ArrayList<ArrayList<feiertag>>();
		for (int i = 0; i < f.length; i++) {
			ArrayList<feiertag> temp = new ArrayList<feiertag>();
			try {
			      Scanner myReader = new Scanner(f[i]);
			      while (myReader.hasNextLine()) {
			        String data = myReader.nextLine();
			        temp.add(new feiertag(aFabrik.toDate(data),aFabrik.toName(data)));
			      }
			      myReader.close();
			    } catch (FileNotFoundException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			    }
			ret.add(temp);
		}
		return ret;
	}
	
	private static String toDate (String s) {
		String ret = "";
		//Jahr
		ret = s.substring(8, 12) + "-";
		//Monat
		String temp = s.substring(4, 7);
		switch (temp) {
		case "Jan":
			temp = "01";
			break;
		case "Feb":
			temp = "02";
			break;
		case "Mär":
			temp = "03";
			break;
		case "Apr":
			temp = "04";
			break;
		case "Mai":
			temp = "05";
			break;
		case "Jun":
			temp = "06";
			break;
		case "Jul":
			temp = "07";
			break;
		case "Aug":
			temp = "08";
			break;
		case "Sep":
			temp = "09";
			break;
		case "Okt":
			temp = "10";
			break;
		case "Nov":
			temp = "11";
			break;
		case "Dez":
			temp = "12";
			break;

		default:
			break;
		}
		
		ret = ret + temp + "-";
		ret = ret + s.substring(0, 2);
		return ret;
	}
	
	private static String toName (String s) {
		String ret = "";
		ret = s.substring(13);
		return ret;
	}
	
	public static ArrayList<feiertag> produceFromJSON(JSONObject json){
		ArrayList<feiertag> ret = new ArrayList<feiertag>();
		for (Object key : json.keySet()) {
			String keyStr = (String)key;
			JSONObject keyvalue = json.getJSONObject(keyStr);
			if(keyvalue instanceof JSONObject) {
				ret.add(new feiertag( keyvalue.getString("datum"), keyStr));
			}
		}
		return ret;
	}
	
	public static ArrayList<ArrayList<feiertag>> fill (String beschreibung) {
		ArrayList<ArrayList<feiertag>> ret = new ArrayList<ArrayList<feiertag>>(); 
		for (int i = 2020; i < 2031; i++) {
			String s = "https://feiertage-api.de/api/?jahr=" + i + "&nur_land=" + beschreibung;
			try {
				ret.add(produceFromJSON(JsonReader.readJsonFromUrl(s)));
			} catch (JSONException | IOException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}
		
		
		
	
	
	
	
}
