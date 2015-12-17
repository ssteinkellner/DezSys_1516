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
		boolean success = false;
		String query;
		
		for(int i=0; i<10 && !success; i++){
			
			// set transaction serializable

			query = "SELECT * FROM eintrag WHERE title LIKE '" + title + "';";
			boolean exists = (Math.random() > 0.5);
			
			try{
				if(exists){
					query = "UPDATE eintrag SET (begriffe, file) = ('" + implode(begriffe) + "','" + file + "') WHERE title LIKE '" + title + "';";
				}else{
					query = "INSERT INTO eintrag VALUES('" + title + "','" + implode(begriffe) + "','" + file + "');";
				}
				
				success = true;
			}catch(Exception e){
				e.printStackTrace();
			}

			System.out.println("[DEBUG][Eintrag.save] " + query);
		}
		
		return success;
	}
	
	private String implode(String[] array){
		String text = "";
		
		for(String value : array){
			if(!text.isEmpty()) text += ", ";
			text += value;
		}
		
		return text;
	}
	
	public static Eintrag load(String titel){
		return new Eintrag(titel,"uvw, xyz".split(", "),titel+".html");
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
					if(!lastIsAnd) where = "( " + where + " )";
					where += " AND ";
					lastIsAnd = true;
				}else{
					if(lastIsAnd) where = "( " + where + " )";
					where += " OR ";
					lastIsAnd = false;
				}
			}
			
			where += "begriffe LIKE '%" + begriff.substring(sub) + "%'";
		}
		
		String query = "SELECT FROM eintrag WHERE " + where + ";";
		
		System.out.println("[DEBUG][Eintrag.search] " + query);
		
		return new Eintrag[]{ new Eintrag("test",suchbegriffe,"test.html")};
	}
	
	@Override
	public String toString(){
		return "Eintrag[title=" + title + ", begriffe={" + implode(begriffe) + "}, file=" + file + "]";
	}
}
