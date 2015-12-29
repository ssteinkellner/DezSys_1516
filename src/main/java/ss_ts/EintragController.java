package ss_ts;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ss.connection.ConnectionHandler;
import ss.connection.UserCache;

@RestController
public class EintragController {
	private ConnectionHandler conn;
	
	public EintragController(){
		UserCache uc = new UserCache();
		conn = new ConnectionHandler(uc);
		
		conn.setDatabaseType(ConnectionHandler.POSTGRES);
		conn.setHost("192.168.20.130:5432");
		conn.setDatabase("soarest");
		uc.setUser("soarest_user");
		uc.setPassword("soarest_passwort");
		
		System.out.println("[DEBUG][EintragController.Consturctor] created " + conn);
	}

    @RequestMapping(value="/eintrag",method=RequestMethod.GET,produces="text/plain")
    public Eintrag get(@RequestParam(value="titel",defaultValue="test") String titel) {
        return Eintrag.load(conn.getConnection(), titel);
    }

    @RequestMapping(value="/eintrag",method=RequestMethod.PUT,produces="text/plain")
    public String editOrCreate(
	    		@RequestParam(value="title",defaultValue="test") String titel,
	    		@RequestParam(value="tags",defaultValue="") String begriffe,
	    		@RequestParam(value="file",defaultValue="test.html") String datei
    		) {

    	Eintrag e = Eintrag.load(conn.getConnection(),titel);
    	
    	if(e==null){ // doesnt exists => create;
        	e = new Eintrag(conn.getConnection(), titel, begriffe.split(","), datei);
            if(e.save()) return "successfull created.";
    	}else{ // exists => update
            if(e.save()) return "successfull updated.";
    	}
        
        return "error when creating " + e + "!";
    }

    @RequestMapping(value="/eintrag",method=RequestMethod.DELETE,produces="text/plain")
    public String delete(){
    	return "not implemented jet!";
    }
    
    /*
    @RequestMapping("/error")
    public String error() {
        return "<a href=\"eintrag\">Eintrag</a>";
    }
    */
}
