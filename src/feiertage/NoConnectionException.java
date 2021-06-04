package feiertage;

@SuppressWarnings("serial")
public class NoConnectionException extends Exception{

	public NoConnectionException(String beschreibung){
		super(beschreibung);
	}
}
