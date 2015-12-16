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
	
	/**
	 * 
	 * @param suchbegriffe ein String array mit suchbegriffen. je früher ein eintrag kommt, desto weiter wird er eingekapselt.
	 * begriffe die mit "& " beginnen, werden mit und, alles andere mit oder zusammengehängt
	 * @return
	 */
	public static Eintrag[] search(String[] suchbegriffe){
		String where = "";
		boolean lastIsAnd = false;
		int sub;
		
		for(String begriff : suchbegriffe){
			sub = 0;
			if(!where.isEmpty()){
				if(begriff.startsWith("& ")){
					sub = 2;
					if(!lastIsAnd) where = " (" + where + ") ";
					where += " AND ";
					lastIsAnd = true;
				}else{
					if(lastIsAnd) where = " (" + where + ") ";
					where += " OR ";
					lastIsAnd = false;
				}
			}
			
			where += begriff.substring(sub);
		}
		
		System.out.println(where);
		
		return new Eintrag[0];
	}
}
