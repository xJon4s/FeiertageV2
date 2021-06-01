package feiertage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class aFabrik {

	public static ArrayList<ArrayList<feiertag>> produceFromString(File[] f) {
		ArrayList<ArrayList<feiertag>> ret = new ArrayList<ArrayList<feiertag>>();
		for (int i = 0; i < f.length; i++) {
			ArrayList<feiertag> temp = new ArrayList<feiertag>();
			try {
				Scanner myReader = new Scanner(f[i]);
				while (myReader.hasNextLine()) {
					String data = myReader.nextLine();
					temp.add(new feiertag(aFabrik.toDate(data), aFabrik.toName(data)));
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

	private static String toDate(String s) {
		String ret = "";
		// Jahr
		ret = s.substring(8, 12) + "-";
		// Monat
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

	private static String toName(String s) {
		String ret = "";
		ret = s.substring(13);
		return ret;
	}

	public static ArrayList<feiertag> produceFromJSON(JSONObject json) {
		ArrayList<feiertag> ret = new ArrayList<feiertag>();
		for (Object key : json.keySet()) {
			String keyStr = (String) key;
			JSONObject keyvalue = json.getJSONObject(keyStr);
			if (keyvalue instanceof JSONObject) {
				ret.add(new feiertag(keyvalue.getString("datum"), keyStr));
			}
		}
		return ret;
	}

	public static ArrayList<ArrayList<feiertag>> fill(String beschreibung, String pfad) {
		// Variablen
		ArrayList<ArrayList<feiertag>> ret = new ArrayList<ArrayList<feiertag>>();
		ArrayList<File> otter = new ArrayList<File>();
		boolean fehler = false;

		// Sammeln der Files im Otter
		for (int i = 0; i < 11; i++) {
			int pfadjahr = 2020 + i;
			String tempfad = pfad + "\\" + beschreibung + "\\" + pfadjahr + ".txt";
			File temp = new File(tempfad);
			if (temp.exists()) {
				otter.add(temp);
			} else {
				fehler = true;
				break;
			}
		}
		// Falls Files noch nicht existieren
		if (fehler) {
			for (int i = 2020; i < 2031; i++) {
				String s = "https://feiertage-api.de/api/?jahr=" + i + "&nur_land=" + beschreibung;
				try {
					try {
						File export = new File((pfad + "\\" + beschreibung + "\\" + i + ".txt"));
						if (export.createNewFile()) {
							System.out.println("File created: " + export.getName());
						} else {
							System.out.println("File already exists.");
						}

						try (PrintWriter writer = new PrintWriter(
								new File((pfad + "\\" + beschreibung + "\\" + i + ".txt")))) {

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

		// Einlesen und Einspeisen
		for (int i = 2020; i < 2031; i++) {
			String s = pfad + "\\" + beschreibung + "\\" + i + ".txt";
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
			}
		}

		return ret;
	}

	public static void refresh(String beschreibung, String pfad) {
		for (int i = 2020; i < 2031; i++) {
			String s = "https://feiertage-api.de/api/?jahr=" + i + "&nur_land=" + beschreibung;
			try {
				try {
					try (PrintWriter writer = new PrintWriter(
							new File((pfad + "\\" + beschreibung + "\\" + i + ".txt")))) {

						StringBuilder sb = new StringBuilder();
						sb.append(JsonReader.readJsonFromUrl(s)).toString();
						writer.write(sb.toString());
						System.out.println(beschreibung + ":" + i);
						System.out.println(sb.toString());

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

}
