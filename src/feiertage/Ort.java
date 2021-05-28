package feiertage;

import java.util.ArrayList;

public class Ort {
	private String beschreibung = null;
	private ArrayList<ArrayList<feiertag>> jahr20 = null;

	public ArrayList<feiertag> getFeiertage(int anfang, int ende) {
		ArrayList<feiertag> ret = new ArrayList<feiertag>();
		for (int i = (anfang - 2020); i <= (ende - 2020); i++) {
			for (int j = 0; j < jahr20.get(i).size(); j++) {
				ret.add(jahr20.get(i).get(j));
			}
		}
		return ret;
	}

	public ArrayList<feiertag> getFeiertageInJahr(int jahr) {
		return this.jahr20.get(jahr-2020);
	}

	public Ort(String beschreibung, ArrayList<ArrayList<feiertag>> jahr20) {
		this.beschreibung = beschreibung;
		this.jahr20 = jahr20;
	}

	public String getBeschreibung() {
		return this.beschreibung;
	}

	public ArrayList<ArrayList<feiertag>> getJahr20() {
		return this.jahr20;
	}

}
