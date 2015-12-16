package ss_ts;

public class Eintrag {
	
	private String title;
	private String[] begriffe;
	private String file;
	
	public Eintrag(String titel, String[] suchbegriffe, String dateiname){
		title = titel;
		begriffe = suchbegriffe;
		file = dateiname;
	}

	public String getTitle() {
		return title;
	}

	public String[] getBegriffe() {
		return begriffe;
	}

	public String getFile() {
		return file;
	}
	
	public boolean save(){
		return false;
	}
	
	public static Eintrag load(String titel){
		return new Eintrag(titel,new String[0],"");
	}
}
