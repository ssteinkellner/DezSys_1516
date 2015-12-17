package ss_ts;

import ss_ts.Eintrag;

public class Test_search {

	public static void main(String[] args) {
		Eintrag[] e = Eintrag.search(new String[]{"abc","xyz"});
		if(e.length>0) e[0].save();
		
		System.out.println("\n"+Eintrag.load("test"));
	}

}
