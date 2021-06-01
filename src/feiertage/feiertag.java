package feiertage;

import java.util.ArrayList;

public class feiertag implements Comparable<feiertag>{
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


	@Override
	public int compareTo(feiertag o) {
		int ret = compareDatum(this.datum, o.getDatum());
		return ret;
	}
	
	public int compareDatum(String d1, String d2) {
		int ret = 0;
		if(d1.equals(d2)) {
			ret = 0;
		}else if(!d1.substring(0, 4).equals(d2.substring(0, 4))){
			if(Integer.parseInt(d1.substring(0, 4))>Integer.parseInt(d2.substring(0, 4))) {
				ret = 1;
			}else {
				ret = -1;
			}
		} else if(!d1.substring(5, 7).equals(d2.substring(5, 7))){
			if(Integer.parseInt(d1.substring(5, 7))>Integer.parseInt(d2.substring(5, 7))) {
				ret = 1;
			}else {
				ret = -1;
			}
		} else {
			if(Integer.parseInt(d1.substring(8, 10))>Integer.parseInt(d2.substring(8, 10))) {
				ret = 1;
			}else {
				ret = -1;
			}
		}
		
		
		
		
		
		
		
		return ret;
	}

}
