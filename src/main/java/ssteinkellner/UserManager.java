package ssteinkellner;

import java.io.File;
import java.net.URL;
import java.util.Scanner;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Controller;

/**
 * @author SSteinkellner
 * @version 2016.03.11
 */
@Controller
@Path("/")
public class UserManager {
	UserRepository userRepo;

	public UserManager(){
		userRepo = new UserRepository();
	}
	
	@Path("/")
	@GET
    @Produces({MediaType.TEXT_HTML})
	public String index() {
		String text = "created by Sebastian Steinkellner";

		URL html = this.getClass().getResource("/index.html");
		if(html != null){
			File f = new File(html.getFile());
			try(Scanner scanner = new Scanner(f)){
				text = scanner.useDelimiter("\\A").next();
			}catch(Exception e){
				System.err.println("Error reading index.html!\n"  +e);
			}
		}
		return text;
    }

	@Path("/register")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
	public Response register(@QueryParam("email") String email, @QueryParam("username") String username, @QueryParam("password") String password) {
		if(badParam(email)  || badParam(username) || badParam(password)){
			return Response.status(400).entity(new ResponseMessage(400,"Registration Data not complete!")).build();
		}
		
		User user = new User(email, username, password);
		switch(userRepo.putUser(user)){
			case UserRepository.SAVED:
				return Response.status(200).entity(new ResponseMessage(200,"Saved User.")).build();
			case UserRepository.USER_EXISTS:
				return Response.status(400).entity(new ResponseMessage(400,"User already exists! please use another E-Mail adress.")).build();
		}
		
		return Response.status(503).entity(new ResponseMessage(503,"Internal Server Error.")).build();
    }

	@Path("/login")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response login(@QueryParam("email") String email, @QueryParam("password") String password) {
		if(badParam(email) || badParam(password)){
			return Response.status(400).entity(new ResponseMessage(400,"No valid User provided!")).build();
		}
		
		User user = userRepo.getUser(email);
		if(user != null){
			return Response.status(200).entity(new ResponseMessage(200,"Sucessfully logged in as " + user.getUsername())).build();
		}else{
			switch(userRepo.getLastState()){
				case UserRepository.NO_SUCH_USER:
					return Response.status(400).entity(new ResponseMessage(400,"Invalid Logindata!")).build();
				case UserRepository.DB_ERROR:
					return Response.status(503).entity(new ResponseMessage(400,"Database error!")).build();
			}
		}

		return Response.status(503).entity(new ResponseMessage(503,"Internal Server Error.")).build();
    }
	
	private boolean badParam(String param){
		if(param == null) return true;
		if(param.trim().isEmpty()) return true;
		
		return false;
	}
}
