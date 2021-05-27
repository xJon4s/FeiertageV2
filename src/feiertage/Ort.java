package feiertage;

import java.util.ArrayList;

public class Ort {
	private String beschreibung = null;
	private ArrayList<feiertag>[] jahr20 = null;
	
	public ArrayList<feiertag> getFeiertage(int anfang, int ende){
		ArrayList<feiertag> ret = null;
		//Ausbauen
		return ret;
	}
	
	public Ort (String beschreibung) {
		this.beschreibung = beschreibung;
	}
	
	public String getBeschreibung() {
		return this.beschreibung;
	}
}
