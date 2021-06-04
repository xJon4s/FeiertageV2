package feiertage;

import java.util.ArrayList;

public class Ort {
	private String beschreibung = null;
	private ArrayList<ArrayList<Feiertag>> jahr20 = null;

	public ArrayList<Feiertag> getFeiertage(int anfang, int ende) {
		ArrayList<Feiertag> ret = new ArrayList<Feiertag>();
		for (int i = (anfang - 2020); i <= (ende - 2020); i++) {
			for (int j = 0; j < jahr20.get(i).size(); j++) {
				ret.add(jahr20.get(i).get(j));
			}
		}
		return ret;
	}

	public ArrayList<Feiertag> getFeiertageInJahr(int jahr) {
		return this.jahr20.get(jahr-2020);
	}

	public Ort(String beschreibung, ArrayList<ArrayList<Feiertag>> jahr20) {
		this.beschreibung = beschreibung;
		this.jahr20 = jahr20;
	}

	public String getBeschreibung() {
		return this.beschreibung;
	}

	public ArrayList<ArrayList<Feiertag>> getJahr20() {
		return this.jahr20;
	}
}
