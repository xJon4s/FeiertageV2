package feiertage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

public class FeiertageFactory {

	/**
	 * Reads from FileArray and writes it into ret
	 * @param f The FileArray
	 * @return a ArrayList<ArrayList<Feiertag>> with all the Feiertage
	 */
	public static ArrayList<ArrayList<Feiertag>> produceFromString(File[] f) {
		ArrayList<ArrayList<Feiertag>> ret = new ArrayList<ArrayList<Feiertag>>();
		for (int i = 0; i < f.length; i++) {
			ArrayList<Feiertag> temp = new ArrayList<Feiertag>();
			try {
				Scanner myReader = new Scanner(f[i]);
				while (myReader.hasNextLine()) {
					String data = myReader.nextLine();
					temp.add(new Feiertag(FeiertageFactory.toDate(data), FeiertageFactory.toName(data)));
				}
				myReader.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			ret.add(temp);
		}
		return ret;
	}

	
	/**
	 * Extracts date from a string like "06. Jan 2021 Heilige Drei Könige" into a format like "26.12.2026"
	 * @param s String in long format
	 * @return date in short format
	 */
	private static String toDate(String s) {
		String ret = "";
		// Year
		ret = s.substring(8, 12) + "-";
		// Month
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

	
	/**
	 * Extracts name from a string like "06. Jan 2021 Heilige Drei Könige" into a format like "Heilige Drei Könige"
	 * @param s String in long format
	 * @return name in short format
	 */
	private static String toName(String s) {
		String ret = "";
		ret = s.substring(13);
		return ret;
	}

	/**
	 * Produces a ArrayList<Feiertag> from a json-object
	 * @param json the json-object
	 * @return a ArrayList<Feiertag>
	 */
	public static ArrayList<Feiertag> produceFromJSON(JSONObject json) {
		ArrayList<Feiertag> ret = new ArrayList<Feiertag>();
		for (Object key : json.keySet()) {
			String keyStr = (String) key;
			JSONObject keyvalue = json.getJSONObject(keyStr);
			if (keyvalue instanceof JSONObject) {
				ret.add(new Feiertag(keyvalue.getString("datum"), keyStr));
			}
		}
		return ret;
	}

	/**
	 * Reads files and fills them into a ArrayList<ArrayList<Feiertag>>
	 * If files are not there => create them
	 * @param beschreibung Description of the Bundesland
	 * @param pfad Path of the files
	 * @return
	 */
	public static ArrayList<ArrayList<Feiertag>> fill(String beschreibung, String pfad) throws NoConnectionException{
		ArrayList<ArrayList<Feiertag>> ret = new ArrayList<ArrayList<Feiertag>>();
		ArrayList<File> files = new ArrayList<File>();
		boolean error = false;
		File newdir = new File(pfad + "\\" + beschreibung);
		newdir.mkdirs();

		// Check if all files are there and collect them into files(ArrayList<File>)
		for (int i = 0; i < 11; i++) {
			int pfadjahr = 2020 + i;
			String tempfad = pfad + "\\" + beschreibung + "\\" + pfadjahr + ".json";
			File temp = new File(tempfad);
			if (temp.exists()) {
				files.add(temp);
			} else {
				error = true;
				break;
			}
		}
		
		// If not all files are there
		if (error) {
			//Checks Internet connection
			try {
		         URL url = new URL("http://www.google.com");
		         URLConnection connection = url.openConnection();
		         connection.connect();
		         System.out.println("Internet is connected");
		      } catch (MalformedURLException e) {
		         throw new NoConnectionException("No connection");
		      } catch (IOException e) {
		    	  throw new NoConnectionException("No connection");
		      }
			//Iterates over years
			for (int i = 2020; i < 2031; i++) {
				String s = "https://feiertage-api.de/api/?jahr=" + i + "&nur_land=" + beschreibung;
				try {
					try {
						//tries to create a new file
						File export = new File((pfad + "\\" + beschreibung + "\\" + i + ".json"));
						if (export.createNewFile()) {
							System.out.println("File created: " + export.getName());
						} else {
							System.out.println("File already exists.");
						}

						//writes into new file
						try (PrintWriter writer = new PrintWriter(
								new File((pfad + "\\" + beschreibung + "\\" + i + ".json")))) {
							StringBuilder sb = new StringBuilder();
							sb.append(JsonReader.readJsonFromUrl(s)).toString();
							writer.write(sb.toString());

						} catch (FileNotFoundException e3) {
							e3.printStackTrace();
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}

		// Reading and parsing
		for (int i = 2020; i < 2031; i++) {
			String s = pfad + "\\" + beschreibung + "\\" + i + ".json";
			File file = new File(s);
			FileInputStream fis;
			try {
				fis = new FileInputStream(file);
				byte[] data = new byte[(int) file.length()];
				fis.read(data);
				fis.close();
				String s1 = new String(data, "UTF-8");
				ret.add(produceFromJSON(new JSONObject(s1)));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}catch (JSONException e2) {
				System.out.println("?");
			}
		}
		return ret;
	}

	
	/**
	 * Refreshes the data with the API
	 * @param beschreibung Description of the Bundesland
	 * @param pfad Path of file
	 */
	public static void refresh(String beschreibung, String pfad)throws NoConnectionException{
		//InternetCheck
		try {
	         URL url = new URL("http://www.google.com");
	         URLConnection connection = url.openConnection();
	         connection.connect();
	         System.out.println("Internet is connected");
	      } catch (MalformedURLException e) {
	         throw new NoConnectionException("No connection");
	      } catch (IOException e) {
	    	  throw new NoConnectionException("No connection");
	      }
		File newdir = new File(pfad + "\\" + beschreibung);
		newdir.mkdirs();
		
		for (int i = 2020; i < 2031; i++) {
			String s = "https://feiertage-api.de/api/?jahr=" + i + "&nur_land=" + beschreibung;
			try {
				try {
					try (PrintWriter writer = new PrintWriter(
							new File((pfad + "\\" + beschreibung + "\\" + i + ".json")))) {

						StringBuilder sb = new StringBuilder();
						sb.append(JsonReader.readJsonFromUrl(s)).toString();
						writer.write(sb.toString());

					} catch (FileNotFoundException e3) {
						e3.printStackTrace();
					}

				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} catch (JSONException e) {
				e.printStackTrace();
				System.out.println("error occured");
				break;
			}
		}

	}

}
