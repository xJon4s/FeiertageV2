package feiertage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonReader {

  private static String readAll(Reader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    int cp;
    while ((cp = rd.read()) != -1) {
      sb.append((char) cp);
    }
    return sb.toString();
  }

  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException, UnknownHostException {
    InputStream is = new URL(url).openStream();
    try {
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      String jsonText = readAll(rd);
      JSONObject json = new JSONObject(jsonText);
      return json;
    } finally {
      is.close();
    }
  }

  public static void main(String[] args){
    JSONObject json = null;
	try {
		json = readJsonFromUrl("https://feiertage-api.de/api/?jahr=2016&nur_land=NW");
	} catch (JSONException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
    ArrayList<Feiertag> a = FeiertageFactory.produceFromJSON(json);
    for (int i = 0; i < a.size(); i++) 
        System.out.println(a.get(i).toString()); 
    
  }
}