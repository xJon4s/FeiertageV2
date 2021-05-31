package feiertage;

import java.util.ArrayList;

public class feiertag {
	private String datum = null;
	private String name = null;
	private ArrayList<String> beschreibung = new ArrayList<String>();

//	public feiertag(String datum, String name, String beschreibung) {
//		this.datum = datum;
//		this.name = name;
//		this.beschreibung = beschreibung;
//	}

	public feiertag(String datum, String name) {
		this.datum = datum;
		this.name = name;
	}

	public String toString() {
		String ret = "";
		ret = "datum: " + datum + " Name: " + name;
		return ret;
	}

	public void addOrt(String s) {
		beschreibung.add(s);
	}

	public ArrayList<String> getBeschreibung() {
		return this.beschreibung;
	}

	public String getBeschreibungAsString() {
		String ret = "";
		for (int i = 0; i < beschreibung.size(); i++) {
			ret = ret + beschreibung.get(i);
			if (i != beschreibung.size() - 1) {
				ret = ret + ",";
			}
		}
		return ret;
	}

	public String getDatum() {
		return this.datum;
	}

	public String getName() {
		return this.name;
	}

}
