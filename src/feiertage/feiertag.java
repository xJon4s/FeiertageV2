package feiertage;

public class feiertag {
	private String datum = null;
	private String name = null;
	private String beschreibung = null;
	
	public feiertag(String datum, String name, String beschreibung) {
		this.datum = datum;
		this.name = name;
		this.beschreibung = beschreibung;
	}
	
	public String toString() {
		String ret = "";
		ret = "datum: " + datum + " Name: " + name;
		return ret;
	}

}
