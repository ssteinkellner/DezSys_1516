package ssteinkellner;

import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

/**
 * @author SSteinkellner
 * @version 2016.03.11
 */
@Component
@Path("/")
public class UserManager {

	@Path("/register")
    @POST
    @Produces("application/json")
    public Response register(@Valid User user) {
		return Response.status(200).entity(new ResponseMessage(200,"Not implemented Yet!")).build();
    }

	@Path("/login")
    @POST
    @Produces("application/json")
    public Response login(@Valid User user) {
		return Response.status(200).entity(new ResponseMessage(200,"Not implemented Yet!")).build();
    }
}
