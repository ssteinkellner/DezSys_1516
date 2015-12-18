package ss_ts;

import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("/eintrag")
    public Eintrag get(@RequestParam(value="titel",defaultValue="test") String titel) {
        return Eintrag.load(conn.getConnection(), titel);
    }
    
    /*
    @RequestMapping("/error")
    public String error() {
        return "<a href=\"eintrag\">Eintrag</a>";
    }
    */
}
